package ru.shemich.mypastebin3000.service;

import ru.shemich.mypastebin3000.api.request.MyPastebin3000Request;
import ru.shemich.mypastebin3000.model.Paste;

public interface MyPastebin3000Service {
    Paste getByHash(String hash);
    String create(MyPastebin3000Request request, Paste paste);
}
