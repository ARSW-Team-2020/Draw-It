const getRemainingTime = deadline => {
    let now = new Date(),    
        remainTime = (new Date(deadline) - now + 1000) / 1000,
        remainSeconds = ('0' + Math.floor(remainTime % 60)).slice(-2),
        remainMinutes = ('0' + Math.floor(remainTime / 60 % 60)).slice(-2),
        remainHours = ('0' + Math.floor(remainTime / 3600 % 24)).slice(-2),
        remainDays = Math.floor(remainTime / (3600 * 24));

    return {
        remainSeconds,
        remainMinutes,
        remainHours,
        remainDays,
        remainTime
    }
};
var timerUpdate;
var el;
var enviado;
enviado = false;
const countdown = (deadline,elem) => {
    el = document.getElementById(elem);
    enviado = false;
    timerUpdate = setInterval( () => {
        let t = getRemainingTime(deadline);
        el.innerHTML = `Tiempo de ronda: ${t.remainMinutes}:${t.remainSeconds}`;

        if(t.remainTime <= 1 && !enviado) {
            terminar();
        }else if (t.remainTime <= 1 && enviado){
            cancelar();
        }
    }, 1000)
};

function terminar(){
    clearInterval(timerUpdate);
    enviado = true;
    avanzarRonda();
}

function cancelar(){
    clearInterval(timerUpdate);
}