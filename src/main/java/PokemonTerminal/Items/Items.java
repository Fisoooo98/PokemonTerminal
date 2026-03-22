package PokemonTerminal.Items;

import PokemonTerminal.Tipos.Categoria;

public abstract class Items {
    private String nombre;
    private int dinero;

    public Items(int dinero, String nombre) {
        this.dinero = dinero;
        this.nombre = nombre;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
