package TicTacToe;

import java.util.HashSet;
import java.util.Scanner;

public class TicTacToe {
    static HashSet<Integer> ur_set = new HashSet<Integer>();
    static HashSet<Integer> comp_set = new HashSet<Integer>();

    public static void main(String[] args) {
        char[][] g_board = {
            { ' ', '|', ' ', '|', ' ' },
            { '-', '+', '-', '+', '-' },
            { ' ', '|', ' ', '|', ' ' },
            { '-', '+', '-', '+', '-' },
            { ' ', '|', ' ', '|', ' ' }
        };
        
        Scanner in = new Scanner(System.in);
        printBoard(g_board);
        
        while (true) {
            System.out.print("Enter values from 1-9: ");
            int user_pos = in.nextInt();
            
            while (ur_set.contains(user_pos) || comp_set.contains(user_pos) || user_pos < 1 || user_pos > 9) {
                System.out.println();
                System.out.print("Retry, Enter values from 1-9: ");
                user_pos = in.nextInt();
            }
            
            placeSymbol(g_board, user_pos, "You");
            printBoard(g_board);
            
            String res = checkWinner();
            if (res.length() > 0) {
                System.out.println(res);
                break;
            }
            
            if (ur_set.size() + comp_set.size() == 9) {
                System.out.println("DRAW");
                break;
            }
            
            int cpu_pos = generateRandomPosition();
            while (ur_set.contains(cpu_pos) || comp_set.contains(cpu_pos)) {
                cpu_pos = generateRandomPosition();
            }
            
            placeSymbol(g_board, cpu_pos, "Comp");
            printBoard(g_board);
            
            res = checkWinner();
            if (res.length() > 0) {
                System.out.println(res);
                break;
            }
        }
        
        in.close();
    }

    static void printBoard(char[][] g_board) {
        for (int i = 0; i < g_board.length; i++) {
            for (int j = 0; j < g_board[0].length; j++) {
                System.out.print(g_board[i][j]);
            }
            System.out.println();
        }
    }
    
    static String checkWinner() {
        HashSet<Integer> r1 = new HashSet<Integer>();
        r1.add(1); r1.add(2); r1.add(3);
        
        HashSet<Integer> r2 = new HashSet<Integer>();
        r2.add(4); r2.add(5); r2.add(6);
        
        HashSet<Integer> r3 = new HashSet<Integer>();
        r3.add(7); r3.add(8); r3.add(9);
        
        HashSet<Integer> c1 = new HashSet<Integer>();
        c1.add(1); c1.add(4); c1.add(7);
        
        HashSet<Integer> c2 = new HashSet<Integer>();
        c2.add(2); c2.add(5); c2.add(8);
        
        HashSet<Integer> c3 = new HashSet<Integer>();
        c3.add(3); c3.add(6); c3.add(9);
        
        HashSet<Integer> d1 = new HashSet<Integer>();
        d1.add(1); d1.add(5); d1.add(9);
        
        HashSet<Integer> d2 = new HashSet<Integer>();
        d2.add(3); d2.add(5); d2.add(7);

        HashSet<HashSet<Integer>> set = new HashSet<HashSet<Integer>>();
        set.add(r1);
        set.add(r2);
        set.add(r3);
        set.add(c1);
        set.add(c2);
        set.add(c3);
        set.add(d1);
        set.add(d2);

        for (HashSet<Integer> c : set) {
            if (ur_set.containsAll(c)) {
                return "YOU WIN";
            } else if (comp_set.containsAll(c)) {
                return "YOU LOST";
            }
        }

        if (ur_set.size() + comp_set.size() == 9) {
            return "DRAW";
        }

        return "";
    }

    static int generateRandomPosition() {
        int max = 9;
        int min = 1;
        int range = max - min + 1;
        int res = (int) (Math.random() * range) + min;
        return res;
    }

    static void placeSymbol(char[][] g_board, int pos, String user) {
        char symbol = 'X';
        if (user.equals("You")) {
            symbol = 'X';
            ur_set.add(pos);
        } else if (user.equals("Comp")) {
            symbol = 'O';
            comp_set.add(pos);
        }

        switch (pos) {
            case 1:
                g_board[0][0] = symbol;
                break;
            case 2:
                g_board[0][2] = symbol;
                break;
            case 3:
                g_board[0][4] = symbol;
                break;
            case 4:
                g_board[2][0] = symbol;
                break;
            case 5:
                g_board[2][2] = symbol;
                break;
            case 6:
                g_board[2][4] = symbol;
                break;
            case 7:
                g_board[4][0] = symbol;
                break;
            case 8:
                g_board[4][2] = symbol;
                break;
            case 9:
                g_board[4][4] = symbol;
                break;
            default:
                System.out.println("Invalid Input");
        }
    }
}