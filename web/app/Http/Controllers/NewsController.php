<?php

namespace App\Http\Controllers;

use App\Models\News;
use Illuminate\Http\Request;
use Illuminate\Database\Eloquent;
use Ramsey\Uuid\Type\Integer;
use Illuminate\Support\Facades\URL;

class NewsController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $news = News::all();
        $columns = ['id', 'title', 'author', 'source', 'url', 'description', 'content'];
        $pageSize = $request->query('pageSize', 20);
        $page = $request->query('page', 1);
        $q = $request->query('q');

        $url = url()->full();
        if(str_contains($url,'pageSize')){
            if(str_contains($url,'&q')){
                $query = News::query()->limit($pageSize);
                foreach ($columns as $column) {
                    $query->orWhere($column, 'LIKE', "%{$q}%");
                }
                $news = $query->get();
            }else{
                $news = NewsController::pageSize($pageSize);
            }
        }
        if(str_contains($url,'page') and !str_contains($url,'pageSize')){
            $news = NewsController::page($page);
        }
        if(str_contains($url,'?q')) {
            $query = News::query();
            $columns = ['id', 'title', 'author', 'source', 'url', 'description', 'content'];
            foreach ($columns as $column) {
                $query->orWhere($column, 'LIKE', "%{$q}%");
            }
            $news = $query->get();
        }
        //Return the get request with code 200
        return response([
            'message' =>'Retrieved Successfully',
            'news'=> $news
        ], status:200) ;
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $news = new News;
        $news->title = $request->title;
        $news->author = $request->author;
        $news->source = $request->source;
        $news->url = $request->url;
        $news->description = $request->description;
        $news->content = $request->contentt;
        $news->url_image = $request->url_image;
        $news->published_at = date("Y-m-d h:i:sa", strtotime("now"));
        $news->save();
        NewsController::updatePage();
        return redirect('/insert-form')->with('status', 'News Post Form Data Has Been inserted');
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function showEditForm()
    {
        return view("EditForm");

    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request)
    {
        $news = News::find($request->id);
        $news->title = $request->title;
        $news->author = $request->author;
        $news->source = $request->source;
        $news->url = $request->url;
        $news->description = $request->description;
        $news->content = $request->contentt;
        $news->url_image = $request->url_image;
        $news->published_at = $request->published_at;
        $news->save();
        NewsController::updatePage();
        return redirect('/edit')->with('status', 'News Post Form Data Has Been Edited');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy(Request $request)
    {
        News::destroy($request->id);
        this::updatePage();
        return redirect('/edit')->with('status', 'News Post Form Data Has Been Deleted');
    }

    public function updatePage(){
        $count = 1;
        $allNews = News::orderBy('published_at', 'DESC')->get();
        foreach($allNews as $news){
            $news->id = -$count;
            $count++;
            $news->save();
        }
        $count = 1;
        foreach($allNews as $news){
            $news->id = $count;
            $count++;
            $news->save();
        }
    }
    /**
     * Display a listing of n news
     * Were the max of display is 100
     * If the number of news is not indicated, it is left at 20
     *
     * @param int $n
     * @return News
     */
    public static function pageSize($n)
    {
        if($n == null){
            $n = 20;
        }
        //Select * from News Limit $n;
        $news = News::limit($n)->get();
        return $news;
    }

    /**
     * Shows the page that has the id entered
     *
     * @param $id
     * @return News
     */
    public static function page($id)
    {

        if($id == null){
            $id = 1;
        }
        //Select * from News n where n.id = $id
        $news = News::find($id);
        return $news;
    }


    public function showNewsForm(){
        return view('NewsForm');
    }

    public function searchNewsToEdit(Request $request){
        $news = News::find($request->id);
        return redirect('/editform')->with('news', $news);
    }
    public function showEdit(){
        return view('Edit');
    }
    public function mainmenu(){
        NewsController::updatePage();
        return view('welcome');
    }
}
