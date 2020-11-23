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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

/**
 *  Testing of News
 * @author Christopher Rojas-Garri
 */
public class TestNews {

    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(TestNews.class);

    /**
     * Test if the constructor is working.
     */

    @Test
    public void testNewsContructor(){
        log.debug("Testing ..");
        final Long id = 0L;

        final String title = "Title";

        final String source = "Source";

        final  String author = "Author";

        final String url = "url";

        final String urlImage = "urlImage";

        final String description = "Descriptions";

        final String content = "Contents";

        final ZonedDateTime publishedAt = ZonedDateTime.now(ZoneId.of("-3"));

        // Instance the News
        News news = new News( title, source, author, url, urlImage, description, content, publishedAt );

        // Testing
        Assertions.assertEquals(id, news.getId());

        log.debug("Done.");
    }


}
