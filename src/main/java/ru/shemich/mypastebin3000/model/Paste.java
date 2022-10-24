package ru.shemich.mypastebin3000.model;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class Paste {
    int id;
    String data;
    String hash;
    LocalDateTime lifetime;
    boolean isPublic;
}
