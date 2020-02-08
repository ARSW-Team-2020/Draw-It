# Draw It

## Integrantes

*   Yeison Gualdron Vivas
*   Juan Sebastian Frásica Galeano
*   Carlos Julian Gomez Ardila

## Resumen
Draw it es un videojuego multijugador realizado como proyecto para la materia **Arquitecturas de Software (ARSW)** de la Escuela Colombiana de Ingeniería Julio Garavito.

La temática del juego está basada en uno existente llamado **Pinturillo**, el cual consiste en que un jugador dibuja una palabra y los demás adivinan qué palabra es.

Sin embargo, **Draw it** cuenta con una dinámica diferente y con algunas reglas de juego adicionales, y está implementado para que además de ser un juego en **tiempo real** también cuente con características de **concurrencia** y **paralelismo**.

 
## Descripción del juego

### Jugabilidad

El juego consiste en que 2 equipos, de 4 jugadores cada uno, se enfrentan entre sí para competir por quién es el mejor **pintor** y **adivinador**. Cada partida tiene 3 rondas, con una duración aproximada de 2 minutos cada una.

### Antes de iniciar...
Para poder jugar, es necesario realizar una debida autenticación.    


### El juego...

Una persona al azar de cada equipo empezará siendo **pintor**, y tendrá que dibujar la palabra que se le indique mientras que los demás integrantes de cada equipo tratarán de descubrirla.     

Los equipos solomente podrán visualizar lo que el **pintor** de su equipo está dibujando y los intentos de palabras desacertadas de los integrantes del mismo.   

Una vez que la palabra sea adivinada, se cambiará de **pintor** y de palabra a dibujar.   

El equipo ganador de la ronda será aquel que adivine más palabras hasta que se acabe el tiempo.   

Finalmente, el equipo ganador de la partida será el que gane 2 de 3 rondas.    

En caso de que se llegue a una tercera ronda (desempate), 2 jugadores por cada equipo tendrán que dibujar. Está será la ronda más corta, debido a que el primer equipo que adivine la palabra ganará el juego.


### Al finalizar la partida...

Cada jugador ganará experiencia en su cuenta (dependerá de si ganó o perdió la partida), la cual le permite avanzar de nivel. Además podrá agregar a otros jugadores a su lista de amigos para seguir jugando con ellos.



## Valores agregados y diferenciadores de la aplicación con respecto a Pinturillo

*   Dos equipos de cuatro integrantes cada uno.
*   Se hace rotación de pintores por cada palabra adivinada.
*   Listas de palabras generadas aleatoriamente.
*   Opciones para cambiar el idioma.
*   En caso de ir en empate, dos integrantes dibujarán la palabra.
*   Para poder jugar se debe estar registrado.


# Arquitectura y diseño detallado de la aplicación

## Modelo E-R (Entidad-Relación)

## Diagrama de clases

## Diagrama de casos de uso

## Historias de usuario

### 1.	Crear una cuenta 

 **Criterios de aceptación:**

•	Se debe poder saber si el nombre de usuario está disponible.

•	No es posible tener más de un usuario con el mismo nombre.

### 2.	Iniciar Sesión

 **Criterios de aceptación:**

•	Así como se debe iniciar sesión se debe poder cerrar sesión.

•	Sin poder iniciar sesión no se puede jugar.

### 3.	Añadir un amigo 

 **Criterios de aceptación:**

•	No es posible añadir a un amigo más de una vez.

•	El perfil debe ser existente.

•	Se debe poder ver el perfil del usuario al que se va a añadir.

### 4.	Crear una sala 

 **Criterios de aceptación:**

•	No es posible invitar a la sala a alguien que no esté conectado.

•	No es posible que el mismo usuario este en diferentes salas.

•	Por sala no pueden haber más de 8 jugadores.

### 5.	Ver mi perfil 

 **Criterios de aceptación:**

•	Poder ver qué nivel soy.

•	Poder ver mis estadísticas en el juego. Ejemplo: partidas ganadas, partidas perdidas, etc.

•	Poder ver cuanta experiencia me falta para poder alcanzar el siguiente nivel. (Barra de progreso)

### 6.	Dibujar

 **Criterios de aceptación:**

•	Tiene que ser en tiempo real.

•	Solamente el pintor puede dibujar, cambiar el color y el grosor del lápiz.

•	Solamente los del equipo pueden ver lo que dibuja el pintor.

•	Debe haber una opción que referencia la acción de dibujar (un lápiz o marcador).

•	Debe haber una opción para cambiar el grosor del lápiz.

•	Debe haber una opción de cambio de color del lápiz (una paleta de colores).

### 7.	Borrar

 **Criterios de aceptación:**

•	Debe haber una opción para poder borrar (un borrador).

•	Se debe poder borrar todo lo que está en el tablero.

•	Solo puede borrar el pintor.

### 8.	 Escribir en el chat de mi equipo 

 **Criterios de aceptación:**

•	Ver el registro del chat.

•	Poder ver los comentarios previos de otros jugadores.

•	No será posible ver las palabras adivinas en el chat.

•	Los comentarios solo serán vistos por el equipo que los hizo.

### 9.	 Ver el tiempo restante de la ronda

 **Criterios de aceptación:**

•	Se debe poder ver el tiempo restante de la ronda, la ronda actual y los puntos conseguidos hasta el momento.

### 10.	 Obtener puntos de experiencia

 **Criterios de aceptación:**

•	Solo es posible ver mi nivel actual, cuantos puntos gané en la última partida y cuanto me falta para poder avanzar al siguiente nivel.

### 11.	Conocer los jugadores de la partida finalizada 

 **Criterios de aceptación:**

•	Se debe poder ver el perfil y el nivel de los demás jugadores.

•	Se debe poder agregarlos a mi lista de amigos.

## Mockups

### Pagina de inicio

![](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/Mockups/P%C3%A1gina_de_inicio.png)

### Registro

![](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/Mockups/Registrarse.png)

### Iniciar sesión

![](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/Mockups/Iniciar_sesi%C3%B3n.png)

### Juego

![](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/Mockups/Juego.png)

# Descripción del proceso

## Sprint 1

## Sprint 2

## Sprint 3






 
