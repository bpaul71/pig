import java.util.Scanner;

public class Pig {
	private static int userScore = 0, computerScore = 0;
	private static boolean isUserTurn;
	private static int diceOneValue = 0, diceTwoValue = 0, diceSum = 0;

	public static void printTurn(boolean user_turn) {
		if (user_turn) {
			System.out.println("\nYour turn:\n");
		} else {
			System.out.println("\nComputer's turn:\n");
		}
	}

	public static void turnStats(int currentTurnTotalScore) {
		System.out.println("First Die: " + diceOneValue);
		System.out.println("Second Die: " + diceTwoValue);
		System.out.println("Points earned this turn: " + currentTurnTotalScore);
		int tally = 0;
		if (isUserTurn) {
			tally = userScore + currentTurnTotalScore;
		} else {
			tally = computerScore + currentTurnTotalScore;
		}
		System.out.println("Total Score: " + tally);
	}

	public static void rollTheDice(PairOfDice dice) {
		dice.rollDice();

		diceOneValue = dice.getFirstDie();
		diceTwoValue = dice.getSecondDie();
		diceSum = dice.getSum();
	}

	public static int playerTakesTurn(PairOfDice dice, int currentTurnTotalScore) {
		rollTheDice(dice);

		if (diceSum == 2) {
			currentTurnTotalScore = 0;
			turnStats(currentTurnTotalScore);
			isUserTurn = !isUserTurn;
			printTurn(isUserTurn);
			return -1; // -1 is to be interpreted as the key to turning the players overall score to 0
		} else if (diceOneValue == 1 || diceTwoValue == 1) {
			currentTurnTotalScore = 0;
			turnStats(currentTurnTotalScore);
			isUserTurn = !isUserTurn;
			printTurn(isUserTurn);
			return currentTurnTotalScore;
		} else {
			currentTurnTotalScore += diceSum;
			turnStats(currentTurnTotalScore);
			return currentTurnTotalScore;
		}
	};

	public static void main(String[] args) {
		PairOfDice dice = new PairOfDice();
		int currentTurnTotalScore = 0;

		Scanner scan = new Scanner(System.in);

		System.out.print("Would you like to go first? Enter \"yes\" or \"no\": ");
		String answer = scan.nextLine();

		if (answer.equals("yes")) {
			isUserTurn = true;
		} else {
			isUserTurn = false;
		}
		printTurn(isUserTurn);

		while (true) {
			currentTurnTotalScore = 0; // reset this counter before the new turn begins for this user
			while (isUserTurn) {

				if (userScore + currentTurnTotalScore >= 100) {
					break;
				}

				currentTurnTotalScore = playerTakesTurn(dice, currentTurnTotalScore);
				if (currentTurnTotalScore <= 0) {// The user rolled at lease one 1
					if (currentTurnTotalScore < 0) { // The user rolled two 1's and their total score will get reset to 0
						userScore = 0;
					}	
				} else {
					System.out.print("Would you like to roll again? Enter \"yes\" or \"no\": ");
					String result = scan.nextLine();
		
					if (result.equals("no")) {
						userScore += currentTurnTotalScore;
						isUserTurn = false;
						printTurn(isUserTurn);
					} else {
						System.out.println();
					}
				}
			}

			currentTurnTotalScore = 0; // reset this counter before the new turn begins for this user
			while (!isUserTurn) {

				if (computerScore + currentTurnTotalScore >= 100) {
					break;
				}

				currentTurnTotalScore = playerTakesTurn(dice, currentTurnTotalScore);
				if (currentTurnTotalScore <= 0) {// The computer rolled at lease one 1
					if (currentTurnTotalScore < 0) { // The computer rolled two 1's and their total score will get reset to 0
						computerScore = 0;
					}	
					isUserTurn = true;
				} else {
					//Verify if the computer is still eligible to roll again
					if (currentTurnTotalScore > 20) {
						computerScore += currentTurnTotalScore;
						isUserTurn = true;
						printTurn(isUserTurn);
					} else {
						System.out.println();
					}
				}
			}

			if (userScore >= 100 || computerScore >= 100)
				break;
			else
				continue;
		}

		scan.close();
		if (computerScore >= 100) {
			System.out.println();
			System.out.println("The Computer won :(");
		} else {
			System.out.println();
			System.out.println("You won!");
		}
	}
}
