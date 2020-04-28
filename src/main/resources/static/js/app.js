var app = (function () {
    var player="unknown";

    function getPlayer() {
        return player;
    }
    function setPlayer(name) {
        if(sessionStorage.getItem(name) == null){
            player = name;
            sessionStorage.setItem("playerName",player);
            console.log(player);
        }
    }


    function crearSala() {
        if ($("#Nombre").val() == "") {
            toastr["warning"]("El nombre de usuario no puede ser vacio","Oops! escoge tu nombre");
            return false
        } else {
            setPlayer($("#Nombre").val());
            console.log(player);
            api.crearSala(player);
        }

    }

    function subSala(){
        var codigo =  localStorage.getItem("codigo");
        stompClient.subscribe('/topic/'+codigo,function (eventbody) {
            var jugadores = JSON.parse(eventbody.body);
            createTableJugadores(jugadores);
        },{id:codigo});
        stompClient.subscribe('/topic/'+codigo+'/empezar',function (eventbody) {
            localStorage.setItem("hora",eventbody.body);
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
            api.getSalas(player);

        }
    }

    function visible(){
        if (document.getElementById("table").style.display === "none")
            document.getElementById("table").style.display="block";
        else
            document.getElementById("table").style.display="none";
    }

    function createTable(salas,name){
        var fila= $("#filasSala");
        salas.map(function(element){
            var onclick='api.unirJugadorToSala("'+element.codigo+'/'+ $("#Nombre").val()+'",name)';
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
        //if(localStorage.getItem("autor")!= null && jugadores.length >=8){
        if(jugadores.length >=8){
            document.getElementById("empezar").style.display="block";
        }
    }

    function empezar(){
        console.log(app.getPlayer());
        sessionStorage.setItem("ronda",1);
        /*
        if(localStorage.getItem("autor")!= null){
            api.getEquipoBySalaAndUsuario(localStorage.getItem("codigo"),localStorage.getItem("autor"))
        }else{
            api.getEquipoBySalaAndUsuario(localStorage.getItem("codigo"),localStorage.getItem("usuario"))
        }
        */
        if(sessionStorage.getItem("playerName") != null){
            api.getEquipoBySalaAndUsuario(localStorage.getItem("codigo"),sessionStorage.getItem("playerName"));
        }else if(localStorage.getItem("autor")!= null){
            api.getEquipoBySalaAndUsuario(localStorage.getItem("codigo"),localStorage.getItem("autor"));
        }else{
            api.getEquipoBySalaAndUsuario(localStorage.getItem("codigo"),localStorage.getItem("usuario"));
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
        });
    };

    /**
     *
     * @param equipo
     */
    function organizar(equipo){
        location.assign("webApp/juego.html");

        console.log(equipo);
        console.log("este es el equipo al que pertenezco");
        sessionStorage.setItem("myTeam",equipo[0]);
        localStorage.setItem("equipo",equipo[0]);
        localStorage.setItem("jugador1",equipo[1]);
        localStorage.setItem("jugador2",equipo[2]);
        localStorage.setItem("jugador3",equipo[3]);
        localStorage.setItem("jugador4",equipo[4]);
        localStorage.setItem("jugador5",equipo[5]);
        localStorage.setItem("jugador6",equipo[6]);
        localStorage.setItem("jugador7",equipo[7]);
        localStorage.setItem("jugador8",equipo[8]);
    }

    function mostrarRonda(){
        //var ronda = localStorage.getItem("ronda");
        var ronda = sessionStorage.getItem("ronda");
        var text = document.getElementById("ronda");
        text.innerText = "Ronda "+ronda;
    }

    function mostrarNombres(){
        countdown(localStorage.getItem("hora"),"clock");
        mostrarRonda();
        //api.getPalabra(localStorage.getItem("codigo"),localStorage.getItem("equipo"));

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
            messageText = document.createTextNode(localStorage.getItem("jugador"+i));
            textElement.append(messageText);
            divL.append(textElement);
            textElement = document.createElement('p');
            messageText = document.createTextNode(localStorage.getItem("jugador"+(i+4)));
            textElement.append(messageText);
            divR.append(textElement);
            left.append(divL);
            right.append(divR);
        }
        api.getPalabra(localStorage.getItem("codigo"),sessionStorage.getItem("myTeam"));
    }

    function mostrarPalabra(data){
        var pal = document.getElementById("palabra");
        localStorage.setItem("palabra",data);
        sessionStorage.setItem("palabra",data);
        console.log(sessionStorage.getItem("playerName") +" "+ sessionStorage.getItem("painter"));
        if(sessionStorage.getItem("playerName") == sessionStorage.getItem("painter")){
            pal.innerText = data;
        }else{
          pal.innerText = "";
        }

    }

    return {
        crearSala:crearSala,
        mostrarTabla:mostrarTabla,
        createTable:createTable,
        createTableJugadores:createTableJugadores,
        empezar:function(){
            var codigo = localStorage.getItem("codigo");
            var now  = new Date();
            now.setMinutes(now.getMinutes()+1);
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
