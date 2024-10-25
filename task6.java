public class task6 {
    public static String repeatLetters(String input) {
        StringBuilder output = new StringBuilder();
        
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (i + 1 < input.length() && Character.isDigit(input.charAt(i + 1))) {
                int count = Character.getNumericValue(input.charAt(i + 1));
                for (int j = 0; j < count; j++) {
                    output.append(currentChar);
                }
                i++;
            }
        }
        
        return output.toString();
    }

    public static void main(String[] args) {
        String input = "s2i3m4";
        String result = repeatLetters(input);
        System.out.println(result); 
    }
}

