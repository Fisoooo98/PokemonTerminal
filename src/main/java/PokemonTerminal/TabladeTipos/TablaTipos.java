package PokemonTerminal.TabladeTipos;

import PokemonTerminal.Tipos.Tipo;

import java.util.HashMap;
import java.util.Map;

public class TablaTipos {

    private static final Map<Tipo, Map<Tipo, Double>> tabla = new HashMap<>();

    static {

        for (Tipo atacante : Tipo.values()) {
            tabla.put(atacante, new HashMap<>());
        }

        // ======================
        // FUEGO
        // ======================
        set(Tipo.FUEGO, Tipo.PLANTA, 2);
        set(Tipo.FUEGO, Tipo.HIELO, 2);
        set(Tipo.FUEGO, Tipo.BICHO, 2);
        set(Tipo.FUEGO, Tipo.ACERO, 2);

        set(Tipo.FUEGO, Tipo.FUEGO, 0.5);
        set(Tipo.FUEGO, Tipo.AGUA, 0.5);
        set(Tipo.FUEGO, Tipo.ROCA, 0.5);
        set(Tipo.FUEGO, Tipo.DRAGON, 0.5);

        // ======================
        // AGUA
        // ======================
        set(Tipo.AGUA, Tipo.FUEGO, 2);
        set(Tipo.AGUA, Tipo.TIERRA, 2);
        set(Tipo.AGUA, Tipo.ROCA, 2);

        set(Tipo.AGUA, Tipo.AGUA, 0.5);
        set(Tipo.AGUA, Tipo.PLANTA, 0.5);
        set(Tipo.AGUA, Tipo.DRAGON, 0.5);

        // ======================
        // PLANTA
        // ======================
        set(Tipo.PLANTA, Tipo.AGUA, 2);
        set(Tipo.PLANTA, Tipo.TIERRA, 2);
        set(Tipo.PLANTA, Tipo.ROCA, 2);

        set(Tipo.PLANTA, Tipo.FUEGO, 0.5);
        set(Tipo.PLANTA, Tipo.PLANTA, 0.5);
        set(Tipo.PLANTA, Tipo.VENENO, 0.5);
        set(Tipo.PLANTA, Tipo.VOLADOR, 0.5);
        set(Tipo.PLANTA, Tipo.BICHO, 0.5);
        set(Tipo.PLANTA, Tipo.DRAGON, 0.5);
        set(Tipo.PLANTA, Tipo.ACERO, 0.5);

        // ======================
        // ELECTRICO
        // ======================
        set(Tipo.ELECTRICO, Tipo.AGUA, 2);
        set(Tipo.ELECTRICO, Tipo.VOLADOR, 2);

        set(Tipo.ELECTRICO, Tipo.ELECTRICO, 0.5);
        set(Tipo.ELECTRICO, Tipo.PLANTA, 0.5);
        set(Tipo.ELECTRICO, Tipo.DRAGON, 0.5);

        set(Tipo.ELECTRICO, Tipo.TIERRA, 0); // inmunidad

        // ======================
        // TIERRA
        // ======================
        set(Tipo.TIERRA, Tipo.FUEGO, 2);
        set(Tipo.TIERRA, Tipo.ELECTRICO, 2);
        set(Tipo.TIERRA, Tipo.VENENO, 2);
        set(Tipo.TIERRA, Tipo.ROCA, 2);
        set(Tipo.TIERRA, Tipo.ACERO, 2);

        set(Tipo.TIERRA, Tipo.PLANTA, 0.5);
        set(Tipo.TIERRA, Tipo.BICHO, 0.5);

        set(Tipo.TIERRA, Tipo.VOLADOR, 0); // inmunidad

        // (Puedes completar los demás siguiendo el mismo patrón)
    }

    private static void set(Tipo atacante, Tipo defensor, double valor) {
        tabla.get(atacante).put(defensor, valor);
    }

    public static double Multiplicador(Tipo atacante, Tipo defensor1, Tipo defensor2) {

        double mult1 = tabla
                .getOrDefault(atacante, new HashMap<>())
                .getOrDefault(defensor1, 1.0);

        double mult2 = 1.0;

        if (defensor2 != null) {
            mult2 = tabla
                    .getOrDefault(atacante, new HashMap<>())
                    .getOrDefault(defensor2, 1.0);
        }

        return mult1 * mult2;
    }
}