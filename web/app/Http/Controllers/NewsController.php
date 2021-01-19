<?php

namespace App\Http\Controllers;

use App\Models\News;
use Illuminate\Http\Request;
use Illuminate\Database\Eloquent\Model;
use Ramsey\Uuid\Type\Integer;

class NewsController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //select * from news
        $news = News::all();
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
        $news->published_at = $request->published_at;
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
     * @return \Illuminate\Http\Response
     */
    public static function pageSize($n = 20)
    {
        //Select * from News Limit $n;
        $news = News::limit($n)->get();
        return response([
            'message' =>'Retrieved Successfully',
            'news'=> $news
        ], status:200) ;
    }

    /**
     * Shows the page that has the id entered
     *
     * @param $id
     * @return \Illuminate\Http\Response
     */
    public static function page($id = 1)
    {
        //Select * from News n where n.id = $id
        $news = News::find($id);
        return response([
            'message' =>'Retrieved Successfully',
            'news'=> $news
        ], status:200) ;
    }

    /**
     * Search the news with the keyword.
     *
     * @param $keywords
     * @return \Illuminate\Http\Response
     */
    public static function q($keywords = '')
    {
        //Select * from News where INSTR(*, $keywords) > 0
        $columns = ['id','title','author','source','url','description','content'];
        $query = News::query();
        foreach($columns as $column){
            $query->orWhere($column,'LIKE',"%{$keywords}%");
        }
        $news = $query->get();

        return response([
            'message' =>'Retrieved Successfully',
            'news'=> $news
        ], status:200) ;
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
