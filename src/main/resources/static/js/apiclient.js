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

    function getSalas(){
        axios({
            method:'get',
            url: "http://localhost:8080/drawIt" ,
        })
        .then(response => app.createTable(response.data))
        .catch(error => console.log(error));
    }

    function unirseASala(direccion){
        console.log(direccion);
        var exito=false;
        axios({
            method:'put',
            url: "http://localhost:8080/drawIt/"+direccion ,
        })
        .then(response => exito=true)
        .catch(error => exito=false);
        return exito
    }

//    function crearJugador(autor) {
//        var data = {"autor": autor};
//        var promise = $.ajax({
//            type: 'POST',
//            url: 'http://localhost:8080/drawIt/'+autor,
//            contentType: 'application/json',
//            data: JSON.stringify(data),
//        });
//    }

//    function unirseASala(){
//        var data = {"codigo": codigo};
//        var promise = $.ajax({
//            type: 'PUT',
//            url: 'http://localhost:8080/drawIt/'+ codigo,
//            contentType: 'application/json',
//            data: JSON.stringify(data),
//        });
//    }


    return{
        crearSala:crearSala,
        getSalas:getSalas,
        unirseASala:unirseASala
    }




})();

