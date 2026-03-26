package PokemonTerminal.Pokemon.Pokedex;


import PokemonTerminal.Movimientos.Movimiento;
import PokemonTerminal.Movimientos.Especiales.PistolaAgua;
import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.Tipos.*;

public class Blastoise extends Pokemon {

    private static final int BASE_HP = 79;
    private static final int BASE_ATK = 83;
    private static final int BASE_DEF = 100;
    private static final int BASE_ATK_ESP = 85;
    private static final int BASE_DEF_ESP = 105;
    private static final int BASE_VEL = 78;

    public Blastoise(int nivel, int xp) {
        super("Blastoise", Tipo.AGUA, null,
                BASE_HP, BASE_ATK, BASE_DEF, BASE_ATK_ESP, BASE_DEF_ESP, BASE_VEL,
                Naturaleza.AUDACIOSA, RatioCaptura.MEDIO, new Movimiento[4], true,null);

        setNivel(nivel);
        setXp(xp);

        getMovimientosPorNivel().put(1, new PistolaAgua());

        actualizarMemoria();

        llenarSlotsAutomaticamente();

        recalcularStats();
    }
}