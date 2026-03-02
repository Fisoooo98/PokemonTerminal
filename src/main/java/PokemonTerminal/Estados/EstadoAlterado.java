package PokemonTerminal.Estados;


import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.Tipos.EventoCombate;

/**
 * Interfaz que representa un estado alterado que puede afectar un Pokémon
 * durante el combate.
 */
public interface EstadoAlterado {

    /**
     * Aplica el estado inicial al Pokémon.
     *
     * @param objetivo Pokémon que recibirá el estado
     */
    void aplicar(Pokemon objetivo);

    /**
     * Se ejecuta cada vez que ocurre un evento en combate.
     *
     * @param evento Evento de combate
     * @param objetivo Pokémon afectado por el estado
     */
    void onEvento(EventoCombate evento, Pokemon objetivo);

    /**
     * Indica si el estado sigue activo.
     *
     * @return true si el estado sigue activo
     */
    boolean estaActivo();

    default boolean reducirAtaqueFisico() {
        return false;
    }
    default boolean reducirVelocidad() {
        return false;
    }
}