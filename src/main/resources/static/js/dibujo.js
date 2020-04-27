const canvas =document.getElementById("myCanvas");
const lienzo = canvas.getContext("2d");
var delta  = canvas.getBoundingClientRect();
var player =  sessionStorage.getItem("playerName");
var painter = "painter";
console.log("PLAYER ");
console.log(player);


var x=0,y=0,dibujando=false, color="black",grosor = 3;


function defColor(c ) {
    color = c;
}

function defGrosor(g) {
    grosor =g;
}

function setName(name ) {
    player = name;
    console.log(name);
}

function setPainterName(name) {
    painter =name;
    console.log("key: painter, value: ");
    console.log(localStorage.getItem(painter));

    drawName();
}

function drawName() {

    lienzo.font = "caption";
    lienzo.strokeText(painter, 10, 30);
}

function defBorrar() {
    lienzo.clearRect(0, 0, canvas.width, canvas.height);
    drawName();
}

canvas.addEventListener('mousedown',function (e) {
    if(player==painter){
        x=e.clientX - delta.left;
        y=e.clientY -delta.top; 
        //console.log(x,y);

        dibujando=true;
    }

});

canvas.addEventListener('mousemove',function (e) {
    if(dibujando) {
        //dibujar(x,y,e.clientX-delta.left, e.clientY-delta.top);
        sendDibujar(x,y,e.clientX-delta.left,e.clientY-delta.top,color,grosor);
        x = e.clientX-delta.left;
        y = e.clientY -delta.top;
    }
});

canvas.addEventListener('mouseup',function (e) {
    if(dibujando) {
        //dibujar(x,y,e.clientX-delta.left, e.clientY-delta.top);
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
