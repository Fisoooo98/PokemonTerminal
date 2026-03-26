package PokemonTerminal.Items.Curas;

import PokemonTerminal.Pokemon.Pokemon;

public class UltraPocion extends Curas {
    public UltraPocion() {
        super(1500, "Ultrapoción", 200);
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
