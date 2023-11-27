public class SudokuSolver {
	private final int[][] table;
	private boolean isSolved; 

	public SudokuSolver(int[][] table) {
		this.table = table;
		isSolved = solve();
	}

	private int[] findEmpty() {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) 
				if (table[i][j] == 0)
					return new int[] {i, j};
		return new int[3];
	}
	private boolean checkTable() {
		for (int i = 1; i <= 9; i++) {
			for (int j = 0; j < 9; j++) {
				int[] counters = {0, 0};
				for (int k = 0; k < 9; k++) {
					if (table[j][k] == i)
						counters[0]++;
					if (table[k][j] == i)
						counters[1]++;
					if (counters[0] > 1 || counters[1] > 1)
						return false;
				}
			}
			for (int j = 0; j < 9; j += 3)
				for (int k = 0; k < 9; k += 3) {
					int counter = 0;
					for (int l = 0; l < 3; l++)
						for (int m = 0; m < 3; m++)
							if (table[j+l][k+m] == i)
								counter++;
					if (counter > 1)
						return false;
				}
		}
		return true;
	}
	private boolean solve() {
		if (!checkTable())
			return false;
		if (findEmpty().length == 3)
			return true;
		int x = findEmpty()[0];
		int y = findEmpty()[1];
		for (int n = 1; n <= 9; n++) {
			table[x][y] = n;
			if (checkTable()) 
				if (solve())
					return true;
			table[x][y] = 0;
		}
		return false;
	}
	public void printSolvedTable() {
		if (isSolved)
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++)
					System.out.printf("%d ", table[i][j]);
				System.out.println();
			}
		else 
			System.out.println("This table is unsolvable.");
	}
}
