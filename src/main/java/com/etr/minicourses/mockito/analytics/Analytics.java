package com.etr.minicourses.mockito.analytics;

import java.time.Instant;

public record Analytics(Instant timestamp, Long articleId, Long authorId, String topWord) {

}
