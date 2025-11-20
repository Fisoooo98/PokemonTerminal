package Pokemon;

abstract class Pokemon {
    String nombre;
    int vida;
    int ataque;
    int defensa;
    String tipo;
    Pokemon(String nombre, int vida, int ataque, int defensa, String tipo) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.tipo = tipo;
    }

    //Movimientos en la clase enemigo
    abstract void movimiento1(Pokemon enemigo);
    abstract void movimiento2(Pokemon enemigo);

    //Creamos un metodo que va a servir para atacar
    public void UsarAtaque(Ataque atk,Pokemon enemigo) {
        System.out.println(nombre + " usa " + atk.nombre_atk + " !");
        double multiplicador = Tabladetipos.multiplicador(atk.tipo_atk, enemigo.tipo); //Usamos el tipo de ataque y la el tipo que es el pokemon
        double damage = ((ataque + atk.ataque_atk) - enemigo.defensa) * multiplicador;
        enemigo.vida -= damage;
        if (multiplicador > 1){
            System.out.println("Es muy eficaz!!");
        }else if (multiplicador == 1){
            System.out.println("Es efectivo");
        }else if (multiplicador < 1){
            System.out.println("Es poco eficaz");
        }else {
            System.out.println("No le ga dado");
        }
        System.out.println("Daño causado: " + damage);
        System.out.println("Vida restante: " + enemigo.vida);
    }

}
