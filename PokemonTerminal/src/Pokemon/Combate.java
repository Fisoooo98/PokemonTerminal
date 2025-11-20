package Pokemon;

public class Combate {

    public static void main(String[] args) {

        Pokemon squirtle = new Squirtle();
        Pokemon charmander = new Charmander();

        squirtle.movimiento1(charmander); //Ataca squirtle con el mov1
        charmander.movimiento1(squirtle); //Ataca charmander con el mov1
        squirtle.movimiento2(charmander);//Ataca squirtle con el mov2
        charmander.movimiento2(squirtle); //Ataca charmander con el mov2
    }
}