package PokemonTerminal.Items.Curas;

import PokemonTerminal.Pokemon.Pokemon;

public class Pocion extends Curas {
    public Pocion() {
        super(300, "Poción", 20);
    }

    @Override
    public boolean aplicar(Pokemon p) {
        // Lógica: No funciona si está debilitado o con vida al máximo
        if (p.getHp() > 0 && p.getHp() < p.getHpMax()) {
            p.setHp(p.getHp() + this.puntosSalud);
            return true;
        }
        return false;
    }
}

