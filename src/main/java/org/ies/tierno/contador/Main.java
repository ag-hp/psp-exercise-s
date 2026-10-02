package org.ies.tierno.contador;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final int VUELTAS = 100_000;

    public static void main(String[] args) {

        Contador contador = new Contador();

        Runnable incrementarMuchasVeces = () -> {
            for (int i = 0; i < VUELTAS; i++) {
                contador.incrementar();
            }
        };

        try (ExecutorService pool = Executors.newFixedThreadPool(2)) {
            pool.submit(incrementarMuchasVeces);
            pool.submit(incrementarMuchasVeces);
        }

        System.out.println("Resultado: " + contador.getValor());
    }
}
