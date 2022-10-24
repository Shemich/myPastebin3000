package ru.shemich.mypastebin3000.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@RequiredArgsConstructor
public class MyPastebin3000Response {
    private final String text;
    private final boolean isPublic;
}
