public class CustomStringSearch {

    public static int customIndexOf(String text, char ch) {
        return customIndexOf(text, ch, 0);
    }

    public static int customIndexOf(String text, char ch, int fromIndex) {
        for (int i = Math.max(fromIndex, 0); i < text.length(); i++) {
            if (text.charAt(i) == ch) return i;
        }
        return -1;
    }

    public static int customLastIndexOf(String text, char ch) {
        return customLastIndexOf(text, ch, text.length() - 1);
    }

    public static int customLastIndexOf(String text, char ch, int fromIndex) {
        for (int i = Math.min(fromIndex, text.length() - 1); i >= 0; i--) {
            if (text.charAt(i) == ch) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String test = "banana";
        System.out.println("customIndexOf('a'): " + customIndexOf(test, 'a')); // 1
        System.out.println("customIndexOf('a', 2): " + customIndexOf(test, 'a', 2)); // 3
        System.out.println("customLastIndexOf('a'): " + customLastIndexOf(test, 'a')); // 5
    }
}