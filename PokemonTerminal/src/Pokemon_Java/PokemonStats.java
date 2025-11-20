package Pokemon_Java;

import java.util.Scanner;

public class PokemonStats {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Nombre del pokemon
        System.out.print("¡Has capturado un Pokémon! ponle el nombre que quieras: ");
        String nombrePokemon = scanner.nextLine();  // lee el nombre
        System.out.println("¡"+nombrePokemon+ " está envenenado y a poca vida!");


        Pokemon pokemon = new Pokemon(nombrePokemon, "Snivy", "Planta", "Normal", "Estadisticas",0, 5, 3, 20, false , 10);

        int opcionelegida = 0;

        while (opcionelegida != 6) {

            // Mostrar menú
            System.out.println("\n¿Qué quieres hacer?");
            System.out.println("+-----------------------------+");
            System.out.println("| 1. Pokémon   2. Evolucionar |");
            System.out.println("| 3. Curar     4. Objetos     |");
            System.out.println("| 5. Combatir  6. Salir       |");
            System.out.println("+-----------------------------+");

            opcionelegida = scanner.nextInt();

            if (opcionelegida == 1) {
                pokemon.mostrarEstado();
            }
            else if (opcionelegida == 2) {
                pokemon.evolucionar();
                System.out.println("¡Tu pokémon ha evolucionado!\n");
            }
            else if (opcionelegida == 3) {

                int opcioncura = 0;
                //Mostrar menú de cura
                while (opcioncura != 3) {
                    System.out.println("\n¿Qué quieres usar?");
                    System.out.println("+----------------------------+");
                    System.out.println("| 1. Poción   2. Antídoto    |");
                    System.out.println("| 3. Cancelar                |");
                    System.out.println("+----------------------------+");

                    opcioncura = scanner.nextInt();

                    if (opcioncura == 1) pokemon.usarPocion();
                    else if (opcioncura == 2) pokemon.usarAntidoto();
                    else if (opcioncura == 3) System.out.println("...");
                    else System.out.println("Opción no válida.");
                }
            }
            else if (opcionelegida == 4) {

                int opcionobjeto = 0;
                //Mostrar menú de objetos
                while (opcionobjeto != 4) {
                    System.out.println("\n¿Qué quieres usar?");
                    System.out.println("+---------------------------------+");
                    System.out.println("| 1. Caramelo         2. Baya     |");
                    System.out.println("| 3. Piedra evol.     4. Cancelar |");
                    System.out.println("+---------------------------------+");

                    opcionobjeto = scanner.nextInt();

                    if (opcionobjeto == 1) pokemon.usarCarameloraro();
                    else if (opcionobjeto == 2) pokemon.usarBayas();
                    else if (opcionobjeto == 3) pokemon.evolucionar();
                    else if (opcionobjeto == 4) System.out.println("...");
                    else System.out.println("Opción no válida.");
                }
            }
            else if (opcionelegida == 5){
                int opcionobjeto = 0;
                //Mostrar menú de combate
                while (opcionobjeto != 1) {     //*** SIN TERMINAR ***//
                    System.out.println("\n¿Qué quieres usar?");
                    System.out.println("+---------------------------------+");
                    System.out.println("| 1. Caramelo         2. Baya     |");
                    System.out.println("| 3. Piedra evol.     4. Cancelar |");
                    System.out.println("+---------------------------------+");

                    opcionobjeto = scanner.nextInt();

                    if (opcionobjeto == 1) pokemon.usarCarameloraro();
                    else if (opcionobjeto == 2) pokemon.usarBayas();
                    else if (opcionobjeto == 3) pokemon.evolucionar();
                    else if (opcionobjeto == 4) System.out.println("...");
                    else System.out.println("Opción no válida.");
                }
                pokemon.combate_pokemon();
            }
        }
        scanner.close();
    }
}

