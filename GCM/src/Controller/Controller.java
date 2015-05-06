package Controller;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import ANTLR4_code.*;
import Model.SizeMetric;

public class Controller {
	private static Controller instance;
	
	public synchronized static Controller getInstance() {

        if (instance == null) {

            instance = new Controller();
        }

        return instance;
    }
	
	public static void main(String[] args) throws Exception {
		try {
			JavaLexer lexer;
			if (args.length > 0)
				lexer = new JavaLexer(new ANTLRFileStream(args[0]));
			else
				lexer = new JavaLexer(new ANTLRInputStream(System.in));

			CommonTokenStream tokens = new CommonTokenStream(lexer);
			JavaParser parser = new JavaParser(tokens);

			ParseTree tree = parser.compilationUnit();
			ParseTreeWalker walker = new ParseTreeWalker();
			walker.walk( new SizeMetric(), tree);
			
			System.out.println(tree.toStringTree(parser));
		} catch (Exception e) {
			System.err.println("Error (Test): " + e);
		}
	}
}