package ru.shemich.mypastebin3000.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shemich.mypastebin3000.model.Paste;

@Repository
public interface MyPastebin3000Repository extends JpaRepository<Paste, Long> {
    Paste findByHash(String hash);
}
