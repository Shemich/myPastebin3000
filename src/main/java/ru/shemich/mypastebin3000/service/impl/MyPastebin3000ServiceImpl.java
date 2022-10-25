package ru.shemich.mypastebin3000.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.shemich.mypastebin3000.api.request.MyPastebin3000Request;
import ru.shemich.mypastebin3000.api.response.MyPastebin3000Response;
import ru.shemich.mypastebin3000.api.response.MyPastebin3000UrlResponse;
import ru.shemich.mypastebin3000.exception.EntityNotFoundException;
import ru.shemich.mypastebin3000.model.Paste;
import ru.shemich.mypastebin3000.repository.MyPastebin3000Repository;
import ru.shemich.mypastebin3000.security.Blake3;
import ru.shemich.mypastebin3000.service.MyPastebin3000Service;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import static ru.shemich.mypastebin3000.api.request.PublicStatus.PUBLIC;

@Slf4j
@Service
@RequiredArgsConstructor
@Setter
@ConfigurationProperties(prefix = "app")
public class MyPastebin3000ServiceImpl implements MyPastebin3000Service {

    private String host;
    private int public_list_size;
    private String secret;
    private final MyPastebin3000Repository repository;

    @Override
    public MyPastebin3000Response getByHash(String hash) {
        Paste paste = repository.findByHash(hash).orElse(null);
        if (paste == null) {
            log.warn("Paste not found with hash={}", hash);
            throw new EntityNotFoundException();
        }
        return new MyPastebin3000Response(paste.getText(), paste.isPublic());
    }

    @Override
    public MyPastebin3000UrlResponse create(MyPastebin3000Request request) {

        Paste paste = new Paste();
        paste.setText(request.getText());
        paste.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        paste.setPublic(request.getPublicStatus() == PUBLIC);
        repository.save(paste);

        //TODO: подумать как уменьшить обращения к бд
        Blake3 hasher = Blake3.newInstance();
        String hash = secret + paste.getLifetime() + paste.getId();
        hasher.update(hash.getBytes());
        String hexhash = hasher.hexdigest();
        paste.setHash(hexhash);
        repository.save(paste);

        return new MyPastebin3000UrlResponse(host + "/" + paste.getHash());
    }
}
