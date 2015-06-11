package ErrorHandling;

import javax.swing.JOptionPane;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class LexicalError extends Errorhandling{

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer,
			Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		if(!getErrorShowed()){
			JOptionPane.showMessageDialog(null, "The input has a lexical error, Please check your code.");
			setErrorShowed(true);
			}
			System.err.println("lexical Error. Line " + line + ":" + charPositionInLine + ", " + msg);
	}
}
