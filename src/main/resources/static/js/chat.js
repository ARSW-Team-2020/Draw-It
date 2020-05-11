'use strict';

var chatPage = document.querySelector('#chat-page');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');

var stompClient = null;

function connect() {
    console.info('Connecting to WS...');
    var socket = new SockJS('/stompendpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        onConnected();
    },onConnectError);
}

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

function onConnected() {
    // Subscribe to the Public Topic
    var codigo = sessionStorage.getItem("codigo");
    var equipo = sessionStorage.getItem("myTeam");
    stompClient.subscribe('/topic/'+codigo+'/chat/'+equipo, onMessageReceived);
    stompClient.subscribe('/topic/'+codigo+'/palabra/'+equipo, onPalabraRecieved);
    stompClient.subscribe('/topic/'+codigo+'/ronda/', onRondaRecieved);
    stompClient.subscribe('/topic/'+codigo+'/salir/', onSalir);
    connectDibujar();
}

function onSalir(payload) {
    toastr["error"]("¡"+payload.body+"! ha salido!","Oops!");
}

function salir() {
    location.assign("../index.html");
}
function sendSalir() {
    var playerName = sessionStorage.getItem("playerName");
    var codigo = sessionStorage.getItem("codigo");
    var painter = sessionStorage.getItem("painter");
    var equipo = sessionStorage.getItem("myTeam");
    if (painter == playerName){
        sendRound();
    }
    stompClient.send("/app/"+codigo+"/salir/"+equipo,{},playerName);
}

function onPalabraRecieved(payload){
    app.mostrarPalabra(payload.body);
}

function onRondaRecieved(payload){
    if(payload.body != "-"){
        cancelar();
        var ronda = sessionStorage.getItem("ronda");
        sessionStorage.setItem("ronda",parseInt(ronda,10)+1);
        app.mostrarRonda();
        console.log("solicitar pintor");
        sendPainter();
        countdown(payload.body,"clock");
    }
}

function avanzarRonda(){
    var codigo = sessionStorage.getItem("codigo");
    var ronda  = sessionStorage.getItem("ronda");
    if (ronda == "5"){
        mostrarFinal();
    }else{
        var now  = new Date();
        now.setMinutes(now.getMinutes()+2);
        stompClient.send("/app/"+codigo+"/ronda/"+ronda,{},now.toString());
    }
}

function mostrarFinal(){
    var text;
    var icono;
    var puntos1 = document.getElementById("puntosL").innerText;
    var puntos2 = document.getElementById("puntosR").innerText;
    if (puntos1 == puntos2){
        text = "¡Empate!";
        icono = "question";
    }else if (puntos1 > puntos2){
        text = "¡Has ganado!";
        icono = "success";
    }else{
        text = "Has perdido!";
        icono = "error";
    }
    Swal.fire({
        title: text,
        text: 'Dale ok para salir!',
        icon: icono,
        confirmButtonColor: '#3085d6',
        confirmButtonText: 'Ok!'
      }).then((result) => {
        if (result.value) {
            location.assign("../index.html");
        }
      })
}

function avanzarPalabra(){
    var codigo = sessionStorage.getItem("codigo");
    var equipo = sessionStorage.getItem("myTeam");
    stompClient.send("/app/"+codigo+"/palabra/"+equipo);
}

function send() {
    var codigo = sessionStorage.getItem("codigo");
    var equipo = sessionStorage.getItem("myTeam");
    var palabra = sessionStorage.getItem("palabra");
    var jugador = sessionStorage.getItem("playerName");
    var painter = sessionStorage.getItem("painter");

    if(messageInput.value.toLocaleLowerCase() == palabra.toLocaleLowerCase() && painter != jugador){
        //ademas de avanzar la palabra avanzamos tambien el jugador
        sendRound();
        toastr["success"]("Has acertado","¡Correcto!");

    }else{
        var chatMessage = {
            content: messageInput.value,
            sender: jugador
        };
        if (messageInput.value != ""){
            console.log("/app/"+codigo+"/chat/"+equipo);
            stompClient.send("/app/"+codigo+"/chat/"+equipo, {},JSON.stringify(chatMessage));
        }
    }
    messageInput.value = "";
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    var messageElement = document.createElement('li');
    messageElement.classList.add('chat-message');

    var usernameElement = document.createElement('span');
    var usernameText = document.createTextNode(message.sender);
    usernameElement.appendChild(usernameText);
    messageElement.appendChild(usernameElement);

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);
    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

$("#message").keyup(function(event) {
    if (event.keyCode === 13) {
        $("#botonChat").click();
    }
});