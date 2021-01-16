<html>
<head>
    <title>My News</title>
    <meta name="csrf-token" content="{{ csrf_token() }}">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    @if(session('status'))
    <div class="alert alert-success">
        {{ session('status') }}
    </div>
    @endif
    <div class="card">
        <div class="card-header text-center font-weight-bold">
            MyNews - The Best API for News
        </div>
        <div class="card-body">
            @php
                use App\Models\News;
                $newss = News::all()
            @endphp
            @forelse($newss as $news)
                <div class = "card">
                    <label>  {{$news->title}}</label>
                    <label>  {{$news->author}}</label>
                    <label>  {{$news->published_at}}</label>
                    <label>
                        <a id = "edit" href ="{{"/"}}" class="btn btn-xs btn-default" style="background: lightblue; color: black">Edit</a>
                        <form action="{{ url('delete-news') }}" class="delete-form-validation" method="POST" >
                            @csrf
                            <input name="id" id = "id" type = "hidden" value = "{{$news->id}}">
                            <button type="submit" class="px-4 py-2 bg-red-400 rounded">
                                Delete
                            </button>
                        </form>
                    </label>
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
