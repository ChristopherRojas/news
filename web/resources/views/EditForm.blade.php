<html lang="es-ES" prefix="og: http://ogp.me/ns#">
<html>
<head>
    <title>My News</title>
    <meta name="csrf-token" content="{{ csrf_token() }}">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    @if(session('status'))
    <div class="alert alert-success" style="background: #009688; color:white">
        {{ session('status') }}
    </div>
    @endif
    <div class="card">
        <div class="card-header text-center font-weight-bold" style="background: #009688; color: white">
            MyNews - The Best API for News
        </div>
        <div class="card-body" style = "background: #4DB6AC">
            @php
                use App\Models\News;
                $newss = News::all();
            @endphp
            @forelse($newss as $news)
                <div class = "card-body" style ="background: #4DB6AC">
                    <div class = "card" style="background: #B2DFDB ">
                        &nbsp;
                        <div><b>&nbsp;&nbsp;Title:&nbsp;</b>{{$news->title}}</div>
                        <div><i>&nbsp;&nbsp;Author:&nbsp;{{$news->author}}</i></div>
                        <div><i>&nbsp;&nbsp;Source:&nbsp;{{$news->source}}</i></div>
                        <div>&nbsp;&nbsp;{{$news->published_at}}</div>
                        <div class="navbar">
                            <div class="navbar-inner">
                                <ul class="nav">
                                    <li>
                                        <form action="{{ url('edit-form') }}" method="POST" >
                                            @csrf
                                            <input name="id" id = "id" type = "hidden" value = "{{$news->id}}">
                                            <button type="submit" class="px-4 py-2 bg-red-400 rounded" style="background: #009688; color: white">Edit</button>
                                        </form>
                                    </li>
                                    <li>
                                        <form action="{{url('delete-news')}}" method="POST" onsubmit="return confirmation()">
                                            @csrf
                                            <input name="id" id = "id" type = "hidden" value = "{{$news->id}}">
                                            <button type="submit" class="px-4 py-2 bg-red-400 rounded" style="background: red; color: white">Delete</button>
                                        </form>
                                        <script type="text/javascript">
                                            function confirmation()
                                            {
                                                if(confirm("Desea seguir?"))
                                                {
                                                    return true;
                                                }
                                                else
                                                {
                                                    return false;
                                                }
                                            }
                                        </script>
                                    </li>

                                </ul>
                            </div>
                        </div>
                    </div>
                    &nbsp;
                </div>
            @empty
                <div class = "card">
                    No hay noticias ingresadas aún.

                </div>
            @endforelse
        </div>
    </div>
</div>
</body>
</html>

