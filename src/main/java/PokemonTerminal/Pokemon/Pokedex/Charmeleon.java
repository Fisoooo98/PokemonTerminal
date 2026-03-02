package PokemonTerminal.Pokemon.Pokedex;
import PokemonTerminal.Movimientos.Movimiento;
import PokemonTerminal.Movimientos.Especiales.Lanzallamas;
import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.Tipos.*;

import java.util.Map;

public class Charmeleon extends Pokemon {

    private static final int BASE_HP = 58;
    private static final int BASE_ATK = 64;
    private static final int BASE_DEF = 58;
    private static final int BASE_ATK_ESP = 80;
    private static final int BASE_DEF_ESP = 65;
    private static final int BASE_VEL = 80;

    public Charmeleon(int nivel, int xp) {
        super("Charmeleon", Tipo.FUEGO, null,
                BASE_HP, BASE_ATK, BASE_DEF, BASE_ATK_ESP, BASE_DEF_ESP, BASE_VEL,
                Naturaleza.AUDACIOSA, RatioCaptura.MEDIO, new Movimiento[4], true);

        setNivel(nivel);
        setXp(xp);

        // Movimientos por nivel
        getMovimientosPorNivel().put(1, new Lanzallamas());

        // Evolución por nivel
        getEvoluciones().put(36, Charizard.class);

        recalcularStats();
    }

    public Map<Integer, Class<? extends Pokemon>> getEvoluciones() {
        return super.Evoluciones;
    }
}