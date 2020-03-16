var appi = (function () {

    function crearSala(autor, codigo, jugadores) {
        var data = {"autor": autor, "codigo": codigo, "juagdores": jugadores};
        var promise = $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/blueprints',
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

    function createBlueprint(author, bpname, points) {
        var data = {"author": author, "name": bpname, "points": points};
        var promise = $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/blueprints',
            contentType: 'application/json',
            data: JSON.stringify(data),
        });
        promise.then(
            function () {
                app.getBlueprintsByAuthor();
                app.setCreating(false);
            },
            function () {
                alert("Error al crear el plano " + bpname);
                app.setCreating(false);
            }
        );
        return promise;
    }


})();

