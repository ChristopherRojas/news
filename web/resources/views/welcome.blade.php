<html>
<head>
    <title>My News</title>
    <meta name="csrf-token" content="{{ csrf_token() }}">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <div class="card">
        <div class="card-header text-center font-weight-bold" style="background: #009688; color:white">
            MyNews - The Best API for News
        </div>
        <div class="card-body" style="background: #4DB6AC">
            <form action="{{ url('insert-form') }}" method="GET" >
                @csrf
                <div style="width: 100%; text-align: center">
                    <button type="submit" class="px-5 py-2 bg-red-400 rounded" style="background: #009688; color: white;">Insert News</button>
                </div>
            </form>
            <form action="{{url('edit') }}" method="GET">
                @csrf
                <div style="width: 100%; text-align: center">
                    <button type="submit" class="px-5 py-2 bg-red-400 rounded" style="background: #009688; color: white">
                        View News
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
