import java.util.Random;
import java.util.Scanner;

public class Main {

    //Credit where credit is due:
    //https://www.javacodegeeks.com/methods-to-clear-the-java-console-screen.html - (ANSI escape characters for ClearConsole())
    //https://talyian.github.io/ansicolors/ - (ANSI escape characters for colors)

    //I have worked with ANSI escape codes before and I love them :D
    //They add so much spice to terminals

    static Scanner scnr = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {

        //Used to keep track of the sequence
        String sequence = "";
        String altSequence = "";
        String numSequence = "";

        //Score
        int points = 0;

        //Arrays for the easy mode sequence
        String[] colorArray = {" Red", " Yellow", " Green", " Blue"};
        String[] altColorArray = {" R", " Y", " G", " B"};
        String[] ANSIColorArray = {"\u001B[31m", "\u001B[33m", "\u001B[32m", "\u001B[34m", "\u001B[0m"}; //The ANSI color codes for red, yellow, green, blue, and default

        //Asks the user if they want a tutorial and if they want to engage in hard mode
        PromptTutorial();
        boolean hardMode = PromptHardMode();

        //Prompts the user to begin the game
        System.out.print("Press ENTER to begin the game\n>>");
        scnr.nextLine();
        System.out.println("");

        //Gameplay loop
        while (true) {

            //Prints out the sequence and counts down to clearing
            if (hardMode) {

                sequence += rand.nextInt(10);
                System.out.println("Sequence is: " + sequence + "\n");

            }
            else {

                int next = rand.nextInt(4);

                sequence += colorArray[next];
                altSequence += altColorArray[next];
                numSequence += next;

                int color;

                System.out.print("Sequence is:");

                for (int i = 0; i < numSequence.length(); i++) {

                    color = numSequence.charAt(i) - '0';

                    System.out.print(ANSIColorArray[color] + colorArray[color] + ANSIColorArray[4]);

                }

                System.out.println("");

            }

            Countdown(3);

            //Clears the sequence and prompts the user to re-type it
            BoringCheesyClearConsole();
            System.out.print("What was the sequence?\n>>");

            String input = scnr.nextLine();
            System.out.println("");

            //If the user input matches the sequence or altSequence string (ignoring case and spaces)
            if (sequence.replaceAll("\\s+", "").equalsIgnoreCase(input.replaceAll("\\s+", "")) || altSequence.replaceAll("\\s+", "").equalsIgnoreCase(input.replaceAll("\\s+", ""))) {

                //Add 1 point, tells the user the point total, and prompts the user to advance to the next round

                points += 1;
                System.out.print("Correct! Your point total is now " + points + "\n\nPress ENTER for the next round.\n>>");
                scnr.nextLine();
                System.out.println("");
            }
            else {

                //Asks the user if they want to play again: if yes, reset points and sequences, prompt hard mode, and restart the game; if no, exit program

                System.out.print("Incorrect! Your final point total is " + points + "\n\nWould you like to try again?:");

                if (PromptYN()) {

                    sequence = "";
                    altSequence = "";
                    numSequence = "";

                    hardMode = PromptHardMode();

                    points = 0;

                    System.out.print("Press ENTER to begin the game\n>>");
                    scnr.nextLine();
                    System.out.println("");

                }
                else {

                    System.exit(0);

                }

            }

        }



    }

    //Clears out all the text in the console [Unused bc for some reason Intellij does not support these ANSI characters in particular >:(]
    public static void AwesomeSauceClearConsole() {

        System.out.print("\u001B[H");
        System.out.print("\u001B[2J"); //ANSI escape characters for moving the cursor to the top left of the console and erasing all the text
        System.out.flush(); //Removes any buffered inputs leftover
        return;

    }

    //Clears the console by printing a whole bunch of newlines :(
    public static void BoringCheesyClearConsole() {

        //Doesn't actually get rid of anything, just pushes it really far up >:(
        //We call this the "sweep it under the ceiling" method in the industry
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

    }

    //Prompts the user to type y or n; returns true or false based on input
    public static boolean PromptYN() {

        while (true) {

            //repeatedly prompts the user to input y or n until a valid input is given: returns true for y, false for n

            System.out.print("(Y/N)\n>>");

            String input = scnr.nextLine();

            if ("y".equalsIgnoreCase(input)) {
                System.out.println("");
                return true;
            } else if ("n".equalsIgnoreCase(input)) {
                System.out.println("");
                return false;
            }

            System.out.println("");

        }

    }

    //Asks the user whether or not they want a tutorial and provides it if they do
    public static void PromptTutorial() {

        System.out.println("\n\nWould you like a tutorial?:");

        if (PromptYN()) {

            System.out.println("Welcome to Simon Says: a memory game about COLORS and NUMBERS.\n\nHow to play:");
            System.out.println("Each round the player will be given a sequence to remember. If the player can successfully re-type the");
            System.out.println("sequence into the terminal when prompted, then they will be rewarded with a point. Otherwise, they will");
            System.out.println("Have to start the game over.\n");
            System.out.println("On \'Easy Mode\' the given sequences are of colors. These colors can be re-typed as full words or simply");
            System.out.println("as letters, but do not mix and match the two methods! Answers do not account for spaces and are case");
            System.out.println("insensitive.\n");
            System.out.println("On \'Hard Mode\' the given sequences are of numbers, a much trickier challenge. As with Easy Mode, the");
            System.out.println("Sequences do not account for spaces.\n");
            System.out.print("Press ENTER to continue\n>>");

            scnr.nextLine();
            System.out.println("");

        }

        return;

    }

    //Asks the use whether or not to engage hard mode
    public static boolean PromptHardMode() {

        System.out.println("Would you like to play the game on hard mode?:");
        return PromptYN();

    }

    //Counts down by one every second according to 'count'
    public static void Countdown(int count) {

        for (int i = count; i > 0; i--) {

            try {

                System.out.println(i + "...\n");
                Thread.sleep(1000);

            }
            catch (InterruptedException sad) {}

        }

        return;

    }

}