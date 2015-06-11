package ErrorHandling;

import javax.swing.JOptionPane;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class SyntacticError extends Errorhandling {
	
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer,
			Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
			if(!getErrorShowed()){
			JOptionPane.showMessageDialog(null, "The input has a syntactic error, please check your code.");
			setErrorShowed(true);
			}
			System.err.println("Syntactic Error. Line " + line + ":" + charPositionInLine + ", " + msg);
	}
}
