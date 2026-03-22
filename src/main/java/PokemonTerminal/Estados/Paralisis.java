package PokemonTerminal.Estados;



import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.Tipos.EventoCombate;

import java.util.Random;

/**
 * Estado de parálisis: puede impedir que el Pokémon actúe y reduce velocidad.
 */
public class Paralisis implements EstadoAlterado {

    private boolean activo = true;
    private Random random = new Random();
    private String nombre;

    @Override
    public void aplicar(Pokemon objetivo) {
        this.activo = true;
        // Reducir velocidad a la mitad
        objetivo.setVelocidad(objetivo.getVelocidad() / 2);
        objetivo.aplicarEstado(this);
    }

    @Override
    public void onEvento(EventoCombate evento, Pokemon objetivo) {
        if (evento == EventoCombate.PASARTURNO && activo) {
            // 25% de chance de que el Pokémon quede paralizado y no actúe
            if (random.nextInt(100) < 25) {
                //Bloquear accion
            }
        }
    }

    @Override
    public boolean estaActivo() {
        return activo;
    }
    public String getNombre() {
        return nombre;
    }
}