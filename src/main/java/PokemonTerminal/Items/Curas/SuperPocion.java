package PokemonTerminal.Items.Curas;

import PokemonTerminal.Pokemon.Pokemon;

public class SuperPocion extends Curas {
    public SuperPocion() {
        super(700, "Superpoción", 50);
    }

    @Override
    public boolean aplicar(Pokemon p) {
        if (p.getHp() > 0 && p.getHp() < p.getHpMax()) {
            p.setHp(p.getHp() + this.puntosSalud);
            return true;
        }
        return false;
    }
}
