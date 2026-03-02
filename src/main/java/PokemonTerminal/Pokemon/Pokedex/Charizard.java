package PokemonTerminal.Pokemon.Pokedex;

import PokemonTerminal.Movimientos.Movimiento;
import PokemonTerminal.Movimientos.Especiales.Lanzallamas;
import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.Tipos.*;

public class Charizard extends Pokemon {

    private static final int BASE_HP = 78;
    private static final int BASE_ATK = 84;
    private static final int BASE_DEF = 78;
    private static final int BASE_ATK_ESP = 109;
    private static final int BASE_DEF_ESP = 85;
    private static final int BASE_VEL = 100;

    public Charizard(int nivel, int xp) {
        super("Charizard", Tipo.FUEGO, Tipo.VOLADOR,
                BASE_HP, BASE_ATK, BASE_DEF, BASE_ATK_ESP, BASE_DEF_ESP, BASE_VEL,
                Naturaleza.AUDACIOSA, RatioCaptura.MEDIO, new Movimiento[4], true);

        setNivel(nivel);
        setXp(xp);

        getMovimientosPorNivel().put(1, new Lanzallamas());

        recalcularStats();
    }
}
