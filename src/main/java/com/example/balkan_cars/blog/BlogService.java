package com.example.balkan_cars.blog;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {

    public List<BlogDto> fetchCarFeed() throws Exception {
        URL feedUrl = new URL("https://feeds.highgearmedia.com/?sites=TheCarConnection&type=reviews");
        SyndFeed feed = new SyndFeedInput().build(new XmlReader(feedUrl));

        List<BlogDto> blogDtos = feed.getEntries().stream()
                .map(entry -> {
                    String title = entry.getTitle();
                    String link = entry.getLink();
                    Instant publishedDate = entry.getPublishedDate() != null
                            ? entry.getPublishedDate().toInstant()
                            : null;
                    String descriptionHtml = entry.getDescription() != null
                            ? entry.getDescription().getValue()
                            : "";
                    // Create BlogDto using the static from() method
                    return BlogDto.from(title, link, publishedDate, descriptionHtml);
                })
                .collect(Collectors.toList());

        return blogDtos;
    }
}