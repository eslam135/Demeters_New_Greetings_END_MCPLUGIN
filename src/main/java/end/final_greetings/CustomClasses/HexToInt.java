package end.final_greetings.CustomClasses;

public class HexToInt {
    public static int[] turnintohex(String s) {
        try {
            int counter = 0, rgb = 0;
            String color = "";
            int[] ints = new int[3];
            for (int i = 0; i < 6; i++) {
                if (counter == 2) {
                    counter = 0;
                    color = "";
                    rgb++;
                }
                counter++;
                color += s.charAt(i);
                ints[rgb] = Integer.parseInt(color, 16);
            }
            return ints;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a color in hexadecimal");
            int[] ints = new int[]{00,00,00};
        return ints;
        }
    }
}
