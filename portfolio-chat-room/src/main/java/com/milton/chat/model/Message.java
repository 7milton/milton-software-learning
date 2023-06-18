package com.milton.chat.model;

public record Message(Author author, String content) {
    public record Author(String name) {}
}
