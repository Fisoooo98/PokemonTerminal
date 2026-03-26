package PokemonTerminal.Pokemon.Pokedex;



import PokemonTerminal.Movimientos.Movimiento;
import PokemonTerminal.Movimientos.Especiales.PistolaAgua;
import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.Tipos.*;

import java.util.Map;

public class Squirtle extends Pokemon {

    // Stats base de Squirtle
    private static final int BASE_HP = 44;
    private static final int BASE_ATK = 48;
    private static final int BASE_DEF = 65;
    private static final int BASE_ATK_ESP = 50;
    private static final int BASE_DEF_ESP = 64;
    private static final int BASE_VEL = 43;

    public Squirtle(int nivel, int xp) {
        super("Squirtle", Tipo.AGUA, null,
                BASE_HP, BASE_ATK, BASE_DEF, BASE_ATK_ESP, BASE_DEF_ESP, BASE_VEL,
                Naturaleza.AUDACIOSA, RatioCaptura.MEDIO, new Movimiento[4], true,null);

        // Inicializamos nivel y xp
        setNivel(nivel);
        setXp(xp);

        // Movimientos por nivel
        getMovimientosPorNivel().put(15, new PistolaAgua());

        // Evolución por nivel
        getEvoluciones().put(16, Wartortle.class); // Evoluciona a Wartortle nivel 16

        actualizarMemoria();

        llenarSlotsAutomaticamente();

        recalcularStats();
    }

    public Map<Integer, Class<? extends Pokemon>> getEvoluciones() {
        return super.Evoluciones;
    }
}