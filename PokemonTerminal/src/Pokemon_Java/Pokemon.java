package Pokemon_Java;

import java.util.Random;

public class Pokemon {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    private final String nombre;
    private String especie;
    private final String tipo;
    private final String estado;
    private String estadisticas;
    private int amistad;
    private int nivel;
    private int vida;
    private int vidamax;
    private boolean envenenado;
    private int ataque;

    public Pokemon(String nombre, String especie, String tipo, String estado, String estadisticas,
                   int amistad, int nivel, int vida, int vidamax, boolean envenenado, int ataque) {
        this.nombre = nombre;
        this.especie = especie;
        this.tipo = tipo;
        this.estado = estado;
        this.estadisticas = estadisticas;
        this.amistad = amistad;
        this.nivel = nivel;
        this.vida = vida;
        this.vidamax = vidamax;
        this.envenenado = envenenado;
        this.ataque= ataque;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }

    public int getAmistad() {
        return amistad;
    }

    public int getNivel() {
        return nivel;
    }

    public int getVida() {
        return vida;
    }

    public int getVidamax() {
        return vidamax;
    }

    public int getAtaque() {
        return ataque;
    }

    public boolean getEnvenenado() {
        return envenenado;
    }

    // Métodos de objetos y curas
    public void usarCarameloraro() {
        nivel += 1;
        vida += 3;
        vidamax += 4;
        amistad += 1;
        System.out.println(GREEN + "¡" + nombre + " ha subido a nivel " + nivel + "!" + RESET);
    }

    public void usarAntidoto() {
        envenenado = false;
        amistad += 1;
        System.out.println(GREEN + "¡" + nombre + " ya no está envenenado!" + RESET);
    }

    public void usarPocion() {
        int vidafaltante = vidamax - vida;
        int cantidadcura = 20;
        int curaReal = Math.min(cantidadcura, vidafaltante);
        vida += curaReal;
        amistad += 1;
        System.out.println(GREEN + "¡" + nombre + " ha recuperado " + curaReal + " PS!" + RESET);
    }

    public void usarBayas() {
        amistad += 3;
        vida += 5;
        System.out.println(BLUE + "¡Tu amistad con " + nombre + " ha subido!" + RESET);
    }

    public void mostrarEstado() {


        System.out.println("+----------------------------------+");
        System.out.println("|"+GREEN +" "+ especie+ " " +RESET + "                    " + CYAN+" "+ nombre+" " +RESET);
        System.out.println("| Tipo: "+tipo+"             "+     vida+"PS/"+ vidamax+"PS");
        System.out.println("| Nivel "+nivel+"                Amistad: " +amistad);
        System.out.println("+----------------------------------+");
    }

    public void mostrarEstadisticas() {

    }


    public void evolucionar() {

        if (especie.equals("Snivy")) {
            nivel = 17;
            especie = "Servine"; //1ª Evolucion
            vida += 36;
            vidamax += 48;
            System.out.println(PURPLE + "¡" + nombre + " ha evolucionado a Servine!" + RESET);
            return;
        }

        if (especie.equals("Servine") && nivel >= 18) {
            nivel = 36;
            especie = "Serperior"; //2ª Evolucion
            vida += 57;
            vidamax += 76;
            System.out.println(PURPLE + "¡" + nombre + " ha evolucionado a Serperior!" + RESET);
            return;
        }
    } //*** SIN TERMINAR ***//
    Random rand = new Random();
    public void getVida_enemigo;
    int vida_enemigo = rand.nextInt(1,31);
    int ataque_enemigo = rand.nextInt(1,15);
    public void combate_pokemon(){

        if (vida_enemigo>0){
            System.out.println(nombre + " ha atacado | Vida del enemigo restante: "+ vida_enemigo);
            vida_enemigo-= ataque;
            System.out.println(" Te han herido | Vida restante: "+  vida);
            vida -= ataque_enemigo;
        }
    }
}