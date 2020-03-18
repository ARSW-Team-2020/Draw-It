var app = (function () {

    function crearSala() {
        if ($("#Nombre").val() == "") {
            alert("¡Debes ingresar un nombre!");
            return false
        } else {
            api.crearSala($("#Nombre").val());
        }

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
        crearJugador:crearJugador,
        unirseASala:unirseASala
    }
})();
