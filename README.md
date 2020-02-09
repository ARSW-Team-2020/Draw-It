# Draw It

## Integrantes

*   Juan Sebastian Frásica Galeano
*   Yeisson Gualdron Vivas
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

![ER1](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/ER.png)

## Diagrama de clases

## Diagrama de casos de uso

![CA1](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/CU1.JPG)

![CA2](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/CU2.JPG)

![CA3](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/CU3.JPG)

## Historias de usuario

### 1.	Crear una cuenta 

 **Criterios de aceptación:**

*   Se debe poder saber si el nombre de usuario está disponible.

*	No es posible tener más de un usuario con el mismo nombre.

### 2.	Iniciar sesión

 **Criterios de aceptación:**

*	Así como se debe iniciar sesión, debe haber una opción para poder cerrar sesión.

*	Sin poder iniciar sesión no se puede jugar.

### 3.	Añadir un amigo 

 **Criterios de aceptación:**

*	No es posible añadir a un amigo más de una vez.

*	El perfil a añadir debe ser existente.


### 4.	Crear una sala 

 **Criterios de aceptación:**

*	Al momento de crear una sala, se pedirá al creador que genere una clave. Con esa clave, otros jugadores se podrán unir a dicha sala.
*	Se puede crear una sala o unirse a una sala existente.

*	No es posible que el mismo usuario este en diferentes salas.

*	Por sala no pueden haber más de 8 jugadores.

### 5.	Ver mi perfil 

 **Criterios de aceptación:**

*	Poder ver qué nivel soy.

*	Poder ver mis estadísticas en el juego. Ejemplo: partidas ganadas, partidas perdidas, etc.

*	Poder ver cuanta experiencia me falta para poder alcanzar el siguiente nivel. (Barra de progreso).

### 6.	Dibujar

 **Criterios de aceptación:**

*	Tiene que ser en tiempo real.

*   Solamente el pintor puede dibujar, cambiar el color y el grosor del lápiz.

*	Solamente los integrantes de cada equipo podrán ver lo que dibuja su pintor respectivo.

*	Debe haber una opción que haga referencia a la acción de dibujar (un lápiz o marcador).

*	Debe haber una opción para cambiar el grosor del lápiz.

*	Debe haber una opción para poder cambiar el color del lápiz (una paleta de colores).

### 7.	Borrar

 **Criterios de aceptación:**

*	Debe haber una opción para poder borrar lo que se seleccione (un borrador).

*	Se debe poder borrar todo lo que está en el tablero.

*	Solo el pintor puede borrar.

### 8.	 Escribir en el chat de mi equipo 

 **Criterios de aceptación:**

*	Se debe poder ver el registro del chat, es decir, las palabras que los adivinadores han escrito para intentar adivinar las palabras.
*   Cada equipo tendrá su propio chat, y solo podrá ver su chat respectivo.


### 9.	 Ver el tiempo restante de la ronda

 **Criterios de aceptación:**

*	Debe poderse ver la ronda actual en la que está el juego.
*	Se debe poder ver el tiempo restante de la ronda actual (en segundos).


### 10.	 Obtener puntos de experiencia

 **Criterios de aceptación:**

*	Si el jugador está en el equipo ganador ganará 100 de XP, de lo contrario ganará 50 de XP.

### 11.	Conocer los jugadores de la partida finalizada 

 **Criterios de aceptación:**

*	Se debe poder ver el nombre de usuario y el nivel de los demás jugadores.
*	Debe haber una opción para poderlos agregar a la lista de amigos.


## Mockups

### Página de inicio

![1](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/Mockups/P%C3%A1gina_de_inicio.png)

### Registrarse

![2](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/Mockups/Registrarse.png)

### Iniciar sesión

![3](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/Mockups/Iniciar_sesi%C3%B3n.png)

### Juego

![4](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/Mockups/Juego.png)


## Calidad del código
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5777bc2a53ac41a3834545dc942a474b)](https://www.codacy.com/gh/ARSW-Team-2020/Draw-It?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ARSW-Team-2020/Draw-It&amp;utm_campaign=Badge_Grade)

# Descripción del proceso

## Sprint 1

## Sprint 2

## Sprint 3






 
