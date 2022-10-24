package ru.shemich.mypastebin3000.repository.impl;

import org.springframework.stereotype.Repository;
import ru.shemich.mypastebin3000.exception.ModelNotFoundException;
import ru.shemich.mypastebin3000.model.Paste;
import ru.shemich.mypastebin3000.repository.MyPastebin3000Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class MyPastebin3000RepositoryMap implements MyPastebin3000Repository {

    private final Map<String, Paste> vault = new ConcurrentHashMap<>();

    @Override
    public Paste getByHash(String hash) {
        Paste paste = vault.get(hash);

        if (paste == null) {
            throw new ModelNotFoundException("Paste not found with hash= " + hash);
        }
        return paste;
    }

    @Override
    public List<Paste> getListOfPublicAndAlive(int amount) {
        LocalDateTime now = LocalDateTime.now();

        return vault.values().stream()
                .filter(Paste::isPublic)
                .filter(paste -> paste.getLifetime().isAfter(now))
                .sorted(Comparator.comparing(Paste::getId).reversed())
                .limit(amount)
                .collect(Collectors.toList());
    }

    @Override
    public void add(Paste paste) {
        vault.put(paste.getHash(), paste);
    }
}
