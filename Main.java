import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        breakAll:
        while(true){
            System.out.println("What game would you like to play? (1/2/q)\n1. Sliding Puzzle\n2. Dots and Boxes\nq: quit");

            boolean invalidInput = false;
            do {
                String answer = scan.next();
                switch (answer) {
                    case "1":
                        SlidingController spGame = new SlidingController(scan);
                        spGame.run();
                        break;
                    
                    case "2":
                        DBController dbGame = new DBController(scan);
                        dbGame.run();
                        break breakAll;
                    
                    case "q'":
                        System.out.println("See you next time!");
                        break;
                    
                    default: 
                        System.out.println("Enter 1, 2, or 'q' to proceed.");
                        invalidInput = true;
            }} while (invalidInput);
        }
        
    }
}