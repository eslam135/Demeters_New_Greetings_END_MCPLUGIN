package end.final_greetings.CustomClasses;

import java.util.List;

public class Exists {
    public static boolean CheckExists(String[] args, String msg){
        boolean exists = false;
        for (String s : args) {
            if (msg.compareToIgnoreCase(s) == 0) {
                if (exists) {
                    continue;
                }
                exists = true;
            }
        }
        return exists;
    }
    public static boolean CheckExists(List<String> args, String msg){
        boolean exists = false;
        for (String s : args) {
            if (msg.compareToIgnoreCase(s) == 0) {
                if (exists) {
                    continue;
                }
                exists = true;
            }
        }
        return exists;
    }
}
