package Pokemon;

public class Squirtle extends Pokemon {
    public Squirtle() {
        //Stats squirtle por defecto
        super("Squirtle", 100, 30, 20, "agua");
    }
    //Movimientos Squirtle
    Ataque pistolaAgua = new Ataque("Pistola Agua", "agua", 30);
    Ataque placaje     = new Ataque("Placaje", "normal", 20);

    @Override
    public void movimiento1(Pokemon enemigo) { //Primer movimiento
        UsarAtaque(pistolaAgua , enemigo);
    }
    @Override
    public void movimiento2(Pokemon enemigo) { //Segundo movimiento
        UsarAtaque(placaje , enemigo);
    }
}
