package PokemonTerminal.Movimientos.Especiales;

import PokemonTerminal.Estados.EfectoConProbabilidad;
import PokemonTerminal.Estados.EfectoSecundario;
import PokemonTerminal.Estados.Quemadura;
import PokemonTerminal.Movimientos.Movimiento;
import PokemonTerminal.Movimientos.ResultadoMovimiento;
import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.TabladeTipos.TablaTipos;
import PokemonTerminal.Tipos.Tipo;

import java.util.ArrayList;

public class Lanzallamas extends Movimiento {

    public Lanzallamas() {
        super("Lanzallamas", Tipo.FUEGO, 90, 15, 100,new ArrayList<>());
        // 10% chance de quemar
        agregarEfecto(new EfectoConProbabilidad(100, new Quemadura()));
    }

    @Override
    public ResultadoMovimiento ejecutar(Pokemon atacante, Pokemon enemigo) {
        // 1. Cálculo de efectividad usando tu tabla
        double multiplicadorTipo = TablaTipos.Multiplicador(this.tipo, enemigo.getPrimertipo(), enemigo.getSegundotipo());

        // 2. Bonus por el mismo tipo (STAB)
        double stab = (atacante.getPrimertipo() == this.tipo || atacante.getSegundotipo() == this.tipo) ? 1.5 : 1.0;

        // 3. Fórmula de daño balanceada (Estilo oficial)
        int nivel = atacante.getnivel();
        double base = ((2 * nivel / 5.0) + 2) * potencia * (double) atacante.getAtkEspecial() / enemigo.getDefEspecial();

        // El divisor 50 ajusta el daño a los HP reales del defensor
        int danoFinal = (int) ((base / 50 + 2) * stab * multiplicadorTipo);

        // 4. Aplicar daño y efectos
        enemigo.setHp(enemigo.getHp() - danoFinal);

        for (EfectoSecundario efecto : efectosSecundarios) {
            efecto.aplicar(enemigo);
        }

        return new ResultadoMovimiento(danoFinal, efectosSecundarios);
    }
}