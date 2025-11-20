package Pokemon;

public class Charmander extends Pokemon {
    public Charmander() {
        //Las stats del charmander por defecto
        super("Charmander", 100, 35, 15, "fuego");
    }

    //Movimietos charmander
    Ataque ascuas = new Ataque("Ascuas","fuego",25); //Ascuas
    Ataque arañazo = new Ataque("Arañazo","normal",20);
    @Override
    public void movimiento1(Pokemon enemigo) { //Primer movimiento
        UsarAtaque(ascuas , enemigo);;
    }
    @Override
    public void movimiento2(Pokemon enemigo) { //Segundo movimiento
        UsarAtaque(arañazo , enemigo);
    }


}
