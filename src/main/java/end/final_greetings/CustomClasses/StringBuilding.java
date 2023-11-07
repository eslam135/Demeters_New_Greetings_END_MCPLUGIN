package end.final_greetings.CustomClasses;

import java.util.List;

public class StringBuilding {
    public static String Build(int startWith, String[] args, String inBetweens){
        String msg;
        StringBuilder builder =  new StringBuilder();
        for(int i = startWith; i<args.length; i++){
            builder.append(args[i]);
            if(i == args.length-1){
                continue;
            }
            builder.append(inBetweens);
        }
        msg = builder.toString();
        return msg;
    }
    public static String Build(int startWith, List<String> args, String inBetweens){
        String msg;
        StringBuilder builder =  new StringBuilder();
        for(int i = startWith; i<args.size(); i++){
            builder.append(args.get(i));
            if(i == args.size()-1){
                continue;
            }
            builder.append(inBetweens);
        }
        msg = builder.toString();
        
        return msg;
    }
}
