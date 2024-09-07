package com.etr.minicourses.mockito.stats;

import java.util.Map;

public record ArticleStatsDto(Long id, String title, Map<String, Long> words) {

}
