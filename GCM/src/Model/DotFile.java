package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class DotFile{

	private SizeMetric sizeM;
	private HalsteadSizeMatric halsteadSizeM;
	private ControlMetric controlM;
	private MaintainabilityMetric maintainabilityM;
	
	public DotFile(SizeMetric sizeM, HalsteadSizeMatric halsteadSizeM,
			ControlMetric controlM, MaintainabilityMetric maintainabilityM) {
		this.sizeM = sizeM;
		
		this.halsteadSizeM = halsteadSizeM;
		this.controlM = controlM;
		this.maintainabilityM = maintainabilityM;
	}

	public String calculateColor(Integer metric) { 
		int rgbNumber;
		String hexadecimalColor = "black";
		
		if (metric > 0 && metric <=10){
			rgbNumber = calulateRange(metric, 0, 10);
			hexadecimalColor = "#00"+String.valueOf(Integer.toHexString(rgbNumber))
					+ String.valueOf(Integer.toHexString(rgbNumber));  
		}
		if (metric > 10 && metric <= 100){
			rgbNumber = calulateRange(metric, 10, 100);
			hexadecimalColor = "#"+Integer.toHexString(rgbNumber) + "00" + Integer.toHexString(rgbNumber);
				}
		if (metric > 100 && metric <= 1000){
			rgbNumber = calulateRange(metric, 100, 1000);
			hexadecimalColor = "#"+Integer.toHexString(rgbNumber) + Integer.toHexString(rgbNumber)  + "00";
		}
		if (metric > 1000 && metric <= 10000){
			rgbNumber = calulateRange(metric, 1000, 10000);
			hexadecimalColor = "#0000"+Integer.toHexString(rgbNumber);
		}
		if (metric > 10000 && metric < 1000000){
			rgbNumber = calulateRange(metric, 10000, 100000);
			hexadecimalColor = "#00" + Integer.toHexString(rgbNumber) + "00";
		}
		if (metric >= 1000000){
			hexadecimalColor = "#FF0000";
		}
		return hexadecimalColor;
		
	}
	
	public int calulateRange(Integer metric, Integer i, Integer j) {
		int inteval, numColor, interval ;
		
		interval = j-i;
		metric = metric-i;
		numColor = 255-(int)Math.ceil((metric*155)/interval);
		
		return numColor;
	}

	public void generateFile(String outputFileFormat) {
		try {
			createDotFile();
			toFormat(outputFileFormat);
			openFile(outputFileFormat);
	        } catch (IOException exception) {
			System.out.println("ERROR: The file can not be created");
		}
	}
	
	public void createDotFile() throws IOException {
		String ruta = "graphGCM.dot";
        File archivo = new File(ruta);
        BufferedWriter bw;
       
        bw = new BufferedWriter(new FileWriter(archivo));
        //comienza la escritura del archivo
        bw.write("digraph G {\n");
        
        //se escriben los atributos generales de los nodos y las aristas
    	bw.write("\tedge[ arrowhead= invodot]\n");
    	bw.write("\t node [fontcolor=black, fontname=algerian, fixedsize=true, regular=true, height=2, style=filled]\n\n");
    	
		bw.write("\tsubgraph cluster0{\n");//Inicio del primer subgrafo (metricas generales)
		bw.write("\tlabel = \"General_Metrics\";\n"); //Nombre del subgrafo
		bw.write("\tcolor = \"blue\";\n"); 

		//Atributos para cada nodo de primer subgrafo
		if(sizeM.getNumPrimitiveVars() > 0)
			bw.write("\t Primitive_Variables [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( sizeM.getNumPrimitiveVars() ) 
    			+ "\", label = \"Primitive_Variables\n>> "+ sizeM.getNumPrimitiveVars() +" <<\"];\n");
		if(sizeM.getNumLocalVars() > 0)
			bw.write("\t Local_Variables [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( sizeM.getNumLocalVars() ) 
    			+ "\", label = \"Local_Variables\n>> "+ sizeM.getNumLocalVars() +" <<\"];\n");
		if(sizeM.getNumFields() > 0)
			bw.write("\t Fields [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( sizeM.getNumFields()) 
    			+ "\", label = \"Fields\n>> "+ sizeM.getNumFields() +" <<\" ];\n");
		if(sizeM.getNumMethods() > 0)
			bw.write("\t Methods [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( sizeM.getNumMethods() ) 
    			+ "\", label = \"Methods\n>> "+ sizeM.getNumMethods() +" <<\"];\n");
		if(sizeM.getNumPackage() > 0)
			bw.write("\t Package [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( sizeM.getNumPackage() ) 
    			+ "\", label = \"Package\n>> "+ sizeM.getNumPackage() +" <<\"];\n");
		if(sizeM.getNumImport()  > 0)
			bw.write("\t Import [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( sizeM.getNumImport() ) 
    			+ "\", label = \"Import\n>> "+ sizeM.getNumImport() +" <<\"];\n");
		if(sizeM.getNumInstanceClass()  > 0)
			bw.write("\t Class_Instance [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( sizeM.getNumInstanceClass() ) 
    			+ "\", label = \"Class_Instance\n>> "+ sizeM.getNumInstanceClass() +" <<\"];\n");
		if(sizeM.getNumFather() > 0)
			bw.write("\t Super_Class [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( sizeM.getNumFather() ) 
    			+ "\", label = \"Super_Class \n>> "+ sizeM.getNumFather() +" <<\"];\n");
		if(sizeM.getNumImplementations()  > 0)
			bw.write("\t Implementations [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( sizeM.getNumImplementations() ) 
    			+ "\", label = \"Implementations \n>> "+ sizeM.getNumImplementations() + " <<\"];\n");
    	try {
    		if(halsteadSizeM.getOperators().get("if")  > 0)
    			bw.write("\t If [shape = circle,  peripheries=2, "
        			+ "color=\"" + calculateColor( halsteadSizeM.getOperators().get("if") ) 
        			+ "\", label = \"If \n>> "+ halsteadSizeM.getOperators().get("if")  +" <<\"];\n");
		} catch (Exception e) {}
    	try {
    		if(halsteadSizeM.getOperators().get("while")  > 0)
    			bw.write("\t While [shape = circle,  peripheries=2, "
        			+ "color=\"" + calculateColor( halsteadSizeM.getOperators().get("while") ) 
        			+ "\", label = \"While\n>> "+ halsteadSizeM.getOperators().get("while") +" <<\"];\n");
		} catch (Exception e) {	}
    	try {
    		if(halsteadSizeM.getOperators().get("for")  > 0)
    			bw.write("\t For [shape = circle,  peripheries=2, "
        			+ "color=\"" + calculateColor( halsteadSizeM.getOperators().get("for") ) 
        			+ "\", label = \"For \n>> "+ halsteadSizeM.getOperators().get("for")  +" <<\"];\n");
		} catch (Exception e) {}
			
    	try {
    		if( halsteadSizeM.getOperators().get("case") > 0)
    			bw.write("\t Case [shape = circle,  peripheries=2, "
        			+ "color=\"" + calculateColor( halsteadSizeM.getOperators().get("case") ) 
        			+ "\", label = \"Case \n>> "+ halsteadSizeM.getOperators().get("case") +" <<\"];\n");
		} catch (Exception e) {	}
    	
    	if(sizeM.getNumLinesOfCode() > 0)
    		bw.write("\t Lines [shape = octagon,  peripheries=3, " 
    			+ "color=\"" + calculateColor( sizeM.getNumLinesOfCode() ) 
    			+ "\", label = \"Lines \n>> "+ sizeM.getNumLinesOfCode() +" <<\"];\n\n");
    	//Relaciones existentes para los nodos del primer subgrafo
	    if(sizeM.getNumMethods() > 0 && sizeM.getNumLinesOfCode() > 0)
	    	bw.write("\t Methods -> Lines;\n");
	    if(sizeM.getNumPackage() > 0 && sizeM.getNumLinesOfCode() > 0)
	    	bw.write("\t Package -> Lines;\n");
	    if(sizeM.getNumImport() > 0 && sizeM.getNumLinesOfCode() > 0)
	    	bw.write("\t Import -> Lines;\n");
	    if(sizeM.getNumLocalVars() > 0 && sizeM.getNumLinesOfCode() > 0)
	    	bw.write("\t Local_Variables -> Lines;\n");
	    if(sizeM.getNumFields() > 0 && sizeM.getNumLinesOfCode() > 0)
	    	bw.write("\t Fields -> Lines;\n");
	    if(sizeM.getNumInstanceClass() > 0 && sizeM.getNumLinesOfCode() > 0)
	    	bw.write("\t Class_Instance -> Lines;\n");
	    if(sizeM.getNumPrimitiveVars() > 0 && sizeM.getNumLinesOfCode() > 0)
	    	bw.write("\t Primitive_Variables -> Lines;\n");
	    if(sizeM.getNumFather() > 0 && sizeM.getNumLinesOfCode() > 0)
	    	bw.write("\t Super_Class -> Lines;\n");
	    if(sizeM.getNumImplementations()> 0 && sizeM.getNumLinesOfCode() > 0)
	    	bw.write("\t Implementations -> Lines;\n");
	    
	    try {
	    	if(halsteadSizeM.getOperators().get("if") > 0 && sizeM.getNumLinesOfCode() > 0)
		    	bw.write("\t If -> Lines\n");
		} catch (Exception e) { }
	    
	    try {
	    	if(halsteadSizeM.getOperators().get("while") > 0 && sizeM.getNumLinesOfCode() > 0)
		    	bw.write("\t While -> Lines;\n");
		} catch (Exception e) { }
	    
	    try {
	    	if(halsteadSizeM.getOperators().get("for") > 0 && sizeM.getNumLinesOfCode() > 0)
		    	bw.write("\t For -> Lines;\n");
		} catch (Exception e) { }
	    
	    try {
	    	if(halsteadSizeM.getOperators().get("case") > 0 && sizeM.getNumLinesOfCode() > 0)
		    	bw.write("\t Case -> Lines;\n");
		} catch (Exception e) { }
	    
    	bw.write("\t}\n");//Fin del primer subgrafo	
        bw.write("\n");
        
        bw.write("\tsubgraph cluster1{\n");//Inicio del segundo subgrafo (metricas de halstead)
        bw.write("\tlabel = \"Halstead_Metrics\";\n"); //Nombre del subgrafo
		bw.write("\tcolor = \"blue\";\n");
		//Atributos para cada nodo de segundo subgrafo
		
		if(halsteadSizeM.getTotalOperators() > 0)
			bw.write("\t Operators_Total [shape = circle,  peripheries=2, "
	    			+ "color=\"" + calculateColor( halsteadSizeM.getTotalOperators() ) 
	    			+ "\", label = \"Operators_Total \n>> "+ halsteadSizeM.getTotalOperators() +" <<\"];\n");
		if(halsteadSizeM.getTotalOperands() > 0)
			bw.write("\t Operands_Total [shape = circle,  peripheries=2, "
					+ "color=\"" + calculateColor( halsteadSizeM.getTotalOperands() ) 
					+ "\", label = \"Operands_Total \n>> "+ halsteadSizeM.getTotalOperands() +" <<\"];\n");
		if(halsteadSizeM.getDistinctOperators()  > 0)
			bw.write("\t Operators_Distinct [shape = circle,  peripheries=2, "
	    			+ "color=\"" + calculateColor( halsteadSizeM.getDistinctOperators() ) 
	    			+ "\", label = \"Operators_Distinct \n>> "+ halsteadSizeM.getDistinctOperators() +" <<\"];\n");
		if(halsteadSizeM.getDistinctOperands()  > 0)
			bw.write("\t Operands_Distinct [shape = circle,  peripheries=2, "
	    			+ "color=\"" + calculateColor( halsteadSizeM.getDistinctOperands() ) 
	    			+ "\", label = \"Operands_Distinct\n>> "+ halsteadSizeM.getDistinctOperands() +" <<\"];\n");
		if((int)halsteadSizeM.getCalculatedLength()  > 0)
			bw.write("\t Calculated_Length [shape = octagon,  peripheries=3, " 
	    			+ "color=\"" + calculateColor( (int)halsteadSizeM.getCalculatedLength() ) 
	    			+ "\", label = \"Calculated_Length\n>> "+ halsteadSizeM.getCalculatedLength() +" <<\"];\n");
		if(halsteadSizeM.getVocabulary()  > 0)
			bw.write("\t Vocabulary [shape = octagon,  peripheries=3, " 
	    			+ "color=\"" + calculateColor( halsteadSizeM.getVocabulary() ) 
	    			+ "\", label = \"Vocabulary\n>> "+ halsteadSizeM.getVocabulary() +" <<\"];\n");
		if(halsteadSizeM.getObservedLength() > 0)
			bw.write("\t Observed_Length [shape = octagon,  peripheries=3, " 
	    			+ "color=\"" + calculateColor( halsteadSizeM.getObservedLength() ) 
	    			+ "\", label = \"Observed_Length\n>> "+ halsteadSizeM.getObservedLength() +" <<\"];\n");
		if((int)halsteadSizeM.getDifficulty()  > 0)
			bw.write("\t Difficulty [shape = octagon,  peripheries=3, " 
	    			+ "color=\"" + calculateColor( (int)halsteadSizeM.getDifficulty()) 
	    			+ "\", label = \"Difficulty \n>> "+ halsteadSizeM.getDifficulty() +" <<\"];\n");
		if((int)halsteadSizeM.getVolumen() > 0)
			bw.write("\t Volume [shape = octagon,  peripheries=3, " 
	    			+ "color=\"" + calculateColor( (int)halsteadSizeM.getVolumen() ) 
	    			+ "\", label = \"Volume \n>> "+ halsteadSizeM.getVolumen() +" <<\"];\n");
		if((int)halsteadSizeM.getEffort() > 0)
			bw.write("\t Effort [shape = octagon,  peripheries=3, " 
	    			+ "color=\"" + calculateColor( (int)halsteadSizeM.getEffort() ) 
	    			+ "\", label = \"Effort \n>> "+ halsteadSizeM.getEffort() +" <<\"];\n\n");
		
		
    	//Relaciones existentes para los nodos del segundo subgrafo
		if(halsteadSizeM.getDistinctOperators() > 0 && halsteadSizeM.getVocabulary() > 0)
			bw.write("\t Operators_Distinct -> Vocabulary;\n");
		if(halsteadSizeM.getDistinctOperands() > 0 && halsteadSizeM.getVocabulary() > 0)
	    	bw.write("\t Operands_Distinct -> Vocabulary;\n");
		if(halsteadSizeM.getTotalOperators() > 0 && halsteadSizeM.getObservedLength() > 0)
	    	bw.write("\t Operators_Total -> Observed_Length;\n");
		if(halsteadSizeM.getTotalOperands()> 0 && halsteadSizeM.getObservedLength() > 0)
	    	bw.write("\t Operands_Total -> Observed_Length;\n");
		if(halsteadSizeM.getDistinctOperators() > 0 && halsteadSizeM.getCalculatedLength() > 0)
	    	bw.write("\t Operators_Distinct -> Calculated_Length;\n");
		if(halsteadSizeM.getDistinctOperands() > 0 && halsteadSizeM.getCalculatedLength() > 0)
	    	bw.write("\t Operands_Distinct -> Calculated_Length;\n");
		if(halsteadSizeM.getDistinctOperators() > 0 && halsteadSizeM.getDifficulty() > 0)
	    	bw.write("\t Operators_Distinct-> Difficulty;\n");
		if(halsteadSizeM.getTotalOperands() > 0 && halsteadSizeM.getDifficulty() > 0)
	    	bw.write("\t Operands_Total -> Difficulty;\n");
		if(halsteadSizeM.getDistinctOperands() > 0 && halsteadSizeM.getDifficulty() > 0)
	    	bw.write("\t Operands_Distinct -> Difficulty;\n");
		if(halsteadSizeM.getVocabulary()> 0 && halsteadSizeM.getVolumen() > 0)
	    	bw.write("\t Vocabulary -> Volume;\n");
		if(halsteadSizeM.getObservedLength() > 0 && halsteadSizeM.getVolumen() > 0)
	    	bw.write("\t Observed_Length -> Volume;\n");
		if(halsteadSizeM.getVolumen()> 0 && halsteadSizeM.getEffort() >0)
	    	bw.write("\t Volume -> Effort;\n");
		if(halsteadSizeM.getDifficulty() > 0 && halsteadSizeM.getEffort() > 0)
	    	bw.write("\t Difficulty -> Effort;\n");
    	
    	bw.write("\t}\n");//Fin del segundo subgrafo	
        bw.write("\n");
        
        //Inicio del tercer subgrafo (complejidad ciclomatica)
        bw.write("\tsubgraph cluster2{\n");
        bw.write("\tlabel = \"Cyclomatic_Metric\";\n"); //Nombre del subgrafo
		bw.write("\tcolor = \"blue\";\n");

		//Atributos para cada nodo de tercer subgrafo
		if(controlM.getCyclomaticComplexity() > 0)
			bw.write("\t Cyclomatic_Complexity [shape = octagon,  peripheries=3, " 
	    			+ "color=\"" + calculateColor( controlM.getCyclomaticComplexity() ) 
	    			+ "\", label = \"Cyclomatic_Complexity\n>> "+ controlM.getCyclomaticComplexity() +" <<\"];\n\n");
		//Relaciones existentes para los nodos del tercer subgrafo
    	try {
    		if(halsteadSizeM.getOperators().get("if") > 0 && controlM.getCyclomaticComplexity() > 0)
    			bw.write("\t If -> Cyclomatic_Complexity;\n");	
		} catch (Exception e) {	}
    	
    	try {
    		if(halsteadSizeM.getOperators().get("while") > 0 && controlM.getCyclomaticComplexity() > 0)
    			bw.write("\t While -> Cyclomatic_Complexity;\n");		
		} catch (Exception e) {	}
    	
		try {
			if(halsteadSizeM.getOperators().get("for") > 0 && controlM.getCyclomaticComplexity() > 0)
				bw.write("\t For -> Cyclomatic_Complexity;\n");
		} catch (Exception e) {	}
		
		try {
			if(halsteadSizeM.getOperators().get("case") > 0 && controlM.getCyclomaticComplexity() > 0)
				bw.write("\t Case -> Cyclomatic_Complexity;\n");
		} catch (Exception e) {	}
		    	
    	bw.write("\t}\n");//Fin del tercer subgrafo	
    	
    	//Inicio del Cuarto subgrafo (indice de mantenibilidad)
        bw.write("\tsubgraph cluster3{\n");
        bw.write("\tlabel = \"Maintainability_Index\";\n"); //Nombre del subgrafo
		bw.write("\tcolor = \"blue\";\n");
		
		//Atributos para cada nodo de cuarto subgrafo
		if( (int) maintainabilityM.getMaintainabilityIndex() > 0)
			bw.write("\t Maintainability [shape = octagon,  peripheries=3, " 
    			+ "color=\"" + calculateColor( (int)maintainabilityM.getMaintainabilityIndex()) 
    			+ "\", label = \"Maintainability \n>> "+ maintainabilityM.getMaintainabilityIndex() +" <<\"];\n");
		
		//Relaciones existentes para los nodos del cuarto subgrafo
		if(sizeM.getNumLinesOfCode() > 0 && maintainabilityM.getMaintainabilityIndex() > 0)
			bw.write("\t Lines -> Maintainability;\n");
		if(controlM.getCyclomaticComplexity() > 0 && maintainabilityM.getMaintainabilityIndex() > 0)
			bw.write("\t Cyclomatic_Complexity -> Maintainability;\n");
		if(halsteadSizeM.getVolumen() > 0 && maintainabilityM.getMaintainabilityIndex() > 0)
			bw.write("\t Volume -> Maintainability;\n");
		
    	bw.write("\t}\n");//Fin del cuarto subgrafo	

        bw.write("\n");
        bw.write("}"); //finaliza la escritura del archivo
        
        bw.close();	//cierre del flujo
	}
	
	private void toFormat(String outputFileFormat)throws IOException{
		Process p = Runtime.getRuntime().exec("cmd /C dot -T" + outputFileFormat + " graphGCM.dot -o graphGCM."+outputFileFormat);
		System.out.println("Waiting for file ...");
	    try {
			p.waitFor();
		    System.out.println("The file was created");
		} catch (InterruptedException exception) {
			System.out.println("ERROR: Wait Problem");
		}
	}
	
	public void openFile(String outputFileFormat) throws IOException {
		Process q = Runtime.getRuntime().exec("cmd /C start graphGCM."+ outputFileFormat);
	}

}