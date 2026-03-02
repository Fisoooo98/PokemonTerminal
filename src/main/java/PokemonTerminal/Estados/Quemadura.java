package PokemonTerminal.Estados;

import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.Tipos.EventoCombate;

/**
 * Estado de quemadura: reduce HP por turno y afecta ataque físico.
 */
public class Quemadura implements EstadoAlterado {

    private boolean activo = true;

    @Override
    public void aplicar(Pokemon objetivo) {
        this.activo = true;
        // Aquí se podría aplicar un cambio inicial, como reducir stats físicos
        // ejemplo: objetivo.setAtkFisico((int)(objetivo.getAtkFisico() * 0.5));
    }

    @Override
    public void onEvento(EventoCombate evento, Pokemon objetivo) {
        if (evento == EventoCombate.PASARTURNO && activo) {
            int dano = objetivo.getHp() / 8; // Quemadura quita 1/8 del HP máximo
            objetivo.setHp(objetivo.getHp() - dano);
        }
    }

    @Override
    public boolean estaActivo() {
        return activo;
    }

    @Override
    public boolean reducirAtaqueFisico() {
        return true; // Quemadura reduce ataque físico
    }
}