var stompClient = null;

function connectDibujar() {
    console.info("Connecting to WS dibujar...");
    var socketDibujar = new SockJS("/stompendpoint");
    stompClient = Stomp.over(socketDibujar);
    stompClient.connect({}, function (frame) {
        console.log("dibujar Connected: " + frame);
        onConnectedDibujar();
    },onConnectError);
}

function onConnectedDibujar() {
    // Subscribe to the Public Topic
    var codigo = sessionStorage.getItem("codigo");

    //var equipo = localStorage.getItem("equipo");
    var equipo = sessionStorage.getItem("myTeam");
    stompClient.subscribe('/topic/'+codigo+'/dibujar/'+equipo, onArrayReceived);
    stompClient.subscribe('/topic/'+codigo+'/borrar/'+equipo, onBorrandoReceived);
    stompClient.subscribe('/topic/'+codigo+'/painterName/'+equipo, onPainterNameReceived);
    stompClient.subscribe('/topic/'+codigo+'/round/'+equipo,onRoundReceived );
    stompClient.subscribe('/topic/'+codigo+'/puntaje/'+equipo, onPuntajeReceived);
    stompClient.subscribe('/topic/'+codigo+'/updatePuntaje/'+equipo, onUpdatePuntajeReceived);
    alert(equipo+" "+(equipo%2)+" "+((equipo%2)+1));
    stompClient.subscribe('/topic/'+codigo+'/updatePuntaje/'+((equipo%2)+1), onUpdatePuntajeReceived);
    stompClient.subscribe('/topic/'+codigo+'/palabra/', function (eventbody) {
        console.log(eventbody.body);
        //var eventInterval = setInterval(function(){api.getPalabra(); },5000);
    });
    sendPainter();
    sendPuntaje();
}

function sendPalabra(palabra){
    var codigo = sessionStorage.getItem("codigo");
    stompClient.send("app/"+codigo+"/palabra/", {},JSON.stringify(palabra))

}

function sendDibujar(thisX1,thisY1,thisX2,thisY2, currentColor, currentGrosor) {

    var codigo = sessionStorage.getItem("codigo");
    var equipo = sessionStorage.getItem("myTeam");
    var line = {
        x1  : thisX1,
        y1 : thisY1,
        x2 : thisX2,
        y2 : thisY2,
        color : currentColor,
        grosor : currentGrosor
    };
    stompClient.send("/app/"+codigo+"/dibujar/"+equipo, {},JSON.stringify(line));
}

function sendBorrar() {
    var painter  = sessionStorage.getItem("painter");
    var playername = sessionStorage.getItem("playerName")
    if (painter == playername) {
        var codigo = sessionStorage.getItem("codigo");
        var equipo = sessionStorage.getItem("myTeam");
        var erase = {
            content: "",
        };
        stompClient.send("/app/"+codigo+"/borrar/"+equipo,{},JSON.stringify(erase));
    }
}

function sendPainter() {
    var codigo = sessionStorage.getItem("codigo");
    var equipo = sessionStorage.getItem("myTeam");
    var painter = {
        content: "player",
    };
    stompClient.send("/app/"+codigo+"/painterName/"+equipo,{},JSON.stringify(painter));
}
function sendRound() {
    var codigo = sessionStorage.getItem("codigo");
    var equipo = sessionStorage.getItem("myTeam");
    var round = {
        content: "round",
    };
    stompClient.send("/app/"+codigo+"/round/"+equipo,{},JSON.stringify(round));
    var puntaje = {
        content: "10",
        sender: equipo
    };
    stompClient.send("/app/"+codigo+"/updatePuntaje/"+equipo,{},JSON.stringify(puntaje));
}

function sendPuntaje(){
    var codigo = sessionStorage.getItem("codigo");
    var equipo = sessionStorage.getItem("myTeam");
    stompClient.send("/app/"+codigo+"/puntaje/"+equipo);
}

function onArrayReceived(payload) {
    var arrayDibujo = JSON.parse(payload.body);
    dibujar(arrayDibujo.x1,arrayDibujo.y1,arrayDibujo.x2,arrayDibujo.y2,arrayDibujo.color, arrayDibujo.grosor );
}
function onBorrandoReceived(payload) {
    var message = JSON.parse(payload.body);
    defBorrar();
}

function onPainterNameReceived(payload) {
    var painterName = JSON.parse(payload.body);

    defBorrar();
    console.log("Este es el pintor de tu equipo");
    console.log(painterName.content);
    setPainterNameAndDrawName(painterName.content);
    avanzarPalabra();
}


function onRoundReceived(payload) {
    var round = JSON.parse(payload.body);
    console.log(round);
    sendPainter();
}

function onPuntajeReceived(payload){
    var puntaje = payload.body;
    setPuntajeEquipo(puntaje);
}

function onUpdatePuntajeReceived(payload){
    var puntaje = JSON.parse(payload.body);
    setPuntajeEquipo(puntaje.content,puntaje.sender);
}
