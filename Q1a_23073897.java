import java.util.*;

public class Q1a_23073897 {

    private int n; //size of magic square
    private int[][] square; //2D array

    public Q1a_23073897(int oddNumber) {
        if (oddNumber % 2 == 0) {
            throw new RuntimeException("\nn must be odd\n");
        } 
        if (oddNumber < 0) {
            throw new RuntimeException("\nn must be positive\n");
        }
        n = oddNumber;
        square = new int[n][n]; //create new square with 2D array of n
    }

    public void generateMagicSquare() {

        int y = n - 1; //inital starting position
        int x = n / 2; //inital starting position
        square[y][x] = 1;

        for (int i = 2; i <= n * n; i++) {
            if (square[(y + 1) % n][(x + 1) % n] == 0) {
                y = (y + 1) % n;
                x = (x + 1) % n;
            }
            else {
                y = (y - 1 + n) % n;
            }
            square[y][x] = i;
        }
    }

    public void printMagicSquare() {
        System.out.println("______\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (square[i][j] < 10)  System.out.print(" ");  //alignment
                if (square[i][j] < 100) System.out.print(" "); //alignment
                System.out.print(square[i][j] + " "); 
            }
            System.out.println();
            
        }
        System.out.println("______\n");
    }
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        int n;

        while (true) {
            
            try { 
                System.out.println("Welcome to the Magic Square Game! To start, enter a positive odd integer: \n");
                n = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nNo letters or words! Please enter a positive odd number: \n");
                scanner.nextLine();
                continue;
            }

            if (n % 2 == 0) {
                System.out.println("\nn must be odd\n");
                continue;
            }
            if (n < 0) {
                System.out.println("\nn must be positive\n");
                continue;
            }
            scanner.close();
            Q1a_23073897 magicSquare = new Q1a_23073897(n);
            magicSquare.generateMagicSquare();
            magicSquare.printMagicSquare();
            break;
        }
    }
}