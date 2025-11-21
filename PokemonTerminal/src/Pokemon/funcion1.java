package Pokemon;

import java.util.Scanner;

public class funcion1 {
    public static int Tienda() {
        //Constructores
        Scanner scanner = new Scanner(System.in);
        Entrenador entrenador = new Entrenador("Amador", 3,4);
        Pocion pocion = new Pocion(20,1,200);
        int opcionProducto = 0;
        int gastar;
        return opcionProducto;
    }
    public static void menuTienda() {
        System.out.println("_|========================================|_");
        System.out.println("|              TIENDA POKÉMON              |");
        System.out.println("|                                          |");
        System.out.println("|  Poción                       (Cura 20PS)|");
        System.out.println("|  Superpoción                  (Cura 60PS)|");
        System.out.println("|  Hiperpoción                 (Cura 120PS)|");
        System.out.println("|  Poción Máx.    (Reestablece la vida del |");
        System.out.println("|                  pokémon entera)         |");
        System.out.println("|  Restaurar Todo (Reestablece la vida del |");
        System.out.println("|              pokemon y efectos negativos)|");
        System.out.println("|                                          |");
        System.out.println("|                                          |");
        System.out.println("|                                          |");
        System.out.println("-__________________________________________-");
    }
}
