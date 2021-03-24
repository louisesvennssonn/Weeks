package exercises;

import samples.ObjectArrMeth;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/*
 *  Utilities to input/output player data for a command line game
 *
 *  See:
 *  - UseAConstructor
 *  - ObjectArrMeth
 *
 */
public class Ex1ReadPlayers {
    public static void main(String[] args) {
        new Ex1ReadPlayers().program();
    }

    final Scanner sc = new Scanner(in);

    void program() {
       Player[] players = inputPlayers();
       out.print( "Players are :"+ players);
    }

    // This class captures the concept of a Player
    class Player {
        String name;   // A Player has a name and...
        int points = 0;    // ... and points

        public Player(String name, int points) {
            this.name = name;
            this.points = points;
        }
    }

    // ---------- Methods -------------------

    Player[] inputPlayers() {
        out.println("How many players? > ");
        int nPlayers = sc.nextInt();
        Player[] players = new Player[nPlayers];

        for (int i = 0; i < players.length; i++) {
            out.println("Name for player > " + (i+1));
             String person = sc.nextLine();
        }
        return players;
    }
}
