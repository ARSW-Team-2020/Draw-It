var app = (function () {

    function ingresarNombre() {
        if ($("#Nombre").val() == "") {
            alert("¡Debes ingresar un nombre!");
            return false
        } else {

        }

    }

    return {
        ingresarNombre: ingresarNombre

    }
})();
