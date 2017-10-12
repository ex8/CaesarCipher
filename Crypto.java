/*
* Class: Cryptography
* Limitations: You cannot use any library methods (Except charAt() and length())!
*
* */

public class Crypto {

    /*
    * Step 1
    * Purpose: Normalize string so it is easier to work with.
    *          Remove all white space, punctuation: [. , : ; " ? ()], & turn all uppercase
    * @param s - string to normalize
    * @return n - normalized string
    *
    * */
    public static String normalizeText(String s) {
        char[] deliminators = {' ', '.', ':', ';', '"', '?', '!', '(', ')', };
        String n =  "";
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (!compare(c, deliminators)) {
                n += c;
            }
        }
        return upperCase(n);
    }

    /*
    * Step 2
    * Purpose: Turn normalized string into obfuscated string
    * @param s - normalized string
    * @return n - obfuscated string
    *
    * */
    public static String obify(String s) {
        char[] vowels = {'A', 'E', 'I', 'O', 'U', 'Y'};
        String n = "";
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (compare(c, vowels)) {
                n += "OB" + c;
            }
            else {
                n += c;
            }
        }
        return n;
    }

    /*
    * Purpose: Turn obfuscated text into a normalized string
    * @param s - obfuscated string
    * @return n - normalized string
    *
    * */
    public static String unobify(String s) {
        String n = "";
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c1 = s.charAt(i);
            if (c1 == 'O') {
                char c2 = s.charAt(i + 1);
                if (c2 == 'B') {
                    char c3 = s.charAt(i + 2);
                    n += c3;
                    i += 2;
                }
            }
            else {
                n += c1;
            }
        }
        return n;
    }

    /*
    * Step 3
    * Purpose: Shift individual characater by shift value
    * @param s - normalized string
    * @param shift - number to shift char by
    * @return n
    * ASCII Table: http://www.asciitable.com/
    *
    * */
    public static String caesarify(String s, int shift) {
        String n = "";
        int len = s.length();
        for (int i = 0; i < len; i++) {
            // Convert to ASCII and apply shift
            int c = s.charAt(i) + shift;
            // A = ASCII(65); if A, then wrap around to Z
            if (c < 65) {
                c += 26;
            }
            // Z = ASCII(90); if Z, then wrap around to A
            if (c > 90) {
                c -= 26;
            }
            n += (char) c;
        }
        return n;
    }

    /*
    * Step 4
    * Purpose: Separate string into groups of size n
    * @param s - obish string
    * @param n - number of chars per group
    * @return g - groupified string
    *
    * */
    public static String groupify(String s, int n) {
        if (n == 0) return s;
        String g = "";
        int len = s.length();
        int iter = 0;
        String temp = "";
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (iter == n) {
                g += ' ';
                iter = 0;
                temp = "";
            }
            g += c;
            iter++;
            temp += c;
        }
        // Add additional x's
        if (temp.length() != n) {
            for (int i = temp.length(); i < n; i++) {
                g += 'x';
            }
        }
        return g;
    }

    /*
    * Purpose: Ungroupify the string
    * @param s - groupified string
    * @return u - ungroupified string (normalized string)
    *
    * */
    public static String ungroupify(String s) {
        int len = s.length();
        String u = "";
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (!compare(c, new char[] {'x', ' '})) {
                u += c;
            }
        }
        return u;
    }

    /*
    * Purpose: Encrypt string
    * @param s - plain text
    * @param shift - number to shift characters by
    * @param group - number of characters per group
    * @return - encrypted string
    *
    * */
    public static String encryptString(String s, int shift, int group) {
        String n = normalizeText(s);
        String o = obify(n);
        String c = caesarify(o, shift);
        return groupify(c, group);
    }


    /*
    * Purpose: Decrypt string
    * @param s - encrypted string
    * @param shift - number characters shifted by
    * @return d - decrypted string
    *
    * */
    public static String decryptString(String s, int shift) {
        String d = ungroupify(s);
        // Multiply -1 to shift so it can un-caesarify accordingly
        d = caesarify(d, -1 * shift);
        d = unobify(d);
        return d;
    }

    /*
    * Purpose: Compare if character is in character[]
    * @param c - character to check
    * @param array - char array
    * @return boolean
    *
    * */
    public static boolean compare(char c, char[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            if (c == array[i]) {
                return true;
            }
        }
        return false;
    }


    /*
    * Purpose: Turn the string characters into all captial letters
    * @param s - string to manipulate
    * @return u
    * ASCII Table: http://www.asciitable.com/
    *
    * */
    public static String upperCase(String s) {
        int len = s.length();
        String u = "";
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            // c is lowercase
            if (c >= 95 && c <= 122) {
                c = (char) (c - 32);
                u += c;
            }
            // c is uppercase
            else if (c >= 65 && c <= 90){
                u += c;
            }
        }
        return u;
    }

    /*
    * Main method for testing
    *
    * */
    public static void main(String[] args) {
        // Encrypt Steps: Normalize, Obfuscate, Caesarify, & Groupify
        // Decrypt Steps: Ungroupify, Un-caesarify, Unobify
        String s = "Password Password Password";
        System.out.println("Plain text: " + s);
        System.out.println("ENCRYPTION STEPS");
        String n = normalizeText(s);
        System.out.println("Normalized String: " + n);
        String o = obify(n);
        System.out.println("Obish'd String: " + o);
        String c = caesarify(o, 1);
        System.out.println("Caesarify'd String: " + c);
        String g = groupify(c, 2);
        System.out.println("Groupified String: " + g);
        String e = encryptString(s, 1, 2);
        System.out.println("Encrypted String: " + e);

        System.out.println("");

        System.out.println("DECRYPTION STEPS");
        String ug = ungroupify(e);
        System.out.println("Ungroupify: " + ug);
        String uc = caesarify(ug, -1);
        System.out.println("Uncaesarify: " + uc);
        String uo = unobify(uc);
        System.out.println("Unobish'd: " + uo);
        String d = decryptString(e, 1);
        System.out.println("Decrypted String: " + d);
    }
}
