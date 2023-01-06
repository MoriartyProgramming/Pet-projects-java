import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int moreThan = in.nextInt();

        int sum = 0;
        for (int i : arr) {
            if (i > moreThan) {
                sum += i;
            }
        }
        System.out.println(sum);
    }
}