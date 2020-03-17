var api = (function () {

    function crearSala(autor) {
        var data = {"autor": autor};
        var promise = $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/drawIt',
            contentType: 'application/json',
            data: JSON.stringify(data),
        });
    }

    function crearJugador(autor) {
        var data = {"autor": autor};
        var promise = $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/drawIt/'+autor,
            contentType: 'application/json',
            data: JSON.stringify(data),
        });
    }

    function unirseASala(){
        var data = {"codigo": codigo};
        var promise = $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/drawIt/'+ codigo,
            contentType: 'application/json',
            data: JSON.stringify(data),
        });
    }


    return{
        crearSala:crearSala,
        crearJugador:crearJugador,
        unirseASala:unirseASala
    }




})();

