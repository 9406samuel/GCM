package Model;
// import de librerias de runtime de ANTLR
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import ANTLR4_code.*;

public class Test {
	public static void main(String[] args) throws Exception {
		try {
			JavaLexer lexer;
			if (args.length > 0)
				lexer = new JavaLexer(new ANTLRFileStream(args[0]));
			else
				lexer = new JavaLexer(new ANTLRInputStream(System.in));

			lexer.removeErrorListeners();
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			JavaParser parser = new JavaParser(tokens);
			parser.removeErrorListeners();

			ParseTree tree = parser.compilationUnit();

			ParseTreeWalker walker = new ParseTreeWalker();
			System.out.println(tree.toStringTree(parser));
		} catch (Exception e) {
			System.err.println("Error (Test): " + e);
		}
	}
}