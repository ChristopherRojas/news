<?php

use Illuminate\Support\Facades\Route;
use \App\Http\Controllers\NewsController;
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
Route::get('/',[NewsController::class,'mainmenu']);
Route::any('/insert-form',[NewsController::class,'showNewsForm']);
Route::post("/store-form",[NewsController::class,'store']);
Route::any('/edit',[NewsController::class,'showEditForm']);
Route::post('/delete-news',[NewsController::class,'destroy']);
Route::post('/edit-form',[NewsController::class,'searchNewsToEdit']);
Route::get('/editform',[NewsController::class,'showEdit']);
Route::post('/edit-news',[NewsController::class,'update']);


