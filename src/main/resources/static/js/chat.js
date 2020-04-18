

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
    var codigo = localStorage.getItem("codigo");
    var equipo = localStorage.getItem("equipo");
    stompClient.subscribe('/topic/'+codigo+'/chat/'+equipo, onMessageReceived);
}

function send() {
    console.log("Se va a enviar!!!");
    var codigo = localStorage.getItem("codigo");
    var equipo = localStorage.getItem("equipo");
    var chatMessage = {
        content: messageInput.value,
    };
    console.log("/app/"+codigo+"/chat/"+equipo);
    stompClient.send("/app/"+codigo+"/chat/"+equipo, {},JSON.stringify(chatMessage));
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    var messageElement = document.createElement('li');
    messageElement.classList.add('chat-message');

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