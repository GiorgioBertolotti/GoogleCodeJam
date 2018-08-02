import java.util.*;
public class Parser {
	public static void main(String[] args) {
		Tree tree = parse("(0.2furry(0.81fast(0.3)(0.2))(0.1fishy(0.3freshwater(0.01)(0.01))(0.1)))");
		System.out.println(calcWeight(tree, args));
	}

	public static double calcWeight(Tree tree, String[] categories) {
		if(tree.caratteristica == null)
			return tree.molt;
		return tree.molt * calcWeight((Arrays.asList(categories).contains(tree.caratteristica) ? tree.primo : tree.secondo), categories);
	}

	public static Tree parse(String toParse) {
		toParse = toParse.replaceAll("\\n", "");
		toParse = toParse.replaceAll("\\r", "");
		toParse = toParse.replaceAll(" ", "");
		Tree currentTree = new Tree(0);
		int i = 0;
		while(i < toParse.length()) {
			switch(currentTree.stato) {
				case '0': {
					if(toParse.charAt(i) == '(') {
						currentTree.stato++;
					}
				}
				case '1': {
					if((toParse.charAt(i) > 'a' && toParse.charAt(i) < 'z') || (toParse.charAt(i) > 'A' && toParse.charAt(i) < 'Z')) {
						currentTree.caratteristica += toParse.charAt(i);
						currentTree.molt = Double.parseDouble(currentTree.moltstr);
						currentTree.stato++;
					} else 
					if(toParse.charAt(i) == ')')
						return currentTree; //è foglia
					else
						currentTree.moltstr += toParse.charAt(i);
				}
				case '2': {
					if(toParse.charAt(i) == '(') {
						currentTree.primo = parse(toParse.substring(i));
						currentTree.stato++;
					} else {
						currentTree.caratteristica += toParse.charAt(i);
					}
				}
				case '3': {
					if(toParse.charAt(i) == '(') {
						currentTree.secondo = parse(toParse.substring(i));
						currentTree.stato++;
					} else {
						System.out.println("problems");
					}
				}
				case '4': {
					if(toParse.charAt(i) == ')')
						return currentTree;
				}
			}
		}
		return currentTree;
	}
}