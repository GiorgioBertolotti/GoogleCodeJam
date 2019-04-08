import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int cases = in.nextInt();
		for (int i = 1; i <= cases; ++i) {
			int n = in.nextInt();
			in.nextLine();
			String maze = in.nextLine();
			solveMaze(n, maze, i);
		}
	}

	public static void solveMaze(int n, String maze, int index) {
		String result = "";
		for (char a : maze.toCharArray()) {
			if (a == 'E')
				result += 'S';
			else if (a == 'S')
				result += 'E';
		}
		System.out.println("Case #" + index + ": " + result);
	}
}