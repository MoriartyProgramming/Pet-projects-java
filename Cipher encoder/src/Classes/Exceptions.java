package Classes;

public class Exceptions {
    private static boolean decodeContainingCharactersException(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '0' && str.charAt(i) != ' ') {
                return true;
            }
        }
        return false;
    }

    private static boolean decodeFirstBlockFail(String[] arr) {
        for (int i = 0; i < arr.length; i += 2) {
            if (arr[i].length() != 1 && arr[i].length() != 2) {
                return true;
            }
        }
        return false;
    }

    private static boolean decodeArrIsOdd(String[] arr) {
        return (arr.length % 2 == 1);
    }

    public static boolean ExceptionsCheck(String stroke, String[] arr) {
        boolean b = decodeContainingCharactersException(stroke);
        b = b || decodeFirstBlockFail(arr);
        b = b || decodeArrIsOdd(arr);

        if (b) {
            System.out.println("Encoded string is not valid.\n");
        }
        return b;
    }

    public static boolean checkBinary(String str) {
        if (str.length() % 7 == 0) {
            return true;
        } else {
            System.out.println("Encoded string is not valid.\n");
            return false;
        }
    }
}
