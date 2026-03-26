package PokemonTerminal.Items.Curas;

import PokemonTerminal.Pokemon.Pokemon;

public class Revivir extends Curas {
    public Revivir() {
        super(2000, "Revivir", 0);
    }

    @Override
    public boolean aplicar(Pokemon p) {
        if (p.getHp() == 0) {
            p.setHp(p.getHpMax() / 2);
            return true;
        }
        return false;
    }
}
