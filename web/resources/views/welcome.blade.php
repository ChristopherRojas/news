<html>
<head>
    <title>My News</title>
    <meta name="csrf-token" content="{{ csrf_token() }}">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <div class="card">
        <div class="card-header text-center font-weight-bold">
            MyNews - The Best API for News
        </div>
        <div class="card-body">
            <form action="{{ url('insert-form') }}" method="POST" >
                @csrf
                <button type="submit" class="px-5 py-2 bg-red-400 rounded" style="background: lightblue; color: black">Insert News</button>
            </form>

            <form action="{{ url('edit') }}" method="POST">
                @csrf
                <button type="submit" class="px-5 py-2 bg-red-400 rounded" style="background: red;color: white">
                    View News
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
