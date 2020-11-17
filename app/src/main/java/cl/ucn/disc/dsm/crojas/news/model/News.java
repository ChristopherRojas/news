/*
 * Copyright (c) 2020. Christopher Rojas-Garri, christopher.rojas@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.crojas.news.model;

import org.threeten.bp.ZonedDateTime;

/**
 * The Domain model :News.
 * @author Christopher Rojas-Garri
 */
public final class News {
    /**
     * Unique id
     */

    private Long id;
    /**
     * The Title.
     * Restrictions:not null, size > 2
     */

    private String title;

    /**
     * The Source.
     *
     */
    private String source;

    /**
     * The Author.
     *
     */
    private String author;
    /**
     * The Url.
     *
     */
    private String url;
    /**
     * The Url of Image.
     *
     */
    private String utlImage;
    /**
     * The Description.
     *
     */
    private String description;
    /**
     * The Content.
     *
     */
    private String content;
    /**
     * The Date of publish.
     *
     */
    private ZonedDateTime publishedAt;

    /**
     * The Constructor
     * @param id
     * @param title
     * @param source
     * @param author
     * @param url
     * @param utlImage
     * @param description
     * @param content
     * @param publishedAt
     */
    public News(Long id, String title, String source, String author, String url, String utlImage, String description, String content, ZonedDateTime publishedAt) {
        this.id = id;
        this.title = title;
        this.source = source;
        this.author = author;
        this.url = url;
        this.utlImage = utlImage;
        this.description = description;
        this.content = content;
        this.publishedAt = publishedAt;
    }

    /**
     * @return the id.
     */
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getUtlImage() {
        return utlImage;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public ZonedDateTime getPublishedAt() {
        return publishedAt;
    }
}
