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

El juego consiste en que 2 equipos, de 4 jugadores cada uno, se enfrentan entre sí para competir por quién es el mejor **pintor** y **adivinador**. Cada partida tiene 5 rondas, con una duración aproximada de 2 minutos cada una.


Una persona al azar de cada equipo empezará siendo **pintor**, y tendrá que dibujar la palabra que se le indique mientras que los demás integrantes de cada equipo tratarán de descubrirla.     

Los equipos solomente podrán visualizar lo que el **pintor** de su equipo está dibujando y una vez que la palabra sea adivinada, se cambiará de **pintor** y de palabra a dibujar.   

El equipo ganador de la ronda será aquel que adivine más palabras hasta que se acabe el tiempo.   

Finalmente, el equipo ganador de la partida será el que gane 3 de 5 rondas.    


## Valores agregados y diferenciadores de la aplicación con respecto a Pinturillo

*   Dos equipos de cuatro integrantes cada uno.
*   Se hace rotación de pintores por cada palabra adivinada en cada equipo.
*   Dos jugadores pintando al mismo tiempo.


# Arquitectura y diseño detallado de la aplicación

## Diagrama de casos de uso

![](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/AntesCU.PNG)

![](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/DuranteCU.PNG)

![](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/DespuesCU.PNG)


## Historias de usuario

### 1.	Crear una sala 

COMO Jugador
QUIERO Crear una sala
PARA PODER invitar a amigos 

 **Criterios de aceptación:**

*	Al momento de crear una sala, se pedirá al creador que genere una clave. Con esa clave, otros jugadores se podrán unir a dicha sala.
*	Se puede crear una sala o unirse a una sala existente.

*	No es posible que el mismo usuario este en diferentes salas.

*	Por sala no pueden haber más de 8 jugadores.


### 2.	Dibujar

COMO Jugador pintor
QUIERO Dibujar
PARA PODER pintar la palabra indicada.

 **Criterios de aceptación:**

*	Tiene que ser en tiempo real.

* Solamente el pintor puede dibujar, cambiar el color y el grosor del lápiz, borrar la ultima acción o borrar algo en específico.

*	Solamente los integrantes de cada equipo podrán ver lo que dibuja su pintor respectivo.

*	Debe haber una opción que haga referencia a la acción de dibujar (un lápiz o marcador).

*	Debe haber una opción para cambiar el grosor del lápiz.

*	Debe haber una opción para poder cambiar el color del lápiz (una paleta de colores).

* Debe haber una opción para poder borrar (un borrador).

### 3.	Reiniciar el tablero

Como jugador pintor
Quiero reiniciar el tablero
Para poder volver a comenzar con mi dibujo.


 **Criterios de aceptación:**

*	Debe haber una opción para poder reiniciar el tablero al estado original.

*	Solo el pintor puede borrar.


### 4.	 Escribir en el chat 
COMO Jugador adivinador
QUIERO Escribir en el chat 
PARA PODER adivinar la palabra que el pintor esté dibujando.


 **Criterios de aceptación:**

*	Se debe poder ver el registro del chat, es decir, las palabras que los adivinadores han escrito para intentar adivinar las palabras.


### 5.	 Ver el estado de la partida

COMO Jugador pintor y adivinador
QUIERO Ver el estado de la partida
PARA PODER conocer cuanto tiempo falta, y quien va ganando.

 **Criterios de aceptación:**

*	Debe poderse ver la ronda actual en la que está el juego.

*	Se debe poder ver el tiempo restante de la ronda actual (en segundos).

* Se debe poder conocer cuando un equipo adivinó la palabra.

* Se debe poder conocer quien va ganando la partida.


### 6.	Ver un resumen de la partida

COMO Jugador 
QUIERO Ver un resumen de la partida
PARA PODER conocer cual fue el equipo ganador.

 **Criterios de aceptación:**

*	Al finalizar la partida se debe poder ver el nombre de los demás jugadores con sus palabras adivinadas.

* Se debe mostrar el equipo ganador.

* Debe haber una opción para volver a jugar.


## Mockups

### Página de inicio
![inicio](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/Mockups%20nuevos/inicio.jpg)


### Juego

![2](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/Mockups%20nuevos/2.jpg)

![3](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/Mockups%20nuevos/3.png)

![4](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/Mockups%20nuevos/4.jpg)

## Modelo E-R (Entidad-Relación)

![ER1](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/EntidadRelacion.png)

## Diagrama de clases

![clases](https://github.com/ARSW-Team-2020/Draw-It/blob/master/img/clases.PNG)

## Calidad del código
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5777bc2a53ac41a3834545dc942a474b)](https://www.codacy.com/gh/ARSW-Team-2020/Draw-It?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ARSW-Team-2020/Draw-It&amp;utm_campaign=Badge_Grade)

# Descripción del proceso

## Sprint 1

## Sprint 2

## Sprint 3






 
