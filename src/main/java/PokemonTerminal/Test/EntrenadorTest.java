package PokemonTerminal.Test;

import PokemonTerminal.Entrenador.Entrenador;
import PokemonTerminal.Pokemon.Pokedex.Charmander;
import PokemonTerminal.Pokemon.Pokedex.Squirtle;
import PokemonTerminal.Pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class EntrenadorTest {
    public static void main(String[] args) {
        // 1. Inicialización del Entrenador
        Entrenador entrenador = new Entrenador("Javi", 5000.0);

        // 2. Creación de Pokémon (Niveles altos para ver evoluciones)
        // Charmander -> Charmeleon -> Charizard
        Charmander c1 = new Charmander(5, 0);
        Pokemon charizard = c1.ganarXP(400000);

        // Squirtle -> Wartortle -> Blastoise
        Squirtle s1 = new Squirtle(5, 0);
        Pokemon blastoise = s1.ganarXP(600000);

        // Otros para rellenar
        Pokemon charmeleon = new Charmander(5, 0).ganarXP(20000);
        Pokemon wartortle = new Squirtle(5, 0).ganarXP(25000);
        Pokemon charmanderPeque = new Charmander(5, 0);
        Pokemon squirtlePeque = new Squirtle(5, 0);

        // ---------------------------------------------------------
        // TEST 1: Llenar el equipo (6 pokémon)
        // ---------------------------------------------------------
        entrenador.añadirAlEquipo(charizard, 1);
        entrenador.añadirAlEquipo(blastoise, 2);
        entrenador.añadirAlEquipo(charmeleon, 3);
        entrenador.añadirAlEquipo(wartortle, 4);
        entrenador.añadirAlEquipo(charmanderPeque, 5);
        entrenador.añadirAlEquipo(squirtlePeque, 6);


        entrenador.interfazGestionPC();
    }
}
