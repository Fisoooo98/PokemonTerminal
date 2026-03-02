package PokemonTerminal.Movimientos.Especiales;

import PokemonTerminal.Estados.EfectoConProbabilidad;
import PokemonTerminal.Estados.EfectoSecundario;
import PokemonTerminal.Estados.Quemadura;
import PokemonTerminal.Movimientos.Movimiento;
import PokemonTerminal.Movimientos.ResultadoMovimiento;
import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.Tipos.Tipo;

import java.util.ArrayList;

public class Lanzallamas extends Movimiento {

    public Lanzallamas() {
        super("Lanzallamas", Tipo.FUEGO, 90, 15, 100,new ArrayList<>());
        // 10% chance de quemar
        agregarEfecto(new EfectoConProbabilidad(10, new Quemadura()));
    }

    @Override
    public ResultadoMovimiento ejecutar(Pokemon atacante, Pokemon enemigo) {
        double multiplicadorTipo = 1.0;
        // Aquí usarías tu tabla de efectividades
        // multiplicadorTipo = TablaTipos.multiplicador(this.tipo, enemigo.getPrimerTipo(), enemigo.getSegundotipo());

        double stab = atacante.getPrimertipo() == this.tipo || atacante.getSegundotipo() == this.tipo ? 1.5 : 1.0;

        int dano = (int) (((double) (atacante.getAtkEspecial() * potencia) / enemigo.getDefEspecial()) * stab * multiplicadorTipo);

        enemigo.setHp(enemigo.getHp() - dano);

        // Aplicar efectos secundarios
        for (EfectoSecundario efecto : efectosSecundarios) {
            efecto.aplicar(enemigo);
        }

        return new ResultadoMovimiento(dano, efectosSecundarios);
    }
}