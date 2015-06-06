package Controller;

import java.util.ArrayList;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import ANTLR4_code.*;
import Model.ControlMetric;
import Model.HalsteadSizeMatric;
import Model.MaintainabilityMetric;
import Model.SizeMetric;

public class Controller {
	private static Controller instance;
	
	public static void main(String[] args) throws Exception {
		try {
			ArrayList<String> tokenList = new ArrayList<>();
			JavaLexer lexer;
			if (args.length > 0)
				lexer = new JavaLexer(new ANTLRFileStream(args[0]));
			else
				lexer = new JavaLexer(new ANTLRInputStream(System.in));
			
			//saca la lista de cada uno de los tokens
			for (Token token = lexer.nextToken(); token.getType() != Token.EOF; token = lexer.nextToken()){
				tokenList.add(token.getText());
			}
			lexer.reset();
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			JavaParser parser = new JavaParser(tokens);
			
			ParseTree tree = parser.compilationUnit();
			
			ParseTreeWalker walker = new ParseTreeWalker();
			SizeMetric sizeM = new SizeMetric();
			HalsteadSizeMatric halsteadSizeM = new HalsteadSizeMatric( tokenList );
			walker.walk(sizeM, tree);
			
			halsteadSizeM.computeOperandsAndOperators();
			halsteadSizeM.computeHalsteadMetrics();
			
			ControlMetric controlM = new ControlMetric( halsteadSizeM.getOperators());	
			controlM.computeCyclomaticComplexity();
			
			MaintainabilityMetric maintainabilityM = new MaintainabilityMetric(sizeM.getNumLinesOfCode(), 
														controlM.getCyclomaticComplexity(), halsteadSizeM.getVolumen());
				
			System.out.println(sizeM.toString());
			System.out.println(halsteadSizeM.toString());
			System.out.println(controlM.getCyclomaticComplexity());
			System.out.println(maintainabilityM.toString());
			
			System.out.println(tree.toStringTree(parser));
		} catch (Exception e) {
			System.err.println("Error (Test): " + e);
		}
	}
}