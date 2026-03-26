package PokemonTerminal.Entrenador;

import PokemonTerminal.Movimientos.Movimiento;
import PokemonTerminal.Pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static PokemonTerminal.Pokemon.Pokemon.*;

public class Entrenador {
    private String nombre;
    private double dinero;
    private Pokemon[] equipo;
    private ArrayList<Pokemon> pc;

    public Entrenador(String nombre,double dinero) {
        this.dinero = dinero;
        this.equipo = new Pokemon[6];
        this.nombre = nombre;
        this.pc = new ArrayList<>();
    }


    // ==============================
    // Equipo
    // ==============================
    /**
     * Busca el primer índice libre en el equipo.
     * @return El número de slot (1-6) o -1 si el equipo está lleno.
     */

    public int primerSlotVacio() {
        for (int i = 0; i < equipo.length; i++) {
            if (equipo[i] == null) {
                return i + 1; // Retornamos formato humano (1-6)
            }
        }
        return -1; // Equipo lleno
    }

    public boolean añadirAlEquipo(Pokemon pokemon,int slot) {
        if (slot >= 1 && slot <= 6) {
            this.equipo[slot - 1] = pokemon;
            return true;
        }
        return false;
    }

    public boolean moverPokemonEquipo(int slot1,int slot2){
        if (slot1 >= 1 && slot1 <= 6 && slot2 >= 1 && slot2 <= 6) {
            Pokemon nuevo = equipo[slot1-1];
            equipo[slot1-1] = equipo[slot2-1];
            equipo[slot2-1] = nuevo;
            return true;
        }
        return false;
    }
    // ==============================
    // PC
    // ==============================
    public void añadirPc(Pokemon pokemon) {
        pc.add(pokemon);
    }
    /**
     * Mueve un Pokémon del equipo al PC.
     * @param slot Slot del equipo (1-6).
     * @return true si se movió con éxito, false si es el último Pokémon o slot vacío.
     */

    public boolean depositarEnPc(int slot) {
        if (slot < 1 || slot > 6) return false;

        int indice = slot - 1;
        if (equipo[indice] == null) return false;

        // Contamos cuántos Pokémon quedan en el equipo
        int cont = 0;
        for (Pokemon p : equipo) if (p != null) cont++;

        if (cont <= 1) {
            // No se puede depositar el último Pokémon
            return false;
        }

        // Añadimos al PC y vaciamos el slot
        this.pc.add(equipo[indice]);
        equipo[indice] = null;
        return true;
    }
    /**
     * Saca un Pokémon del PC y lo añade al primer hueco libre del equipo.
     * @param indicePc Índice en la lista del PC (1 a N).
     * @return true si había hueco y se movió, false si el equipo está lleno.
     */
    public boolean sacarDelPc(int indicePc) {
        if (pc.isEmpty() || indicePc < 1 || indicePc > pc.size()) return false;

        int slotLibre = primerSlotVacio(); // Usamos tu método existente

        if (slotLibre != -1) {
            // .remove devuelve el objeto y lo quita de la lista automáticamente
            Pokemon p = pc.remove(indicePc - 1);
            this.equipo[slotLibre - 1] = p;
            return true;
        }
        return false;
    }
    /**
     * Intercambia un Pokémon del equipo por uno del PC directamente.
     * @param slotEquipo Slot del equipo (1-6).
     * @param indicePc Posición en el PC (1 a N).
     * @return true si se realizó el cambio.
     */
    public boolean intercambiarConPc(int slotEquipo, int indicePc) {
        if (slotEquipo < 1 || slotEquipo > 6 || pc.isEmpty() || indicePc < 1 || indicePc > pc.size()) {
            return false;
        }

        int idxE = slotEquipo - 1;
        int idxP = indicePc - 1;

        if (equipo[idxE] == null) {
            // Si el slot estaba vacío, simplemente lo sacamos del PC
            return sacarDelPc(indicePc);
        }

        // Realizamos el intercambio
        Pokemon delEquipo = equipo[idxE];
        Pokemon delPc = pc.get(idxP);

        equipo[idxE] = delPc;
        pc.set(idxP, delEquipo); // Reemplazamos en la misma posición del PC

        return true;
    }
    /**
     * Elimina permanentemente un Pokémon del PC.
     * @param indicePc Índice del PC (1 a N).
     * @return true si se liberó correctamente.
     */
    public boolean liberarPokemonPc(int indicePc) {
        if (pc.isEmpty() || indicePc < 1 || indicePc > pc.size()) return false;

        pc.remove(indicePc - 1);
        return true;
    }

    // ==============================
    // Curar Equipo
    // ==============================
    /**
     * Restaura completamente la salud y los PP de todos los Pokémon del equipo.
     * Simula el efecto de un Centro Pokémon.
     */
    public void curarEquipo() {
        int curados = 0;
        for (Pokemon p : equipo) {
            if (p != null) {
                // Restauramos HP al máximo
                p.setHp(p.getHpMax());

                // Restauramos los PP de todos sus movimientos
                for (Movimiento m : p.getMovimientos()) {
                    if (m != null) {
                        m.setPpActual(m.getPpMax());
                    }
                }
                curados++;
            }
        }
        // Como no queremos souts internos, podrías devolver el int 'curados'
        // para que tu Main diga: "Se han curado X Pokémon".
    }

    // ==============================
    // Getters y setters
    // ==============================
    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public Pokemon[] getEquipo() {
        return equipo;
    }

    public void setEquipo(Pokemon[] equipo) {
        this.equipo = equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Pokemon> getPc() {
        return pc;
    }


    /**
     * Muestra por terminal de forma visual los 6 slots del equipo.
     * Los slots se muestran del 1 al 6 para facilitar la interacción del usuario.
     */
    public void mostrarEquipo() {
        String separador = "  " + "─".repeat(45);
        System.out.println("\n" + Pokemon.NEGRITA + "╔" + "═".repeat(45) + "╗");
        System.out.println("║" + " ".repeat(15) + "EQUIPO POKÉMON" + " ".repeat(16) + "║");
        System.out.println("╚" + "═".repeat(45) + "╝" + RESET);

        for (int i = 0; i < equipo.length; i++) {
            int numeroSlot = i + 1; // Visualización 1-6
            Pokemon p = equipo[i];

            if (p != null) {
                // Colores basados en el tipo del Pokémon
                String color = Pokemon.getColorPorTipo(p.getPrimertipo());
                double pctHP = (double) p.getHp() / p.getHpMax();
                String barraHP = dibujarBarraMini(pctHP);

                System.out.println(color + "  [" + numeroSlot + "] " + Pokemon.NEGRITA +
                        String.format("%-12s", p.getNombre().toUpperCase()) +
                        RESET + " Lv." + p.getnivel() +
                        "  HP: " + barraHP + " " + p.getHp() + "/" + p.getHpMax());
                System.out.println("      " + p.getPrimertipo() + (p.getSegundotipo() != null ? "/" + p.getSegundotipo() : "") +
                        "  |  Estado: " + p.getEstadoActual());
            } else {
                // Slot vacío
                System.out.println("  [" + numeroSlot + "] " + Pokemon.BLANCO + "-------------------------- (VACÍO)" + RESET);
            }

            if (i < equipo.length - 1) System.out.println(separador);
        }
        System.out.println("  " + "─".repeat(45) + "\n");
    }

    /**
     * Genera una barra de vida pequeña para la vista de equipo.
     */
    private String dibujarBarraMini(double porcentaje) {
        int bloques = (int) (porcentaje * 10);
        String color = (porcentaje > 0.5) ? Pokemon.VERDE : (porcentaje > 0.2) ? AMARILLO : ROJO;
        return color + "█".repeat(bloques) + RESET + "░".repeat(10 - bloques);
    }
    /**
     * Muestra el contenido del PC de forma horizontal y compacta.
     * Formato: [Índice] Nombre (Nv. XX)
     */
    public void mostrarPC() {
        System.out.println("\n" + Pokemon.NEGRITA + Pokemon.BG_NEGRO + Pokemon.BLANCO +
                "  SISTEMA DE ALMACENAMIENTO POKÉMON (PC)  " + RESET + "\n");

        if (pc == null || pc.isEmpty()) {
            System.out.println("    [!] El PC está vacío.");
            return;
        }

        // Definimos cuántos queremos por fila
        int pokemonPorFila = 4;
        String separadorHorizontal = "  " + "─".repeat(85);

        System.out.println(separadorHorizontal);

        for (int i = 0; i < pc.size(); i++) {
            Pokemon p = pc.get(i);
            if (p == null) continue;

            String color = Pokemon.getColorPorTipo(p.getPrimertipo());

            // Formato: [001] Charizard (Nv.36)
            // %-3d para el índice del PC
            // %-12s para el nombre
            String infoPokemon = String.format("%s[%03d]%s %s%-12s %s(Nv.%-2d)%s",
                    Pokemon.BLANCO, (i + 1), RESET,
                    color, p.getNombre().toUpperCase(),
                    RESET, p.getnivel(), RESET);

            System.out.print("  " + infoPokemon + "  │");

            // Salto de línea cada 'pokemonPorFila'
            if ((i + 1) % pokemonPorFila == 0) {
                System.out.println("\n" + separadorHorizontal);
            }
        }

        // Ajuste final si la última fila quedó incompleta
        if (pc.size() % pokemonPorFila != 0) {
            System.out.println("\n" + separadorHorizontal);
        }

        System.out.println("  Total en el PC: " + pc.size() + " Pokémon.");
    }

// ... dentro de la clase Entrenador ...

    public void interfazGestionPC() {
        Scanner sc = new Scanner(System.in);

        // Definimos los nuevos tonos neutros
        String GRIS = "\u001B[90m";
        String BLANCO_B = "\u001B[97m"; // Blanco brillante
        String RESET = "\u001B[0m";
        String NEGRITA = "\u001B[1m";

        while (true) {
            System.out.println("\n".repeat(3));

            // --- CABECERA LIMPIA ---
            System.out.println(GRIS + "─".repeat(84) + RESET);
            System.out.println(NEGRITA + String.format("  %-80s  ", "CENTRO DE GESTIÓN POKÉMON | OPERADOR: " + nombre.toUpperCase()) + RESET);
            System.out.println(GRIS + "─".repeat(84) + RESET);

            // --- ESTRUCTURA DE TABLA EN BLANCO/GRIS ---
            System.out.println(NEGRITA + "  EQUIPO ACTUAL (Slots 1-6) " + " ".repeat(15) + "ALMACENAMIENTO DEL PC" + RESET);
            System.out.println(GRIS + "┌" + "─".repeat(40) + "┬" + "─".repeat(41) + "┐" + RESET);

            int maxFilas = Math.max(6, pc.size());

            for (int i = 0; i < maxFilas; i++) {
                // --- COLUMNA EQUIPO ---
                String celdaEquipo;
                if (i < 6) {
                    if (equipo[i] != null) {
                        // Mantenemos el color del TIPO del pokemon para que sea funcional, pero el resto es blanco
                        String colorTipo = Pokemon.getColorPorTipo(equipo[i].getPrimertipo());
                        String texto = String.format("%d. %-12s Nv.%-3d", (i+1), equipo[i].getNombre().toUpperCase(), equipo[i].getnivel());
                        celdaEquipo = " " + colorTipo + texto + RESET + " ".repeat(38 - texto.length());
                    } else {
                        String vacio = (i+1) + ". [ VACÍO ]";
                        celdaEquipo = " " + GRIS + vacio + RESET + " ".repeat(38 - vacio.length());
                    }
                } else {
                    celdaEquipo = " ".repeat(39);
                }

                // --- COLUMNA PC ---
                String celdaPC;
                if (i < pc.size()) {
                    Pokemon p = pc.get(i);
                    String colorTipo = Pokemon.getColorPorTipo(p.getPrimertipo());
                    String texto = String.format("[%02d] %-12s Nv.%-3d", (i+1), p.getNombre().toUpperCase(), p.getnivel());
                    celdaPC = " " + colorTipo + texto + RESET + " ".repeat(39 - texto.length());
                } else {
                    celdaPC = " ".repeat(40);
                }

                // --- IMPRESIÓN CON BORDES GRISES ---
                System.out.println(GRIS + "│" + RESET + celdaEquipo + GRIS + "│" + RESET + celdaPC + GRIS + "│" + RESET);
            }

            System.out.println(GRIS + "└" + "─".repeat(40) + "┴" + "─".repeat(41) + "┘" + RESET);

            // --- PANEL DE COMANDOS MINIMALISTA ---
            System.out.println("\n " + NEGRITA + "OPERACIONES:" + RESET);
            System.out.println("  " + BLANCO_B + "• [Slot] [PC]" + RESET + " Intercambiar   " + BLANCO_B + "• [Slot] 0" + RESET + " Depositar   " + GRIS + "• EXIT" + RESET);
            System.out.print("\n " + NEGRITA + "> " + RESET);

            String input = sc.next();
            if (input.equalsIgnoreCase("EXIT")) break;

            try {
                int slotE = Integer.parseInt(input);
                int idxP = sc.nextInt();
                procesarMovimiento(slotE, idxP);
            } catch (Exception e) {
                System.out.println(ROJO + "  ! Entrada inválida." + RESET);
                sc.nextLine();
            }
        }
    }

    private void procesarMovimiento(int slotE, int idxP) {
        if (slotE < 1 || slotE > 6) {
            System.out.println(ROJO + "  ⚠ Slot de equipo inválido." + RESET);
            return;
        }

        int iE = slotE - 1;
        int iP = idxP - 1;

        // CASO A: DEPOSITAR (Si el usuario pone 0 o un índice mayor al tamaño del PC)
        if (idxP == 0 || idxP > pc.size()) {
            if (equipo[iE] == null) {
                System.out.println(AMARILLO + "  ⚠ El slot está vacío, nada que depositar." + RESET);
            } else if (this.depositarEnPc(slotE)) {
                System.out.println(VERDE + "  ✔ Guardado en el primer hueco libre del PC." + RESET);
            } else {
                System.out.println(ROJO + "  ⚠ No puedes depositar a tu único Pokémon." + RESET);
            }
        }
        // CASO B: SACAR O INTERCAMBIAR
        else {
            if (this.intercambiarConPc(slotE, idxP)) {
                System.out.println(VERDE + "  ✔ Operación de intercambio exitosa." + RESET);
            } else {
                System.out.println(ROJO + "  ⚠ Error en la transferencia." + RESET);
            }
        }

        try { Thread.sleep(1000); } catch (InterruptedException e) {}
    }
}
