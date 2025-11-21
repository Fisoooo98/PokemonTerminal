package Pokemon;

import java.util.Scanner;

public class Combate {

    public static void main(String[] args) {
        System.out.println("Bienvenido al mundo pokemon");
        ElegirPokemon();
        Consumirpocion();

    }

    public static void ElegirPokemon() {
        Scanner sc = new Scanner(System.in);
        Entrenador entrenador = (new Entrenador("Fiso", 1000, 0));
        Pokemon pokemonsDisponibles[] = new Pokemon[2];
        {
            pokemonsDisponibles[0] = new Charmander();
            pokemonsDisponibles[1] = new Squirtle();
        }
        Pokemon[] equipojugador = new Pokemon[3]; //Creamos los pokemons del jugador
        //Elegir pokemons
        System.out.println("Cual pokemon quieres elegir: ");
        for (int i = 0; i < pokemonsDisponibles.length; i++) {
            System.out.println(i + 1 + "." + pokemonsDisponibles[i].nombre);
        }
        for (int j = 0; j < 1; j++) {
            int opcion = sc.nextInt();
            opcion -= 1;
            System.out.println(entrenador.getnombre() + " ha elegido a " + pokemonsDisponibles[opcion].nombre);
        }

    }

    public static void Consumirpocion() {
        Scanner sc = new Scanner(System.in);
        Pocion pocion = new Pocion(20, 1, 200);
        Pokemon pokemonsDisponibles[] = new Pokemon[2];
        {
            pokemonsDisponibles[0] = new Charmander();
            pokemonsDisponibles[1] = new Squirtle();
        }
        Pokemon[] equipojugador = new Pokemon[3]; //Creamos los pokemons del jugador
        //Elegir pokemons
        System.out.println("Cual pokemon quieres curar: ");
        for (int i = 0; i < pokemonsDisponibles.length; i++) {
            System.out.println(i + 1 + "." + pokemonsDisponibles[i].nombre);
        }
        for (int j = 0; j < equipojugador.length; j++) {
            int opcion = sc.nextInt();
            opcion -= 1;
            System.out.println(" has curado a " + pokemonsDisponibles[opcion].nombre);
            int pokemon_curado = pokemonsDisponibles[opcion].vida + pocion.vida;
            System.out.println(pokemon_curado);
        }
    }
}



