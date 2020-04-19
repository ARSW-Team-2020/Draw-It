
function connectDibujar() {
    console.info('Connecting to WS dibujar...');
    var socketDibujar = new SockJS('/stompendpoint');
    stompClient = Stomp.over(socketDibujar);
    stompClient.connect({}, function (frame) {
        console.log('dibujar Connected: ' + frame);
        onConnectedDibujar();
    });
}

function onConnectedDibujar() {
    // Subscribe to the Public Topic
    var codigo = localStorage.getItem("codigo");
    var equipo = localStorage.getItem("equipo");
    stompClient.subscribe('/topic/'+codigo+'/dibujar/'+equipo, onArrayReceived);
}
function sendDibujar(thisX1,thisY1,thisX2,thisY2) {
    console.log("Se va a enviar!!!");
    var codigo = localStorage.getItem("codigo");
    var equipo = localStorage.getItem("equipo");
    var line = {
        x1  : thisX1,
        y1 : thisY1,
        x2 : thisX2,
        y2 : thisY2
    };
    console.log("/app/"+codigo+"/chat/"+equipo);
    stompClient.send("/app/"+codigo+"/dibujar/"+equipo, {},JSON.stringify(line));
}
function onArrayReceived(payload) {
    var arrayDibujo = JSON.parse(payload.body);
    //console.log(arrayDibujo);
    dibujar(arrayDibujo.x1,arrayDibujo.y1,arrayDibujo.x2,arrayDibujo.y2 );

}
