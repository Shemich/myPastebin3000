package ru.shemich.mypastebin3000.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.shemich.mypastebin3000.api.request.MyPastebin3000Request;
import ru.shemich.mypastebin3000.api.response.MyPastebin3000Response;
import ru.shemich.mypastebin3000.api.response.MyPastebin3000UrlResponse;
import ru.shemich.mypastebin3000.service.MyPastebin3000Service;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class MyPastebin3000Controller {

    private final MyPastebin3000Service myPastebin3000Service;

    @GetMapping("/")
    public Collection<MyPastebin3000Response> getPublicPasteList() {
        return myPastebin3000Service.getFirstPublicPaste();
    }

    @GetMapping("/{hash}")
    public MyPastebin3000Response getByHash(@PathVariable String hash) {
        return myPastebin3000Service.getByHash(hash);
    }

    @PostMapping("/")
    public MyPastebin3000UrlResponse add(@RequestBody MyPastebin3000Request request) {
        return myPastebin3000Service.create(request);
    }
}
