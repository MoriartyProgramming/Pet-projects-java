import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Get cinema parameters
        System.out.println("Enter the number of rows:");
        int rows = in.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = in.nextInt();

        // Initialize cinema field
        char[][] cinemaField = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cinemaField[i][j] = 'S';
            }
        }

        // main loop
        int command = 1;
        while (command != 0) {
            command = menu(cinemaField, rows, cols);
        }
    }

    // Menu
    public static int menu(char[][] cinemaField, int rows, int cols) {
        Scanner in = new Scanner(System.in);

        System.out.println("""
                    \n1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit""");
        int command = in.nextInt();

        if (command == 1) {
            printField(cinemaField, rows, cols);
            return 1;
        } else if (command == 2) {
            buyTicket(cinemaField, rows, cols);
            return 2;
        } else if (command == 3) {
            stats(cinemaField, rows, cols);
            return 3;
        } else {
            return 0;
        }
    }

    // Statistics
    public static void stats(char[][] cinemaField, int rows, int cols) {
        int countPurchased = countPurchasedTickets(cinemaField, rows, cols);
        System.out.println("\nNumber of purchased tickets: " + countPurchased);
        System.out.printf("Percentage: %.2f", percentagePurchased(countPurchased, rows, cols));
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome(cinemaField, rows, cols));
        System.out.println("Total income: $" + totalIncome(rows, cols));
    }

    // Count total income
    public static int totalIncome(int rows, int cols) {
        if (rows * cols <= 60) {
            return rows * cols * 10;
        }
        int firstPart = rows / 2;
        int sum = firstPart * cols * 10 + (rows - firstPart) * cols * 8;
        return sum;
    }

    // Count current income by counting occupied seats
    public static int currentIncome(char[][] cinemaField, int rows, int cols) {
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int ticket = 10;
                if (cinemaField[i][j] == 'B') {
                    if (i + 1 > rows / 2) {
                        ticket = 8;
                    }
                    if (rows * cols <= 60) {
                        ticket = 10;
                    }
                    count += ticket;
                }
            }
        }

        return count;
    }

    // Return purchased percentage
    public static double percentagePurchased(int countPurchased, int rows, int cols) {
        return ((double)countPurchased / (double) (rows * cols) * 100.0);
    }

    // Count seats with indicator B
    public static int countPurchasedTickets(char[][] cinemaField, int rows, int cols) {
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cinemaField[i][j] == 'B') {
                    count++;
                }
            }
        }

        return count;
    }

    // Show changed cinema field
    public static void printField(char[][] cinemaField, int rows, int cols) {
        System.out.print("\nCinema:\n  ");
        for (int i = 0; i < cols; i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < cols; j++) {
                System.out.print(" " + cinemaField[i][j]);
            }
            System.out.println();
        }
    }

    public static void buyTicket(char[][] cinemaField, int rows, int cols) {
        Scanner in = new Scanner(System.in);

        int ticketRow = 1;
        int ticketCol = 1;
        boolean check = true;
        do {
            try {
                System.out.println("\nEnter a row number:");
                ticketRow = in.nextInt();
                System.out.println("Enter a seat number in that row:");
                ticketCol = in.nextInt();

                if (!((rows >= ticketRow && ticketRow > 0) && (cols >= ticketCol && ticketCol > 0))) {
                    System.out.println("\nWrong input!");
                    check = false;
                } else if (cinemaField[ticketRow - 1][ticketCol - 1] == 'B') {
                    System.out.println("\nThat ticket has already been purchased!");
                    check = false;
                }
                else {
                    check = true;
                }
            }
            catch (Exception ex) {
                in.nextLine();
                System.out.println("\nWrong input!");
            }
        } while (!check);

        int ticketPrice = setTicketPrice(rows, cols, ticketRow);
        System.out.println("\nTicket price: $" + ticketPrice);
        cinemaField[ticketRow - 1][ticketCol - 1] = 'B';
    }

    // Set price of ticket
    public static int setTicketPrice(int rows, int cols, int ticketRow) {
        int ticketPrice = 8;
        if ((ticketRow <= rows / 2) || (rows * cols <= 60)) {
            ticketPrice = 10;
        }

        return ticketPrice;
    }
}