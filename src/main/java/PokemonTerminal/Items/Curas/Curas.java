package PokemonTerminal.Items.Curas;



import PokemonTerminal.Items.Items;
import PokemonTerminal.Pokemon.Pokemon;

public abstract class Curas extends Items {
    protected int puntosSalud;

    public Curas(int dinero, String nombre, int puntosSalud) {
        super(dinero, nombre);
        this.puntosSalud = puntosSalud;
    }

    public abstract boolean aplicar(Pokemon p);
}
