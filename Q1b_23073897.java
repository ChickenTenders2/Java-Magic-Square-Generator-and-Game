import java.util.*;

public class Q1b_23073897 {

    private int n; //size of magic square
    private int[][] square; //2D array
    private String[] directions = {"U", "D", "L", "R", "UP", "DOWN", "LEFT", "RIGHT"};
    private int moveCount = 0;

    public Q1b_23073897(int oddNumber) {
        if (oddNumber % 2 == 0) {
            throw new RuntimeException("n must be odd");
        } 
        if (oddNumber < 0) {
            throw new RuntimeException("n must be positive");
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
    
    public void shuffleMagicSquare() {
        Random random = new Random();
        int numberOfSwaps = n * n;
    
        for (int i = 0; i < numberOfSwaps; i++) {
            int row = random.nextInt(n); //row = y
            int col = random.nextInt(n); //col = x

            int swapDirection = random.nextInt(4); //0 = up, 1 = right, 2 = down, 3 = left
            int temp;
            switch (swapDirection) {
                case 0: //check for left swap
                    if (col > 0) {
                        temp = square[row][col];
                        square[row][col] = square[row][col - 1];                
                        square[row][col - 1] = temp;
                    }
                    break;
                case 1: //check for right swap
                    if (col < n - 1) {
                        temp = square[row][col];
                        square[row][col] = square[row][col + 1];                
                        square[row][col + 1] = temp;
                    }
                    break;
                case 2: //check for up swap
                    if (row > 0) {
                        temp = square[row][col];
                        square[row][col] = square[row - 1][col];                
                        square[row - 1][col] = temp;
                    }
                    break;
                case 3: //check for down swap
                    if (row < n - 1) {
                        temp = square[row][col];
                        square[row][col] = square[row + 1][col];                
                        square[row + 1][col] = temp;
                    }
                    break;
            }
        }
    }

    public void printMagicSquare() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (square[i][j] < 10)  System.out.print(" ");  //alignment
                if (square[i][j] < 100) System.out.print(" "); //alignment
                System.out.print(square[i][j] + " "); 
            }
            System.out.println();
        }
    }

    public boolean isValidMove(int row, int col, String direction) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            System.out.println("\nMove is out of bounds, please try again: ");
            return false;
        }

        if (!Arrays.stream(directions).anyMatch(direction::equals)) { //checks whether array has value of direction in it
            System.out.println("Invalid direction, please try again: ");
            return false;
        }    
        return true;
    }
    private boolean winCondition(int square[][]) {
        int diag_sum1 = 0, diag_sum2 = 0;

        for (int i = 0; i < n; i++) { //Calculate diagonal sums
            diag_sum1 += square[i][i];
            diag_sum2 += square[i][n - 1 - i];
        }

        // System.out.println("Diagonal Sum 1 (Top-Left to Bottom-Right): " + diag_sum1); //debug code (IGNORE)
        // System.out.println("Diagonal Sum 2 (Top-Right to Bottom-Left): " + diag_sum2); //debug code (IGNORE)
        
        if (diag_sum1 != diag_sum2) {
            return false;
        }
    
        for (int i = 0; i < n; i++) { //Calculate row and col sums
            int row_sum = 0, col_sum = 0;
            for (int j = 0; j < n; j++) {
                row_sum += square[i][j];
                col_sum += square[j][i];
            }
    
            // System.out.println("Row Sum " + (i + 1) + ": " + row_sum); //debug code (IGNORE)
            // System.out.println("Column Sum " + (i + 1) + ": " + col_sum); //debug code (IGNORE)
    
            if (row_sum != col_sum || col_sum != diag_sum1) {
                return false;
            }
        }
        return true;
    }

    public void userInteraction() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int row;
            int col;
            String direction;
            
            System.out.println("\nWhat move would you like to make?: \n");
            try {
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;
                direction = scanner.next().toUpperCase();
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input, please try again: \n");
                scanner.nextLine();
                printMagicSquare();
                continue;
            }
           
            if (isValidMove(row, col, direction)) {
                switch(direction) {
                    case "L": //check for left swap
                    if (col > 0) {
                        int temp = square[row][col];
                        square[row][col] = square[row][col - 1];                
                        square[row][col - 1] = temp;
                        moveCount++;
                        System.out.println("\nMovement Success! Continue.\n");
                        printMagicSquare();
                    } else {
                        System.out.println("\nCan't go left, try again...\n");
                        printMagicSquare();
                    }
                    break;
                    case "R": //check for right swap
                    if (col < n - 1) {
                        int temp = square[row][col];
                        square[row][col] = square[row][col + 1];                
                        square[row][col + 1] = temp;
                        moveCount++;
                        System.out.println("\nMovement Success! Continue.\n");
                        printMagicSquare();
                    } else {
                        System.out.println("\nCan't go right, try again...\n");
                        printMagicSquare();
                    }
                    break;
                    case "U": //check for up swap
                    if (row > 0) {
                        int temp = square[row][col];
                        square[row][col] = square[row - 1][col];                
                        square[row - 1][col] = temp;
                        moveCount++;
                        System.out.println("\nMovement Success! Continue.\n");
                        printMagicSquare();
                    } else {
                        System.out.println("\nCan't go up, try again...\n");
                        printMagicSquare();
                    }
                    break;
                    case "D": //check for down swap
                    if (row < n - 1) {
                        int temp = square[row][col];
                        square[row][col] = square[row + 1][col];                
                        square[row + 1][col] = temp;
                        moveCount++;
                        System.out.println("\nMovement Success! Continue.\n");
                        printMagicSquare();
                    } else {
                        System.out.println("\nCan't go down, try again...\n");
                        printMagicSquare();
                    }
                    break;
                    default: 
                        System.out.println("\nInvalid input, please try again...\n");
                        printMagicSquare();
                        break;
                }
                if (winCondition(square)) { //if win condition is true
                    int answer;
                    try {
                        System.out.println("\nCongratulations! You have solved the Magic Square!\n");
                        System.out.println("\nYou have moved a total of " + moveCount + " moves!\n");
                        System.out.println("\nWould you like to play again? (1 = Yes, 0 = No)\n");
                        answer = scanner.nextInt();
                        if (answer == 0) {
                            System.out.println("\nThanks for playing! Goodbye!\n");
                            break;
                        }
                        if (answer == 1) {
                            System.out.println("\nFYI: To restart with a new magic square size, please restart the program :)\n");
                            shuffleMagicSquare();
                            printMagicSquare();
                            moveCount = 0;
                        }      
                    } catch (InputMismatchException e) {
                        System.out.println("\nThat isn't 1 or 0, please restart the program and try again...\n");
                        break;
                    }      
                }
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        int n;

        while (true) {
            
            try { 
                System.out.println("\n" + //
                                        "───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────\n" + //
                                        "─██████──────────██████─██████████████─██████████████─██████████─██████████████────██████████████─██████████████───██████──██████─██████████████─████████████████───██████████████─\n" + //
                                        "─██░░██████████████░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░██─██░░░░░░░░░░██────██░░░░░░░░░░██─██░░░░░░░░░░██───██░░██──██░░██─██░░░░░░░░░░██─██░░░░░░░░░░░░██───██░░░░░░░░░░██─\n" + //
                                        "─██░░░░░░░░░░░░░░░░░░██─██░░██████░░██─██░░██████████─████░░████─██░░██████████────██░░██████████─██░░██████░░██───██░░██──██░░██─██░░██████░░██─██░░████████░░██───██░░██████████─\n" + //
                                        "─██░░██████░░██████░░██─██░░██──██░░██─██░░██───────────██░░██───██░░██────────────██░░██─────────██░░██──██░░██───██░░██──██░░██─██░░██──██░░██─██░░██────██░░██───██░░██─────────\n" + //
                                        "─██░░██──██░░██──██░░██─██░░██████░░██─██░░██───────────██░░██───██░░██────────────██░░██████████─██░░██──██░░██───██░░██──██░░██─██░░██████░░██─██░░████████░░██───██░░██████████─\n" + //
                                        "─██░░██──██░░██──██░░██─██░░░░░░░░░░██─██░░██──██████───██░░██───██░░██────────────██░░░░░░░░░░██─██░░██──██░░██───██░░██──██░░██─██░░░░░░░░░░██─██░░░░░░░░░░░░██───██░░░░░░░░░░██─\n" + //
                                        "─██░░██──██████──██░░██─██░░██████░░██─██░░██──██░░██───██░░██───██░░██────────────██████████░░██─██░░██──██░░██───██░░██──██░░██─██░░██████░░██─██░░██████░░████───██░░██████████─\n" + //
                                        "─██░░██──────────██░░██─██░░██──██░░██─██░░██──██░░██───██░░██───██░░██────────────────────██░░██─██░░██──██░░██───██░░██──██░░██─██░░██──██░░██─██░░██──██░░██─────██░░██─────────\n" + //
                                        "─██░░██──────────██░░██─██░░██──██░░██─██░░██████░░██─████░░████─██░░██████████────██████████░░██─██░░██████░░████─██░░██████░░██─██░░██──██░░██─██░░██──██░░██████─██░░██████████─\n" + //
                                        "─██░░██──────────██░░██─██░░██──██░░██─██░░░░░░░░░░██─██░░░░░░██─██░░░░░░░░░░██────██░░░░░░░░░░██─██░░░░░░░░░░░░██─██░░░░░░░░░░██─██░░██──██░░██─██░░██──██░░░░░░██─██░░░░░░░░░░██─\n" + //
                                        "─██████──────────██████─██████──██████─██████████████─██████████─██████████████────██████████████─████████████████─██████████████─██████──██████─██████──██████████─██████████████─\n" + //
                                        "───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
                System.out.println("Welcome to the Magic Square Game! To start, enter a positive odd integer (or 0 to exit): ");
                n = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("No letters or words! Please enter a positive odd number: ");
                scanner.nextLine();
                continue;
            }
            
            if (n == 0) {
                System.out.println("Exiting program...");
                break;
            }
            if (n % 2 == 0) {
                System.out.println("n must be odd, try again...");
                continue;
            }
            if (n < 0) {
                System.out.println("Umm... n must be positive, try again...");
                continue;
            }
            Q1b_23073897 magicSquare = new Q1b_23073897(n);
            magicSquare.generateMagicSquare();
            magicSquare.shuffleMagicSquare();
            System.out.println("______________________________________________________________________________________________________________________________________________________________________________________\n\n" + //
                                "Great! You have now generated a Magic Square! However something doens't look right, the numbers are shuffled! Your task is to reconstruct it back to what the Magic Square should've been like originally." + //
                                "______________________________________________________________________________________________________________________________________________________________________________________\n");
            magicSquare.printMagicSquare();
            System.out.println("______________________________________________________________________________________________________________________________________________________________________________________\n\n" + //
                                "How to play: To swap a random element from the Magic Square, enter the (row of element, column of element, direction of movement (eg. U, D, L, R)) you wish to move with the format: (eg. 2 1 D)." + //
                                "______________________________________________________________________________________________________________________________________________________________________________________\n");
            System.out.println("Remember, I'll be counting the amount of steps you take. Let's see how little steps you can take to solve it!\n" + // 
                                "______________________________________________________________________________________________________________________________________________________________________________________\n");
            magicSquare.userInteraction(); 
            break;
        }
        scanner.close();
    }
}
