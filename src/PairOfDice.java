
public class PairOfDice 
{
	private static PairOfDice instance = new PairOfDice();

	private final int MAX = 6;
	private int faceValue1;
	private int faceValue2;
	
	Die die1 = new Die();
	Die die2 = new Die();

	public static PairOfDice getInstance(){
		return instance;
	}
	
	private PairOfDice() {		
		faceValue1 = 1;
		faceValue2 = 1;
	}
	
	public void rollDice() {
		faceValue1 = die1.roll();
		faceValue2 = die2.roll();
	}
	
	public int getFirstDie() {
		return faceValue1;
	}
	
	public int getSecondDie() {		
		return faceValue2;
	}
	
	public void setFirstDie(int value) {
		if (value > 0 && value <= MAX)
			faceValue1 = value;
	}
	
	public void setSecondDie(int value) {
		if (value > 0 && value <= MAX)
			faceValue2 = value;
	}
	
	public int getSum() {
		int sum = faceValue1 + faceValue2;
		return sum;
	}
}