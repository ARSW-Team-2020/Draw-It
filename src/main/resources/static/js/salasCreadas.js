function subGeneral() {
    var codigo =  sessionStorage.getItem("codigo");
    stompClient.subscribe('/topic/general/',function (eventbody) {
        if (document.getElementById("table").style.display === "block") {
            $('#table tbody').empty();
            api.getSalas();
        }
    },{id:"general"});
}

function sendGeneral() {
    stompClient.send("/app/general");

}

var stompClient = null;
function connectGeneral() {
    console.info('Connecting to WS...');
    var socket = new SockJS('/stompendpoint');
    socket.onopen = function(event){
        alert("se abrio");
    }
    socket.onclose = function(event){
        alert(event.code);
        alert("se cerro");
    }
    socket.onmessage = function(event){
        alert("Mensaje");
    }
    socket.onerror = function(event){
        alert("hubo error");
    }

    stompClient = Stomp.over(socket);
    sto
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        subGeneral();
    });

};