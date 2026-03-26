package PokemonTerminal.Pokemon;

import PokemonTerminal.Estados.Paralisis;
import PokemonTerminal.Estados.Quemadura;
import PokemonTerminal.Items.Evolutivos.ItemEvolutivo;
import PokemonTerminal.Movimientos.Movimiento;
import PokemonTerminal.Tipos.*;
import PokemonTerminal.Estados.EstadoAlterado;

import java.util.*;

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
    private final int BASEHP;
    private final int BASEATK;
    private final int BASEDEF;
    private final int BASE_ATKESP;
    private final int BASEDEF_ESP;
    private final int BASEVEL;

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
    protected Map<Class<? extends ItemEvolutivo>, Class<? extends Pokemon>> EvolucionesPorItem = new HashMap<>();
    protected Set<Class<? extends Movimiento>> mtCompatibles = new HashSet<>();
    private Set<Movimiento> movimientosConocidos = new HashSet<>();

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
    private Movimiento[] movimientos;

    // ==============================
    // Estado alterado actual
    // ==============================
    private EstadoAlterado estadoActual;
    // ==============================
    // Diseño
    // ==============================
    public static final String RESET = "\u001B[0m";
    public static final String NEGRITA = "\u001B[1m";
    public static final String SUBRAYADO = "\u001B[4m";


    public static final String NEGRO = "\u001B[30m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String PURPURA = "\u001B[35m";
    public static final String CIAN = "\u001B[36m";
    public static final String BLANCO = "\u001B[37m";

    // Colores de fondo (Background)
    public static final String BG_NEGRO = "\u001B[40m";
    public static final String BG_ROJO = "\u001B[41m";
    public static final String BG_VERDE = "\u001B[42m";
    public static final String BG_AMARILLO = "\u001B[43m";
    public static final String BG_AZUL = "\u001B[44m";
    public static final String BG_PURPURA = "\u001B[45m";
    public static final String BG_CIAN = "\u001B[46m";
    public static final String BG_BLANCO = "\u001B[47m";
    // ==============================
    // Constructor
    // ==============================
    public Pokemon(String nombre, Tipo primertipo, Tipo segundotipo,
                   int baseHP, int baseAtk, int baseDef,
                   int baseAtkEsp, int baseDefEsp, int baseVel,
                   Naturaleza naturaleza, RatioCaptura ratioCaptura,
                   Movimiento[] movimientos, boolean capturable , EstadoAlterado estadoActual) {

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
        //Estado actual
        this.estadoActual = estadoActual;
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
        this.EvolucionesPorItem = new HashMap<>();
        this.movimientosConocidos = new HashSet<>();

        // Al final del constructor, después de inicializar movimientosConocidos
        if (this.movimientos != null) {
            for (Movimiento m : this.movimientos) {
                if (m != null) this.movimientosConocidos.add(m);
            }
        }
    }

    // ==============================
    // Movimientos
    // ==============================

    public Movimiento escogerMovimiento(int slot) {
        if (slot < 1 || slot > 4) return null;
        return movimientos[slot - 1];
    }

    public void setMovimiento(int slot, Movimiento movimiento) {
        this.aprenderMovimiento(movimiento,slot);
    }
    /**
     * Intenta aprender un movimiento. Si hay hueco, lo aprende.
     * Si no, lo guarda en la "memoria" para aprenderlo luego.
     */
    public boolean aprenderMovimiento(Movimiento nuevo, int slot) {
        if (nuevo == null) return false;

        // 1. Siempre al baúl de recuerdos (CEREBRO)
        this.movimientosConocidos.add(nuevo);

        // 2. EQUIPAR (MANO)
        // Si slot es 0, busca el primer hueco vacío (útil al subir de nivel)
        if (slot == 0) {
            for (int i = 0; i < 4; i++) {
                if (this.movimientos[i] == null) {
                    this.movimientos[i] = nuevo;
                    return true;
                }
            }
            return false;
        }

        // Si se especifica un slot (1-4), sobreescribimos el índice (0-3)
        if (slot >= 1 && slot <= 4) {
            this.movimientos[slot - 1] = nuevo;
            return true;
        }

        return false;
    }
    /**
     * Intercambia un movimiento del equipo por uno de la memoria.
     * @param slot Slot del equipo (1-4).
     * @param movRecordar El movimiento de la lista de conocidos.
     */
    public void recordarMovimiento(int slot, Movimiento movRecordar) {
        if (slot < 1 || slot > 4 || !movimientosConocidos.contains(movRecordar)) return;

        // Guardamos el que vamos a olvidar en la memoria (por si acaso no estaba)
        if (movimientos[slot - 1] != null) {
            movimientosConocidos.add(movimientos[slot - 1]);
        }

        // Ponemos el nuevo
        movimientos[slot - 1] = movRecordar;
    }

    public void mostrarMovimientosConocidos() {
        System.out.println("\n--- Movimientos disponibles para " + nombre + " ---");
        int i = 1;
        for (Movimiento m : movimientosConocidos) {
            System.out.println("[" + i + "] " + m.getNombre() + " (Tipo: " + m.getTipo() + ")");
            i++;
        }
    }

    public void llenarSlotsAutomaticamente() {
        // Limpiamos los slots actuales para no duplicar
        for (int i = 0; i < 4; i++) movimientos[i] = null;

        // Buscamos los movimientos de mayor nivel hacia abajo
        int slotsOcupados = 0;
        for (int lvl = this.nivel; lvl >= 1 && slotsOcupados < 4; lvl--) {
            Movimiento mov = MovimientosPorNivel.get(lvl);
            if (mov != null) {
                movimientos[slotsOcupados] = mov;
                slotsOcupados++;
            }
        }
    }
    /**
     * Revisa el mapa de movimientos por nivel y añade a 'conocidos'
     * todos los que el Pokémon ya debería saber por su nivel actual.
     */
    public void actualizarMemoria() {
        for (Map.Entry<Integer, Movimiento> entrada : MovimientosPorNivel.entrySet()) {
            if (this.nivel >= entrada.getKey()) {
                // Solo lo añadimos a la memoria, no a los slots de combate aún
                this.movimientosConocidos.add(entrada.getValue());
            }
        }
    }

    /**
     * Añade un movimiento a la lista de compatibles por MT para este Pokémon.
     * Se usa normalmente en el constructor de las especies específicas.
     * @param claseMov La clase del movimiento (ej. RayoHielo.class).
     */
    protected void añadirMTCompatible(Class<? extends Movimiento> claseMov) {
        this.mtCompatibles.add(claseMov);
    }
    /**
     * Verifica si un movimiento es compatible con el Pokémon, ya sea porque
     * lo aprende por nivel o porque está en su lista de MT compatibles.
     * * @param mov El movimiento a comprobar.
     * @return {@code true} si puede aprenderlo, {@code false} en caso contrario.
     */
    public boolean puedeAprender(Movimiento mov) {
        // 1. Comprobamos si está en su mapa de movimientos por nivel
        if (getMovimientosPorNivel().containsValue(mov)) {
            return true;
        }
        // 2. Comprobamos si su clase está en el Set de MT compatibles
        return mtCompatibles.contains(mov.getClass());
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
    // Metodo atacar
    // ==============================


    // ==============================
    // Métodos de progreso
    // ==============================
    protected Pokemon subirNivel() {
        nivel++; // Subimos el nivel
        recalcularStats();

        // Aprender movimientos correspondientes al nuevo nivel
        Movimiento nuevoMov = MovimientosPorNivel.get(nivel);
        if (nuevoMov != null) {
            aprenderMovimiento(nuevoMov, 0); // slot 0 = busca espacio libre
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
        if (piedraEvolutiva != null && EvolucionesPorItem.containsKey(piedraEvolutiva)) {
            return EvolucionesPorItem.get(piedraEvolutiva);
        }

        // No puede evolucionar
        return null;
    }
    // ==============================
    // Evoluciones
    // ==============================
    /**
     * Intenta evolucionar al Pokémon usando un ítem específico.
     * @param item El objeto que se intenta usar.
     * @return La nueva instancia del Pokémon si evolucionó, o el mismo si no.
     */
    public Pokemon usarItemEvolutivo(ItemEvolutivo item) {
        Class<? extends Pokemon> claseEvolucion = EvolucionesPorItem.get(item.getClass());

        if (claseEvolucion != null) {
            System.out.println("¡" + this.getNombre() + " está reaccionando al ítem!");
            // Aquí llamas al método que crea la nueva instancia (ej. ejecutarEvolucion que vimos antes)
            return this.ejecutarEvolucion(claseEvolucion);
        } else {
            System.out.println("No parece que " + this.getNombre() + " pueda usar eso...");
            return this;
        }
    }
    public Pokemon ejecutarEvolucion(Class<? extends Pokemon> nuevaEspecie) {
        try {
            // 1. Instanciar la nueva especie (ej. Charmeleon) usando su constructor (nivel, xp)
            Pokemon evolucion = nuevaEspecie
                    .getDeclaredConstructor(int.class, int.class)
                    .newInstance(this.getnivel(), this.getXp());

            // 2. Transferir el "ADN" (IVs) para que no cambien tras evolucionar
            evolucion.ivHp = this.ivHp;
            evolucion.ivAtkFisico = this.ivAtkFisico;
            evolucion.ivDefFisico = this.ivDefFisico;
            evolucion.ivAtkEspecial = this.ivAtkEspecial;
            evolucion.ivDefEspecial = this.ivDefEspecial;
            evolucion.ivVelocidad = this.ivVelocidad;

            // 3. Transferir los movimientos actuales que ya conocía
            evolucion.getMovimientosConocidos().addAll(this.movimientosConocidos);

            // 4. Recalcular los Stats con las BASES de la nueva especie y los IVs viejos
            evolucion.recalcularStats();

            System.out.println("¡Felicidades! Tu " + this.getNombre() + " ha evolucionado en " + evolucion.getNombre() + "!");
            return evolucion;

        } catch (Exception e) {
            System.err.println("Error crítico en la evolución: " + e.getMessage());
            return this; // Si algo falla (ej. no existe el constructor), devolvemos el original
        }
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

    public String getEstadoActual() {

        if (this.estadoActual == null) {
            return "Ninguno";
        }


        if (this.estadoActual instanceof Paralisis) {
            return "Paralisis";
        }

        if (this.estadoActual instanceof Quemadura) {
            return "Quemado";
        }

        return "Otro Estado";
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
    public  int getBaseAtkesp() {
        return BASE_ATKESP;
    }

    public  int getBASEATK() {
        return BASEATK;
    }

    public  int getBASEDEF() {
        return BASEDEF;
    }

    public  int getBasedefEsp() {
        return BASEDEF_ESP;
    }

    public  int getBASEVEL() {
        return BASEVEL;
    }

    public  int getBASEHP() {
        return BASEHP;
    }
    public boolean isCapturable() {
        return capturable;
    }

    public Movimiento[] getMovimientos() {
        return this.movimientos;
    }
    public Set<Movimiento> getMovimientosConocidos() {
        return movimientosConocidos;
    }

    public int getnivel() {
        return nivel;
    }

    public int getXp() {
        return xp;
    }


    public void setHp(int hp) {
        // Math.max(0, ...) impide que la vida sea negativa.
        // Math.min(..., hpMax) impide que la vida supere el máximo.
        this.hp = Math.max(0, Math.min(hp, hpMax));
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

    public static String getColorPorTipo(Tipo tipo) {
        if (tipo == null) return "\u001B[37m"; // Blanco por defecto

        return switch (tipo) {
            case FUEGO -> "\u001B[31m";    // Rojo
            case AGUA -> "\u001B[34m";     // Azul
            case PLANTA -> "\u001B[32m";   // Verde
            case ELECTRICO -> "\u001B[33m"; // Amarillo
            case HIELO -> "\u001B[36m";    // Cian claro
            case VENENO -> "\u001B[35m";   // Púrpura
            case TIERRA -> "\u001B[33m";   // Marrón/Amarillo
            case VOLADOR -> "\u001B[36m";  // Cian
            case PSIQUICO -> "\u001B[35m"; // Rosa/Fucsia
            case BICHO -> "\u001B[32m";    // Verde oliva
            case ROCA -> "\u001B[30m";     // Gris oscuro (o usar Bold)
            case FANTASMA -> "\u001B[35m"; // Violeta
            case DRAGON -> "\u001B[34m";   // Azul oscuro
            case ACERO -> "\u001B[37m";    // Gris plata
            case HADA -> "\u001B[95m";     // Rosado
            default -> "\u001B[37m";       // Blanco (Normal)
        };
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // --- 1. PREPARACIÓN DE DATOS ---
        double porcentajeHP = Math.max(0, Math.min(1, (double) hp / hpMax));
        String colorHP = (porcentajeHP > 0.5) ? VERDE : (porcentajeHP > 0.2) ? AMARILLO : ROJO;
        String colorEstado = (estadoActual == null) ? VERDE : ROJO;
        String colorTipoP = getColorPorTipo(primertipo);
        String txtEstado = (estadoActual == null) ? "SANO" : getEstadoActual().toUpperCase();

        // Iconos de apoyo
        String iHP = "❤️ ", iAtk = "⚔️ ", iDef = "🛡️ ", iSpA = "🔮 ", iSpD = "🔰 ", iVel = "👟 ";
        String iconoTipo = (primertipo == Tipo.FUEGO) ? "🔥 " : (primertipo == Tipo.AGUA) ? "💧 " : "🌀 ";

        // --- 2. CONSTRUCCIÓN VISUAL ---

        // Línea superior decorativa
        sb.append(colorTipoP).append("------------------------------------------------------------").append(RESET).append("\n");

        // LÍNEA 1: Identidad (Sin fondo, solo texto y color)
        sb.append("  ").append(NEGRITA).append(colorTipoP).append(nombre.toUpperCase()).append(RESET);
        sb.append(String.format("  [Lv.%-3d]  │  %sXP: %-10d%s\n", nivel, CIAN, xp, RESET));

        // LÍNEA 2: Tipo y Estado
        sb.append("  ").append(iconoTipo).append(colorTipoP).append(primertipo);
        if (segundotipo != null) sb.append("/").append(segundotipo);
        sb.append(RESET).append(String.format("  │  Nat: %-12s  │  Estado: %s%s%s\n", naturaleza, colorEstado, txtEstado, RESET));

        sb.append("  ").append("─".repeat(56)).append("\n");

        // LÍNEA 3: Salud (HP) con barra de textura
        int rellenas = (int)(porcentajeHP * 10);
        String barra = colorHP + "▓".repeat(rellenas) + RESET + "░".repeat(10 - rellenas);
        sb.append("  ").append(iHP).append(NEGRITA).append("HP ").append(RESET).append("│ ").append(barra);
        sb.append(String.format("  %s[%d/%d]%s\n", colorHP, hp, hpMax, RESET));

        sb.append("  ").append("─".repeat(56)).append("\n");

        // LÍNEA 4: Stats con Iconos
        sb.append("  ").append(iAtk).append(ROJO).append("Atk:").append(RESET).append(String.format(" %-3d  ", atkFisico));
        sb.append(iDef).append(AZUL).append("Def:").append(RESET).append(String.format(" %-3d  ", defFisico));
        sb.append(iSpA).append(PURPURA).append("SpA:").append(RESET).append(String.format(" %-3d\n", atkEspecial));

        sb.append("  ").append(iSpD).append(CIAN).append("SpD:").append(RESET).append(String.format(" %-3d  ", defEspecial));
        sb.append(iVel).append(AMARILLO).append("Vel:").append(RESET).append(String.format(" %-3d\n", velocidad));

        sb.append("  ").append("─".repeat(56)).append("\n");

        // LÍNEA 5: Movimientos (Vertical con iconos)
        sb.append("  ").append(NEGRITA).append("⚔️  MOVIMIENTOS:").append(RESET).append("\n");
        for (int i = 0; i < 4; i++) {
            if (movimientos[i] != null) {
                String colorMov = getColorPorTipo(movimientos[i].getTipo());
                sb.append("   ").append(NEGRITA).append("[").append(i + 1).append("] ").append(RESET);
                sb.append(colorMov).append(String.format("%-15s", movimientos[i].getNombre())).append(RESET).append("\n");
            } else {
                sb.append("   ").append(String.format("[%d] %-15s\n", (i + 1), "----------"));
            }
        }

        // Línea inferior decorativa
        sb.append(colorTipoP).append("------------------------------------------------------------").append(RESET).append("\n");

        return sb.toString();
    }
    public void interfazGestionMovimientos() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            // --- CABECERA ---
            System.out.println("\n" + BG_CIAN + NEGRO + NEGRITA + " ".repeat(18) + " ADMINISTRADOR DE MOVIMIENTOS " + " ".repeat(18) + RESET);
            System.out.println(CIAN + "╔" + "═".repeat(60) + "╗" + RESET);
            System.out.println(CIAN + "║" + RESET + "  USUARIO: " + NEGRITA + nombre.toUpperCase() + RESET + " ".repeat(28 - nombre.length()) + " LV: " + nivel + "    " + CIAN + "║" + RESET);
            System.out.println(CIAN + "╚" + "═".repeat(60) + "╝" + RESET);

            // --- CUERPO ---
            System.out.println(NEGRITA + AZUL + "  [ EQUIPO DE COMBATE ]" + " ".repeat(17) + VERDE + "[ REGISTRO HISTÓRICO ]" + RESET);

            List<Movimiento> baul = new ArrayList<>(movimientosConocidos);
            int maxFilas = Math.max(4, baul.size());

            for (int i = 0; i < maxFilas; i++) {
                StringBuilder fila = new StringBuilder("  ");

                if (i < 4) {
                    String nombreM = (movimientos[i] != null) ? movimientos[i].getNombre() : "----------";
                    String colorM = (movimientos[i] != null) ? getColorPorTipo(movimientos[i].getTipo()) : BLANCO;
                    fila.append(AZUL + (i + 1) + " » " + RESET + colorM + String.format("%-18s", nombreM) + RESET);
                } else {
                    fila.append(" ".repeat(23));
                }

                fila.append(" ".repeat(6));

                if (i < baul.size()) {
                    Movimiento mb = baul.get(i);
                    fila.append(VERDE + "(" + (i + 1) + ") " + RESET + getColorPorTipo(mb.getTipo()) + String.format("%-18s", mb.getNombre()) + RESET);
                }
                System.out.println(fila.toString());
            }

            System.out.println(CIAN + "─".repeat(62) + RESET);
            System.out.println(NEGRITA + " ACCIÓN: " + RESET + "[Nº Baúl] [Slot Destino] | " + AMARILLO + "0 para salir" + RESET);
            System.out.print(CIAN + " > " + RESET);

            try {
                int indexBaul = sc.nextInt();
                if (indexBaul == 0) break;
                int slotDestino = sc.nextInt();

                if (indexBaul < 1 || indexBaul > baul.size() || slotDestino < 1 || slotDestino > 4) {
                    System.out.println(BG_ROJO + BLANCO + " ERROR: POSICIÓN INVÁLIDA " + RESET);
                    continue;
                }

                Movimiento movElegido = baul.get(indexBaul - 1);
                int indiceDestino = slotDestino - 1;

                // --- LÓGICA DE SUSTITUCIÓN LIMPIA ---

                // 1. Si el movimiento ya está en otro slot, lo borramos de esa posición antigua
                for (int i = 0; i < 4; i++) {
                    if (movimientos[i] != null && movimientos[i].equals(movElegido)) {
                        movimientos[i] = null;
                    }
                }

                // 2. Colocamos el movimiento en el slot deseado (esto sustituye automáticamente lo que hubiera allí)
                movimientos[indiceDestino] = movElegido;

                System.out.println(BG_VERDE + NEGRO + " ✔ " + movElegido.getNombre().toUpperCase() + " ASIGNADO AL SLOT " + slotDestino + " " + RESET);
                Thread.sleep(600);

            } catch (Exception e) {
                System.out.println(ROJO + "Entrada inválida." + RESET);
                sc.nextLine();
            }
        }
    }
}