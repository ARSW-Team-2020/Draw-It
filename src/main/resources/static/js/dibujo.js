var canvas =document.getElementById("myCanvas");
const lienzo = canvas.getContext("2d");

var player =  sessionStorage.getItem("playerName");
var painter = "painter";
var team = 0;


var x=0,y=0,dibujando=false, color="black",grosor = 3;


function defColor(c ) {
    color = c;
}

function defGrosor(g) {
    grosor =g;
}

function getPainter(){
    sendPainter();
}

function setPuntajeEquipo(newPuntaje,equipo){
    if(equipo == sessionStorage.getItem("myTeam")){
        var puntajeEquipo = document.getElementById("puntosL");
        puntajeEquipo.innerText = "Puntos: "+newPuntaje;
    }else{
        var puntajeEquipo = document.getElementById("puntosR");
        puntajeEquipo.innerText = "Puntos: "+newPuntaje;
    }
}

function setPainterNameAndDrawName(name) {
    if (painter != name) {
        painter = name;
        sessionStorage.setItem("painter",painter);
        console.log("key: painter, value: ");
        console.log(sessionStorage.getItem("painter"));
        lienzo.clearRect(0, 0, canvas.width, canvas.height);
        drawName();
        toastr["warning"](painter+" Va a dibujar","¡Atentos!");
    }
}


function defBorrar() {
    lienzo.clearRect(0, 0, canvas.width, canvas.height);
    drawName();
}

function drawName(){
    lienzo.font = "caption";
    lienzo.strokeText(painter, 10, 30);
}

canvas.addEventListener('mousedown',function (e) {
    canvas = document.getElementById("myCanvas");
    delta = canvas.getBoundingClientRect();
    if(player==painter){
        var delta  = canvas.getBoundingClientRect();
        x=e.clientX - delta.left;
        y=e.clientY - delta.top;
        dibujando=true;
    }

});

canvas.addEventListener('mousemove',function (e) {
    canvas = document.getElementById("myCanvas");
    delta = canvas.getBoundingClientRect();
    if(dibujando) {
        //dibujar(x,y,e.clientX-delta.left, e.clientY-delta.top);
        var delta  = canvas.getBoundingClientRect();
        sendDibujar(x,y,e.clientX-delta.left,e.clientY-delta.top,color,grosor);
        x = e.clientX-delta.left;
        y = e.clientY -delta.top;
    }
});

canvas.addEventListener('mouseup',function (e) {
    canvas = document.getElementById("myCanvas");
    delta  = canvas.getBoundingClientRect();
    if(dibujando) {
        //dibujar(x,y,e.clientX-delta.left, e.clientY-delta.top);
        var delta  = canvas.getBoundingClientRect();
        sendDibujar(x,y,e.clientX-delta.left,e.clientY-delta.top,color,grosor);
        x = 0;
        y = 0;
        dibujando=false;
    }
});

function dibujar(x1,y1,x2,y2,color,grosor) {

    lienzo.beginPath();
    lienzo.strokeStyle=color;
    lienzo.lineWidth=grosor;
    lienzo.moveTo(x1,y1);
    lienzo.lineTo(x2,y2);
    lienzo.stroke();
    lienzo.closePath();

}
