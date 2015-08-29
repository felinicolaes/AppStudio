public class BRTest {
	private int calls = 0;
	private int successfulCalls = 0;
	private int totalReturned = 0;

	private int[] exepCounts = new int[5];
	
	public static void main(String[] args) {
		BRTest me = new BRTest();
		me.resetCounts();
		me.nRandInts(30);
		me.writeData();
	}

	public void callIt() {
		calls++;
		try {
			totalReturned = totalReturned + BadRandom.randVal();
			successfulCalls++;
		} catch (ArithmeticException e) {
			System.out.println(e.getMessage());
			exepCounts[0]++;
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			exepCounts[1]++;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
			exepCounts[2]++;
		} catch (ClassCastException e) {
			System.out.println(e.getMessage());
			exepCounts[3]++;
		} catch (NegativeArraySizeException e) {
			System.out.println(e.getMessage());
			exepCounts[4]++;
		} 
	}

	public void resetCounts() {
		calls = 0;
		successfulCalls = 0;
		totalReturned = 0;

		exepCounts[0] = 0;
		exepCounts[1] = 0;
		exepCounts[2] = 0;
		exepCounts[3] = 0;
		exepCounts[4] = 0;
	}

	public void nRandInts(int n) {
		while (successfulCalls < n) {
			callIt();
		}
	}

	public void writeData() {
		System.out.println();
		System.out.println("===============================================");
		System.out.println("Number of calls: " + calls);
		System.out.println("Sucessful calls: " + successfulCalls);
		System.out.println("Total returned: " + totalReturned);
		System.out.println("Percentage ArithmeticExceptions: " + (double) exepCounts[0]/calls*100);
		System.out.println("Percentage NullPointerException: " + (double) exepCounts[1]/calls*100);
		System.out.println("Percentage ArrayIndexOutOfBoundsException: " + (double) exepCounts[2]/calls*100);
		System.out.println("Percentage ClassCastException: " + (double) exepCounts[3]/calls*100);
		System.out.println("Percentage NegativeArraySizeException: " + (double) exepCounts[4]/calls*100);
		System.out.println("Percentage of successful calls: " + (double) successfulCalls/calls*100);
	}

}