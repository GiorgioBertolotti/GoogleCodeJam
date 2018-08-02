import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlphabetCake {
	static List<Character> letters = new ArrayList<>();

	public static void main(String[] args) {
		try {
			String fileName = System.getProperty("user.dir") + "\\input.in";
			File file = new File(fileName);
			Scanner input = new Scanner(file);
			List<String> lines = new ArrayList<String>();
			while (input.hasNextLine()) {
				lines.add(input.nextLine());
			}
			input.close();
			int testCases = Integer.parseInt(lines.get(0));
			lines.remove(0);
			for (int i = 0; i < testCases; i++) {
				String cakeSize = lines.get(0);
				lines.remove(0);
				int R = Integer.parseInt(cakeSize.substring(0, cakeSize.indexOf(" ")));
				int C = Integer.parseInt(cakeSize.substring(cakeSize.indexOf(" ") + 1));
				char[][] M = new char[R][C];
				for (int j = 0; j < R; j++) {
					String row = lines.get(0);
					lines.remove(0);
					for (int k = 0; k < C; k++) {
						M[j][k] = row.charAt(k);
					}
				}
				M = solve(M, 0, 0, M.length, M[0].length);
				System.out.println("Case #" + (i + 1) + ":");
				printCase(M);
				letters.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static char[][] solve(char[][] M, int startX, int startY, int endX, int endY) {
		if (!thereAreQM(M))
			return M;
		int primoX = -1;
		int primoY = -1;
		int secondoX = -1;
		int secondoY = -1;
		for (int i = startX; i < endX; i++) {
			for (int j = startY; j < endY; j++) {
				if (M[i][j] != '?') {
					if (primoX != -1) {
						secondoX = i;
						secondoY = j;
						break;
					} else {
						primoX = i;
						primoY = j;
					}
				}
			}
			if (secondoX != -1)
				break;
		}
		if (secondoX == -1) {
			// RIEMPIO
			for (int i = startX; i < endX; i++) {
				for (int j = startY; j < endY; j++) {
					M[i][j] = M[primoX][primoY];
				}
			}
		} else {
			if (primoX == secondoX) {
				int rigaStop = -1;
				for (int i = primoX + 1; i < endX; i++) {
					for (int j = startY; j < secondoY; j++) {
						if (M[i][j] != '?') {
							rigaStop = i;
							break;
						}
					}
					if (rigaStop != -1)
						break;
				}
				if (rigaStop == -1) {
					// RIEMPIO
					for (int i = startX; i < endX; i++) {
						for (int j = startY; j < secondoY; j++) {
							M[i][j] = M[primoX][primoY];
						}
					}
					M = solve(M, startX, secondoY, endX, endY);
				} else {
					// RIEMPIO
					for (int i = startX; i < rigaStop; i++) {
						for (int j = startY; j < secondoY; j++) {
							M[i][j] = M[primoX][primoY];
						}
					}
					// RICORSIVA
					M = solve(M, startX, secondoY, rigaStop, endY);
					M = solve(M, rigaStop, startY, endX, endY);
				}
			} else {
				int rigaStop = secondoX;
				// RIEMPIO
				for (int i = startX; i < rigaStop; i++) {
					for (int j = startY; j < endY; j++) {
						M[i][j] = M[primoX][primoY];
					}
				}
				// RICORSIVA
				M = solve(M, rigaStop, startY, endX, endY);
			}
		}
		return M;
	}

	public static boolean thereAreQM(char[][] M) {
		boolean thereIs = false;
		for (int j = 0; j < M.length; j++) {
			for (int k = 0; k < M[j].length; k++) {
				if (M[j][k] == '?') {
					thereIs = true;
					break;
				}
			}
			if (thereIs)
				break;
		}
		return thereIs;
	}

	public static void printCase(char[][] M) {
		for (int j = 0; j < M.length; j++) {
			for (int k = 0; k < M[j].length; k++) {
				System.out.print(M[j][k]);
			}
			System.out.println();
		}
	}
}
