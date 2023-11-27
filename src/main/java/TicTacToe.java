import java.util.Scanner;

public class TicTacToe {
    private static final Scanner scanner = new Scanner(System.in);
    private final byte[] box = new byte[9];
    private byte winner = 0;

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }

    public void play() {
        initializeBox();
        printWelcomeMessage();
        while (true) {
            printBox();
            if (winner != 0) {
                printResult();
                break;
            }
            playerMove();
            if (checkWin('X')) {
                winner = 1;
                continue;
            }
            if (checkDraw()) {
                winner = 3;
                continue;
            }
            computerMove();
            if (checkWin('O')) {
                winner = 2;
            }
        }
    }

    private void initializeBox() {
        for (int i = 0; i < 9; i++) {
            box[i] = (byte) (i + 1);
        }
    }

    private void printWelcomeMessage() {
        System.out.println("Enter box number to select. Enjoy!\n");
    }

    private void printBox() {
        System.out.println("\n\n " + getBoxChar(0) + " | " + getBoxChar(1) + " | " + getBoxChar(2) + " ");
        System.out.println("-----------");
        System.out.println(" " + getBoxChar(3) + " | " + getBoxChar(4) + " | " + getBoxChar(5) + " ");
        System.out.println("-----------");
        System.out.println(" " + getBoxChar(6) + " | " + getBoxChar(7) + " | " + getBoxChar(8) + " \n");
    }

    private char getBoxChar(int index) {
        return (char) box[index];
    }

    private void playerMove() {
        while (true) {
            byte input = scanner.nextByte();
            if (input > 0 && input < 10) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O') {
                    System.out.println("That one is already in use. Enter another.");
                } else {
                    box[input - 1] = 'X';
                    break;
                }
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }

    private boolean checkWin(char player) {
        return (box[0] == player && box[1] == player && box[2] == player) ||
                (box[3] == player && box[4] == player && box[5] == player) ||
                (box[6] == player && box[7] == player && box[8] == player) ||
                (box[0] == player && box[3] == player && box[6] == player) ||
                (box[1] == player && box[4] == player && box[7] == player) ||
                (box[2] == player && box[5] == player && box[8] == player) ||
                (box[0] == player && box[4] == player && box[8] == player) ||
                (box[2] == player && box[4] == player && box[6] == player);
    }

    private boolean checkDraw() {
        for (int i = 0; i < 9; i++) {
            if (box[i] != 'X' && box[i] != 'O') {
                return false;
            }
        }
        return true;
    }

    private void computerMove() {
        while (true) {
            byte rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                box[rand - 1] = 'O';
                break;
            }
        }
    }

    private void printResult() {
        if (winner == 1) {
            System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
        } else if (winner == 2) {
            System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
        } else if (winner == 3) {
            System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
        }
    }
}