<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Formulario de Noticias.</title>
</head>
<style>
    *{
        font-family: sans-serif;
        background: aquamarine;
    }
    #formulario{
        background: white;
        color: black;
    }
    #button{
        text-align: center;
    }
    #division{
        display: inline-block;
    }
    body{
        text-align: center;
    }
</style>
<body>
    <h1>Formulario de ingreso de noticias.</h1>
    <hr>
    <form name="form" id = "form" method="POST" action="{{url('store-form')}}">>
        {{--Primera columna--}}
        <div id="division">
            <div>
                {{--Titulo--}}
                <h3 id="titulos">Titulo</h3>
                <input id = "formulario" type="text" name="title" placeholder="Ingrese Titulo" /><br><br>
            </div>
            <div>
                {{--Autor--}}
                <h3 id="titulos">Autor</h3>
                <input id = "formulario" type="text" name="author" placeholder="Ingrese autor" /><br><br>
            </div>
            <div>
                {{--Fuente--}}
                <h3 id="titulos">Fuente</h3>
                <input id = "formulario" type="text" name="source" placeholder="Ingrese fuente" /><br><br>
            </div>
            <div>
                {{--URL--}}
                <h3 id="titulos">URL</h3>
                <input id = "formulario" type="url" name="url" placeholder="Ingrese URL" /><br><br>
            </div>
        </div>
        {{--Segunda columna--}}
        <div id="division">
            <div>
                {{--Descripción--}}
                <h3 id="titulos">Descripción</h3>
                <input id = "formulario" type="text" name="description" placeholder="Ingrese descripción" /><br><br>
            </div>
            <div>
                {{--Contenido--}}
                <h3 id="titulos">Contenido</h3>
                <input id = "formulario" type="text" name="content" placeholder="Ingrese contenido" /><br><br>
            </div>
            <div>
                {{--URL de la imagen--}}
                <h3 id="titulos">URL de la imagen</h3>
                <input id = "formulario" type="url" name="url_image" placeholder="Ingrese URL de la imágen" /><br><br>
            </div>
            <div>
                {{--Fecha de publicación--}}
                <h3 id="titulos">Fecha de publicación</h3>
                <input id = "formulario" type="date" name="published_at" placeholder="Ingrese fecha de publicación" /><br><br>
            </div>
        </div>
        {{--Botón--}}
        <div id="button">
             <button id = "formulario"  type="submit" class="btn btn-primary">Ingresar</button>
        </div>


</form>
</body>
