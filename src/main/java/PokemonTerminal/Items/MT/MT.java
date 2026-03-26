package PokemonTerminal.Items.MT;


import PokemonTerminal.Items.Items;
import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.Movimientos.Movimiento;

/**
 * Representa una Máquina Técnica (MT) que permite enseñar un movimiento a un Pokémon.
 * Hereda de la clase base Items para gestionar su nombre y precio.
 * * @author TuNombre
 * @version 1.0
 */
public class MT extends Items {
    private Movimiento movimientoContenido;

    /**
     * Constructor para crear una nueva MT.
     * @param dinero Precio de compra de la MT en la tienda.
     * @param nombre Nombre de la MT (ej. "MT01 - Rayo Hielo").
     * @param movimiento Instancia del movimiento que esta MT enseñará.
     */
    public MT(int dinero, String nombre, Movimiento movimiento) {
        super(dinero, nombre);
        this.movimientoContenido = movimiento;
    }

    /**
     * Intenta enseñar el movimiento de la MT a un Pokémon en un slot específico.
     * Este método es ideal para la Interfaz Gráfica ya que devuelve un booleano
     * para confirmar si la acción fue posible.
     * * @param p El Pokémon que intentará aprender el movimiento.
     * @param slotSeleccionado El índice del array de movimientos (0-3) donde se guardará.
     * @return {@code true} si el Pokémon es compatible y aprendió el movimiento;
     * {@code false} si no es compatible o el Pokémon está debilitado.
     */
    public boolean usar(Pokemon p, int slotSeleccionado) {
        if (p == null || slotSeleccionado < 1 || slotSeleccionado > 4) return false;

        if (p.puedeAprender(this.movimientoContenido)) {
            // Llamamos al método maestro que actualiza Set y Array a la vez
            return p.aprenderMovimiento(this.movimientoContenido, slotSeleccionado);
        }

        return false;
    }

    /** @return El objeto Movimiento que contiene la MT. */
    public Movimiento getMovimientoContenido() {
        return movimientoContenido;
    }
}