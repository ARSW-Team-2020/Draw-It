var app = (function () {

    function crearSala() {
        if ($("#Nombre").val() == "") {
            alert("¡Debes ingresar un nombre!");
            return false
        } else {
            api.crearSalas($("#Nombre").val());
            window.location = "/crearSala.html";
            //podriamos agregar un alert en caso de que la respuesta no dea 201

        }

    }

    function mostrarTabla(){
        if ($("#Nombre").val() == "") {
            alert("¡Debes ingresar un nombre!");
        } else {
            $('#table tbody').empty();
            visible();
            api.getSalas();
        }
    }

    function visible(){
        if (document.getElementById("table").style.display === "none")
            document.getElementById("table").style.display="block";
        else
            document.getElementById("table").style.display="none";
    }

    function createTable(salas){
        var fila= $("#filasSala");
        salas.map(function(element){
            var onclick='api.unirJugadorToSala("'+element.codigo+'/'+ $("#Nombre").val()+'")';
            var markup = "<tr> <td>"+ element.autor +"</td> <td>"+element.codigo+"</td><td><a type='button' class='btn-get-started' onclick= "+onclick+">Unirse</a></td> </tr>";
            fila.append(markup)
        })

    }

    function createJugadores(){
        console.log(document.cookie);
        var jugadores= document.cookie;
        api.getJugadoresBySala(jugadores);

    }

    function createTableJugadores(jugadores){
        //console.log(document.cookie);
        //jugadores=document.cookie;
        //jugadores=jugadores.split(',');
        var fila= $("#filasJugador");
        jugadores.map(function(element){
            console.log(element)
            var markup = "<tr> <td>"+ element+"</td> </tr>";
            fila.append(markup)
        })
        $("jugadores").append(fila);
    }


    return {
        crearSala:crearSala,
        mostrarTabla:mostrarTabla,
        createTable:createTable,
        createJugadores:createJugadores,
        createTableJugadores:createTableJugadores
    }
})();
