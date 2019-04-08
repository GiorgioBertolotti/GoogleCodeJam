import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int cases = in.nextInt();
		for (int i = 1; i <= cases; ++i) {
			int n = in.nextInt();
			solveNum(n, i);
		}
	}

	public static void solveNum(int n, int index) {
		int len = (int) Math.log10(n) + 1;
		int a = 0, b = 0, i = 0, acc = n;
		while (acc > 0) {
			int digit = acc % 10;
			if (digit == 4) {
				a += 3 * Math.pow(10, i);
				b += Math.pow(10, i);
			} else {
				a += digit * Math.pow(10, i);
			}
			i++;
			acc /= 10;
		}
		System.out.println("Case #" + index + ": " + a + " " + b);
	}
}