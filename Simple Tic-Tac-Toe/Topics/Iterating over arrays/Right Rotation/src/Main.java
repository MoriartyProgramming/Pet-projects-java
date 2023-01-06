import java.util.Scanner;
import java.util.Arrays;

class Main {
    // implement me
    private static void rotate(int[] arr, int steps) {
        int[] tmp = new int[arr.length];

        int j = 0;
        int limit = arr.length - steps % arr.length;
        for (int i = limit; i < arr.length; i++) {
            tmp[j] = arr[i];
            j++;
        }
        for (int i = 0; i < limit; i++) {
            tmp[j] = arr[i];
            j++;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = tmp[i];
        }
    }

    // do not change code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int steps = Integer.parseInt(scanner.nextLine());

        rotate(arr, steps);

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}