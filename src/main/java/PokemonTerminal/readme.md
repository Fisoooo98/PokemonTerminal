# 🎮 Pokémon Terminal

Sistema de combate Pokémon modelado con **UML y Programación Orientada a Objetos** 🐾⚔️  
El proyecto representa batallas, movimientos, items y entrenadores de forma estructurada y modular.

---

## 📌 Características principales

✨ Sistema de combate por turnos  
🔥 Movimientos físicos y especiales  
💊 Movimientos de estado (curaciones y buffs)  
🎒 Uso de objetos en combate  
⚡ Items equipables con efectos automáticos  
📊 Tabla de efectividades por tipo  
📈 Sistema de experiencia y subida de nivel

---

## 🧱 Estructura del Proyecto

El sistema está organizado en módulos claros:

### 🐉 Pokémon
- Stats: vida, ataque, defensa, velocidad
- Tipos (ej: Fuego 🔥, Agua 💧, Planta 🌱)
- Hasta 4 movimientos
- 1 item equipable por Pokémon
- Sistema de nivel y experiencia

---

### ⚔️ Movimientos

**Tipos de movimientos:**

- 🗡️ Ataque → hacen daño
- 💊 Estado → curan o mejoran estadísticas

Cada movimiento tiene:
- Nombre
- Tipo
- Categoría (Físico / Especial)
- Efecto correspondiente

---

### 🎁 Items

Tipos de objetos disponibles:

- 🧴 Curativos (Pociones)
- 🎯 Pokéballs (captura)
- ⚡ Equipables (efectos automáticos en combate)

📌 Solo se puede equipar **un objeto por Pokémon**.

---

### 🧑‍🏫 Entrenadores

- Equipo de hasta 6 Pokémon
- Mochila con objetos
- Puede:
    - Cambiar Pokémon 🔄
    - Usar objetos 💊
    - Capturar Pokémon 🎯

---

## ⚡ Sistema de Eventos (Combate Inteligente)

El sistema usa eventos internos para activar efectos automáticamente.

Ejemplos:
- 🗡️ Cuando un Pokémon ataca
- 💥 Cuando recibe daño
- 💊 Cuando usa movimiento de estado

Esto permite que los **items equipables reaccionen sin código manual extra en cada turno**.

---

## 🧠 Objetivo del Proyecto

Este proyecto busca:

- Practicar UML 📊
- Aplicar herencia y polimorfismo 🧬
- Diseñar un sistema modular y escalable 🏗️
- Simular una batalla Pokémon estructurada ⚔️

---

## 🚀 Posibles mejoras futuras

- 🌍 Más tipos de Pokémon
- 🧪 Estados alterados (veneno, parálisis…)
- 🤖 IA para entrenadores
- 🎨 Interfaz gráfica

---

## 👨‍💻 Autor

Proyecto académico / personal para practicar diseño orientado a objetos.

---

⭐ Si estás empezando en el proyecto:
1. Revisa primero la clase base `Pokemon`
2. Entiende cómo funcionan los movimientos
3. Después mira el sistema de items
4. Finalmente revisa el flujo de combate

---

¡A entrenar se ha dicho! 🏆🔥