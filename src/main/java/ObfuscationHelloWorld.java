public class ObfuscationHelloWorld {
    public static void main(String... a) {
        String s = "";
        for (char c : new char[] {72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 33}) {
            s += (char) ((c + 13) % 26 + (Character.isUpperCase(c) ? 65 : Character.isLowerCase(c) ? 97 : c - 13));
        }
        System.out.println(">>"+new StringBuilder(s).reverse().toString());


        int[] obfuscated = {19, 53, 92, 92, 96, 49, 43, 92, 90, 92, 97, 83};
        String message = "";

        for (int i : obfuscated) {
            message += (char) (i ^ 111);
        }

        System.out.println(">>"+message);
    }
}
