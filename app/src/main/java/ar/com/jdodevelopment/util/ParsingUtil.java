package ar.com.jdodevelopment.util;

public class ParsingUtil {



    public static Double parseDouble(String amount) {
        try {
            return Double.parseDouble(amount);
        } catch (Exception e) {
            return 0D;
        }
    }


}
