package Pokemon;

import java.io.*;
import java.util.ArrayList;

// --------------------- CLASES DE GUARDADO ---------------------
class PokemonGuardado extends Pokemon {
    public Ataque[] movimientos;

    public PokemonGuardado(String nombre, int vida, int ataque, int defensa, String tipo, Ataque[] movimientos) {
        super(nombre, vida, ataque, defensa, tipo);
        this.movimientos = movimientos;
    }

    @Override
    void movimiento1(Pokemon enemigo) {
        if (movimientos.length > 0) UsarAtaque(movimientos[0], enemigo);
    }

    @Override
    void movimiento2(Pokemon enemigo) {
        if (movimientos.length > 1) UsarAtaque(movimientos[1], enemigo);
    }
}

class DatosPartida {
    public Entrenador entrenador;
    public Pokemon[] pokemons;

    public DatosPartida(Entrenador entrenador, Pokemon[] pokemons) {
        this.entrenador = entrenador;
        this.pokemons = pokemons;
    }
}

// --------------------- GESTOR DE PARTIDA ---------------------
public class GestorPartida {

    // --- GUARDAR PARTIDA ---
    public static void guardarPartida(Entrenador entrenador, Pokemon[] pokemons, String archivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            // Entrenador
            bw.write("#ENTRENADOR"); bw.newLine();
            bw.write(entrenador.nombre); bw.newLine();
            bw.write(entrenador.dinero + ""); bw.newLine();
            bw.write(entrenador.medallas + ""); bw.newLine();

            // Pokémon
            for (Pokemon p : pokemons) {
                if (p != null) {
                    bw.write("#POKEMON"); bw.newLine();
                    bw.write(p.nombre); bw.newLine();
                    bw.write(p.vida + ""); bw.newLine();
                    bw.write(p.ataque + ""); bw.newLine();
                    bw.write(p.defensa + ""); bw.newLine();
                    bw.write(p.tipo); bw.newLine();

                    // Guardar movimientos si existen
                    if (p instanceof PokemonGuardado) {
                        Ataque[] movs = ((PokemonGuardado) p).movimientos;
                        bw.write(movs.length + ""); bw.newLine();
                        for (Ataque atk : movs) {
                            bw.write(atk.nombre_atk); bw.newLine();
                            bw.write(atk.tipo_atk); bw.newLine();
                            bw.write(atk.ataque_atk + ""); bw.newLine();
                        }
                    } else {
                        bw.write("2"); bw.newLine(); // 2 movimientos por defecto
                        if (p instanceof Charmander) {
                            bw.write("Ascuas"); bw.newLine();
                            bw.write("fuego"); bw.newLine();
                            bw.write("25"); bw.newLine();
                            bw.write("Arañazo"); bw.newLine();
                            bw.write("normal"); bw.newLine();
                            bw.write("20"); bw.newLine();
                        } else if (p instanceof Squirtle) {
                            bw.write("Pistola Agua"); bw.newLine();
                            bw.write("agua"); bw.newLine();
                            bw.write("30"); bw.newLine();
                            bw.write("Placaje"); bw.newLine();
                            bw.write("normal"); bw.newLine();
                            bw.write("20"); bw.newLine();
                        }
                    }

                    bw.write("#END"); bw.newLine();
                }
            }
            System.out.println("✔ Partida guardada correctamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- CARGAR PARTIDA ---
    public static DatosPartida cargarPartida(String archivo) {
        Entrenador entrenador = null;
        ArrayList<Pokemon> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.equals("#ENTRENADOR")) {
                    String nombre = br.readLine();
                    int dinero = Integer.parseInt(br.readLine());
                    int medallas = Integer.parseInt(br.readLine());
                    entrenador = new Entrenador(nombre, dinero, medallas);
                }
                if (linea.equals("#POKEMON")) {
                    String nombre = br.readLine();
                    int vida = Integer.parseInt(br.readLine());
                    int ataque = Integer.parseInt(br.readLine());
                    int defensa = Integer.parseInt(br.readLine());
                    String tipo = br.readLine();

                    int numMovs = Integer.parseInt(br.readLine());
                    Ataque[] movimientos = new Ataque[numMovs];
                    for (int i = 0; i < numMovs; i++) {
                        String nombreAtk = br.readLine();
                        String tipoAtk = br.readLine();
                        double atk = Double.parseDouble(br.readLine());
                        movimientos[i] = new Ataque(nombreAtk, tipoAtk, atk);
                    }

                    Pokemon p = new PokemonGuardado(nombre, vida, ataque, defensa, tipo, movimientos);
                    lista.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pokemon[] todos = lista.toArray(new Pokemon[0]);
        return new DatosPartida(entrenador, todos);
    }
}
