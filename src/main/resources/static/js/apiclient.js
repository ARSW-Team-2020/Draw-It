var api = (function () {

    function crearSalas(autor) {
        console.log(autor),
        axios({
            method:'post',
            url: "/drawIt" ,
            data:autor,
        })
        .then(response => console.log(response))
        .catch(error => console.log(error));
    }

    function getSalas(){
        axios({
            method:'get',
            url: "/drawIt" ,
        })
        .then(response => app.createTable(response.data))
        .catch(error => console.log(error));
    }

    function unirJugadorToSala(direccion){
        console.log(direccion);
        codigo= direccion.split('/');
        console.log(codigo[0]);
        axios({
            method:'put',
            url: "/drawIt/"+direccion ,
        })
        //.then(response => getJugadoresBySala(codigo[0]))
        .then(response => cookie(codigo[0]))
        .catch(error => console.log(error));
    }

    function getJugadoresBySala(sala){
        axios({
            method:'get',
            url: "/drawIt/"+sala,
        })
        .then(response => app.createTableJugadores(response.data))
        .catch(error => console.log(error));
    }

    function cookie (respuesta){
        location.assign("crearSala.html");
        document.cookie = respuesta;
    }






    return{
        crearSalas:crearSalas,
        getSalas:getSalas,
        unirJugadorToSala:unirJugadorToSala,
        getJugadoresBySala:getJugadoresBySala
    }




})();

