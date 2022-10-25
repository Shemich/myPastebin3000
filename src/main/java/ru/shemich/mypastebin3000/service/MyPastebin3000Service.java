package ru.shemich.mypastebin3000.service;

import ru.shemich.mypastebin3000.api.request.MyPastebin3000Request;
import ru.shemich.mypastebin3000.api.response.MyPastebin3000Response;
import ru.shemich.mypastebin3000.api.response.MyPastebin3000UrlResponse;

import java.util.List;

public interface MyPastebin3000Service {
    MyPastebin3000Response getByHash(String hash);
    MyPastebin3000UrlResponse create(MyPastebin3000Request request);
}
