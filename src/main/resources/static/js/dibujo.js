var dibujo = (function () {

    var canvas =document.getElementById("myCanvas");
    var lienzo = canvas.getContext("2d");
    var delta  = canvas.getBoundingClientRect();
    var borrar  = document.getElementById("borrar");


    var thisX=0,thisY=0,dibujando=false, color="black",grosor = 1;

    var _author;
    var open = false;
    var currentBlueprint={
        author: null,
        points: []
    };

    function init() {
        borrar.addEventListener('click',defBorrar);
        canvas.addEventListener('mousedown',function (e) {
            thisX=e.clientX - delta.left;
            thisY=e.clientY -delta.top;
            //console.log(x,y);
            dibujando=true;
        });

        canvas.addEventListener('mousemove',function (e) {
            if(dibujando) {
                console.log(thisX,thisY,"antes");
                defGraficar(thisX,thisY,e.clientX-delta.left, e.clientY-delta.top);
                thisX = e.clientX-delta.left;
                thisY = e.clientY -delta.top;
                currentBlueprint.points.push({x:thisX,y:thisY});
                console.log(thisX,thisY,"despues");
            }
        });

        canvas.addEventListener('mouseup',function (e) {
            if(dibujando) {
                defGraficar(thisX,thisY,e.clientX-delta.left, e.clientY-delta.top);
                currentBlueprint.points.push({x:e.clientX-delta.left,y:te.clientY-delta.top});
                thisX = 0;
                thisY = 0;
                dibujando=false;
            }
        });
    }
    function defColor(c ) {
        color = c;
    };

    function defGrosor(g) {
        grosor =g;
    };

    function defBorrar() {
        lienzo.clearRect(0, 0, canvas.width, canvas.height);
    };

    function saveTablero() {
        open=true;
        currentBlueprint.author = document.getElementById("nombreUsuario").value;
        if(currentBlueprint.author == null| currentBlueprint.author==""){
            alert('Debe escribir el nombre del autor');
        }
        else{
            api.saveTablero(currentBlueprint).then(function (){
                getBlueprintsAuthor(_author);
            });
        }


    }


    function defGraficar(x1,y1,x2,y2) {
        lienzo.beginPath();
        lienzo.strokeStyle=color;
        lienzo.lineWidth=grosor;
        lienzo.moveTo(x1,y1);
        lienzo.lineTo(x2,y2);
        lienzo.stroke();
        lienzo.closePath();
    };
    return{
        init:init,
        defGraficar:defGraficar,
        defBorrar:defBorrar,
        defGrosor:defGrosor,
        defColor:defColor,
		saveTablero:saveTablero
    }
})();