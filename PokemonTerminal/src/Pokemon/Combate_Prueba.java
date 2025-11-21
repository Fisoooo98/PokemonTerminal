package Pokemon;

import java.util.Scanner;

public class Combate_Prueba {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("¡Bienvenido al mundo Pokémon!");

        // --- Opción de cargar partida ---
        System.out.print("¿Quieres cargar partida? (1=Sí / 2=No): ");
        int cargar = sc.nextInt();

        Entrenador entrenador;
        PokemonGuardado[] equipo;

        if (cargar == 1) {
            DatosPartida datos = GestorPartida.cargarPartida("partida.txt");
            entrenador = datos.entrenador;
            equipo = new PokemonGuardado[datos.pokemons.length];
            for (int i = 0; i < datos.pokemons.length; i++) {
                Pokemon p = datos.pokemons[i];
                Ataque[] movs = (p instanceof PokemonGuardado) ? ((PokemonGuardado)p).movimientos
                        : new Ataque[]{ new Ataque("Ataque1","normal",20), new Ataque("Ataque2","normal",20) };
                equipo[i] = new PokemonGuardado(p.nombre, p.vida, p.ataque, p.defensa, p.tipo, movs);
            }
            System.out.println("Partida cargada. Entrenador: " + entrenador.nombre);
        } else {
            entrenador = new Entrenador("Javier", 1000, 1);
            PokemonGuardado[] pokemonsDisponibles = {
                    new PokemonGuardado("Charmander", 100, 35, 15, "fuego",
                            new Ataque[]{ new Ataque("Ascuas","fuego",25), new Ataque("Arañazo","normal",20)}),
                    new PokemonGuardado("Squirtle", 100, 30, 20, "agua",
                            new Ataque[]{ new Ataque("Pistola Agua","agua",30), new Ataque("Placaje","normal",20)}),
                    new PokemonGuardado("Bulbasaur", 100, 32, 18, "planta",
                            new Ataque[]{ new Ataque("Látigo Cepa","planta",25), new Ataque("Placaje","normal",20)})
            };
            equipo = elegirEquipo(pokemonsDisponibles, entrenador, 3);
        }

        // --- Pokémon enemigo ---
        PokemonGuardado enemigo = new PokemonGuardado("Rival", 120, 30, 20, "normal",
                new Ataque[]{ new Ataque("Ataque 1","normal",25), new Ataque("Ataque 2","normal",20)});
        System.out.println("\n¡Comienza la batalla contra " + enemigo.nombre + "!\n");

        int activo = 0; // índice del Pokémon activo del jugador

        // --- Bucle de batalla ---
        while (hayPokemonVivo(equipo) && enemigo.vida > 0) {

            PokemonGuardado actual = equipo[activo];
            System.out.println("\nTu Pokémon: " + actual.nombre + " | Vida: " + actual.vida);
            System.out.println("Enemigo: " + enemigo.nombre + " | Vida: " + enemigo.vida);

            // Opciones de turno
            System.out.println("\nElige acción:");
            System.out.println("1. Atacar");
            System.out.println("2. Curar");
            System.out.println("3. Cambiar Pokémon");
            int accion = sc.nextInt();

            switch (accion) {
                case 1: // Atacar
                    System.out.println("Elige ataque:");
                    System.out.println("1. " + actual.movimientos[0].nombre_atk);
                    System.out.println("2. " + actual.movimientos[1].nombre_atk);
                    int atk = sc.nextInt() - 1;
                    actual.UsarAtaque(actual.movimientos[atk], enemigo);
                    break;
                case 2: // Curar
                    Pocion pocion = new Pocion("Poción", 20,1,200);
                    actual.vida += pocion.vida;
                    System.out.println(actual.nombre + " curado. Vida actual: " + actual.vida);
                    break;
                case 3: // Cambiar Pokémon
                    System.out.println("Elige Pokémon activo:");
                    for (int i = 0; i < equipo.length; i++) {
                        System.out.println((i + 1) + ". " + equipo[i].nombre + " | Vida: " + equipo[i].vida);
                    }
                    int nuevo = sc.nextInt() - 1;
                    if (equipo[nuevo].vida > 0) {
                        activo = nuevo;
                        System.out.println("Ahora activo: " + equipo[activo].nombre);
                    } else {
                        System.out.println("Ese Pokémon está debilitado, elige otro.");
                    }
                    continue; // No pasa el turno al enemigo si solo cambias
            }

            // Comprobar si enemigo se debilitó
            if (enemigo.vida <= 0) {
                System.out.println("\n¡" + enemigo.nombre + " se debilitó! ¡Ganaste!");
                break;
            }

            // Turno enemigo simple: siempre ataque 1
            System.out.println("\nTurno enemigo: " + enemigo.nombre);
            enemigo.UsarAtaque(enemigo.movimientos[0], equipo[activo]);

            // Comprobar si todos tus Pokémon están debilitados
            if (!hayPokemonVivo(equipo)) {
                System.out.println("\nTodos tus Pokémon se debilitaron. ¡Perdiste!");
                break;
            }
        }

        // --- Guardar partida ---
        System.out.print("\n¿Quieres guardar la partida? (1=Sí / 2=No): ");
        int guardar = sc.nextInt();
        if (guardar == 1) {
            GestorPartida.guardarPartida(entrenador, equipo, "partida.txt");
        }

        sc.close();
    }

    // --------------------- FUNCIONES AUXILIARES ---------------------
    public static PokemonGuardado[] elegirEquipo(PokemonGuardado[] disponibles, Entrenador entrenador, int tamaño) {
        PokemonGuardado[] equipo = new PokemonGuardado[tamaño];
        System.out.println("\nElige tus Pokémon:");
        for (int i = 0; i < disponibles.length; i++) {
            System.out.println((i + 1) + ". " + disponibles[i].nombre + " | Vida: " + disponibles[i].vida);
        }
        for (int j = 0; j < tamaño; j++) {
            int opcion = sc.nextInt() - 1;
            equipo[j] = disponibles[opcion];
            System.out.println(entrenador.nombre + " eligió a " + equipo[j].nombre);
        }
        return equipo;
    }

    public static boolean hayPokemonVivo(PokemonGuardado[] equipo) {
        for (PokemonGuardado p : equipo) {
            if (p.vida > 0) return true;
        }
        return false;
    }
}
