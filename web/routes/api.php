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
//Route::resource('news', NewsController::class);
/*Route::get('news?{f}={p?}', function ($f,$p){
    if($f == "pageSize"){
        return NewsController::pageSize($p);
    }
    if($f == 'page'){
        return NewsController::page($p);
    }
    if($f == 'q'){
        return NewsController::q($p);
    }

});*/
Route::get('news',[NewsController::class,'index']);

