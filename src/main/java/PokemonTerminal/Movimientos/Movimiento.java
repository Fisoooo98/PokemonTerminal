package PokemonTerminal.Movimientos;

import PokemonTerminal.Estados.EfectoSecundario;
import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.Tipos.Tipo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public abstract class Movimiento {



    protected String nombre;
    protected Tipo tipo;
    protected int potencia;
    protected int ppActual;
    protected int ppMax;


    protected int precision;
    protected List<EfectoSecundario> efectosSecundarios;

    protected Random random = new Random();


    public Movimiento(String nombre, Tipo tipo, int potencia, int pp, int precision, List<EfectoSecundario> efectosSecundarios) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.potencia = potencia;
        this.ppActual = pp;
        this.precision = precision;
        this.efectosSecundarios = new ArrayList<>();
    }

    /**
     * Ejecuta el movimiento sobre un enemigo.
     * @param atacante Pokémon que realiza el movimiento
     * @param enemigo Pokémon objetivo
     * @return ResultadoMovimiento con daños y efectos
     */
    public abstract ResultadoMovimiento ejecutar(Pokemon atacante, Pokemon enemigo);

    public void agregarEfecto(EfectoSecundario efecto) {
        efectosSecundarios.add(efecto);
    }
    //<--Getters-->\\


    public String getNombre() {
        return nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getPpMax() {
        return ppMax;
    }

    public int getPpActual() {
        return ppActual;
    }

    public void setPpActual(int ppActual) {
        this.ppActual = Math.max(0, Math.min(ppActual, ppMax));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movimiento that = (Movimiento) o;
        return Objects.equals(nombre, that.nombre); // Compara por nombre, no por dirección de memoria
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
    
  
}

