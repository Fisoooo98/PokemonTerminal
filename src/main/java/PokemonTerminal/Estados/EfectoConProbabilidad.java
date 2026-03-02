package PokemonTerminal.Estados;


import PokemonTerminal.Pokemon.Pokemon;

import java.util.Random;

/**
 * Efecto secundario que se activa con cierta probabilidad.
 */
public class EfectoConProbabilidad implements EfectoSecundario {

    private double probabilidad;
    private EstadoAlterado efecto;
    private Random random = new Random();

    public EfectoConProbabilidad(double probabilidad, EstadoAlterado efecto) {
        this.probabilidad = probabilidad;
        this.efecto = efecto;
    }

    @Override
    public void aplicar(Pokemon objetivo) {
        if (random.nextDouble() * 100 < probabilidad) {
            objetivo.aplicarEstado(efecto);
        }
    }
}