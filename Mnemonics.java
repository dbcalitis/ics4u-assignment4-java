/*
 * The program generates all possible mnemonics for the given number.
 *
 * @author Daria Bernice Calitis
 * @version 1.0
 * @since 2022-11-19
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * The Mnemonics class.
 */
public final class Mnemonics {
    /**
     * An array of the letters found on a dial pad.
     */
    private static final String[] DIAL_LETTERS = {
        "ABC", "DEF",
        "GHI", "JKL", "MNO",
        "PQRS", "TUV", "WXYZ",
    };

    /**
     * StringUtils.
     *
     * @throws IllegalStateException - Exception.
     */
    private StringUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * The listMnemonics function.
     *
     * @param phoneNumber     - the phone number
     * @param currentMnemonic - the current mnemonic being created
     * @param mnemonics       - the list of mnemonics to be returned
     * @param index           - the current index
     * @return ArrayList
     */
    private static ArrayList<String> listMnemonics(
            String phoneNumber, String currentMnemonic,
            ArrayList<String> mnemonics, int index) {
        // Adds the mnemonic if it matches the number's length.
        if (currentMnemonic.length() == phoneNumber.length()) {
            mnemonics.add(currentMnemonic);
        } else {
            String mnemonic = "";

            final String letters = DIAL_LETTERS[Integer.parseInt(""
                + phoneNumber.charAt(index)) - 2];

            for (int count = 0; count < letters.length(); count++) {
                // Converting the letter from char
                // to String and adds to the currentMnemonic
                final String letter = "" + letters.charAt(count);
                mnemonic = currentMnemonic + letter;

                // Goes to the next number.
                listMnemonics(phoneNumber, mnemonic,
                    mnemonics, index + 1);

                // Removes the last letter.
                mnemonic = mnemonic.substring(
                    0, mnemonic.length() - 1);
            }
        }
        return mnemonics;
    }

    /**
     * Main.
     *
     * @param args         - No arguments
     * @throws IOException - Exception
     */
    public static void main(String[] args) throws IOException {
        final BufferedReader bReader = new BufferedReader(
            new InputStreamReader(System.in));
        System.out.println("Mnemonic Generator");
        System.out.println(
            "The number must have digits from 2 to 9, inclusive.\n");
        System.out.print("Enter a phone number: ");

        // Input
        try {
            final String phoneNumber = bReader.readLine();
            System.out.println();
            // Process & Output
            System.out.println(listMnemonics(phoneNumber, "",
                new ArrayList<String>(), 0));
        } catch (java.util.InputMismatchException
                | java.lang.ArrayIndexOutOfBoundsException
                | java.lang.NumberFormatException err) {
            System.err.println("Invalid Input.");
        }

        System.out.println("\nDone.");

    }
}
