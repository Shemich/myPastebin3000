package ru.shemich.mypastebin3000.api.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyPastebin3000Request {
    String text;
    long expirationTimeSeconds;
}
