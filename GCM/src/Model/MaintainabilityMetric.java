package Model;

import java.text.DecimalFormat;

public class MaintainabilityMetric extends Metric {

	private double maintainabilityIndex;
	private int numLinesOfCode;
	private int numCyclomaticComplexity;
	private double numhalsteadVolume;
	private DecimalFormat decimalFormat = new DecimalFormat("###.##");


	public MaintainabilityMetric(int l, int c, double h) {
		maintainabilityIndex = 0;
		numLinesOfCode = l;
		numCyclomaticComplexity = c;
		numhalsteadVolume = h;
	}

	public double getMaintainabilityIndex() {
		return maintainabilityIndex;
	}

	public void setMaintainabilityIndex(double maintainabilityIndex) {
		this.maintainabilityIndex = maintainabilityIndex;
	}

	public double computeMaintainabilityIndex() {

		if (numhalsteadVolume <= 0 && numLinesOfCode <= 0)
			setMaintainabilityIndex(171 - 0.23 * numCyclomaticComplexity);
		else if (numhalsteadVolume <= 0)
			setMaintainabilityIndex(171 - 0.23 * numCyclomaticComplexity - 16.2 * Math.log(numLinesOfCode));
		else if (numLinesOfCode <= 0)
			setMaintainabilityIndex(171 - 5.2 * Math.log(numhalsteadVolume) - 0.23 * numCyclomaticComplexity);
		else
			setMaintainabilityIndex(171 - 5.2 * Math.log(numhalsteadVolume) - 0.23 * numCyclomaticComplexity - 16.2 * Math.log(numLinesOfCode));
		
		return getMaintainabilityIndex();
	}

	@Override
	public String toString() {
		return "MAINTAINABILITY METRICS:\n"
				+ "Maintainability index: " +  decimalFormat.format(maintainabilityIndex)
				+ "\n";
	}
}
