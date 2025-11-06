package com.example.balkan_cars.blog;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {


    public List<BlogDto> fetchCarFeed() throws Exception {
        URL feedUrl = new URL("https://feeds.highgearmedia.com/?sites=TheCarConnection&type=reviews");

        SyndFeed feed = new SyndFeedInput().build(new XmlReader(feedUrl));

        return feed.getEntries().stream().map(entry -> new BlogDto(
                entry.getTitle(),
                entry.getLink(),
                entry.getPublishedDate().toInstant(),
                entry.getDescription() != null ? entry.getDescription().getValue() : ""
        )).collect(Collectors.toList());
    }
}
