package ru.shemich.mypastebin3000.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.shemich.mypastebin3000.api.request.MyPastebin3000Request;
import ru.shemich.mypastebin3000.api.response.MyPastebin3000Response;
import ru.shemich.mypastebin3000.api.response.MyPastebin3000UrlResponse;
import ru.shemich.mypastebin3000.model.Paste;
import ru.shemich.mypastebin3000.repository.MyPastebin3000Repository;
import ru.shemich.mypastebin3000.service.MyPastebin3000Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.shemich.mypastebin3000.api.request.PublicStatus.PUBLIC;

@Service
@RequiredArgsConstructor
@Setter
@ConfigurationProperties(prefix = "app")
public class MyPastebin3000ServiceImpl implements MyPastebin3000Service {

    private String host;
    private int publicListSize;
    private final MyPastebin3000Repository repository;
    private AtomicInteger idGenerator = new AtomicInteger(0);

    @Override
    public MyPastebin3000Response getByHash(String hash) {
        Paste paste = repository.getByHash(hash);
        return new MyPastebin3000Response(paste.getText(), paste.isPublic());
    }

    @Override
    public List<MyPastebin3000Response> getFirstPublicPaste() {
    List<Paste> list = repository.getListOfPublicAndAlive(publicListSize);
    return list.stream()
            .map(paste -> new MyPastebin3000Response(paste.getText(), paste.isPublic()))
            .collect(Collectors.toList());
    }

    @Override
    public MyPastebin3000UrlResponse create(MyPastebin3000Request request) {
        int hash = generateId();
        Paste paste = new Paste();
        paste.setText(request.getText());
        paste.setId(hash);
        paste.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        paste.setHash(Integer.toHexString(hash));
        paste.setPublic(request.getPublicStatus() == PUBLIC);
        repository.add(paste);
        return new MyPastebin3000UrlResponse(host + "/" + paste.getHash());
    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }
}
