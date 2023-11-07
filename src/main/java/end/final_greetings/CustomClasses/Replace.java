package end.final_greetings.CustomClasses;

public class Replace {
    public static String replace(String msg, String replace, String replacewith){
        if(msg.contains(replace)){
            msg= msg.replace(replace, replacewith);
            return msg;
        }else
            return msg;
    }
}
