package PokemonTerminal.Test;

import PokemonTerminal.Items.MT.MT;
import PokemonTerminal.Movimientos.Especiales.Lanzallamas;
import PokemonTerminal.Movimientos.Especiales.PistolaAgua;
import PokemonTerminal.Movimientos.Especiales.Rayo;
import PokemonTerminal.Movimientos.Movimiento;
import PokemonTerminal.Pokemon.Pokedex.Charmander;
import PokemonTerminal.Pokemon.Pokedex.Charmeleon;
import PokemonTerminal.Tipos.Tipo;
public class MovimientosTest {
    public static void main(String[] args) {
        // 1. Instanciamos los movimientos disponibles
        Movimiento lanzallamas = new Lanzallamas(); // Fuego
        Movimiento pistolaAgua = new PistolaAgua(); // Agua
        Movimiento rayo = new Rayo(); // Eléctrico

        // Creamos una MT de Rayo
        MT mtRayo = new MT(2000, "MT24 - Rayo", rayo);

        System.out.println("--- TEST 1: NACIMIENTO Y MAPA DE NIVELES ---");

        // Simulamos un Charmander que por nivel aprende:
        // Nivel 1: Llamarada (Muy potente para empezar, pero es un test)
        // Nivel 10: Pistola Agua (Movimiento raro para un Charmander, por probar)
        Charmander c = new Charmander(15, 0);

        // Limpiamos y forzamos su mapa para este test específico
        c.getMovimientosPorNivel().clear();
        c.getMovimientosPorNivel().put(1, lanzallamas);
        c.getMovimientosPorNivel().put(10, pistolaAgua);

        // Procesamos la lógica nueva
        c.actualizarMemoria();
        c.llenarSlotsAutomaticamente();

        System.out.println("Pokémon: " + c.getNombre() + " | Nivel: " + c.getnivel());
        System.out.println("Ataques en equipo (Slots):");
        for (int i = 0; i < 4; i++) {
            Movimiento m = c.getMovimientos()[i];
            System.out.println("  Slot " + (i + 1) + ": " + (m != null ? m.getNombre() : "---"));
        }

        // 2. Probar la Memoria (Set)
        System.out.println("\n--- TEST 2: MEMORIA HISTÓRICA (SET) ---");
        System.out.println("Ataques conocidos (en el baúl):");
        for (Movimiento m : c.getMovimientosConocidos()) {
            System.out.println("  - " + m.getNombre());
        }

        // 3. Probar MT y Sobreescritura
        System.out.println("\n--- TEST 3: APRENDIZAJE POR MT ---");
        System.out.println("Usando MT Rayo en Slot 1 (Sustituye a Llamarada)...");

        // Al usar la MT, el método 'usar' debería llamar a 'comprobarAprendizaje'
        mtRayo.usar(c, 1);

        System.out.println("Ataque en Slot 1 ahora: " + c.getMovimientos()[0].getNombre());

        // Comprobación CRÍTICA: ¿Sigue Llamarada en la memoria aunque ya no esté en el equipo?
        boolean sigueLlamarada = c.getMovimientosConocidos().contains(lanzallamas);
        boolean estaRayo = c.getMovimientosConocidos().contains(rayo);

        System.out.println("¿Rayo guardado en memoria?: " + (estaRayo ? "SÍ" : "NO"));
        System.out.println("¿Llamarada sigue en memoria (aunque se borró del slot)?: " + (sigueLlamarada ? "SÍ" : "NO"));
        c.interfazGestionMovimientos();
    }

}