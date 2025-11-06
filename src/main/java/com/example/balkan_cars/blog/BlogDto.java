package com.example.balkan_cars.blog;

import java.time.Instant;

public record BlogDto(
         String title,
         String link,
         Instant publishedDate,
         String summary
) {
}
