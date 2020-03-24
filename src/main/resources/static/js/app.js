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
        var fila= $("#filasSala");
        salas.map(function(element){
            var onclick='api.unirJugadorToSala("'+element.codigo+'/'+ $("#Nombre").val()+'")';
            var markup = "<tr> <td>"+ element.autor +"</td> <td>"+element.codigo+"</td><td><a type='button' class='btn-get-started' onclick= "+onclick+">Unirse</a></td> </tr>";
            fila.append(markup)
        })

    }

    function createJugadores(){
        var codigo= localStorage.getItem("codigo");
        api.getJugadoresBySala(codigo);
        var eventInterval = setInterval(function(){api.getJugadoresBySala(codigo); },5000); //<-- in milliseconds

    }

    function createTableJugadores(jugadores){
        $('#jugadores tbody').empty();
        var fila= $("#filasJugador");
        jugadores.map(function(element){
            var markup = "<tr> <td>"+ element+"</td> </tr>";
            fila.append(markup)
        })
        $("jugadores").append(fila);
        //if(localStorage.getItem("autor")!= null && jugadores.length >=8){
        if(jugadores.length >=8){
            document.getElementById("empezar").style.display="block";
        }
    }

    function empezar(){
        location.assign("webApp/juego.html");
        if(localStorage.getItem("autor")!= null){
            api.getEquipoBySalaAndUsuario(localStorage.getItem("codigo"),localStorage.getItem("autor"))
        }else{
            api.getEquipoBySalaAndUsuario(localStorage.getItem("codigo"),localStorage.getItem("usuario"))
        }
    }


    return {
        crearSala:crearSala,
        mostrarTabla:mostrarTabla,
        createTable:createTable,
        createJugadores:createJugadores,
        createTableJugadores:createTableJugadores,
        empezar:empezar
    }
})();
