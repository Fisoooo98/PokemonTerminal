package PokemonTerminal.Pokemon;

import PokemonTerminal.Movimientos.Movimiento;
import PokemonTerminal.Tipos.*;
import PokemonTerminal.Estados.EstadoAlterado;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Clase abstracta que representa la base de un Pokémon en el sistema de combate.
 *
 * <p>Incluye:</p>
 * <ul>
 *     <li>Estadísticas base y actuales (HP, ataque, defensa, etc.)</li>
 *     <li>Sistema de tipos (primario y secundario)</li>
 *     <li>Sistema de niveles y experiencia</li>
 *     <li>IVs individuales</li>
 *     <li>Sistema de captura</li>
 *     <li>Movimientos disponibles</li>
 *     <li>Estados alterados aplicados en combate</li>
 * </ul>
 *
 * Esta clase debe ser extendida por Pokémon específicos como Charmander, Bulbasur, etc.
 *
 * @version 2.0
 */
public abstract class Pokemon {

    // ==============================
    // Información básica
    // ==============================
    private String nombre;
    private Tipo primertipo;
    private Tipo segundotipo;
    private final Naturaleza naturaleza;
    private int nivel = 1;
    private int xp = 0;

    // ==============================
    // Estadísticas base y actuales
    // ==============================
    private int hp;
    private int hpMax;
    private int atkFisico;
    private int defFisico;
    private int atkEspecial;
    private int defEspecial;
    private int velocidad;



    // Estadísticas base de la especie (para recalcular)
    private static int BASEHP;
    private static int BASEATK;
    private static int BASEDEF;
    private static int BASE_ATKESP;
    private static int BASEDEF_ESP;
    private static int BASEVEL;

    // ==============================
    // IVs
    // ==============================
    private int ivHp;
    private int ivAtkFisico;
    private int ivDefFisico;
    private int ivAtkEspecial;
    private int ivDefEspecial;
    private int ivVelocidad;

    // ==============================
    // Captura
    // ==============================
    private boolean capturable;
    private RatioCaptura ratioCaptura;

    // ==============================
    // Mapas de movimientos y evolución
    // ==============================
    private Map<Integer, Movimiento> MovimientosPorNivel;
    protected Map<Integer, Class<? extends Pokemon>> Evoluciones;
    private Map<String, MetodoAprendizaje> MovimientosAprendibles;
    private Map<String, Class<? extends Pokemon>> EvolucionesPorPiedra;

    // ==============================
    // Buffs/debuffs (niveles de combate)
    // ==============================
    private int nivelAtk = 0;
    private int nivelDef = 0;
    private int nivelAtkEsp = 0;
    private int nivelDefEsp = 0;
    private int nivelVel = 0;

    // ==============================
    // Movimientos
    // ==============================
    private Movimiento[] movimientos = new Movimiento[4];

    // ==============================
    // Estado alterado actual
    // ==============================
    private EstadoAlterado estadoActual;

    // ==============================
    // Constructor
    // ==============================
    public Pokemon(String nombre, Tipo primertipo, Tipo segundotipo,
                   int baseHP, int baseAtk, int baseDef,
                   int baseAtkEsp, int baseDefEsp, int baseVel,
                   Naturaleza naturaleza, RatioCaptura ratioCaptura,
                   Movimiento[] movimientos, boolean capturable) {

        this.nombre = nombre;
        this.primertipo = primertipo;
        this.segundotipo = segundotipo;
        this.naturaleza = naturaleza;
        this.ratioCaptura = ratioCaptura;
        this.capturable = capturable;

        // Inicializar stats base de la especie
        BASEHP = baseHP;
        BASEATK = baseAtk;
        BASEDEF = baseDef;
        BASE_ATKESP = baseAtkEsp;
        BASEDEF_ESP = baseDefEsp;
        BASEVEL = baseVel;

        // Inicializar stats actuales con base
        this.hpMax = BASEHP;
        this.hp = hpMax;
        this.atkFisico = BASEATK;
        this.defFisico = BASEDEF;
        this.atkEspecial = BASE_ATKESP;
        this.defEspecial = BASEDEF_ESP;
        this.velocidad = BASEVEL;

        // Movimientos iniciales
        this.movimientos = movimientos != null ? movimientos : new Movimiento[4];

        // IVs aleatorios
        this.ivHp = (int) (Math.random() * 32);
        this.ivAtkFisico = (int) (Math.random() * 32);
        this.ivDefFisico = (int) (Math.random() * 32);
        this.ivAtkEspecial = (int) (Math.random() * 32);
        this.ivDefEspecial = (int) (Math.random() * 32);
        this.ivVelocidad = (int) (Math.random() * 32);

        // Inicializar mapas
        this.MovimientosPorNivel = new HashMap<>();
        this.MovimientosAprendibles = new HashMap<>();
        this.Evoluciones = new HashMap<>();
        this.EvolucionesPorPiedra = new HashMap<>();
    }
    // ==============================
    // Métodos de combate
    // ==============================

    /**
     * Devuelve un movimiento en un slot determinado (1-4).
     *
     * @param slot Slot del movimiento
     * @return Movimiento si existe, null si no
     */

    public Movimiento escogerMovimiento(int slot) {
        if (slot < 1 || slot > 4) return null;
        return movimientos[slot - 1];
    }

    public void setMovimiento(int slot, Movimiento movimiento) {
        this.movimientos[slot - 1] = movimiento;
    }

    /**
     * Calcula el multiplicador de stats según buff/debuff de combate.
     *
     * @param nivel Nivel de buff/debuff
     * @return Multiplicador
     */
    public double getMultiplicador(int nivel) {
        if (nivel >= 0) {
            return (2.0 + nivel) / 2.0;
        } else {
            return 2.0 / (2.0 - nivel);
        }
    }

    public int getAtkFisicoCombate() {
        int atk = (int) (atkFisico * getMultiplicador(nivelAtk));
        if (estadoActual != null && estadoActual.reducirAtaqueFisico()) atk /= 2;
        return atk;
    }

    public int getDefFisicoCombate() {
        return (int) (defFisico * getMultiplicador(nivelDef));
    }

    public int getAtkEspecialCombate() {
        return (int) (atkEspecial * getMultiplicador(nivelAtkEsp));
    }

    public int getDefEspecialCombate() {
        return (int) (defEspecial * getMultiplicador(nivelDefEsp));
    }

    public int getVelocidadCombate() {
        return (int) (velocidad * getMultiplicador(nivelVel));
    }

    // ==============================
    // Métodos de progreso
    // ==============================
    protected Pokemon subirNivel() {
        nivel++; // Subimos el nivel
        recalcularStats();

        // Aprender movimientos correspondientes al nuevo nivel
        Movimiento nuevoMov = MovimientosPorNivel.get(nivel);
        if (nuevoMov != null) {
            comprobarAprendizaje(nuevoMov, 0); // slot 0 = busca espacio libre
        }

        // Comprobar evolución por nivel
        if (Evoluciones.containsKey(nivel)) {
            try {
                Pokemon evolucion = Evoluciones.get(nivel)
                        .getDeclaredConstructor(int.class, int.class)
                        .newInstance(nivel, xp); // PASAR nivel y xp actuales

                // Copiar movimientos actuales
                for (int i = 0; i < 4; i++) {
                    if (movimientos[i] != null) {
                        evolucion.setMovimiento(i + 1, movimientos[i]);
                    }
                }
                return evolucion;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return this; // Si no evoluciona, devuelve el mismo
    }

    public int getXpNecesaria(int nivel) {
        return nivel * nivel * nivel;
    }

    /**
     * Intenta que el Pokémon aprenda un nuevo movimiento.
     *
     * @param nuevoMovimiento Movimiento que se quiere aprender.
     * @param slotElegido     Si el Pokémon ya tiene 4 movimientos, el slot (1-4) que reemplazará.
     *                        Si es 0, se usará el primer espacio libre.
     * @return true si el movimiento se aprende, false si no se puede (slot inválido).
     */
    public boolean comprobarAprendizaje(Movimiento nuevoMovimiento, int slotElegido) {
        // Slot 0 = buscar primer espacio libre
        if (slotElegido == 0) {
            for (int i = 0; i < movimientos.length; i++) {
                if (movimientos[i] == null) {
                    movimientos[i] = nuevoMovimiento;
                    return true;
                }
            }
            return false; // No hay espacio
        }

        // Slot específico (1-4)
        if (slotElegido >= 1 && slotElegido <= 4) {
            movimientos[slotElegido - 1] = nuevoMovimiento;
            return true;
        }

        return false; // Slot inválido
    }

    public boolean conoceMovimiento(Movimiento movimiento) {
        for (Movimiento mov : movimientos) {
            if (mov.equals(movimiento)) {
                return true;
            }
        }
        return false;
    }

    protected void recalcularStats() {
        // HP
        hpMax = (int) ((((2 * getBASEHP() + ivHp) * nivel) / 100.0) + nivel + 10);
        hp = hpMax; // Inicializamos la vida completa

        // Ataque físico
        int atkBaseCalc = (int) ((((2 * getBASEATK() + ivAtkFisico) * nivel) / 100.0) + 5);
        atkFisico = (int) (atkBaseCalc * naturaleza.getMultiplicador(Stat.ATAQUE));

        // Defensa física
        int defBaseCalc = (int) ((((2 * getBASEDEF() + ivDefFisico) * nivel) / 100.0) + 5);
        defFisico = (int) (defBaseCalc * naturaleza.getMultiplicador(Stat.DEFENSA));

        // Ataque especial
        int atkEspBaseCalc = (int) ((((2 * getBaseAtkesp() + ivAtkEspecial) * nivel) / 100.0) + 5);
        atkEspecial = (int) (atkEspBaseCalc * naturaleza.getMultiplicador(Stat.ATAQUE_ESPECIAL));

        // Defensa especial
        int defEspBaseCalc = (int) ((((2 * getBasedefEsp() + ivDefEspecial) * nivel) / 100.0) + 5);
        defEspecial = (int) (defEspBaseCalc * naturaleza.getMultiplicador(Stat.DEFENSA_ESPECIAL));

        // Velocidad
        int velBaseCalc = (int) ((((2 * getBASEVEL() + ivVelocidad) * nivel) / 100.0) + 5);
        velocidad = (int) (velBaseCalc * naturaleza.getMultiplicador(Stat.VELOCIDAD));
    }

    public Pokemon ganarXP(int cantidad) {
        xp += cantidad;

        Pokemon actual = this;

        while (xp >= getXpNecesaria(actual.nivel)) {
            xp -= getXpNecesaria(actual.nivel);
            actual = actual.subirNivel();
        }

        return actual;
    }

    /**
     * Comprueba si el Pokémon puede evolucionar y devuelve la clase del Pokémon resultante.
     *
     * @param piedraEvolutiva Piedra opcional usada para evolucionar, puede ser null.
     * @return Clase del Pokémon resultante si puede evolucionar, null si no puede.
     */
    public Class<? extends Pokemon> comprobarEvolucion(Class<? extends Pokemon> piedraEvolutiva) {
        // Primero comprobamos evolución por nivel
        if (Evoluciones.containsKey(nivel)) {
            return Evoluciones.get(nivel);
        }

        // Si se proporcionó piedra evolutiva, comprobamos si coincide
        if (piedraEvolutiva != null && EvolucionesPorPiedra.containsKey(piedraEvolutiva)) {
            return EvolucionesPorPiedra.get(piedraEvolutiva);
        }

        // No puede evolucionar
        return null;
    }

    // ==============================
    // Métodos de estado alterado
    // ==============================

    /**
     * Aplica un estado alterado al Pokémon si aún no tiene uno activo.
     *
     * @param estado Estado alterado
     */
    public void aplicarEstado(EstadoAlterado estado) {
        if (estadoActual == null || !estadoActual.estaActivo()) {
            this.estadoActual = estado;
            estado.aplicar(this);
        }
    }

    /**
     * Limpia el estado alterado actual
     */
    public void limpiarEstado() {
        this.estadoActual = null;
    }

    public EstadoAlterado getEstadoActual() {
        return estadoActual;
    }

    // ==============================
    // Getters y setters
    // ==============================

    public String getNombre() {
        return nombre;
    }

    public Tipo getPrimertipo() {
        return primertipo;
    }

    public Tipo getSegundotipo() {
        return segundotipo;
    }

    public int getHp() {
        return hp;
    }

    public int getHpMax() {
        return hpMax;
    }

    public int getAtkFisico() {
        return atkFisico;
    }

    public int getDefFisico() {
        return defFisico;
    }

    public int getAtkEspecial() {
        return atkEspecial;
    }

    public int getDefEspecial() {
        return defEspecial;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public Naturaleza getNaturaleza() {
        return naturaleza;
    }

    public RatioCaptura getRatioCaptura() {
        return ratioCaptura;
    }
    public static int getBaseAtkesp() {
        return BASE_ATKESP;
    }

    public static int getBASEATK() {
        return BASEATK;
    }

    public static int getBASEDEF() {
        return BASEDEF;
    }

    public static int getBasedefEsp() {
        return BASEDEF_ESP;
    }

    public static int getBASEVEL() {
        return BASEVEL;
    }

    public static int getBASEHP() {
        return BASEHP;
    }
    public boolean isCapturable() {
        return capturable;
    }

    public Movimiento[] getMovimientos() {
        return Arrays.stream(movimientos)       // Convertimos el array a stream
                .filter(Objects::nonNull)  // Filtramos los que no son null
                .toArray(Movimiento[]::new); // Devolvemos un array solo con movimientos válidos
    }

    public int getnivel() {
        return nivel;
    }

    public int getXp() {
        return xp;
    }

    public void setHp(int hp) {
        this.hp = Math.min(hp, hpMax);
    }

    public void setAtkFisico(int atkFisico) {
        this.atkFisico = atkFisico;
    }

    public void setDefFisico(int defFisico) {
        this.defFisico = defFisico;
    }

    public void setAtkEspecial(int atkEspecial) {
        this.atkEspecial = atkEspecial;
    }

    public void setDefEspecial(int defEspecial) {
        this.defEspecial = defEspecial;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void setMovimientos(Movimiento[] movimientos) {
        this.movimientos = movimientos;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Map<Integer, Movimiento> getMovimientosPorNivel() {
        return MovimientosPorNivel;
    }

    public void setMovimientosPorNivel(Map<Integer, Movimiento> movimientosPorNivel) {
        MovimientosPorNivel = movimientosPorNivel;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("=== Pokémon ===\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Nivel: ").append(nivel).append(" | XP: ").append(xp).append("\n");
        sb.append("Tipo: ").append(primertipo);
        if (segundotipo != null) sb.append("/").append(segundotipo);
        sb.append("\n");
        sb.append("Naturaleza: ").append(naturaleza).append("\n");
        sb.append("Capturable: ").append(capturable).append(" | Ratio de captura: ").append(ratioCaptura).append("\n\n");

        sb.append("--- Stats ---\n");
        sb.append(String.format("HP: %d/%d (Base: %d | IV: %d)\n", hp, hpMax, BASEHP, ivHp));
        sb.append(String.format("Atk Físico: %d (Base: %d | IV: %d)\n", atkFisico, BASEATK, ivAtkFisico));
        sb.append(String.format("Def Físico: %d (Base: %d | IV: %d)\n", defFisico, BASEDEF, ivDefFisico));
        sb.append(String.format("Atk Especial: %d (Base: %d | IV: %d)\n", atkEspecial, BASE_ATKESP, ivAtkEspecial));
        sb.append(String.format("Def Especial: %d (Base: %d | IV: %d)\n", defEspecial, BASEDEF_ESP, ivDefEspecial));
        sb.append(String.format("Velocidad: %d (Base: %d | IV: %d)\n\n", velocidad, BASEVEL, ivVelocidad));

        sb.append("--- Movimientos ---\n");
        for (int i = 0; i < movimientos.length; i++) {
            if (movimientos[i] != null) {
                sb.append((i + 1)).append(": ").append(movimientos[i].getNombre()).append("\n");
            } else {
                sb.append((i + 1)).append(": [Vacío]\n");
            }
        }

        sb.append("\n--- Estado Actual ---\n");
        sb.append(estadoActual != null && estadoActual.estaActivo() ? estadoActual : "Ninguno").append("\n");

        return sb.toString();
    }
}