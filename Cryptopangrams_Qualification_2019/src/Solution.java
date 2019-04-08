import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int cases = in.nextInt();
		for (int i = 1; i <= cases; ++i) {
			int n = in.nextInt();
			int l = in.nextInt();
			in.nextLine();
			String crypted = in.nextLine();
			solvePangram(n, l, crypted, i);
		}
	}

	public static void solvePangram(int n, int l, String crypted, int index) {
		String[] splitted = crypted.split(" ");
		int[] numbers = new int[l];
		for (int i = 0; i < l; i++) {
			numbers[i] = Integer.parseInt(splitted[i]);
		}
		int[] msgPrimes = new int[l + 1];
		for (int i = 0; i < l - 1; i++) {
			int max = Math.max(numbers[i], numbers[i + 1]);
			int min = Math.min(numbers[i], numbers[i + 1]);
			msgPrimes[i + 1] = max != min ? gcd(max, min) : (int) Math.sqrt(max);
		}
		msgPrimes[0] = numbers[0] / msgPrimes[1];
		msgPrimes[l] = numbers[l - 1] / msgPrimes[l - 1];
		Set<Integer> msgPrimesOrdered = new TreeSet<>();
		for (int num : msgPrimes)
			msgPrimesOrdered.add(num);
		Map<Integer, Character> alphabet = new HashMap<>();
		char currentLett = 'A';
		for (Integer num : msgPrimesOrdered) {
			alphabet.put(num, currentLett++);
		}
		String result = "";
		for (int num : msgPrimes)
			result += alphabet.get(num);
		System.out.println("Case #" + index + ": " + result);
	}

	public static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}
}