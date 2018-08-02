import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class DecisionTree {
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
			int cases = Integer.parseInt(lines.get(0));
			lines.remove(0);
			for (int i = 0; i < cases; i++) {
				System.out.println("Case #" + (i + 1) + ":");
				int linesToParse = Integer.parseInt(lines.get(0));
				lines.remove(0);
				String str = "";
				for (int j = 0; j < linesToParse; j++) {
					str += lines.get(0);
					lines.remove(0);
				}
				Tree t = parse(str, 0);
				int tests = Integer.parseInt(lines.get(0));
				lines.remove(0);
				for (int j = 0; j < tests; j++) {
					String testCase = lines.get(0).substring(lines.get(0).indexOf(" ") + 1);
					testCase = testCase.substring(testCase.indexOf(" ") + 1);
					String[] splited = testCase.split("\\s+");
					double res = calcWeight(t, splited);
					DecimalFormat df = new DecimalFormat("#.#######");
					df.setRoundingMode(RoundingMode.DOWN);
					String finale = df.format(res);
					while (finale.length() < 9)
						finale += "0";
					finale = finale.replace(',', '.');
					System.out.println(finale);
					lines.remove(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static double calcWeight(Tree tree, String[] categories) {
		try {
			if (tree.primo == null && tree.secondo == null)
				return tree.molt;
			Tree child;
			if (Arrays.asList(categories).contains(tree.caratteristica)) {
				child = tree.primo;
			} else {
				child = tree.secondo;
			}
			return tree.molt * calcWeight(child, categories);
		} catch (Exception e) {
			return -1;
		}
	}

	public static Tree parse(String toParse, int depth) {
		toParse = toParse.replaceAll("\\n", "");
		toParse = toParse.replaceAll("\\r", "");
		toParse = toParse.replaceAll("\\t", "");
		toParse = toParse.replaceAll(" ", "");
		Tree currentTree = new Tree(0);
		currentTree.count = 0;
		int i = 0;
		while (i < toParse.length()) {
			switch (currentTree.stato) {
			case 0: {
				if (toParse.charAt(i) == '(') {
					currentTree.stato++;
				}
				break;
			}
			case 1: {
				if ((toParse.charAt(i) >= 'a' && toParse.charAt(i) <= 'z')
						|| (toParse.charAt(i) >= 'A' && toParse.charAt(i) <= 'Z')) {
					currentTree.caratteristica += toParse.charAt(i);
					currentTree.molt = Double.parseDouble(currentTree.moltstr);
					currentTree.stato++;
				} else if (toParse.charAt(i) == ')') {
					currentTree.molt = Double.parseDouble(currentTree.moltstr);
					return currentTree;
				} else
					currentTree.moltstr += toParse.charAt(i);
				break;
			}
			case 2: {
				if (toParse.charAt(i) == '(') {
					currentTree.primo = parse(toParse.substring(i), depth + 1);
					i += currentTree.primo.count - 1;
					currentTree.count += currentTree.primo.count - 1;
					currentTree.stato++;
				} else {
					currentTree.caratteristica += toParse.charAt(i);
				}
				break;
			}
			case 3: {
				if (toParse.charAt(i) == '(') {
					currentTree.secondo = parse(toParse.substring(i), depth + 1);
					i += currentTree.secondo.count - 1;
					currentTree.count += currentTree.secondo.count - 1;
					currentTree.stato++;
				}
				break;
			}
			case 4: {
				if (toParse.charAt(i) == ')') {
					return currentTree;
				}
				break;
			}
			}
			i++;
			currentTree.count++;
		}
		return currentTree;
	}
}