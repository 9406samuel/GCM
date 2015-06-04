package Controller;

import java.util.ArrayList;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import ANTLR4_code.*;
import Model.HalsteadSizeMatric;
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
			
			
			System.out.println(tree.toStringTree(parser));
		} catch (Exception e) {
			System.err.println("Error (Test): " + e);
		}
	}
}