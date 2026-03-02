package PokemonTerminal.Movimientos.Especiales;

import PokemonTerminal.Estados.EfectoConProbabilidad;
import PokemonTerminal.Estados.EfectoSecundario;
import PokemonTerminal.Estados.Quemadura;
import PokemonTerminal.Movimientos.Movimiento;
import PokemonTerminal.Movimientos.ResultadoMovimiento;
import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.Tipos.Tipo;

import java.util.ArrayList;

public class PistolaAgua extends Movimiento {

    public PistolaAgua() {
        super("Pistola Agua", Tipo.AGUA, 90, 15, 100, new ArrayList<>());
        // 10% chance de reducir ataque físico del enemigo
    }

    @Override
    public ResultadoMovimiento ejecutar(Pokemon atacante, Pokemon enemigo) {
        double multiplicadorTipo = 1.0;
        double stab = (atacante.getPrimertipo() == this.tipo || atacante.getSegundotipo() == this.tipo) ? 1.5 : 1.0;

        int dano = (int) (((double) (atacante.getAtkEspecial() * potencia) / enemigo.getDefEspecial()) * stab * multiplicadorTipo);

        enemigo.setHp(enemigo.getHp() - dano);

        // Aplicar efectos secundarios
        for (EfectoSecundario efecto : efectosSecundarios) {
            efecto.aplicar(enemigo);
        }

        return new ResultadoMovimiento(dano, efectosSecundarios);
    }
}