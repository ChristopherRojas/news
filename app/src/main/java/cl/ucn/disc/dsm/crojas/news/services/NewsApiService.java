/*
 * Copyright (c) 2020. Christopher Rojas-Garri, christopher.rojas@alumnos.ucn.cl
 * Copyright (c) 2021. Camilo Barrera-Arancibia,camilo.barrera@alumnos.ucn.cl
 * Copyright (c) 2021. Marcelo Lam-Biagguini,marcelo.lam@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
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

import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.kwabenaberko.newsapilib.network.APIClient;
import com.kwabenaberko.newsapilib.network.APIService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.ucn.disc.dsm.crojas.news.utils.Validation;
import retrofit2.Response;

/**
 * Naive syncronic NewsApi implementation.
 *
 * @author Christpher Rojas-Garri
 */
public final class NewsApiService {

    /**
     * The Key.
     */
    private final String apiKey;

    /**
     * The sub-service
     */
    private final APIService apiService;

    /**
     * The Constructor.
     *
     * @param apiKey
     */
    public NewsApiService(String apiKey) {
        Validation.notNull(apiKey, "apiKey");
        this.apiKey = apiKey;
        this.apiService = APIClient.getAPIService();
    }

    /**
     * The getTopHeadLines adaptor.
     *
     * @param category
     * @param pageSize
     * @return
     * @throws IOException
     */
    public List<Article> getTopHeadLines(final String category, final Integer pageSize) throws IOException {
        Validation.notNull(category, "category");
        Validation.notNull(pageSize, "pageSize");

        if (pageSize < 1) {
            throw new IllegalArgumentException("Error: Pagesize need to be > 0");

        }

        //TODO: Implements the correect map to request parameters.
        //https://newsapi.org/docs/endpoints/top-headlines
        // The map of parameters.
        Map<String, String> query = new HashMap<>();
        query.put("apiKey", this.apiKey);

        //query.put("country", topHeadLinesRequest.getCountry());
        //query.put("language", topHeadLinesRequest.getLanguage());
        query.put("category", category);

        //query.put("source", topHeadLinesRequest.getSources());
        //query.put("q", topHeadLinesRequest.getQ());
        query.put("pageSize", pageSize.toString());

        //query.put("page", topHeadLinesRequest.getPages());

        //The response (syncronic!)
        Response<ArticleResponse> response = apiService.getTopHeadlines(query).execute();

        //All ok, return the data
        if (response.isSuccessful()) {
            return response.body().getArticles();
        }
        throw new RuntimeException("Error:" + response.code() + "--->" + response.errorBody().toString());
    }
}
