public class task8 {
    public static void swapStrings(String str1, String str2) {
        str1 = str1 + str2; 
        str2 = str1.substring(0, str1.length() - str2.length());
        str1 = str1.substring(str2.length());
        
        System.out.println("Swapped Strings:");
        System.out.println("String1: " + str1);
        System.out.println("String2: " + str2);
    }
    public static void main(String[] args) {
       
        String string1 = "Simple";
        String string2 = "Energy";

        System.out.println("Before swapping:");
        System.out.println("String1: " + string1);
        System.out.println("String2: " + string2);

        swapStrings(string1, string2);
    }
}
