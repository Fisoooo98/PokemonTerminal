package PokemonTerminal.Movimientos.Especiales;

import PokemonTerminal.Estados.EfectoConProbabilidad;
import PokemonTerminal.Estados.EfectoSecundario;
import PokemonTerminal.Estados.Paralisis;
import PokemonTerminal.Estados.Quemadura;
import PokemonTerminal.Movimientos.Movimiento;
import PokemonTerminal.Movimientos.ResultadoMovimiento;
import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.TabladeTipos.TablaTipos;
import PokemonTerminal.Tipos.Tipo;

import java.util.ArrayList;

/**
 * Representa el movimiento especial "Rayo".
 * Es un ataque de tipo Eléctrico con una potencia base de 90
 * y una probabilidad del 10% de paralizar al objetivo.
 * * @author TuNombre
 * @version 1.0
 */
public class Rayo extends Movimiento {

    /**
     * Constructor de Rayo.
     * Define: Potencia 90, Tipo Eléctrico, PP 15 y Precisión 100.
     * Agrega un efecto secundario de Parálisis con un 10% de probabilidad.
     */
    public Rayo() {
        // Nombre, Tipo, Potencia, PP, Precisión, Lista de Efectos
        super("Rayo", Tipo.ELECTRICO, 90, 15, 100, new ArrayList<>());

        // 10% de probabilidad de aplicar el estado Paralisis
        // Nota: Asegúrate de que la clase Paralisis extienda de Estado
        agregarEfecto(new EfectoConProbabilidad(20, new Paralisis()));
    }

    /**
     * Ejecuta la lógica del movimiento Rayo siguiendo la fórmula de daño oficial.
     * * @param atacante El Pokémon que lanza el ataque.
     * @param enemigo El Pokémon que recibe el ataque.
     * @return Un objeto ResultadoMovimiento con el daño causado y los efectos aplicados.
     */
    @Override
    public ResultadoMovimiento ejecutar(Pokemon atacante, Pokemon enemigo) {
        // 1. Cálculo de efectividad usando la tabla de tipos
        double multiplicadorTipo = TablaTipos.Multiplicador(this.tipo, enemigo.getPrimertipo(), enemigo.getSegundotipo());

        // 2. Bonus por el mismo tipo (STAB)
        double stab = (atacante.getPrimertipo() == this.tipo || atacante.getSegundotipo() == this.tipo) ? 1.5 : 1.0;

        // 3. Fórmula de daño balanceada (Estilo oficial)
        // Rayo es un ataque especial, por lo que usa AtkEspecial vs DefEspecial
        int nivel = atacante.getnivel();
        double base = ((2 * nivel / 5.0) + 2) * this.potencia * (double) atacante.getAtkEspecial() / enemigo.getDefEspecial();

        // El divisor 50 ajusta el daño a los HP reales del defensor
        int danoFinal = (int) ((base / 50 + 2) * stab * multiplicadorTipo);

        // 4. Aplicar daño mediante el setHp (que ya tiene el filtro de 0 y maxHp)
        enemigo.setHp(enemigo.getHp() - danoFinal);

        // 5. Intentar aplicar efectos secundarios (Parálisis)
        for (EfectoSecundario efecto : efectosSecundarios) {
            efecto.aplicar(enemigo);
        }

        // Devolvemos el resultado para que la Interfaz Gráfica lo procese
        return new ResultadoMovimiento(danoFinal, efectosSecundarios);
    }
}