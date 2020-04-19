var canvas =document.getElementById("myCanvas");
var lienzo = canvas.getContext("2d");
var delta  = canvas.getBoundingClientRect();
var borrar  = document.getElementById("borrar");
borrar.addEventListener('click',defBorrar);
//console.log(delta);
//console.log(localStorage.getItem("jugador1"));

var x=0,y=0,dibujando=false, color="black",grosor = 1;


function defColor(c ) {
    color = c;
}

function defGrosor(g) {
    grosor =g;
}

function defBorrar() {
    lienzo.clearRect(0, 0, canvas.width, canvas.height);
}

canvas.addEventListener('mousedown',function (e) {
    x=e.clientX - delta.left;
    y=e.clientY -delta.top;
    //console.log(x,y);
    dibujando=true;
});

canvas.addEventListener('mousemove',function (e) {
    if(dibujando) {
        //dibujar(x,y,e.clientX-delta.left, e.clientY-delta.top);
        sendDibujar(x,y,e.clientX-delta.left,e.clientY-delta.top);
        x = e.clientX-delta.left;
        y = e.clientY -delta.top;
    }
});

canvas.addEventListener('mouseup',function (e) {
    if(dibujando) {
        //dibujar(x,y,e.clientX-delta.left, e.clientY-delta.top);
        sendDibujar(x,y,e.clientX-delta.left,e.clientY-delta.top);
        x = 0;
        y = 0;
        dibujando=false;
    }
});

function dibujar(x1,y1,x2,y2) {

    lienzo.beginPath();
    lienzo.strokeStyle=color;
    lienzo.lineWidth=grosor;
    lienzo.moveTo(x1,y1);
    lienzo.lineTo(x2,y2);
    lienzo.stroke();
    lienzo.closePath();
}
