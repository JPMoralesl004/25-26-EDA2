# Criptoaritmética — Solver por Backtracking

## ¿Qué es la Criptoaritmética?

La **criptoaritmética** (o criptosuma) es un tipo de puzzle matemático donde cada letra representa un dígito único del 0 al 9, y el objetivo es descubrir qué dígito corresponde a cada letra para que la ecuación aritmética sea correcta.

**Ejemplo clásico:**
```
  FORTY
+   TEN
+   TEN
-------
  SIXTY
```

---

## Descripción del Código

Este programa resuelve puzzles de criptoaritmética mediante la técnica de **backtracking recursivo**. Dado un conjunto de palabras que forman una suma, el algoritmo prueba sistemáticamente todas las combinaciones posibles de dígitos para cada letra hasta encontrar aquellas que satisfacen la ecuación.

### Puzzles que resuelve por defecto

| Ecuación                    | Expresión            |
|-----------------------------|----------------------|
| `ODD + ODD = EVEN`          | Dos sumandos         |
| `FORTY + TEN + TEN = SIXTY` | Tres sumandos        |

---

## Estructura del Proyecto

```
Criptoaritmetica.java
│
├── main()                    → Punto de entrada; lanza los acertijos
├── ejecutarAcertijo()        → Prepara los datos y arranca el backtracking
├── backtracking()            → Núcleo recursivo del algoritmo ⬅️ (ver abajo)
├── validarSuma()             → Comprueba si la asignación actual es correcta
├── convertir()               → Convierte una palabra a su número según el mapa
├── extraerLetrasUnicas()     → Obtiene las letras distintas de la ecuación
├── esInicial()               → Detecta si una letra es inicial de alguna palabra
└── imprimirResultado()       → Muestra la solución encontrada
```

---

## El Algoritmo: Backtracking Recursivo

El método central es `backtracking()`. Su lógica consiste en asignar dígitos a las letras una a una, y si en algún punto se viola una restricción, **retrocede** (`backtrack`) y prueba con otro dígito.

### Firma del método

```java
private static void backtracking(int idx, String letras, boolean[] usados,
                                 Map<Character, Integer> mapa, String[] p)
```

| Parámetro | Significado                                              |
|-----------|----------------------------------------------------------|
| `idx`     | Índice de la letra que se está asignando en este momento |
| `letras`  | Cadena con todas las letras únicas de la ecuación        |
| `usados`  | Array de 10 booleanos; marca qué dígitos ya están en uso |
| `mapa`    | Asignación actual de letra → dígito                      |
| `p`       | Las palabras de la ecuación (sumandos + resultado)       |

---

### Caso Base

```java
if (idx == letras.length()) {
    if (validarSuma(p[0], p[1], p[2], p[3], mapa)) {
        imprimirResultado(p[0], p[1], p[2], p[3], mapa);
    }
    return;
}
```

**¿Cuándo se ejecuta?**
Cuando `idx` llega al total de letras únicas, significa que **ya se ha asignado un dígito a cada letra**. En ese momento se verifica si la suma es correcta con `validarSuma()`. Si lo es, se imprime la solución. Luego se hace `return` para volver al nivel anterior y seguir explorando otras combinaciones.

---

### Caso Recursivo

```java
char letraActual = letras.charAt(idx);

for (int d = 0; d <= 9; d++) {
    if (!usados[d] && !(d == 0 && esInicial(letraActual, p))) {
        mapa.put(letraActual, d);       // 1. Asignar dígito
        usados[d] = true;

        backtracking(idx + 1, letras, usados, mapa, p);  // 2. Recursión

        usados[d] = false;             // 3. Deshacer (backtrack)
        mapa.remove(letraActual);
    }
}
```

**¿Qué hace en cada iteración?**

1. **Elige** la letra en la posición `idx`.
2. **Prueba** cada dígito del 0 al 9, respetando dos restricciones:
   - El dígito no puede estar ya en uso (`!usados[d]`).
   - Si la letra es la **inicial de alguna palabra**, no puede ser `0` (no hay números con ceros a la izquierda).
3. **Asigna** el dígito y marca como usado.
4. **Llama a sí misma** con `idx + 1` para asignar el siguiente dígito.
5. Al regresar, **deshace la asignación** (`usados[d] = false` y `mapa.remove(...)`) para explorar las siguientes posibilidades.

---

### Árbol de Exploración (simplificado para `ODD + ODD = EVEN`)

```
Letras únicas: O, D, E, V, N
                     │
          Asignar O = 0..9
         /      |       \
    O=1        O=2  ...  O=9
     │          │
  Asignar D = 0..9 (≠ O)
   / | \
D=0 D=2 ...
         │
      Asignar E ...
              │
           ...hasta N
                   │
           ¿ODD+ODD==EVEN? → Sí: imprimir / No: retroceder
```

---

## Restricciones del Algoritmo

| Restricción                          | Dónde se aplica              |
|--------------------------------------|------------------------------|
| Cada dígito se usa como máximo 1 vez | Array `usados[]`             |
| Letras iniciales no pueden ser `0`   | Método `esInicial()`         |
| La suma debe ser aritméticamente correcta | Método `validarSuma()` |

---

## Cómo Ejecutar

```bash
# Compilar
javac Criptoaritmetica.java

# Ejecutar
java Criptoaritmetica
```

### Salida esperada (ejemplo)

```
Solución:
O = 5
D = 1
E = 2
V = 8
N = 3
ODD = 511
EVEN = 1022
------
```

---

## Añadir Nuevos Puzzles

Llama a `ejecutarAcertijo()` desde `main()` con tus palabras. El tercer parámetro es opcional (puede ser `null` si solo hay dos sumandos):

```java
// Dos sumandos:  SEND + MORE = MONEY
ejecutarAcertijo("SEND", "MORE", null, "MONEY");

// Tres sumandos: BASE + BALL + GAMES = PLAYED
ejecutarAcertijo("BASE", "BALL", "GAMES", "PLAYED");
```

---

## Complejidad

En el **peor caso**, el algoritmo explora todas las permutaciones de 10 dígitos tomados de `n` en `n` (donde `n` es el número de letras únicas):

```
O(10! / (10 - n)!)
```

Para `n = 10` letras únicas, esto equivale a **3.628.800 combinaciones** posibles. Las restricciones de letras iniciales y dígitos usados reducen considerablemente el espacio de búsqueda en la práctica.