package com.example.balkan_cars.blog;

import java.time.Instant;
import java.util.UUID;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public record BlogDto(
        UUID id,
        String title,
        String link,
        Instant publishedDate,
        String imgUrl,
        String summary
) {
    public BlogDto(String title, String link, Instant publishedDate, String summaryHtml) {
        this(UUID.randomUUID(), title, link, publishedDate, null, null);
        // Call the processing method to set imgUrl and summary
        var processed = processSummary(summaryHtml);
        // Using reflection or setting via compact constructor isn't straightforward;
        // instead, you'll probably want a static factory method that does this
        // that returns a new BlogDto.
    }

    // Static factory method that processes summary HTML
    public static BlogDto from(String title, String link, Instant publishedDate, String summaryHtml) {
        var processed = processSummary(summaryHtml);
        return new BlogDto(
                UUID.randomUUID(), // Generate or pass id as needed
                title,
                link,
                publishedDate,
                processed.imgUrl(),
                processed.summary()
        );
    }

    // Record to hold processed data temporarily
    private record Processed(String imgUrl, String summary) {}

    private static Processed processSummary(String summaryHtml) {
        String imgUrl = "";
        String textSummary = summaryHtml;

        try {
            Document doc = Jsoup.parse(summaryHtml);
            Elements imgs = doc.select("img");
            if (!imgs.isEmpty()) {
                Element img = imgs.first();
                imgUrl = img.attr("src");
                img.remove(); // Remove the img tag
            }
            textSummary = doc.text();
        } catch (Exception e) {
            // handle error if needed
        }

        return new Processed(imgUrl, textSummary);
    }
}