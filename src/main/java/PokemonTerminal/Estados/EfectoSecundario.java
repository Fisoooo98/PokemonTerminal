package PokemonTerminal.Estados;



import PokemonTerminal.Pokemon.Pokemon;

/**
 * Interfaz para efectos secundarios de movimientos.
 */
public interface EfectoSecundario {

    /**
     * Aplica el efecto secundario sobre un Pokémon.
     *
     * @param objetivo Pokémon afectado
     */
    void aplicar(Pokemon objetivo);
}