/**
 * Java. Level 1. Lesson 4. Example of homework
 *  Tic-tac-toe in console with simple AI
 *
 * @author Sergey Iryupin
 * @version dated Aug 08, 2017
 */
import java.util.*;

class HW4Lesson {

    final int SIZE = 3;         // size of the game map
    final char DOT_X = 'x';     // sign of human
    final char DOT_O = 'o';     // sign of AI
    final char DOT_EMPTY = '.'; // sign of empty cell
    char[][] map = new char[SIZE][SIZE];
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public static void main(String[] args) {
        new HW4Lesson();
    }

    HW4Lesson() {
        initMap();
        while (true) {
            printMap();
            turnHuman();
            if (checkWin(DOT_X)) {
                System.out.println("YOU WON!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Sorry, DRAW!");
                break;
            }
            turnAI();
            //printMap();
            if (checkWin(DOT_O)) {
                System.out.println("AI WON!");
                break;
            }
            /*if (isMapFull()) {                    // this code doesn't matter
                System.out.println("Sorry, DRAW!"); // because a human always
                break;                              //  makes a move last in 3x3
            }*/
        }
        System.out.println("GAME OVER.");
        printMap();
    }

    void initMap() {                                // init game's field
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                map[i][j] = DOT_EMPTY;
    }

    void printMap() {                               // output game's field
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    void turnHuman() {                              // human action
        int x, y;
        do {
            System.out.println("Enter X and Y (1..3):");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    void turnAI() {                                 // AI action
        int x, y;
        for (x = 0; x < SIZE; x++)                  // simple blocking
            for (y = 0; y < SIZE; y++)
                if (isCellValid(x, y)) {            // if cell empty
                    map[y][x] = DOT_X;              // try to use like human
                    if (checkWin(DOT_X)) {          // if win
                        map[y][x] = DOT_O;          // block
                        return;                     // and exit
                    }
                    map[y][x] = DOT_EMPTY;          // restore cell
                }
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        map[y][x] = DOT_O;
    }

    boolean checkWin(char dot) {                    // check win condition
        // check horizontals and verticals
        for (int i = 0; i < SIZE; i++)
            if ((map[i][0] == dot && map[i][1] == dot && map[i][2] == dot) ||
                (map[0][i] == dot && map[1][i] == dot && map[2][i] == dot))
                return true;
        // check diagonals
        if ((map[0][0] == dot && map[1][1] == dot && map[2][2] == dot) ||
            (map[2][0] == dot && map[1][1] == dot && map[0][2] == dot))
            return true;
        return false;
    }

    boolean isMapFull() {                           // check field filling
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (map[i][j] == DOT_EMPTY)
                    return false;
        return true;
    }

    boolean isCellValid(int x, int y) {             // check cell
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE)
            return false;
        if (map[y][x] == DOT_EMPTY)
            return true;
        return false;
    }
}