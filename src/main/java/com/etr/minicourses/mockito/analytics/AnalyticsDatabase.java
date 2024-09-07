package com.etr.minicourses.mockito.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AnalyticsDatabase {
    private static final String CSV_FILE_PATH = "src/main/resources/analytics.csv";
    private static final Logger LOG = LoggerFactory.getLogger(AnalyticsDatabase.class);

    public void save(Analytics data) {
        try (
            FileWriter fileWriter = new FileWriter(CSV_FILE_PATH, true);
            PrintWriter printWriter = new PrintWriter(fileWriter)
        ) {
            printWriter.printf("%s,%s,%s,%s%n", data.timestamp(), data.articleId(), data.authorId(), data.topWord());
            LOG.info("Analytics data saved successfully! articleId={}", data.articleId());
        } catch (IOException e) {
            LOG.error("Couldn't save Analytics data for articleId={}", data.articleId(), e);
            throw new RuntimeException(e);
        }
    }

}
