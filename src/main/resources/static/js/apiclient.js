var api = (function () {

    function crearSala(autor) {
        console.log(autor),
        axios({
            method:'get',
            url: "/drawIt/1/" +  autor ,
        })
        .then(response => storage(response.data,"autor"))
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
        axios({
            method:'put',
            url: "/drawIt/"+direccion ,
        })
        .then(response => cookie(direccion))
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
        codigo= respuesta.split('/');
        storage(codigo,"usuario");
    }

    function storage(info,usuario){
        location.assign("crearSala.html");
        localStorage.setItem("codigo",info[0]);
        localStorage.setItem(usuario,info[1]);
    }

    function getEquipoBySalaAndUsuario(sala,usuario){
        axios({
            method:'get',
            url: "/drawIt/"+sala+"/"+usuario,
        })
        .then(response => app.organizar(response.data))
        .catch(error => console.log(error));
    }




    return{
        crearSala:crearSala,
        getSalas:getSalas,
        unirJugadorToSala:unirJugadorToSala,
        getJugadoresBySala:getJugadoresBySala,
        getEquipoBySalaAndUsuario:getEquipoBySalaAndUsuario
    }




})();

