var app = (function () {
    

    function crearSala() {
        if ($("#Nombre").val() == "") {
            alert("¡Debes ingresar un nombre!");
            return false
        } else {
            api.crearSala($("#Nombre").val());
        }

    }

    function subSala(){
        var codigo =  localStorage.getItem("codigo");
        stompClient.subscribe('/topic/'+codigo,function (eventbody) {
            var jugadores = JSON.parse(eventbody.body);
            createTableJugadores(jugadores);
        },{id:codigo});
        stompClient.subscribe('/topic/'+codigo+'/empezar',function (eventbody) {
            empezar();
        },{id:codigo+'/empezar'});
        stompClient.send("/app/union/"+codigo);
    }

    function mostrarTabla(){
        if ($("#Nombre").val() == "") {
            alert("¡Debes ingresar un nombre!");
        } else {
            $('#table tbody').empty();
            visible();
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
            var onclick='api.unirJugadorToSala("'+element.codigo+'/'+ $("#Nombre").val()+'")';
            var markup = "<tr> <td>"+ element.autor +"</td> <td>"+element.codigo+"</td><td><a type='button' class='btn-get-started' onclick= "+onclick+">Unirse</a></td> </tr>";
            fila.append(markup)
        })
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

        if(localStorage.getItem("autor")!= null){
            api.getEquipoBySalaAndUsuario(localStorage.getItem("codigo"),localStorage.getItem("autor"))
        }else{
            api.getEquipoBySalaAndUsuario(localStorage.getItem("codigo"),localStorage.getItem("usuario"))
        }
        stompClient.disconnect();
    }



    var stompClient = null;
    var connect = function () {
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            subSala();
        });
    };

    function organizar(equipo){
        location.assign("webApp/juego.html");
        console.log(equipo);
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

    function mostrarNombres(){
        console.log("Hola")
        var left = document.getElementById("leftSide");
        var right = document.getElementById("rightSide");
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

        var eventInterval = setInterval(function(){api.getPalabra(); },5000);


        //var eventInterval = setInterval(function(){api.getPalabra(); },120000); //<-- in milliseconds
    }

    return {
        crearSala:crearSala,
        mostrarTabla:mostrarTabla,
        createTable:createTable,
        createTableJugadores:createTableJugadores,
        empezar:function(){
            var codigo = localStorage.getItem("codigo");
            stompClient.send("/app/"+codigo+"/empezar");
        },
        organizar:organizar,
        connect:connect,
        mostrarNombres:mostrarNombres
    }

})();
