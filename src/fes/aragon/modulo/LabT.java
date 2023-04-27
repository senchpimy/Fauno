package fes.aragon.modulo;

import java.util.Random;

public enum LabT {
	TUNEL,RUINAS,NORMAL;//,DUNGEON;

    public static LabT randomTipo()  {
    	Random PRNG = new Random();
        LabT[] directions = values();
        return directions[PRNG.nextInt(directions.length)];
    }
}
