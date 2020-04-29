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
    });
}

function onConnected() {
    // Subscribe to the Public Topic
    var codigo = sessionStorage.getItem("codigo");
    var equipo = sessionStorage.getItem("myTeam");
    stompClient.subscribe('/topic/'+codigo+'/chat/'+equipo, onMessageReceived);
    stompClient.subscribe('/topic/'+codigo+'/palabra/'+equipo, onPalabraRecieved);
    stompClient.subscribe('/topic/'+codigo+'/ronda/', onRondaRecieved);
    connectDibujar();
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
    Swal.fire({
        title: 'La partida a acabado',
        text: "Los puntajes fueron: ",
        icon: 'success',
        confirmButtonColor: '#3085d6',
        confirmButtonText: 'Ok!'
      }).then((result) => {
        if (result.value) {
            sessionStorage.clear();
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

    if(messageInput.value == palabra && painter != jugador){
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
