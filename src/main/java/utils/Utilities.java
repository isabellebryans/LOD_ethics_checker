package utils;

public class Utilities {
    public static boolean ArrayContains(String[] array, String value){
        for (String item : array) {
            if (value.equals(item)) {
                return true;
            }
        }
        return false;
    }
}
