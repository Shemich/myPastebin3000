package ru.shemich.mypastebin3000.exception;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String s) {
        super(s);
    }
}
