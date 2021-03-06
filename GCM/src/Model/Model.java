package Model;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import ANTLR4_code.JavaBaseListener;

public class Model extends JavaBaseListener{

	private SizeMetric sizeM;
	private HalsteadSizeMatric halsteadSizeM ;
	private ControlMetric controlM;
	private MaintainabilityMetric maintainabilityM;
	private ANTLRAnalysis analyzer;
	private DotFile dotF;


	public Model(){
		analyzer = new ANTLRAnalysis();
		
	}
	
	public void startAnalysis( String nameInput ){
		System.out.println("archivo a analizar: " + nameInput);
		sizeM = new SizeMetric(); 
		start( analyzer.startAnalysis( nameInput, sizeM ));
		dotF = new DotFile(sizeM, halsteadSizeM,
				controlM, maintainabilityM);
		//printResults();
	}

	

	public void start( ArrayList<String> tokenList ){
		halsteadSizeM = new HalsteadSizeMatric( tokenList, sizeM.getOperandsList());
		halsteadSizeM.computeOperandsAndOperators();
		halsteadSizeM.computeHalsteadMetrics();		
		controlM = new ControlMetric( halsteadSizeM.getOperators());
		controlM.computeCyclomaticComplexity();
		maintainabilityM = new MaintainabilityMetric(sizeM.getNumLinesOfCode(), controlM.getCyclomaticComplexity(), halsteadSizeM.getVolumen());
		maintainabilityM.computeMaintainabilityIndex();
		
	
	}
			
	public ANTLRAnalysis getAnalyzer() {
		return analyzer;
	}
	
	public void setAnalyzer(ANTLRAnalysis analyzer) {
		this.analyzer = analyzer;
	}
	
	public SizeMetric getSizeM() {
		return sizeM;
	}

	public void setSizeM(SizeMetric sizeM) {
		this.sizeM = sizeM;
	}

	public HalsteadSizeMatric getHalsteadSizeM() {
		return halsteadSizeM;
	}

	public void setHalsteadSizeM(HalsteadSizeMatric halsteadSizeM) {
		this.halsteadSizeM = halsteadSizeM;
	}

	public ControlMetric getControlM() {
		return controlM;
	}

	public void setControlM(ControlMetric controlM) {
		this.controlM = controlM;
	}

	public MaintainabilityMetric getMaintainabilityM() {
		return maintainabilityM;
	}

	public void setMaintainabilityM(MaintainabilityMetric maintainabilityM) {
		this.maintainabilityM = maintainabilityM;
	}
	
	public DotFile getDotF() {
		return this.dotF;
	}
	
	public void setDotF(DotFile dotF) {
		this.dotF = dotF;
	}
	
	public String printResults(){

		return getSizeM().toString()
			 + getHalsteadSizeM().toString()
			 + getControlM().toString()
		 	 + getMaintainabilityM().toString();
		
	}


	
}
