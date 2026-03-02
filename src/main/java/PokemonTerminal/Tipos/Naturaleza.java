package PokemonTerminal.Tipos;

public enum Naturaleza {

    AUDACIOSA(Stat.ATAQUE, Stat.ATAQUE_ESPECIAL),
    MIEDOSA(Stat.VELOCIDAD, Stat.ATAQUE),
    SERENA(Stat.DEFENSA_ESPECIAL, Stat.ATAQUE);

    private final Stat aumenta;
    private final Stat reduce;

    Naturaleza(Stat aumenta, Stat reduce) {
        this.aumenta = aumenta;
        this.reduce = reduce;
    }

    public double getMultiplicador(Stat stat) {
        if (stat == aumenta) return 1.1;
        if (stat == reduce) return 0.9;
        return 1.0;
    }
}