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

    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        subGeneral();
    },onConnectError);
    stompClient.debug = null;
};

function onConnectError(message){
    Swal.fire({
        title: 'Whoops!',
        text: "¡Hubo un problema para conectar al servidor!",
        icon: 'error',
        confirmButtonColor: '#3085d6',
        confirmButtonText: 'Refrescar!'
    }).then((result) => {
        if (result.value) {
            location.reload();
        }
    })
}