# Prácticas de Diseño y Análisis de Algoritmos

Este repositorio contiene una pequeña implementación en Java de un problema de selección de actividades mediante un enfoque voraz.

## Contenido

- `PracticaVoraz/src/practicavoraz/Actividad.java`: representa una actividad con inicio y fin.
- `PracticaVoraz/src/practicavoraz/Algoritmo.java`: implementa el algoritmo voraz.
- `PracticaVoraz/src/practicavoraz/PracticaVoraz.java`: programa principal que lee entradas y muestra la solución.

## ¿Qué resuelve?

El programa toma un conjunto de actividades con intervalos `[inicio, fin]` y devuelve un subconjunto compatible de máxima cardinalidad usando la estrategia greedy:

- ordenar por tiempo de finalización
- seleccionar la actividad actual si es compatible con la anterior

## Cómo ejecutarlo

Desde la raíz del proyecto:

```bash
cd PracticaVoraz
javac -d build/classes src/practicavoraz/*.java
java -cp build/classes practicavoraz.PracticaVoraz
```

También admite lectura desde un fichero de entrada:

```bash
java -cp build/classes practicavoraz.PracticaVoraz entrada.txt
```

## Nota

El repositorio incluye artefactos generados por NetBeans (`build/`, `dist/`, `.class`, `*.jar`), los cuales se ignoran con el fichero `.gitignore` para mantener el proyecto más limpio.
