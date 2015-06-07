package Model;

import java.util.ArrayList;
import java.util.List;

public class Model {

	private SizeMetric sizeM;
	private HalsteadSizeMatric halsteadSizeM ;
	private ControlMetric controlM;
	private MaintainabilityMetric maintainabilityM;
	
	public Model( ArrayList<String> tokenList ){
		sizeM = new SizeMetric(); 
		halsteadSizeM = new HalsteadSizeMatric( tokenList );
		controlM = new ControlMetric( halsteadSizeM.getOperators());	
		maintainabilityM = new MaintainabilityMetric(sizeM.getNumLinesOfCode(), 
				controlM.getCyclomaticComplexity(), halsteadSizeM.getVolumen());
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

	public void computeMetrics(){
		
		halsteadSizeM.computeOperandsAndOperators();
		halsteadSizeM.computeHalsteadMetrics();		
		controlM.computeCyclomaticComplexity();
		maintainabilityM.computeMaintainabilityIndex();
	}
	
						
}
