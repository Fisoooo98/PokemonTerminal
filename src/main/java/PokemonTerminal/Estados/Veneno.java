package PokemonTerminal.Estados;



import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.Tipos.EventoCombate;

/**
 * Estado de veneno: quita HP cada turno.
 */
public class Veneno implements EstadoAlterado {

    private boolean activo = true;

    @Override
    public void aplicar(Pokemon objetivo) {
        this.activo = true;
    }

    @Override
    public void onEvento(EventoCombate evento, Pokemon objetivo) {
        if (evento == EventoCombate.PASARTURNO && activo) {
            int dano = objetivo.getHp() / 8; // Veneno quita 1/8 del HP máximo
            objetivo.setHp(objetivo.getHp() - dano);
        }
    }

    @Override
    public boolean estaActivo() {
        return activo;
    }
}
