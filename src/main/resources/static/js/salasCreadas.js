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
    console.log(app.getPlayer());
    console.info('Connecting to WS...');
    var socket = new SockJS('/stompendpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        subGeneral();
    });
};