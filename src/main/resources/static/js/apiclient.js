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

    function unirseAUnaSala(autor, codigo, jugadores) {
        var data = {"autor": autor, "codigo": codigo, "juagdores": jugadores};
        var promise = $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/blueprints',
            contentType: 'application/json',
            data: JSON.stringify(data),
        });
    }
    return{
        crearSala:crearSala
    }




})();

