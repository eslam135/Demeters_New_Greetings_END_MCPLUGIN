package end.final_greetings.CustomClasses;

import java.util.Random;

public class RandomNoGen {
    public static int generateNum(int boundaries){
        int num;
        Random random = new Random();
        num = random.nextInt(boundaries);
        return num;
    }
}
