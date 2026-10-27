# DAA_Practicas

Repositorio dedicado a las prácticas de la asignatura de Diseño y Análisis de Algoritmos (DAA). Aquí se recopilan ejercicios y soluciones desarrollados en Java, con un enfoque principal en algoritmos de aproximación greedy y análisis de complejidad.

## Proyecto actual

La práctica principal incluida en este repositorio es la de un algoritmo voraz para seleccionar actividades no solapadas:

- `PracticaAlgoritmoVoraz/`
  - `practicavoraz/`
    - `Actividad.java`: representa cada actividad con su intervalo de inicio y fin.
    - `Algoritmo.java`: implementa la estrategia voraz ordenando por hora de finalización.
    - `PracticaVoraz.java`: programa principal que lee la entrada y muestra la solución.
  - `entrada1.txt`, `entrada2.txt`, `entrada3.txt`: ejemplos de entrada.
  - `salida1.txt`, `salida2.txt`, `salida3.txt`: resultados esperados.
  - `PracticaVoraz.jar`: versión compilada del proyecto.
  - `Practica1_DAA_DanielRF_DiegoCS.pdf`: documento de la práctica con explicación y resultados.

## Descripción de la práctica

El problema resuelto es el clásico de selección de actividades incompatibles:

- Cada actividad tiene un instante de inicio y de fin.
- Dos actividades son compatibles si no se solapan.
- La estrategia greedy consiste en elegir siempre la actividad que finaliza antes para maximizar el número de actividades seleccionadas.

Este enfoque es un ejemplo típico de algoritmo voraz y permite obtener una solución óptima para este problema.

## Estructura del algoritmo

La lógica principal se encuentra en `Algoritmo.voraz(...)`:

1. Ordena las actividades por su hora de finalización.
2. Selecciona la primera actividad.
3. Recorre el resto y añade únicamente aquellas compatibles con la última elegida.
4. Devuelve los índices de las actividades seleccionadas.

## Cómo ejecutar el proyecto

### Opción 1: Ejecutar desde Java

```bash
javac PracticaAlgoritmoVoraz/practicavoraz/*.java
java -cp PracticaAlgoritmoVoraz/practicavoraz practicavoraz.PracticaVoraz PracticaAlgoritmoVoraz/entrada1.txt
```

### Opción 2: Ejecutar el JAR

```bash
java -jar PracticaAlgoritmoVoraz/PracticaVoraz.jar PracticaAlgoritmoVoraz/entrada1.txt
```

Si no se pasa ningún argumento, el programa solicita los datos por teclado.

## Autoría

Este repositorio ha sido desarrollado como parte de la asignatura de Diseño y Análisis de Algoritmos, con la práctica de algoritmo voraz incluida dentro de la carpeta `PracticaAlgoritmoVoraz`.

## Licencia

El proyecto no incluye una licencia explícita en el repositorio. Si vas a reutilizar o distribuir el código, conviene revisar primero si se requiere una licencia específica por parte del autor.
