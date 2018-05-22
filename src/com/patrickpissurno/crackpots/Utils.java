package com.patrickpissurno.crackpots;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {
    public static int randomRange(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
