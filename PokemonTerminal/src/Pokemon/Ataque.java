package Pokemon;

public class Ataque {
    String nombre_atk;
    String tipo_atk;
    double ataque_atk;
    public Ataque(String nombre, String tipo, double ataque) {
        this.nombre_atk = nombre;
        this.tipo_atk = tipo;
        this.ataque_atk = ataque;
    }

    //Getters
    public String getNombre() {
        return nombre_atk;
    }

}
