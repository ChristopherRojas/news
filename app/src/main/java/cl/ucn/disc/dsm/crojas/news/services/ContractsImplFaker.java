/*
 * Copyright (c) 2020. Christopher Rojas-Garri, christopher.rojas@alumnos.ucn.cl
 * Copyright (c) 2021. Camilo Barrera-Arancibia,camilo.barrera@alumnos.ucn.cl
 * Copyright (c) 2021. Marcelo Lam-Biagguini,marcelo.lam@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.crojas.news.services;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.github.javafaker.Faker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;
import java.util.List;

import cl.ucn.disc.dsm.crojas.news.model.News;
import cl.ucn.disc.dsm.crojas.news.utils.Validation;
import retrofit2.Call;

/**
 * The faker implementation of {@link Contracts}.
 *
 * @author chritopher Rojas-Garri.
 */
public class ContractsImplFaker implements Contracts {

    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(ContractsImplFaker.class);

    /**
     * The List of News.
     */
    private final List<News> theNews = new ArrayList<>();

    /**
     * The Constructor: Generate 5 {@link News}.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ContractsImplFaker() {

        // The faker to use
        final Faker faker = Faker.instance();

        for (int i = 0; i < 5; i++) {
            //Integer.toUnsignedLong(i),
            this.theNews.add(new News(
                    faker.book().title(),
                    faker.name().username(),
                    faker.name().fullName(),
                    faker.internet().url(),
                    faker.internet().avatar(),
                    faker.harryPotter().quote(),
                    faker.lorem().paragraph(3),
                    ZonedDateTime.now(ZoneId.of("-3"))
            ));

        }

    }

    /**
     * Get the list of News.
     *
     * @param size size of the list.
     * @return the List of News.
     */
    // TODO : Default implementation
    @Override
    public List<News> retrieveNews(final Integer size) {

        // Returns data compare size
        if (size > theNews.size()) {
            return this.theNews;
        }

        // The last "size" elements.
        return this.theNews.subList(0, theNews.size());
    }

    /**
     * Save one News into the System.
     *
     * @param news to save.
     */
    @Override
    public void saveNews(final News news) {

        //Nullity
        Validation.notNull(news, "news");

        //Check duplicates
        for (News n : this.theNews) {
            if (n.getId().equals(news.getId())) {
                throw new IllegalArgumentException("Can't allow duplicate news !");
            }
        }

        //Add news
        this.theNews.add(news);
    }


}
