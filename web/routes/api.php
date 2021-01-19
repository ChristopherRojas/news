<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use \App\Http\Controllers\NewsController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

//News routes
Route::resource('news', NewsController::class);
Route::get('news/pageSize',[NewsController::class,'pageSize']);
Route::get('news/pageSize/{n?}',[NewsController::class,'pageSize']);


Route::get('news/page/{id}', function ($id) {
    return NewsController::page($id);
});
Route::get('news/page/', function () {
    return NewsController::page(1);
});
