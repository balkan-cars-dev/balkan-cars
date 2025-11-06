package com.example.balkan_cars.blog;

import java.time.Instant;
import java.util.UUID;

public record BlogDto(
         UUID id,
         String title,
         String link,
         Instant publishedDate,
         String summary
) {
    public BlogDto(String title, String link, Instant publishedDate, String summary) {
        this(UUID.randomUUID(), title, link, publishedDate, summary);
    }
}
