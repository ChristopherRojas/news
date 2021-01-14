<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

/*Route::get('/', function () {
    return view('welcome');
});*/
//FIX ME: Problemas con NewsController, me dice que no existe.
Route::view("/","form");
Route::post("/store",[NewsController::class,'store']);
Route::redirect("/store","/"); //FIX ME: Causa Error 419 Page Expired
//Route::get('/store', [NewsController::class,'store']);


