var app = (function () {
    var player="unknown";

    function getPlayer() {
        return player;
    }

    function setPlayer(name) {
        player = name;
        sessionStorage.setItem("playerName",player);
    }

    function crearSala() {
        if ($("#Nombre").val() == "") {
            toastr["warning"]("El nombre de usuario no puede ser vacio","Oops! escoge tu nombre");
            return false
        } else {
            setPlayer($("#Nombre").val());
            console.log(player);
            api.crearSala(player);
            sendGeneral();
        }

    }

    function subSala(){
        var codigo =  sessionStorage.getItem("codigo");
        stompClient.subscribe('/topic/'+codigo,function (eventbody) {
            var jugadores = JSON.parse(eventbody.body);
            createTableJugadores(jugadores);
        },{id:codigo});
        stompClient.subscribe('/topic/'+codigo+'/empezar',function (eventbody) {
            sessionStorage.setItem("hora",eventbody.body);
            empezar();
        },{id:codigo+'/empezar'});
        stompClient.send("/app/union/"+codigo);
    }

    /**
     * funcion para unirse a una sala
     */
    function mostrarTabla(){
        if ($("#Nombre").val() == "") {
            toastr["warning"]("El nombre de usuario no puede ser vacio","Oops! escoge tu nombre");
        } else {
            $('#table tbody').empty();
            visible();
            setPlayer( $("#Nombre").val());
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
            var onclick='api.unirJugadorToSala("'+element.codigo+'")';
            var markup = "<tr> <td>"+ element.autor +"</td> <td>"+element.codigo+"</td><td><a type='button' class='btn-get-started' onclick= "+onclick+">Unirse</a></td> </tr>";
            fila.append(markup)
        });
    }

    function createTableJugadores(jugadores){
        $('#jugadores tbody').empty();
        var fila= $("#filasJugador");
        jugadores.map(function(element){
            var markup = "<tr> <td>"+ element+"</td> </tr>";
            fila.append(markup)
        })
        $("jugadores").append(fila);
        if(jugadores.length >=8){
            document.getElementById("empezar").style.display="block";
        }
    }

    function empezar(){
        console.log(app.getPlayer());
        sessionStorage.setItem("ronda",1);
        if(sessionStorage.getItem("playerName") != null){
            api.getEquipoBySalaAndUsuario(sessionStorage.getItem("codigo"),sessionStorage.getItem("playerName"));
        }else if(sessionStorage.getItem("autor")!= null){
            api.getEquipoBySalaAndUsuario(sessionStorage.getItem("codigo"),sessionStorage.getItem("autor"));
        }else{
            api.getEquipoBySalaAndUsuario(sessionStorage.getItem("codigo"),sessionStorage.getItem("usuario"));
        }
        stompClient.disconnect();
    }

    var stompClient = null;
    var connect = function () {
        console.log(app.getPlayer());
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            subSala();
        },onConnectError);
        stompClient.debug = null;
    };

    function onConnectError(message){
        Swal.fire({
            title: 'Whoops!',
            text: "¡Hubo un problema para conectar al servidor!",
            icon: 'error',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Refrescar!',
            showCancelButton: true,
            cancelButtonText: 'Salir'
        }).then((result) => {
            if (result.value) {
                location.reload();
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                location.assign("../index.html");
            }
        })
    }

    /**
     *
     * @param equipo
     */
    function organizar(equipo){
        console.log(equipo);
        console.log("este es el equipo al que pertenezco");
        sessionStorage.setItem("myTeam",equipo[0]);
        sessionStorage.setItem("equipo",equipo[0]);
        sessionStorage.setItem("jugador1",equipo[1]);
        sessionStorage.setItem("jugador2",equipo[2]);
        sessionStorage.setItem("jugador3",equipo[3]);
        sessionStorage.setItem("jugador4",equipo[4]);
        sessionStorage.setItem("jugador5",equipo[5]);
        sessionStorage.setItem("jugador6",equipo[6]);
        sessionStorage.setItem("jugador7",equipo[7]);
        sessionStorage.setItem("jugador8",equipo[8]);
        location.assign("webApp/juego.html");
    }

    function mostrarRonda(){
        //var ronda =   Storage.getItem("ronda");
        var ronda = sessionStorage.getItem("ronda");
        var text = document.getElementById("ronda");
        text.innerText = "Ronda "+ronda;
    }

    function mostrarNombres(){
        countdown(sessionStorage.getItem("hora"),"clock");
        mostrarRonda();
        var left = document.getElementById("team1");
        var right = document.getElementById("team2");
        var divL;
        var divR;
        var textElement;
        var messageText;
        for (var i = 1; i < 5; i++) {
            divL = document.createElement('div');
            divL.classList.add('item');
            divR = document.createElement('div');
            divR.classList.add('item');
            textElement = document.createElement('p');
            messageText = document.createTextNode(sessionStorage.getItem("jugador"+i));
            textElement.append(messageText);
            divL.append(textElement);
            textElement = document.createElement('p');
            messageText = document.createTextNode(sessionStorage.getItem("jugador"+(i+4)));
            textElement.append(messageText);
            divR.append(textElement);
            left.append(divL);
            right.append(divR);
        }
        api.getPalabra(sessionStorage.getItem("codigo"),sessionStorage.getItem("myTeam"));
    }

    function mostrarPalabra(data){
        var pal = document.getElementById("palabra");
        sessionStorage.setItem("palabra",data);
        console.log(sessionStorage.getItem("playerName") +" "+ sessionStorage.getItem("painter"));
        if(sessionStorage.getItem("playerName") == sessionStorage.getItem("painter")){
            pal.innerText = data;
        }else{
            var s = "";
            var l = data.length;
            for(var i = 0; i<l ;i++){
                s+="_ "
            }
            pal.innerText = s;
        }

    }

    return {
        crearSala:crearSala,
        mostrarTabla:mostrarTabla,
        createTable:createTable,
        createTableJugadores:createTableJugadores,
        empezar:function(){
            var codigo = sessionStorage.getItem("codigo");
            var now  = new Date();
            now.setMinutes(now.getMinutes()+2);
            stompClient.send("/app/"+codigo+"/empezar",{},now.toString());
        },
        organizar:organizar,
        connect:connect,
        mostrarNombres:mostrarNombres,
        mostrarPalabra:mostrarPalabra,
        mostrarRonda:mostrarRonda,
        getPlayer:getPlayer,
        setPlayer:setPlayer
    }

})();
