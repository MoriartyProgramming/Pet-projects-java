import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int i = 0;
        int count = 1;
        while(i < n) {
            for (int j = 0; j < count; j++) {
                System.out.print(count + " ");
                i++;
                if (i >= n) {
                    break;
                }
            }
            count++;
        }
    }
}