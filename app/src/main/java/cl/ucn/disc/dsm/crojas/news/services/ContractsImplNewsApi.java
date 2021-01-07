/*
 * Copyright (c) 2020. Christopher Rojas-Garri, christopher.rojas@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.crojas.news.services;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.kwabenaberko.newsapilib.models.Article;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import cl.ucn.disc.dsm.crojas.news.model.News;
import cl.ucn.disc.dsm.crojas.news.utils.Validation;

/**
 * The NewsApi Implementation of contracts.
 *
 * @author Christopher Rojas-Garri
 */
public final class ContractsImplNewsApi implements Contracts {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(ContractsImplNewsApi.class);

    /**
     * The connection to NewsApi;
     */
    private final NewsApiService newsApiService;

    /**
     * THe constructor.
     *
     * @param apiKey
     */
    public ContractsImplNewsApi(String apiKey) {
        Validation.notNull(apiKey, "ApiKey !!");
        this.newsApiService = new NewsApiService(apiKey);
    }

    /**
     * The Assembler /Transformer pattern!
     *
     * @param article used to source.
     * @return
     */
    private static News toNews(final Article article) {
        Validation.notNull(article, "Article null! ?");

        //Warning message?
        boolean needFix = false;

        //Fix the author null
        if (article.getAuthor() == null || article.getAuthor().length() == 0) {
            article.setAuthor("No Author");
            needFix = true;
        }

        //Fix more restrictions
        if (article.getDescription() == null || article.getDescription().length() == 0) {
            article.setDescription("No Descriptions");
            needFix = true;
        }

        //yes, warning message..
        if (needFix) {
            log.warn("Article with invalid restriccions: {}.", ToStringBuilder.reflectionToString(article, ToStringStyle.MULTI_LINE_STYLE));
        }

        //The date
        ZonedDateTime publishedAt = ZonedDateTime.parse(article.getPublishedAt()).withZoneSameInstant(ZoneId.of("-3"));

        //the News
        return new News(
                article.getTitle(),
                article.getSource().getName(),
                article.getAuthor(),
                article.getUrl(),
                article.getUrlToImage(),
                article.getDescription(),
                article.getDescription(),//FIXME: falta content
                publishedAt
        );
    }

    /**
     * Get the list of News.
     *
     * @param size size of the list.
     * @return the List of News.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<News> retrieveNews(Integer size) {
        try {
            List<Article> articles = newsApiService.getTopHeadLines("technology", size);

            //The list of Articles to list of News
            List<News> news = new ArrayList<>();
            for (Article article : articles) {
                //log.debug("Article: {}.",ToStringBuilder.reflectionToString(article,ToStringStyle.MULTI_LINE_STYLE));
                news.add(toNews(article));
            }

            // Filter and sort the News
            // Remote the duplicates (by id)
            // Sort the stream by publishedAt
            // return the stream to list
            return news.stream().filter(distintById(News::getId))
                    .sorted((k1, k2) -> k2.getPublishedAt().compareTo(k1.getPublishedAt()))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            log.error("Error", ex);
            return null;
        }
    }

    /**
     * Filter the stream.
     *
     * @param idExtractor
     * @param <T>         news to filter
     * @return true if the news alrady exists.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private static <T> Predicate<T> distintById(Function<? super T, ?> idExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(idExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * Save one News into the System.
     *
     * @param news to save.
     */
    @Override
    public void saveNews(News news) {
        throw new NotImplementedException("Can't save news in NewsAPI !");
    }
}
