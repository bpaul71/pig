public class App {
    private static Player user, computer;
    private static boolean gameIsActive = true;
    private static int END_GAME_SCORE = 100;
    private static String COMPUTER_NAME = "Computer";

    public static void main(String[] args) throws Exception {

		determinePlayers();

        while(gameIsActive){
            playGame();
        }
        if (computer.getTotalScore() >= END_GAME_SCORE) {
			System.out.println();
			System.out.println("The Computer won :(");
		} else {
			System.out.println();
			System.out.println("You won!");
		}

    }

    private static void playGame() {
        if (user.isPlayersTurn()) {
            user.takeTurn();
        } else {
            computer.takeTurn();
        }
        if (user.getTotalScore() >= END_GAME_SCORE || computer.getTotalScore() >= END_GAME_SCORE) {
            gameIsActive = false;
        }
    }

    private static void determinePlayers(){
        computer = new Player(getComputersName());

        user = new Player(UserInputUtility.getInstance().getPlayersName());

        boolean isUserFirst = UserInputUtility.getInstance().doesUserWantToGoFirst();
        user.setPlayersTurn(isUserFirst);
        computer.setPlayersTurn(!isUserFirst);
    }

    public static void switchToNextPlayersTurn(){
        user.setPlayersTurn(!user.isPlayersTurn());
        computer.setPlayersTurn(!computer.isPlayersTurn());
    }

    public static int getEndGameScore() {
        return END_GAME_SCORE;
    }

    public static String getComputersName(){
        return COMPUTER_NAME;
    }
}
