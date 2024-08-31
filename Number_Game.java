import java.util.*;

public class Number_Game{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        boolean playagain = true;
        int totalScore = 0;
        int roundNumber =1;

        while(playagain){
            int targetNumber = random.nextInt(100)+1;//Random number between 1 to 100
            int attempts = 0;
            int maxAttempts = 7; //Limiting attempts to 7 
            boolean hasWon = false;

            System.out.println("Round "+roundNumber+": Guess the number between 1 to 100..");

            while(attempts < maxAttempts){
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                attempts++;

                if(guess < targetNumber){
                    System.out.println("Too low");
                }
                else if(guess > targetNumber){
                    System.out.println("Too High");
                }
                else{
                    System.out.println("Yeah! You guessed the number in "+ attempts+ " attempts.");
                    hasWon = true;
                    totalScore +=(maxAttempts - attempts +1); // Scoring based on remaining attempts
                    break;
                }
            }

            if(!hasWon){
                System.out.println("Sorry , You have used all your attempts. The number was " + targetNumber+" .");
    
            }
            System.out.print("Do you want to play another round? (yes/no): ");
            playagain = sc.next().equalsIgnoreCase("yes");
            roundNumber++;
        
        }
        System.out.println("Game over! Your total score is: "+totalScore);
        sc.close();
    }
}