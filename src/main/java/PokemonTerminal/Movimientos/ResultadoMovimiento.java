package PokemonTerminal.Movimientos;

import PokemonTerminal.Estados.EfectoSecundario;

import java.util.List;

/**
 * Clase para encapsular los resultados de un movimiento:
 * daño, efectos secundarios, estados aplicados, etc.
 */
public class ResultadoMovimiento{
    private int dano;
    private List<EfectoSecundario> efectos;

    public ResultadoMovimiento(int dano, List<EfectoSecundario> efectos) {
        this.dano = dano;
        this.efectos = efectos;
    }

    public int getDano() { return dano; }
    public List<EfectoSecundario> getEfectos() { return efectos; }
}