package Model;

import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import ANTLR4_code.JavaLexer;
import ANTLR4_code.JavaParser;
import Controller.Controller;
import ErrorHandling.LexicalError;
import ErrorHandling.SyntacticError;

public class ANTLRAnalysis {

	private JavaLexer lexer;
	private CommonTokenStream tokens;
	private JavaParser parser;
	private ArrayList<String> tokenList;

	public ANTLRAnalysis() {

	}

	public ArrayList<String> startAnalysis(String nameFileInput, SizeMetric sizeM) {
		try {
			
			
			lexer = new JavaLexer(new ANTLRFileStream(nameFileInput));
			tokens = new CommonTokenStream(lexer);
			tokenList = new ArrayList<>();

			for (Token token = lexer.nextToken(); token.getType() != Token.EOF; token = lexer
					.nextToken()) {
				tokenList.add(token.getText());
			}
			lexer.reset();
			
			parser = new JavaParser(tokens);
			lexer.removeErrorListeners();
			parser.removeErrorListeners();
			lexer.addErrorListener(new LexicalError());
			parser.addErrorListener(new SyntacticError());
			
			ParseTree tree = parser.compilationUnit();
			ParseTreeWalker walker = new ParseTreeWalker();
			walker.walk(sizeM, tree);
			
			//System.out.println(tree.toStringTree(parser));
	
		} catch (Exception e) {

		}
		return tokenList;

	}

}
