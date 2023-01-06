package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Set initial table
        String positions = "         ";
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(positions.charAt(i * 3 + j) + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");

        //Game
        boolean player = true;
        while (true) {
            // Get move
            int yCoord = -1;
            int xCoord = -1;
            while (yCoord == -1) {
                try {
                    yCoord = in.nextInt() - 1;
                    xCoord = in.nextInt() - 1;
                    if ((xCoord > 2 || xCoord < 0) || (yCoord > 2 || yCoord < 0)) {
                        yCoord = -1;
                        xCoord = -1;
                        System.out.println("Coordinates should be from 1 to 3!");
                        continue;
                    }
                    if (positions.charAt(yCoord * 3 + xCoord) != ' ') {
                        System.out.println("This cell is occupied! Choose another one!");
                        yCoord = -1;
                        xCoord = -1;
                    }
                }
                catch (Exception ex) {
                    in.nextLine();
                    System.out.println("You should enter numbers!");
                }
            }

            // Set move
            int index = yCoord * 3 + xCoord;
            if (player) {
                positions = positions.substring(0, index) + 'X' + positions.substring(index + 1);
                player = false;
            }
            else {
                positions = positions.substring(0, index) + 'O' + positions.substring(index + 1);
                player = true;
            }

            // Show table
            System.out.println("---------");
            for (int i = 0; i < 3; i++) {
                System.out.print("| ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(positions.charAt(i * 3 + j) + " ");
                }
                System.out.println("|");
            }
            System.out.println("---------");

            // Check winner X O
            boolean xWin = false;
            boolean oWin = false;
            String diagonal1 = "";
            String diagonal2 = "";

            for (int i = 0; i < 3; i++) {
                String stroke1 = "";
                String stroke2 = "";
                diagonal1 += positions.charAt(i * 4);
                diagonal2 += positions.charAt(i * 2 + 2);
                for (int j = 0; j < 3; j++) {
                    stroke1 += positions.charAt(i * 3 + j);
                    stroke2 += positions.charAt(i + 3 * j);
                }
                if (stroke1.equals("XXX") || stroke2.equals("XXX")) {
                    xWin = true;
                }
                if (stroke1.equals("OOO") || stroke2.equals("OOO")) {
                    oWin = true;
                }
            }
            if (diagonal1.equals("XXX")) {
                xWin = true;
            } else if (diagonal1.equals("OOO")) {
                oWin = true;
            }
            if (diagonal2.equals("XXX")) {
                xWin = true;
            } else if (diagonal2.equals("OOO")) {
                oWin = true;
            }

            // count X O
            int countX = 0;
            int countO = 0;
            for (int i = 0; i < positions.length(); i++) {
                char c = positions.charAt(i);
                if (c == 'X') {
                    countX++;
                }
                if (c == 'O') {
                    countO++;
                }
            }

            // Show result
            if ((xWin && oWin) || (Math.abs(countX - countO) > 1)) {
                System.out.println("Impossible");
                break;
            } else if (xWin) {
                System.out.println("X wins");
                break;
            } else if (oWin) {
                System.out.println("O wins");
                break;
            } else if (countX + countO == 9) {
                System.out.println("Draw");
                break;
            }
        }
    }
}
