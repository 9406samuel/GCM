package Model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class HalsteadSizeMatric extends SizeMetric {

	private ArrayList<String> tokenList;
	private HashMap<String, Integer> operands;
	private HashMap<String, Integer> operators;
	private int distinctOperators;
	private int distinctOperands;
	private int totalOperators;
	private int totalOperands;
	private int vocabulary;
	private int observedLength;
	private double calculatedLength;
	private double volumen;
	private double difficulty;
	private double effort;
	private DecimalFormat decimalFormat = new DecimalFormat("###.##");

	
	public HalsteadSizeMatric( ArrayList<String> l, HashMap<String, Integer> m ){
		this.tokenList = l;
		operands = m;
		operators = new HashMap<>();
		distinctOperators = 0;
		distinctOperands = 0;
		totalOperators = 0;
		totalOperands = 0;
		vocabulary = 0;
		observedLength = 0;
		calculatedLength = 0;
		volumen = 0;
		difficulty = 0;
		effort = 0;
	}
	
	public ArrayList<String> getTokenList() {
		return tokenList;
	}

	public void setTokenList(ArrayList<String> tokenList) {
		this.tokenList = tokenList;
	}

	public HashMap<String, Integer> getOperands() {
		return operands;
	}

	public void setOperands(HashMap<String, Integer> operands) {
		this.operands = operands;
	}

	public HashMap<String, Integer> getOperators() {
		return operators;
	}

	public void setOperators(HashMap<String, Integer> operators) {
		this.operators = operators;
	}

	public int getDistinctOperators() {
		return distinctOperators;
	}

	public void setDistinctOperators(int distinctOperators) {
		this.distinctOperators = distinctOperators;
	}

	public int getDistinctOperands() {
		return distinctOperands;
	}

	public void setDistinctOperands(int distinctOperands) {
		this.distinctOperands = distinctOperands;
	}

	public int getTotalOperators() {
		return totalOperators;
	}

	public void setTotalOperators(int totalOperators) {
		this.totalOperators = totalOperators;
	}

	public int getTotalOperands() {
		return totalOperands;
	}

	public void setTotalOperands(int totalOperands) {
		this.totalOperands = totalOperands;
	}

	public int getVocabulary() {
		return vocabulary;
	}

	public void setVocabulary(int vocabulary) {
		this.vocabulary = vocabulary;
	}

	public int getObservedLength() {
		return observedLength;
	}

	public void setObservedLength(int observedLength) {
		this.observedLength = observedLength;
	}

	public double getCalculatedLength() {
		return calculatedLength;
	}

	public void setCalculatedLength(double calculatedLength) {
		this.calculatedLength = calculatedLength;
	}

	public double getVolumen() {
		return volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public double getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(double difficulty) {
		this.difficulty = difficulty;
	}

	public double getEffort() {
		return effort;
	}

	public void setEffort(double effort) {
		this.effort = effort;
	}
	
	

	public DecimalFormat getDecimalFormat() {
		return this.decimalFormat;
	}

	public void computeOperandsAndOperators(){
	
		try{
		for( String x: tokenList ){
			if( operands.containsKey(x) ){
				operands.put(x, operands.get(x) + 1);
			}
			else
				if(operators.containsKey(x)){
					operators.put(x, operators.get(x) + 1);
				}
				else
					operators.put(x, 1);
		}
	}catch( Exception e ){
		
	}
}
	
	public void computeHalsteadMetrics(){
		computeDistinctOperators();
		computeDistinctOperands();
		computeTotalOperators();
		computeTotalOperands();
		computeVocabulary();
		computeObservedLength();
		computeCalculatedLength();
		computeVolume();
		computeDifficulty();
		computeEffort();
	
		}
		
	private void computeDistinctOperators(){
		setDistinctOperators( operators.size() );
	}
	

	private void computeDistinctOperands(){
		setDistinctOperands( operands.size() );
	}
	
	private void computeTotalOperators(){
		int total = 0;
		for( Integer x: operators.values())
			total += x;
		setTotalOperators( total );
	}
	
	private void computeTotalOperands(){
		int total = 0;
		for( Integer x: operands.values())
			total += x;
		setTotalOperands( total );
	}

	public void computeVocabulary(){
		setVocabulary( getDistinctOperands() + getDistinctOperators() );
	}

	public void computeObservedLength(){
		setObservedLength( getTotalOperands() + getTotalOperators() );
	}

	public void computeCalculatedLength(){
		if(getDistinctOperands() > 0 )
			setCalculatedLength( getDistinctOperands()*(Math.log(getDistinctOperands())/Math.log(2))+ 
							 getDistinctOperators()*(Math.log(getDistinctOperators())/Math.log(2)) );
		else
			setCalculatedLength( 0 );
	}

	private void computeVolume(){
		setVolumen( getObservedLength()*(Math.log(getVocabulary())/Math.log(2)));
	}
	
	private void computeDifficulty(){
		if(getDistinctOperands() > 0)
			setDifficulty( (getDistinctOperators()/2)*(getTotalOperands()/getDistinctOperands()));
		else
			setDifficulty( 0 );
	}
	
	private void computeEffort(){
		setEffort( getDifficulty()*getVolumen());
	}

	@Override
	public String toString() {
		return "HALSTEAD SIZE METRICS:\n"
				+ "Distinct operands: " + distinctOperands + "\n"
				+ "Distinct operators: " + distinctOperators + "\n"
				+ "Total operands: " + totalOperands + "\n"
				+ "Total operators: " + totalOperators + "\n"
				+ "Vocabulary: " + vocabulary + "\n"
				+ "Observerd length: " + observedLength + "\n"
				+ "Calculated length: " + decimalFormat.format(calculatedLength) + "\n"
				+ "Volumen: " + decimalFormat.format(volumen) + "\n"
				+ "Difficulty: " + decimalFormat.format(difficulty) + "\n"
				+ "Effort: " + decimalFormat.format(effort) + "\n"
				+ "\n";
	}
	
	
}
