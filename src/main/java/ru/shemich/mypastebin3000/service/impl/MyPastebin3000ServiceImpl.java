package ru.shemich.mypastebin3000.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.shemich.mypastebin3000.api.request.MyPastebin3000Request;
import ru.shemich.mypastebin3000.model.Paste;
import ru.shemich.mypastebin3000.repository.MyPastebin3000Repository;
import ru.shemich.mypastebin3000.security.Blake3;
import ru.shemich.mypastebin3000.service.MyPastebin3000Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@Setter
@ConfigurationProperties(prefix = "app")
public class MyPastebin3000ServiceImpl implements MyPastebin3000Service {

    private String host;
    private String secret;
    private final MyPastebin3000Repository repository;

    @Override
    public Paste getByHash(String hash) {
        return repository.findByHash(hash);
    }

    @Override
    public String create(MyPastebin3000Request request, Paste paste) {
        paste.setText(request.getText());
        paste.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        repository.save(paste);

        //TODO: подумать как уменьшить обращения к бд
        Blake3 hasher = Blake3.newInstance();
        String hash = secret + paste.getLifetime() + paste.getId();
        hasher.update(hash.getBytes());
        String hexhash = hasher.hexdigest();
        paste.setHash(hexhash);
        repository.save(paste);
        return host + "/" + hexhash;
    }
}
