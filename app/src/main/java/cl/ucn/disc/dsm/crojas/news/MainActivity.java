
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
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.crojas.news;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.kwabenaberko.newsapilib.network.APIService;
import com.mifmif.common.regex.Main;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ModelAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import cl.ucn.disc.dsm.crojas.news.model.News;
import cl.ucn.disc.dsm.crojas.news.model.NewsItem;
import cl.ucn.disc.dsm.crojas.news.services.ApiServices;
import cl.ucn.disc.dsm.crojas.news.services.Contracts;
import cl.ucn.disc.dsm.crojas.news.services.ContractsImplNewsApi;
//import cl.ucn.disc.dsm.crojas.news.services.NewsApiKey;
import cl.ucn.disc.dsm.crojas.news.services.NewsApiKey;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The Main Class
 *
 * @author Christopher Rojas-Garri.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(MainActivity.class);

    /**
     * The List View
     */
    protected ListView listView;

    /**
     * The pull to refresh
     */
    private SwipeRefreshLayout swipeRefreshLayout;

    /**
     * The Switch button
     */
    SwitchCompat switchCompat;
    SharedPreferences sharedPreferences = null;

    /**
     * List to news of APIRest
     */

    List<News> NewsList;

    /**
     * Retrofit Initialization
     */
    Retrofit cliente;

    /**
     * APIRest call and URL
     */
    ApiServices apiService;


    /**
     * OnCreate.
     *
     * @param savedInstanceState used to reload the app.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        //Switch Night Mode Theme
        switchCompat = findViewById(R.id.switchCompat);

        //Logger
        Logger log = LoggerFactory.getLogger(MainActivity.class);

        //Connection to APIRest created on Laravel
        cliente= new Retrofit.Builder().baseUrl(ApiServices.URL).addConverterFactory(GsonConverterFactory.create()).build();
        apiService=cliente.create(ApiServices.class);
        apiService.NewsList().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()){
                    NewsList=response.body();
                }
            }
            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                log.warn("Error: ",t.getMessage());

            }
        });

        //Saving state of our app
        sharedPreferences = getSharedPreferences("night", 0);
        Boolean booleanValue = sharedPreferences.getBoolean("night_mode", true);
        if (booleanValue) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            switchCompat.setChecked(true);
        }
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            /**
             * Switch button on checked changed
             * @param buttonView
             * @param isChecked
             */
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    switchCompat.setChecked(true);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("night_mode", true);
                    editor.commit();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    switchCompat.setChecked(false);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("night_mode", false);
                    editor.commit();
                }
            }
        });

        //The Toolbar
        this.setSupportActionBar(findViewById(R.id.am_t_toolbar));

        //The swipe Refresh layout
        swipeRefreshLayout = (SwipeRefreshLayout
                ) findViewById(R.id.am_srl_refresh);


        // The FastAdapter
        ModelAdapter<News, NewsItem> newsAdapter = new ModelAdapter<>(NewsItem::new);
        FastAdapter<NewsItem> fastAdapter = FastAdapter.with(newsAdapter);
        fastAdapter.withSelectable(false);

        // The Recycler View
        RecyclerView recyclerView = findViewById(R.id.am_rv_news);
        recyclerView.setAdapter(fastAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        // Get the news in the background thread
        AsyncTask.execute(() -> {

            // Using the contracts
            //TODO:APiKEy Ocult
            Contracts contracts = new ContractsImplNewsApi(NewsApiKey.getApiKey());

            //Get the news
            List<News> listNews = contracts.retrieveNews(30);

            // Set the adapter
            runOnUiThread(() -> {
                newsAdapter.add(listNews);
            });
        });

        //The SwipeRefresh configure data update for news
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {

                    //Refresh the news list until a new request is a made.
                    @Override
                    public void onRefresh() {
                        fastAdapter.notifyAdapterDataSetChanged();
                        Toast.makeText(MainActivity.this, "Se recargo", Toast.LENGTH_LONG).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );
    }
}