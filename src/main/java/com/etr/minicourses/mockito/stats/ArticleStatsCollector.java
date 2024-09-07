package com.etr.minicourses.mockito.stats;

import java.time.Instant;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etr.minicourses.mockito.analytics.Analytics;
import com.etr.minicourses.mockito.analytics.AnalyticsDatabase;
import com.etr.minicourses.mockito.blog.BlogApiClient;
import com.etr.minicourses.mockito.blog.BlogArticleDto;
import com.etr.minicourses.mockito.linguistics.BagOfWords;

@RestController
@RequestMapping("api/articles")
public class ArticleStatsCollector {

    private final BlogApiClient blogApi;
    private final BagOfWords bagOfWords;
    private final AnalyticsDatabase database;

    @GetMapping("/{articleId}/stats")
    public ArticleStatsDto collectArticleStats(@PathVariable Long articleId) {
        BlogArticleDto article = blogApi.fetchArticle(articleId);
        Map<String, Long> words = analyzeArticle(article);
        return new ArticleStatsDto(articleId, article.title(), words);
    }

    private Map<String, Long> analyzeArticle(BlogArticleDto article) {
        Map<String, Long> words = bagOfWords.analyze(article.title(), article.body());

        String topWord = words.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("The article cannot have NO title and content! articleId=" + article.id()));

        database.save(new Analytics(Instant.now(), article.id(), article.userId(), topWord));
        return words;
    }

    public ArticleStatsCollector(BlogApiClient blogApi, BagOfWords bagOfWords, AnalyticsDatabase database) {
        this.blogApi = blogApi;
        this.bagOfWords = bagOfWords;
        this.database = database;
    }
}
