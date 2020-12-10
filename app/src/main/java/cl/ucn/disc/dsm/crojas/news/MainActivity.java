/*
 * Copyright (c) 2020. Christopher Rojas-Garri, christopher.rojas@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 *  including without limitation the rights to use, copy, modify, merge, publish, distribute,
 *  sublicense, and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 *  substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 *  NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.crojas.news;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import cl.ucn.disc.dsm.crojas.news.model.News;
import cl.ucn.disc.dsm.crojas.news.services.Contracts;
import cl.ucn.disc.dsm.crojas.news.services.ContractsImplNewsApi;

/**
* The Main Class
* @author Christopher Rojas-Garri.
* */

public class MainActivity extends AppCompatActivity {
    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(MainActivity.class);
    /**
     * THe List View
     */
     protected ListView listView;
    /**
     * OnCreate.
     *
     * @param savedInstanceState used to reload the app.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log.debug("OnCreate...");
        setContentView(R.layout.activity_main);
        this.listView = findViewById(R.id.am_lv_news);

        //Get the news in the background thread
        AsyncTask.execute(() -> {
            Contracts contracts = new ContractsImplNewsApi("b7f870ce98c249898a22cf1a244c02bc");

            //Get the news from NewsApi (Internet)
            List<News> listNews = contracts.retrieveNews(30);

            //Build the simple adapter to show the list of news (String!)
            ArrayAdapter<String> adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1,
                    listNews);

            // Set the adapter !
            runOnUiThread(()->{
            this.listView.setAdapter(adapter);
           });
        });
    }
}