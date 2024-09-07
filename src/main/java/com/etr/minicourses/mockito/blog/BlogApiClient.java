package com.etr.minicourses.mockito.blog;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BlogApiClient {
    private static final String BLOG_API_URL = "https://jsonplaceholder.typicode.com";

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public BlogArticleDto fetchArticle(Long articleId) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BLOG_API_URL + "/posts/" + articleId))
            .GET()
            .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), BlogArticleDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
