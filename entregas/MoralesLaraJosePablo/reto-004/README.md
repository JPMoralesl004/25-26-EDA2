
## 2Think

1. Proponga un algoritmo que determine si un valor _**k**_ existe en la matriz, **minimizando el número de comparaciones**. Descríbalo con palabras y trácelo sobre la matriz buscando el valor 22.

2. ¿Cuántas comparaciones hace su algoritmo en el mejor caso? ¿Y en el peor caso? Identifique en la matriz un elemento que provoque ese peor caso y trace la búsqueda.
   
3. ¿Existe algún algoritmo que pueda resolver este problema con menos comparaciones en el peor caso? Justifique su respuesta.
---
### Propuesta Algoritmo

Empezar por la esquina superior derecha, `si k = actual --> mueve una columna a la izquierda` (descarta la columna entera).

`Si k = actual --> mueve una fila hacía abajo.
(descarta esa fila) y repite hasta encontrar `k.

```
función buscarEnMatriz(M, k):

    fila ← 1
    col  ← número_de_columnas(M) 

    mientras fila ≤ número_de_filas(M) y col ≥ 1:

        si M[fila][col] == k:
            retornar (fila, col)      

        si no, si k > M[fila][col]:
            fila ← fila + 1          

        si no:
            col ← col - 1           
    retornar NO_ENCONTRADO
```

---

**Traza sobre la matriz buscando k = 22:**

|Iteración|fila|col|M[fila][col]|Comparación|Acción|
|---|---|---|---|---|---|
|1|1|5|21|22 > 21|fila ← 2|
|2|2|5|25|22 < 25|col ← 4|
|3|2|4|17|22 > 17|fila ← 3|
|4|3|4|20|22 > 20|fila ← 4|
|5|4|4|27|22 < 27|col ← 3|
|6|4|3|22|22 == 22|✓ retornar (4, 3)|

---
### Mejor caso

**1 comparación.**

Ocurre cuando `k` es exactamente el elemento de la esquina superior derecha, donde el algoritmo empieza. En esta matriz, ese elemento es el **21**. El algoritmo lo compara una vez y retorna inmediatamente.

---
### Peor caso

**n + m − 1 comparaciones**, donde n = filas y m = columnas.

En esta matriz (5×5): **5 + 5 − 1 = 9 comparaciones**.

Ocurre cuando el algoritmo recorre el máximo de pasos posible: baja todas las filas o se desplaza por todas las columnas sin poder "cortar" antes. El camino más largo posible es bajar (n−1) veces y moverse a la izquierda (m−1) veces, en cualquier orden.

**El elemento que provoca el peor caso en esta matriz es el 19** (fila 5, col 1), o bien buscar un valor que no existe y obligue a recorrer todo ese camino, como **k = 1** o **k = 3**.

Usaremos **k = 19** porque está en la matriz y fuerza el recorrido máximo.

---
### Traza para k = 19 (peor caso)

|It.|fila|col|M[fila][col]|Comparación|Acción|
|---|---|---|---|---|---|
|1|1|5|21|19 < 21|col ← 4|
|2|1|4|14|19 > 14|fila ← 2|
|3|2|4|17|19 > 17|fila ← 3|
|4|3|4|20|19 < 20|col ← 3|
|5|3|3|15|19 > 15|fila ← 4|
|6|4|3|22|19 < 22|col ← 2|
|7|4|2|18|19 > 18|fila ← 5|
|8|5|2|24|19 < 24|col ← 1|
|9|5|1|19|19 == 19|✓ retornar (5,1)|

**9 comparaciones exactas** → confirma el peor caso teórico.

El camino zigzaguea bajando 4 veces y moviéndose a la izquierda 4 veces: 4 + 4 + 1 comparación final = **9**.

---
### ¿Existe un algoritmo mejor?

**No, el algoritmo de la esquina es óptimo.**

Se puede demostrar que cualquier algoritmo que resuelva este problema necesita al menos **n + m − 1** comparaciones en el peor caso. La razón es que existe una "frontera diagonal" entre los elementos menores y mayores que cualquier algoritmo está obligado a cruzar comparando como mínimo n + m − 1 elementos.

Para nuestra matriz 5×5 eso son exactamente **9 comparaciones**, que es justo lo que hace el algoritmo de la esquina. Por tanto, no se puede hacer mejor en el peor caso.

---
## 2Think²

1. Pruebe su algoritmo buscando 21
2. Pruebe su algoritmo buscando 16
3. ¿Existe algún caso concreto donde arrancar del centro sea mejor que arrancar de la esquina? Si no existe, demuéstrelo. Si existe, encuéntrelo.

---
### Pruebe su algoritmo buscando 21

**Traza para k = 21**

|It.|fila|col|M[fila][col]|Comparación|Acción|
|---|---|---|---|---|---|
|1|1|5|21|21 == 21|✓ retornar (1,5)|

**1 sola comparación** → este es el **mejor caso**: k está en la esquina superior derecha donde el algoritmo empieza y por lo tanto termina de inmediato.

---
### Pruebe su algoritmo buscando 16

**Traza para k = 16 (elemento no existente)**

|It.|fila|col|M[fila][col]|Comparación|Acción|
|---|---|---|---|---|---|
|1|1|5|21|16 < 21|col ← 4|
|2|1|4|14|16 > 14|fila ← 2|
|3|2|4|17|16 < 17|col ← 3|
|4|2|3|11|16 > 11|fila ← 3|
|5|3|3|15|16 > 15|fila ← 4|
|6|4|3|22|16 < 22|col ← 2|
|7|4|2|18|16 < 18|col ← 1|
|8|4|1|13|16 > 13|fila ← 5|
|9|5|1|19|16 < 19|col ← 0|

**col = 0** → sale de la matriz → retorna **NO ENCONTRADO**

**9 comparaciones**. El algoritmo termina sin encontrar el elemento, ya que el 16 no existe en la matriz.

---
### ¿Existe algún caso concreto donde arrancar del centro sea mejor que arrancar de la esquina? Si no existe, demuéstrelo. Si existe, encuéntrelo.

Si existe pero solo en casos puntuales:

Desde la esquina superior derecha, cada comparación elimina una fila **o** una columna completa. En el peor caso se hacen n + m − 1 comparaciones.

Desde el centro, cada comparación solo descarta un cuadrante, pero los otros tres siguen siendo candidatos y necesitas comparaciones adicionales para delimitar. No existe una regla de movimiento que garantice descartar siempre una fila o columna completa.

**Conclusión:** arrancar del centro nunca es mejor en el peor caso y en el caso promedio tampoco, porque la ventaja estructural de la esquina (descartar fila o columna entera en cada paso) se pierde desde cualquier otra posición.

---
