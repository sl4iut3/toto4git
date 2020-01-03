package toto;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Eval {

	public final String CONFIG_FILE = "jest.cfg";

	private HashMap<String, Integer> variables;

	private BufferedReader reader;

	public Eval(int scorePique, int scoreTrefle, int scoreCarreau, int scoreCoeur, boolean joker)
			throws FileNotFoundException {
		this.variables = new HashMap<String, Integer>();
		this.variables.put("SPique", scorePique);
		this.variables.put("STrefle", scoreTrefle);
		this.variables.put("SCarreau", scoreCarreau);
		this.variables.put("SCoeur", scoreCoeur);
		this.variables.put("Score", 0);
		this.variables.put("Paires", 1);
		this.variables.put("AsCinq", 1);
		if (joker)
			this.variables.put("Joker", 1);
		else
			this.variables.put("Joker", 0);
		this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.CONFIG_FILE)));
	}

	public void eval() throws IOException {
		String line = this.reader.readLine();
		while (line != null) {
			line = line.replaceAll(" ", "");
			if (line.contains("?"))
				this.evalTest(line);
			else
				this.evalAffectation(line);
			line = this.reader.readLine();
		}
	}

	private void evalTest(String line) {
		String[] tokens = line.split("\\?");
		String cond = tokens[0];
		boolean res;
		if (cond.contains("|")) {
			String[] conds = cond.split("|");
			res = false;
			for (int i = 0; i < conds.length; i++)
				res = res || this.evalCond(conds[i]);
		} else if (cond.contains("&")) {
			String[] conds = cond.split("&");
			res = true;
			for (int i = 0; i < conds.length; i++)
				res = res && this.evalCond(conds[i]);
		} else
			res = this.evalCond(cond);
		String[] affect = tokens[1].split(";");
		if (res)
			this.evalAffectation(affect[0]);
		else if (affect.length > 1)
			this.evalAffectation(affect[1]);
	}

	private boolean evalCond(String cond) {
		cond = cond.replaceAll("=", " = ").replaceAll("<", " < ").replaceAll(">", " > ");
		String[] tokens = cond.split(" ");
		switch (tokens[1]) {
		case "=":
			return (this.variables.get(tokens[0]) == Integer.parseInt(tokens[2]));
		case "<":
			return (this.variables.get(tokens[0]) < Integer.parseInt(tokens[2]));
		case ">":
			return (this.variables.get(tokens[0]) > Integer.parseInt(tokens[2]));
		}
		return false;
	}

	private void evalAffectation(String line) {
		if (line.compareTo("") == 0)
			return;
		String[] tokens = line.split("=");
		this.variables.put(tokens[0], this.evalExpr(tokens[1]));
	}

	private int evalExpr(String expr) {
		expr = expr.replaceAll("\\+", " + ").replaceAll("-", " - ");
		String[] tokens = expr.split(" ");
		int res = 0;
		for (int i = 0; i < tokens.length; i++) {
			switch (tokens[i]) {
			case "+":
				res += this.variables.get(tokens[++i]);
				break;
			case "-":
				res -= this.variables.get(tokens[++i]);
				break;
			default:
				if (this.variables.containsKey(tokens[i]))
					res = this.variables.get(tokens[i]);
				else
					res = Integer.parseInt(tokens[i]);
				break;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		try {
			Eval e = new Eval(4, 4, 10, 5, true);
			e.eval();
			System.out.println(e.variables);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
