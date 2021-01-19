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
            <form name="add-news-post-form" id="add-news-post-form" method="post" action="{{url('store-form')}}">
                @csrf
                <div class="form-group">
                    <label for="exampleInputEmail1">Title</label>
                    <input type="text" id="title" name="title" class="form-control" required="" minlength="2">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">Author</label>
                    <input type="text" id="author" name="author" class="form-control" required="" minlength="2">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">Source</label>
                    <input type="text" id="source" name="source" class="form-control" required="" minlength="2">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">URL</label>
                    <input type="url" id="url" name="url" class="form-control">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">Image URL</label>
                    <input type="url" id="url_image" name="url_image" class="form-control">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">Content</label>
                    <textarea id="contentt" name="contentt" class="form-control" required=""></textarea>
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">Description</label>
                    <textarea id="description" name="description" class="form-control" required=""></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
