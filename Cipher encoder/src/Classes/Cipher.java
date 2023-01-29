package Classes;

import java.util.Scanner;
import Classes.Exceptions;

public class Cipher {
    Scanner in = new Scanner(System.in);
    private String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    // Main func
    public void program() {
        while (true) {
            System.out.println("Please input operation (encode/decode/exit):");
            command = in.nextLine();
            if (!switchMenu()) {
                return;
            }
        }
    }

    private boolean switchMenu() {
        switch (command) {
            case "encode":
                encode();
                break;
            case "decode":
                decode();
                break;
            case "exit":
                System.out.println("Bye!");
                return false;
            default:
                System.out.println("There is no \'" + command + "\' operation\n");
                break;
        }
        return true;
    }

    private void encode() {
        Scanner in = new Scanner(System.in);

        System.out.println("Input string:");
        String mainStroke = in.nextLine();
        System.out.println("Encoded string:");
        char repeat = '2';
        String ciphed = "";
        for (int i = 0; i < mainStroke.length(); i++) {
            String binary = Integer.toBinaryString(mainStroke.charAt(i));
            binary = String.format("%07d", Integer.parseInt(binary));

            for (int j = 0; j < binary.length(); j++) {
                if (repeat == binary.charAt(j)) {
                    ciphed += "0";
                }
                else {
                    if (!(ciphed.equals(""))) {
                        ciphed += " ";
                    }
                    if (binary.charAt(j) == '0') {
                        ciphed += "00 0";
                    }
                    else {
                        ciphed += "0 0";
                    }
                }
                repeat = binary.charAt(j);
            }
        }
        System.out.print(ciphed + "\n\n");
    }

    static void decode() {
        Scanner in = new Scanner(System.in);

        System.out.println("Input encoded string:");
        String stroke = in.nextLine();
        String strokeArr[] = stroke.split(" ");

        if (!Exceptions.ExceptionsCheck(stroke, strokeArr)) {
            String binary = "";
            for (int i = 0; i < strokeArr.length; i+=2) {
                if (strokeArr[i].equals("00")) {
                    for (int j = 0; j < strokeArr[i + 1].length(); j++) {
                        binary += "0";
                    }
                } else {
                    for (int j = 0; j < strokeArr[i + 1].length(); j++) {
                        binary += "1";
                    }
                }
            }

            if (Exceptions.checkBinary(binary)) {
                String decodedStroke = "";
                for (int i = 0; i < binary.length(); i+=7) {
                    int sumForChar = 0;
                    int iterator = 64;
                    for (int j = i; j < i + 7; j++) {
                        sumForChar += (binary.charAt(j) - '0') * iterator;
                        iterator /= 2;
                    }
                    decodedStroke += (char)sumForChar;
                }
                System.out.println("Decoded string:");
                System.out.println(decodedStroke + "\n");
            }
        }
    }
}
