var api = (function () {

    function crearSala(autor) {
        console.log("api crear sala recibido");
        axios({
            method:'get',
            url: "/drawIt/1/" +  autor ,
        })
        .then(response => storage(response.data,"autor"))
        .catch(error => console.log(error));
    }

    function getSalas(playerName){
        axios({
            method:'get',
            url: "/drawIt" ,
        })
        .then(response => app.createTable(response.data, playerName))
        .catch(error => console.log(error));
    }

    function unirJugadorToSala(direccion){
        var playerName = $("#Nombre").val();
        direccion += "/"+playerName;
        axios({
            method:'put',
            url: "/drawIt/"+direccion ,
        })
        .then(response => cookie(direccion,playerName))
        .catch(function(error){
            toastr["warning"]("Ya hay un jugador en con tu nombre","Oops! Cambia de nombre");
            console.log(error);
        });
    }

    function getJugadoresBySala(sala){
        axios({
            method:'get',
            url: "/drawIt/"+sala,
        })
        .then(response => app.createTableJugadores(response.data))
        .catch(error => console.log(error));
    }

    function cookie (respuesta,playerName){
        codigo= respuesta.split('/');
        storage(codigo,"usuario");

    }

    function storage(info,usuario){

        location.assign("crearSala.html");
        sessionStorage.setItem("codigo",info[0]);
        sessionStorage.setItem(usuario,info[1]);
        sessionStorage.setItem(usuario,info[1]);
    }

    function getEquipoBySalaAndUsuario(sala,usuario){
        axios({
            method:'get',
            url: "/drawIt/"+sala+"/"+usuario,
        })
        .then(response => app.organizar(response.data))
        .catch(error => console.log(error));
    }

    function getPalabra(codigo,equipo){
        axios({
             method:'get',
             url: "/drawIt/palabra/"+codigo+"/"+equipo,
         })
        .then(response => app.mostrarPalabra(response.data))
        .catch(error => console.log(error));
    }

    return{
        crearSala:crearSala,
        getSalas:getSalas,
        unirJugadorToSala:unirJugadorToSala,
        getJugadoresBySala:getJugadoresBySala,
        getEquipoBySalaAndUsuario:getEquipoBySalaAndUsuario,
        getPalabra:getPalabra
    }
})();
