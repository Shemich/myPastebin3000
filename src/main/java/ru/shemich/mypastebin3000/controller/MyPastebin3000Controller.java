package ru.shemich.mypastebin3000.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shemich.mypastebin3000.api.request.MyPastebin3000Request;
import ru.shemich.mypastebin3000.model.Paste;
import ru.shemich.mypastebin3000.service.MyPastebin3000Service;

@RestController
@RequiredArgsConstructor
public class MyPastebin3000Controller {

    private final MyPastebin3000Service myPastebin3000Service;

    @GetMapping("/{hash}")
    public ResponseEntity<String> getByHash(@PathVariable String hash) {
        Paste paste = myPastebin3000Service.getByHash(hash);
        if (paste == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(paste.getText(), HttpStatus.OK);
        }
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> add(@RequestBody MyPastebin3000Request request) {
        Paste paste = new Paste();
        String url = myPastebin3000Service.create(request, paste);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", paste.getHash());
        return new ResponseEntity<>(url, httpHeaders, HttpStatus.CREATED);
    }
}
