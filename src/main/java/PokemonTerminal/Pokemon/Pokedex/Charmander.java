package PokemonTerminal.Pokemon.Pokedex;

import PokemonTerminal.Items.Evolutivos.ItemEvolutivo;
import PokemonTerminal.Items.Evolutivos.PiedraFuego;
import PokemonTerminal.Movimientos.Movimiento;
import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.Tipos.Naturaleza;
import PokemonTerminal.Tipos.RatioCaptura;
import PokemonTerminal.Tipos.Tipo;

/**
 * Clase concreta que representa a Charmander.
 */

import PokemonTerminal.Movimientos.Movimiento;
import PokemonTerminal.Movimientos.Especiales.Lanzallamas;
import PokemonTerminal.Tipos.*;

import java.util.Map;

public class Charmander extends Pokemon {

    // Stats base de Charmander
    private static final int BASE_HP = 39;
    private static final int BASE_ATK = 52;
    private static final int BASE_DEF = 43;
    private static final int BASE_ATK_ESP = 60;
    private static final int BASE_DEF_ESP = 50;
    private static final int BASE_VEL = 65;

    public Charmander(int nivel, int xp) {
        super("Charmander", Tipo.FUEGO, null,
                BASE_HP, BASE_ATK, BASE_DEF, BASE_ATK_ESP, BASE_DEF_ESP, BASE_VEL,
                Naturaleza.AUDACIOSA, RatioCaptura.MEDIO, new Movimiento[4], true,null);

        // Inicializamos nivel y xp
        setNivel(nivel);
        setXp(xp);

        // Movimientos por nivel
        getMovimientosPorNivel().put(7, new Lanzallamas()); // Ejemplo, puede añadir más

        // Evolución por nivel
        getEvoluciones().put(16, Charmeleon.class); // Evoluciona a Charmeleon nivel 16
        getEvolucionesPorItem().put(PiedraFuego.class,Charmeleon.class);
        // Recalcular stats según nivel
        recalcularStats();

        for (int lvl = 1; lvl <= nivel; lvl++) {
            Movimiento mov = getMovimientosPorNivel().get(lvl);
            if (mov != null) {
                comprobarAprendizaje(mov, 0); // slot 0 = primer hueco libre
            }
        }
    }

    // Getter para la mapa Evoluciones
    public Map<Integer, Class<? extends Pokemon>> getEvoluciones() {
        return super.Evoluciones;
    }
    public Map<Class<? extends ItemEvolutivo>, Class<? extends Pokemon>> getEvolucionesPorItem() {
        return super.EvolucionesPorItem;
    }

}