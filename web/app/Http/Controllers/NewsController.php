<?php

namespace App\Http\Controllers;

use App\Models\News;
use Illuminate\Http\Request;

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
        //
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
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }

    /**
     * Display a listing of n news
     * Were the max of display is 100
     * If the number of news is not indicated, it is left at 20
     *
     * @param int $n
     * @return \Illuminate\Http\Response
     */
    public function pageSize($n)
    {
        //Select * from News Limit $n;
        if (count(func_get_args() == 1))
        {
            $news = News::all()->limit($n);
        }
        //Select * from News Limit 20;
        else{
            $news = News::all()->limit(20);
        }


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
    public function page($id)
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
    public function q($keywords)
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
}
