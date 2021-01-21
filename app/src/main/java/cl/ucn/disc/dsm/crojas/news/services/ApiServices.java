/*
 * Copyright (c) 2021. Christopher Rojas-Garri, christopher.rojas@alumnos.ucn.cl
 * Copyright (c) 2021. Camilo Barrera-Arancibia,camilo.barrera@alumnos.ucn.cl
 * Copyright (c) 2021. Marcelo Lam-Biagguini,marcelo.lam@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.crojas.news.services;

import java.util.List;

import cl.ucn.disc.dsm.crojas.news.model.News;
import retrofit2.Call;
import retrofit2.http.GET;

/**
<<<<<<< HEAD
 * The Connection to APIRest created on Laravel.
 * @author Camilo Barrera A.
 */
public interface ApiServices {
    //Base URL to service
=======
 * Conexion interface Api service
 * @author Christopher Rojas - Camilo Barrera
 */
public interface ApiServices {

    //Url APIRest with Android Studio
>>>>>>> ff81153be3c084eefb1db497c5c36d9fb7b51e32
    public static final String URL="http://127.0.0.1:8000/";

    //Call to service
    @GET("api/news")
    Call<List<News>>NewsList();
}
