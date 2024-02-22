import java.util.Random;
import java.util.Scanner;

public class MayinTarlasi {
    int rows;
    int columns;
    String[][] mineMap;
    String[][] gameMap;
    int mine, r, c, count;
    boolean isTrue = true;

    public MayinTarlasi(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.gameMap = new String[rows][columns];
        this.mineMap = new String[rows][columns];
        this.mine = (rows * columns) / 4;
    }

    public void mineMap() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mineMap[i][j] = "-";
                gameMap[i][j] = "-";
            }
        }
    }

    public void randomNumber() {
        Random r = new Random();
        for (int i = 0; i < this.mine; i++) {
            int rowNumber = r.nextInt(this.rows);
            int columnsNumber = r.nextInt(this.columns);
            if (!this.mineMap[rowNumber][columnsNumber].equals("*")) {
                this.mineMap[rowNumber][2] = "*";
            }
        }
    }

    public void printMineMap() {
        randomNumber();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (!this.mineMap[i][j].equals("*")) {
                    this.mineMap[i][j] = "-";
                }
            }
        }
    }

    public void printGameMap() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.gameMap[i][j] = "-";
                System.out.print(this.gameMap[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void control() {
        count = 0;
        for (int i = (r - 1); i <= (r + 1); i++) {
            for (int j = (c - 1); j <= (c + 1); j++) {
                if (i < 0 || j < 0 || i >= this.rows || j >= this.columns) {
                    continue;
                }
                if (this.mineMap[i][j].equals("*")) {
                    count++;
                }
            }
        }
        this.gameMap[r][c] = String.valueOf(count);
        this.mineMap[r][c] = String.valueOf(count);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                System.out.print(this.gameMap[i][j] + " ");
            }
            System.out.println();
        }

    }

    public boolean finish() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (this.mineMap[i][j].equals("-")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void ChooseIndex() {
        Scanner sc = new Scanner(System.in);
        isTrue = false;
        while (!isTrue) {
            System.out.print("Enter row: ");
            r = sc.nextInt();
            System.out.print("Enter column: ");
            c = sc.nextInt();

            r -= 1;
            c -= 1;

            if (r > rows || c > columns) {
                System.out.println("You entered a value outside the map boundaries. Enter again.");
                continue;
            }
            if (mineMap[r][c].equals("*")) {
                System.out.println("Game Over!");
                break;
            }
            control();
            if (finish()) {
                System.out.println("Congratulations! You win.");
                break;
            }

        }
        sc.close();
    }

    public void run() {
        printGameMap();
        mineMap();
        printMineMap();
        ChooseIndex();
    }

}
