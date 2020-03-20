var app = (function () {

    function crearSala() {
        if ($("#Nombre").val() == "") {
            alert("¡Debes ingresar un nombre!");
            return false
        } else {
            api.crearSala($("#Nombre").val());
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
        salas.map(function(element){
            var onclick='api.unirseASala("'+element.codigo+'/'+ $("#Nombre").val()+'")';
            var markup = "<tr> <td>"+ element.autor +"</td> <td>"+element.codigo+"</td><td><a type='button' href='crearSala.html' class='btn-get-started' onclick=return "+onclick+">Unirse</a></td>";
            $("#filasSala").append(markup)
        })
    }

    function crearJugador(){
        if ($("#Nombre").val() == "") {
            alert("¡Debes ingresar un nombre!");
            return false
        } else {
            api.crearJugador($("#Nombre").val());
        }

    }

    function unirseASala(){
        if ($("#Codigo").val() == "") {
            alert("¡Debes ingresar un codigo!");
            return false
        } else {
            api.unirseASala($("#Codigo").val());
        }
    }

    return {
        crearSala:crearSala,
        mostrarTabla:mostrarTabla,
        createTable:createTable,
        crearJugador:crearJugador,
        unirseASala:unirseASala
    }
})();
