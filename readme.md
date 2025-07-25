# 🧭 Fauno

![Badge](https://img.shields.io/badge/Estado-En%20Desarrollo-orange) ![Badge](https://img.shields.io/badge/Versi%C3%B3n-1.0.0-blue) ![Badge](https://img.shields.io/badge/JavaFX-%F0%9F%92%BB-green)

**Fauno** es un videojuego de exploración en laberintos desarrollado en Java utilizando **JavaFX**, donde el jugador debe sobrevivir, encontrar comida y evitar criaturas hostiles llamadas *Deambulantes* y *Caminadores*. A medida que avanzas de nivel, los escenarios cambian y aumentan en dificultad.

---

## 🎮 Características

* 🎨 Interfaz gráfica basada en **Canvas** para mayor control visual.
* 🧭 Generación aleatoria de laberintos con múltiples estilos: ruinas, túneles, entre otros.
* 🧟‍♂️ Presencia de entidades dinámicas (Deambulantes y Caminadores) que interactúan con el jugador.
* 🍎 Sistema de recolección de comida para mantener la salud.
* 🎵 Música de fondo e interacciones sonoras.
* 💀 Pantalla de "Game Over" personalizada con puntuación basada en niveles recorridos.
* ⌨️ Control del jugador mediante teclado (WASD / Flechas).

---

## 🕹️ Controles

| Tecla | Acción          |
| ----- | --------------- |
| W / ↑ | Mover arriba    |
| A / ← | Mover izquierda |
| S / ↓ | Mover abajo     |
| D / → | Mover derecha   |

---

## 🚀 Cómo ejecutar

### Requisitos:

* JDK 17 o superior
* JavaFX SDK
* IDE compatible con Java (por ejemplo: IntelliJ IDEA, NetBeans, Eclipse)

### Instrucciones:

1. Clona el repositorio:

```bash
git clone https://github.com/senchpimy/Fauno.git
cd fauno
```
2. Asegúrate de tener configurado JavaFX en tu entorno (incluye las librerías en el classpath).
3. Ejecuta `Main.java` desde tu IDE.

> 📝 **Nota**: Los recursos (imágenes y audio) deben mantenerse en la carpeta `media` al mismo nivel que el ejecutable.

---

## 🧠 Conceptos implementados

* Animaciones con `AnimationTimer`
* Manipulación de `Canvas` con `GraphicsContext`
* Control de eventos de teclado (`KeyEvent`)
* Reproducción de música con `MediaPlayer`
* Uso de pilas (`Stack`) para representar elementos dinámicos (como comida)
* Lógica de interacción entre entidades (jugador vs enemigos)

---

## 📸 Capturas (sugerido)

> *(Agrega aquí screenshots del juego en acción: pantalla de inicio, movimiento en el laberinto, pantalla de muerte, etc.)*

---

## 📝 Licencia

Este proyecto está licenciado bajo la **MIT License**.
¡Eres libre de usarlo, modificarlo y compartirlo!
![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)

