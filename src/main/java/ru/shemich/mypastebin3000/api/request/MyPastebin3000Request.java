package ru.shemich.mypastebin3000.api.request;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;
@Data
@FieldDefaults(level = PRIVATE)
public class MyPastebin3000Request {
    String data;
    long expirationTimeSeconds;
    PublicStatus publicStatus;
}
