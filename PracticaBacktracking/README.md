# Práctica de Backtracking: Robot Tornillo

Este proyecto implementa una solución basada en backtracking para resolver un problema de búsqueda en un tablero donde un robot debe localizar un tornillo (representado por el valor `1`) evitando celdas bloqueadas (`-1`) y desplazándose por celdas libres (`0`).

La práctica forma parte de la asignatura de Diseño y Análisis de Algoritmos (DAA) y tiene como objetivo demostrar la aplicación del algoritmo de vuelta atrás para explorar un espacio de soluciones de forma recursiva.

## Objetivo

El robot empieza en la posición inicial `(0,0)` y debe recorrer el mapa buscando la casilla con tornillo. Si el mapa es navegable, el algoritmo intenta explorar caminos de forma recursiva, marcando las posiciones visitadas y retrocediendo cuando un camino no conduce a la solución.

## Estructura del proyecto

El proyecto está organizado en la carpeta `src` y contiene las siguientes clases principales:

- `RobotTornillo.java`: clase principal que inicia la ejecución.
- `Entrada.java`: lectura de datos desde teclado o fichero.
- `Robot.java`: lógica del robot y algoritmo de backtracking.
- `Mapa.java`: representación del tablero y comprobaciones de accesibilidad.
- `TipoCelda.java`: enumeración para los tipos de celda (`L`, `E`, `T`).
- `Salida.java`: muestra los resultados del recorrido.
- `Test.java`: genera pruebas de complejidad y exporta los resultados a CSV.
- `GestorErrores.java`: gestión de errores y mensajes de aviso.
- `ExcepcionPropia.java`: excepciones personalizadas del proyecto.

## Representación del tablero

Cada celda del tablero puede ser:

- `0`: celda libre
- `-1`: pared / celda bloqueada
- `1`: tornillo

La posición inicial del robot es siempre `(0,0)` y se considera una celda válida siempre que no sea una pared.

## Algoritmo

El enfoque sigue el patrón clásico de backtracking:

1. El robot intenta moverse a una casilla válida y no explorada.
2. Marca la posición como visitada.
3. Comprueba si esa celda contiene el tornillo.
4. Si no lo encuentra, prueba recursivamente las siguientes direcciones posibles.
5. Si una rama falla, retrocede y prueba otra opción.

Este proceso continúa hasta encontrar el tornillo o agotar todas las rutas posibles.

## Ejecución

### Compilar

```bash
cd PracticaBacktracking
javac -d out $(find src -name "*.java")
```

### Ejecutar con entrada por teclado

```bash
java -cp out robottornillo.RobotTornillo
```

### Ejecutar con un fichero de entrada

```bash
java -cp out robottornillo.RobotTornillo entrada.txt
```

## Formato de entrada

El programa admite dos modos:

- Entrada por teclado.
- Lectura desde un fichero con el siguiente formato:

```text
filas columnas
valor_fila_1_columna_1 valor_fila_1_columna_2 ...
valor_fila_2_columna_1 valor_fila_2_columna_2 ...
...
```

Ejemplo:

```text
3 3
0 0 0
0 -1 0
0 0 1
```

En este ejemplo:

- El robot inicia en la esquina superior izquierda.
- Debe alcanzar la posición del tornillo en la última casilla.

## Generación de pruebas de complejidad

La clase `Test` permite evaluar el comportamiento del algoritmo con tableros aleatorios variando:

- tamaño del tablero,
- número de intentos por tamaño,
- probabilidad de pared.

También puede exportar los resultados a un fichero CSV con el nombre indicado por el usuario.

## Observaciones

- La solución está pensada para demostración académica y análisis del algoritmo.
- El proyecto combina lectura de datos, validación, búsqueda con backtracking y análisis experimental.
- El objetivo principal es comprender cómo el backtracking puede resolver un problema de exploración en un espacio de estados parcialmente definido.

## Autor

Proyecto desarrollado como práctica de DAA.

## Licencia

El proyecto se entrega como material académico sin una licencia específica indicada.
