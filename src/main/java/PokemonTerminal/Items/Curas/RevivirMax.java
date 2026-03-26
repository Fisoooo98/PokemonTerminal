package PokemonTerminal.Items.Curas;

import PokemonTerminal.Pokemon.Pokemon;

public class RevivirMax extends Curas {
    public RevivirMax() {
        super(2000, "Revivir", 0);
    }

    @Override
    public boolean aplicar(Pokemon p) {
        if (p.getHp() == 0) {
            p.setHp(p.getHpMax());
            return true;
        }
        return false;
    }
}
