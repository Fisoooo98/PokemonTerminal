package PokemonTerminal.Pokemon.Pokedex;

import PokemonTerminal.Movimientos.Movimiento;
import PokemonTerminal.Movimientos.Especiales.PistolaAgua;
import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.Tipos.*;

import java.util.Map;

public class Wartortle extends Pokemon {

    private static final int BASE_HP = 59;
    private static final int BASE_ATK = 63;
    private static final int BASE_DEF = 80;
    private static final int BASE_ATK_ESP = 65;
    private static final int BASE_DEF_ESP = 80;
    private static final int BASE_VEL = 58;

    public Wartortle(int nivel, int xp) {
        super("Wartortle", Tipo.AGUA, null,
                BASE_HP, BASE_ATK, BASE_DEF, BASE_ATK_ESP, BASE_DEF_ESP, BASE_VEL,
                Naturaleza.AUDACIOSA, RatioCaptura.MEDIO, new Movimiento[4], true);

        setNivel(nivel);
        setXp(xp);

        getMovimientosPorNivel().put(1, new PistolaAgua());

        getEvoluciones().put(36, Blastoise.class);

        recalcularStats();
    }

    public Map<Integer, Class<? extends Pokemon>> getEvoluciones() {
        return super.Evoluciones;
    }
}
