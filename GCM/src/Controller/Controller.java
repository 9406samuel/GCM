package Controller;

import java.awt.EventQueue;
import java.util.ArrayList;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import ANTLR4_code.*;
import Model.Model;
import View.Application;

public class Controller {

	private static Application window;
	private static Model model;
	
	public static Application getWindow() {
		return window;
	}

	public static void setWindow(Application window) {
		Controller.window = window;
	}

	public static Model getModel() {
		return model;
	}

	public static void setModel(Model model) {
		Controller.model = model;
	}

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
			
			model = new Model( tokenList );
			walker.walk( getModel().getSizeM(), tree);
			
			model.computeMetrics();
			
			System.out.println(model.getSizeM().toString());
			System.out.println(model.getControlM().toString());
			System.out.println(model.getHalsteadSizeM().toString());
			System.out.println(model.getMaintainabilityM().toString());
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						window = new Application();
						window.getFrame().setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		
			System.out.println(tree.toStringTree(parser));
		} catch (Exception e) {
			System.err.println("Error (Test): " + e);
		}
	}
}