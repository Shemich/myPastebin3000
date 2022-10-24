package ru.shemich.mypastebin3000.repository;

import ru.shemich.mypastebin3000.model.Paste;

import java.util.List;

public interface MyPastebin3000Repository {
    Paste getByHash(String hash);
    List<Paste> getListOfPublicAndAlive(int amount);
    void add(Paste paste);
}
