package org.ies.tierno.contador;

public class Contador {

    private int valor = 0;

    // Ejer2 - synchronized
    public synchronized void incrementar() {
        valor++;
    }

    public int getValor() {
        return valor;
    }
}

/*
Anota los resultados:
----------|------------------------------------------------------------|
Ejecución |        1       |       2       |       3       |  4  |  5  |  6  |  7  |  8  |  9  |  10  |
----------|----------------|---------------|---------------|-----|-----|-----|-----|-----|-----|------|
Resultado |     124.532    |    141.209    |    118.940    |     |     |      |
----------|----------------|---------------|---------------|-----|-----|-----|-----|------|


Responde:
1. ¿Qué valor deberías obtener si los dos hilos hicieran bien su trabajo?
200.000, porque hay 2 hilos y cada uno ejecuta la función de incrementar 100.000 veces (100.000 x 2 = 200.000)

2. ¿Has obtenido ese valor alguna vez? ¿Se repite el mismo resultado en todas las ejecuciones?
No, casi nunca se obtiene ese valor y el resultado cambia en cada ejecución (por ejemplo: 142301, 118942, 165400)

3. La instrucción valor++ parece una sola operación. ¿Se te ocurre por qué podría no serlo?
Porque a nivel de código máquina o bytecode se desglosa en tres pasos distintos:

    · Lectura: Leer el valor actual de la variable desde la memoria.
    · Modificación: Sumarle 1 a dicho valor.
    · Escritura: Guardar el nuevo resultado en la memoria.

Si un hilo lee el valor justo antes de que el otro guarde su actualización, ambos trabajarán con el mismo número
original, sobrescribiéndose mutuamente y contando solo un incremento en lugar de dos

4. Cambia los 100_000 por 100 y vuelve a ejecutar diez veces. ¿Qué observas ahora? ¿Significa eso que el programa es correcto?
Ahora el resultado obtenido es casi siempre 200 exactos, pero NO significa que el programa sea correcto.

Al ser solo 100 iteraciones, el bucle tarda poquísimo tiempo en completarse (milisegundos) y la probabilidad
de que los dos hilos coincidan en el mismo instante exacto es minúscula. El problema de concurrencia sigue
estando presente en el código, pero queda oculto por la brevedad del proceso (es un fallo o bug latente).

 */