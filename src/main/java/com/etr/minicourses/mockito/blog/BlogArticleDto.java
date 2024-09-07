package com.etr.minicourses.mockito.blog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BlogArticleDto(Long userId, Long id, String title, String body) {

}
