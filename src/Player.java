public class Player {
    private String name;
    private int totalPlayersGameScore = 0, currentTurnTotalScore = 0;
    private boolean isPlayersTurn;
	private int diceOneValue = 0, diceTwoValue = 0, diceSum = 0;
    private PairOfDice dice = PairOfDice.getInstance();

    public Player(String name) {
        this.name = name;
    }

    public void takeTurn(){
        System.out.println("\n" + name +" picks up the dice, shakes them, and give them a virtual roll!");
        while(isPlayersTurn) {
            rollTheDice();
            evaluateTheDice();
            // First determine if the player lost their turn by a bad role of the dice
            if (isPlayersTurn) {
                if (totalPlayersGameScore + currentTurnTotalScore >= App.getEndGameScore()){
                    totalPlayersGameScore += currentTurnTotalScore;
                } else {
                    determineIfPlayerWantsToContinue();
                }
            }
        }
    }

    private void determineIfPlayerWantsToContinue() {
        if (name.equals(App.getComputersName())) {
            if (currentTurnTotalScore >= 20) {
                voluntarilyEndPlayersCurrentTurn();
            }
        } else {
            boolean continueRolling = UserInputUtility.getInstance().doesUserWantToContinueRolling();
            if (!continueRolling) {
                voluntarilyEndPlayersCurrentTurn();
            }
        }    
    }

    private void voluntarilyEndPlayersCurrentTurn(){
        totalPlayersGameScore += currentTurnTotalScore;
        currentTurnTotalScore = 0;
        App.switchToNextPlayersTurn();
    }

	private void rollTheDice() {
		dice.rollDice();

		diceOneValue = dice.getFirstDie();
		diceTwoValue = dice.getSecondDie();
		diceSum = dice.getSum();
		System.out.println("\nFirst Die: " + diceOneValue);
		System.out.println("Second Die: " + diceTwoValue);
        System.out.println(("Total Rolled: " + diceSum));
	}

    private void evaluateTheDice() {
        if (diceSum == 2) {
			currentTurnTotalScore = 0;
            totalPlayersGameScore = 0;
			System.out.println("OH NO! Because " + name + " rolled two 1's, " + name + " has lost all of their game points :(");
			App.switchToNextPlayersTurn();
		} else if (diceOneValue == 1 || diceTwoValue == 1) {
			currentTurnTotalScore = 0;
            System.out.println("SORRY. Because " + name + " roled one 1, " + name + " will get no points this turn");
            System.out.println("Points earned this turn: " + currentTurnTotalScore);
			App.switchToNextPlayersTurn();
		} else {
			currentTurnTotalScore += diceSum;
            System.out.println("Points earned this turn: " + currentTurnTotalScore);
            int tally = totalPlayersGameScore + currentTurnTotalScore;
            System.out.println("Total Score: " + tally);
		}
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getTotalScore() {
        return totalPlayersGameScore;
    }
    public void setTotalScore(int totalScore) {
        this.totalPlayersGameScore = totalScore;
    }

    public boolean isPlayersTurn() {
        return isPlayersTurn;
    }

    public void setPlayersTurn(boolean isPlayersTurn) {
        this.isPlayersTurn = isPlayersTurn;
    }
    
}
