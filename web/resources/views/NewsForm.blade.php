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
        <div class="card-body" style="background: #4DB6AC">
            <form name="add-news-post-form" id="add-news-post-form" method="post" action="{{url('store-form')}}">
                @csrf
                <div class="form-group">
                    <label for="exampleInputEmail1"><b>Title</b></label>
                    <input type="text" id="title" name="title" class="form-control" required="" minlength="2" style="background: #B2DFDB">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1"><b>Author</b></label>
                    <input type="text" id="author" name="author" class="form-control" required="" minlength="2" style="background: #B2DFDB">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1"><b>Source</b></label>
                    <input type="text" id="source" name="source" class="form-control" required="" minlength="2" style="background: #B2DFDB">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1"><b>URL</b></label>
                    <input type="url" id="url" name="url" class="form-control" style="background: #B2DFDB">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1"><b>Image URL</b></label>
                    <input type="url" id="url_image" name="url_image" class="form-control" style="background: #B2DFDB">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1"><b>Content</b></label>
                    <textarea id="contentt" name="contentt" class="form-control" required="" style="background: #B2DFDB"></textarea>
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1"><b>Description</b></label>
                    <textarea id="description" name="description" class="form-control" required="" style="background: #B2DFDB"></textarea>
                </div>
                <button type="submit" class="btn btn-primary" style="background: #009688">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
