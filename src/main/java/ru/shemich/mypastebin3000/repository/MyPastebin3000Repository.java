package ru.shemich.mypastebin3000.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shemich.mypastebin3000.model.Paste;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyPastebin3000Repository extends JpaRepository<Paste, Long> {
    Optional<Paste> findByHash(String hash);
}
