package PokemonTerminal.Items;

import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.Movimientos.Movimiento;

/**
 * Clase base para ítems que restauran Puntos de Poder (PP).
 */
public abstract class RestaurarPP extends Items {
    protected int cantidadARestaurar;
    protected boolean restaurarTodo;

    public RestaurarPP(int dinero, String nombre, int cantidad, boolean todo) {
        super(dinero, nombre);
        this.cantidadARestaurar = cantidad;
        this.restaurarTodo = todo;
    }

    /**
     * Aplica la restauración de PP a un movimiento específico del Pokémon.
     * @param p Pokémon objetivo.
     * @param indiceMovimiento Índice del movimiento (0-3).
     * @return true si se restauró algo, false si ya estaba al máximo o error.
     */
    public boolean usar(Pokemon p, int indiceMovimiento) {
        if (p == null || indiceMovimiento < 0 || indiceMovimiento >= 4) return false;

        Movimiento mov = p.getMovimientos()[indiceMovimiento];
        if (mov == null) return false;

        // Si ya tiene los PP al máximo, no se puede usar el ítem
        if (mov.getPpActual() >= mov.getPpMax()) return false;

        if (restaurarTodo) {
            mov.setPpActual(mov.getPpMax());
        } else {
            int nuevoPP = mov.getPpActual() + cantidadARestaurar;
            mov.setPpActual(Math.min(nuevoPP, mov.getPpMax()));
        }

        return true;
    }
}
