import java.util.Scanner;

public class UserInputUtility {
    private Scanner scan = new Scanner(System.in);

    private static UserInputUtility instance = new UserInputUtility();

	public static UserInputUtility getInstance(){
		return instance;
	}
	
	private UserInputUtility() {		
	}

    public String getPlayersName(){
        System.out.println("What is your name?");
        String playersName = scan.nextLine();
        return playersName;
    }

    public boolean doesUserWantToGoFirst(){
        System.out.println("\nWould you like to go first? Enter \"yes\" or \"no\": ");
		String answer = scan.nextLine();

        if (answer.equals("yes")) {
            return true;
        } else {
            return false;
        }       
    }

    public boolean doesUserWantToContinueRolling(){
        System.out.println("\nWould you like to roll again? Enter \"yes\" or \"no\": ");
        String result = scan.nextLine();

        if (result.equals("no")) {
            return false;
        } else {
            return true;
        }
    }
}
