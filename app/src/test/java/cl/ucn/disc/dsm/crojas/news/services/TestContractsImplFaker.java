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

import com.github.javafaker.Faker;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;
import java.util.List;

import cl.ucn.disc.dsm.crojas.news.model.News;

/**
 * Testing of ContractsImpl.
 *
 * @author Christopher Rojas-Garri.
 */

public class TestContractsImplFaker {


    private static final Logger log = LoggerFactory.getLogger(TestContractsImplFaker.class);

    /**
     * The Test of Retrieve News.
     */
    private final List<News> theNews = new ArrayList<>();

    @Test
    public void testRetrieveNews() {
        log.debug("Testing ..");

        //The Implementation
        Contracts contracts = new ContractsImplFaker();
        // Call the method
        List<News> news = contracts.retrieveNews(5);
        // .. the list can't be null ..
        Assertions.assertNotNull(news, "List was null :(");
        // .. the list can't be empty ..
        Assertions.assertFalse(news.isEmpty(), "Empty list? ಠ╭╮ಠ");

        // .. the size(list) == 5 ..
        Assertions.assertEquals(5, news.size(), "List size != 5 ಠ╭╮ಠ");

        // debug to log
        for (News n : news) {
            log.debug("News: {}", ToStringBuilder.reflectionToString(n, ToStringStyle.MULTI_LINE_STYLE));
        }

        // size = 0
        Assertions.assertEquals(0, contracts.retrieveNews(0).size(), "List != 0");

        // size = 3
        Assertions.assertEquals(3, contracts.retrieveNews(3).size(), "List != 3");

        // size = 10
        Assertions.assertTrue(contracts.retrieveNews(10).size() <= 10, "List != 10");

        log.debug("Done.");
    }

    /**
     * The Test of Save News.
     */
    @Test
    public void testSaveNews() {
         int tam =5;
        log.debug("Testing the News..");
          Contracts contracts = new ContractsImplFaker();
        // the List of noticia
          List<News> news = contracts.retrieveNews(tam);
        // Add the system create the News
          Faker faker = Faker.instance();
            Long id = Integer.toUnsignedLong(tam);
            String title = faker.book().title();
            String source = faker.name().username();
            String author = faker.name().fullName();
            String url = faker.internet().url();
            String urlImage = faker.internet().avatar();
            String description = faker.lorem().sentence();
            String content = faker.lorem().paragraph(3);
            ZonedDateTime publishedAt = org.threeten.bp.ZonedDateTime.now(ZoneId.of("-3"));
            //Add The New
            News newNew = new News( title, source, author, url, urlImage, description, content, publishedAt);
            //save the New in the contracts
            contracts.saveNews(newNew);
          //Test get of list and check size +1
          Assertions.assertEquals(6, contracts.retrieveNews(tam + 1).size(), "Error the size");
          //The New is not null
          Assertions.assertNotNull(newNew , "The News is null");
        System.out.println("Name: " + faker.name().title());
        log.debug("Done..");
    }

    /**
     * Show the faker.
     */
    @Test
    public void testFaker() {
        //Build the faker
        Faker faker = Faker.instance();
        for (int i = 0; i < 5; i++) {
            log.debug("Name:{} ", faker.name().fullName());
            //FIXME: remover
            System.out.println("Name: " + faker.name().fullName());
        }

    }
}
