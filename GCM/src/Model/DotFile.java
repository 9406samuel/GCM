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
    	bw.write("\t node [fontcolor=red,fontname=algerian, fixedsize=true, regular=true, height=2]\n\n");
    	
		bw.write("\tsubgraph cluster0{\n");//Inicio del primer subgrafo (metricas generales)
		bw.write("\tlabel = \"General_Metrics\";\n"); //Nombre del subgrafo
		bw.write("\tcolor = \"blue\";\n"); 

		//Atributos para cada nodo de primer subgrafo
		
    	bw.write("\t Primitive_Variables [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( sizeM.getNumPrimitiveVars() ) + "\"];\n");
    	bw.write("\t Local_Variables [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( sizeM.getNumLocalVars() ) + "\"];\n");
    	bw.write("\t Fields [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( sizeM.getNumFields()) + "\" ];\n");
    	bw.write("\t Methods [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( sizeM.getNumMethods() ) + "\"];\n");
    	bw.write("\t Package [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( sizeM.getNumPackage() ) + "\"];\n");
    	bw.write("\t Import [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( sizeM.getNumImport() ) + "\"];\n");
    	bw.write("\t Class_Instance [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( sizeM.getNumInstanceClass() ) + "\"];\n");
    	bw.write("\t Super_Class [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( sizeM.getNumFather() ) + "\"];\n");
    	bw.write("\t Implementations [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( sizeM.getNumImplementations() ) + "\"];\n");
    	try {
    		bw.write("\t If [shape = circle,  peripheries=2, "
        			+ "color=\"" + calculateColor( halsteadSizeM.getOperators().get("if") ) + "\"];\n");
		} catch (Exception e) {
			bw.write("\t If [shape = circle,  peripheries=2, "
	    			+ "color=\"black\"];\n");
		}
    	try {
    		bw.write("\t While [shape = circle,  peripheries=2, "
        			+ "color=\"" + calculateColor( halsteadSizeM.getOperators().get("while") ) + "\"];\n");
		} catch (Exception e) {
			bw.write("\t While [shape = circle,  peripheries=2, "
	    			+ "color=\"black\"];\n");
		}
    	try {
    		bw.write("\t For [shape = circle,  peripheries=2, "
        			+ "color=\"" + calculateColor( halsteadSizeM.getOperators().get("for") ) + "\"];\n");
		} catch (Exception e) {
			bw.write("\t For [shape = circle,  peripheries=2, "
	    			+ "color=\"black\"];\n");
		}
    	try {
    		bw.write("\t Case [shape = circle,  peripheries=2, "
        			+ "color=\"" + calculateColor( halsteadSizeM.getOperators().get("case") ) + "\"];\n");
		} catch (Exception e) {
			bw.write("\t Case [shape = circle,  peripheries=2, "
	    			+ "color=\"black\"];\n");
		}
    	
    	bw.write("\t Lines [shape = octagon,  peripheries=3, " 
    			+ "color=\"" + calculateColor( sizeM.getNumLinesOfCode() ) + "\"];\n\n");
    	//Relaciones existentes para los nodos del primer subgrafo
    	bw.write("\t Methods -> Lines;\n");
    	bw.write("\t Package -> Lines;\n");
    	bw.write("\t Import -> Lines;\n");
    	bw.write("\t Local_Variables -> Lines;\n");
    	bw.write("\t Fields -> Lines;\n");
    	bw.write("\t Class_Instance -> Lines;\n");
    	bw.write("\t Primitive_Variables -> Lines;\n");
    	bw.write("\t Super_Class -> Lines;\n");
    	bw.write("\t Implementations -> Lines;\n");
    	bw.write("\t If -> Lines\n");
    	bw.write("\t While -> Lines;\n");
    	bw.write("\t For -> Lines;\n");
    	bw.write("\t Case -> Lines;\n");
    	
    	bw.write("\t}\n");//Fin del primer subgrafo	
        bw.write("\n");
        
        bw.write("\tsubgraph cluster1{\n");//Inicio del segundo subgrafo (metricas de halstead)
        bw.write("\tlabel = \"Halstead_Metrics\";\n"); //Nombre del subgrafo
		bw.write("\tcolor = \"blue\";\n");
		//Atributos para cada nodo de segundo subgrafo
		bw.write("\t Operators_Total [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( halsteadSizeM.getTotalOperators() ) + "\"];\n");
		bw.write("\t Operands_Total [shape = circle,  peripheries=2, "
				+ "color=\"" + calculateColor( halsteadSizeM.getTotalOperands() ) + "\"];\n");
		bw.write("\t Operators_Distinct [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( halsteadSizeM.getDistinctOperators() ) + "\"];\n");
		bw.write("\t Operands_Distinct [shape = circle,  peripheries=2, "
    			+ "color=\"" + calculateColor( halsteadSizeM.getDistinctOperands() ) + "\"];\n");
		bw.write("\t Calculated_Length [shape = octagon,  peripheries=3, " 
    			+ "color=\"" + calculateColor( (int)halsteadSizeM.getCalculatedLength() ) + "\"];\n");
		bw.write("\t Vocabulary [shape = octagon,  peripheries=3, " 
    			+ "color=\"" + calculateColor( halsteadSizeM.getVocabulary() ) + "\"];\n");
		bw.write("\t Observed_Length [shape = octagon,  peripheries=3, " 
    			+ "color=\"" + calculateColor( halsteadSizeM.getObservedLength() ) + "\"];\n");
		bw.write("\t Difficulty [shape = octagon,  peripheries=3, " 
    			+ "color=\"" + calculateColor( (int)halsteadSizeM.getDifficulty()) + "\"];\n");
		bw.write("\t Volume [shape = octagon,  peripheries=3, " 
    			+ "color=\"" + calculateColor( (int)halsteadSizeM.getVolumen() ) + "\"];\n");
		bw.write("\t Effort [shape = octagon,  peripheries=3, " 
    			+ "color=\"" + calculateColor( (int)halsteadSizeM.getEffort() ) + "\"];\n\n");
		
    	//Relaciones existentes para los nodos del segundo subgrafo
		bw.write("\t Operators_Distinct -> Vocabulary;\n");
    	bw.write("\t Operands_Distinct -> Vocabulary;\n");
    	bw.write("\t Operators_Total -> Observed_Length;\n");
    	bw.write("\t Operands_Total -> Observed_Length;\n");
    	bw.write("\t Operators_Distinct -> Calculated_Length;\n");
    	bw.write("\t Operands_Distinct -> Calculated_Length;\n");
    	bw.write("\t Operators_Distinct-> Difficulty;\n");
    	bw.write("\t Operands_Total -> Difficulty;\n");
    	bw.write("\t Operands_Distinct -> Difficulty;\n");
    	
    	bw.write("\t Vocabulary -> Volume;\n");
    	bw.write("\t Observed_Length -> Volume;\n");
    	bw.write("\t Volume -> Effort;\n");
    	bw.write("\t Difficulty -> Effort;\n");
    	
    	bw.write("\t}\n");//Fin del segundo subgrafo	
        bw.write("\n");
        
        //Inicio del tercer subgrafo (complejidad ciclomatica)
        bw.write("\tsubgraph cluster2{\n");
        bw.write("\tlabel = \"Cyclomatic_Metric\";\n"); //Nombre del subgrafo
		bw.write("\tcolor = \"blue\";\n");

		//Atributos para cada nodo de tercer subgrafo
		bw.write("\t Cyclomatic_Complexity [shape = octagon,  peripheries=3, " 
    			+ "color=\"" + calculateColor( controlM.getCyclomaticComplexity() ) + "\"];\n\n");
		//Relaciones existentes para los nodos del tercer subgrafo
		bw.write("\t If -> Cyclomatic_Complexity;\n");
    	bw.write("\t While -> Cyclomatic_Complexity;\n");
    	bw.write("\t For -> Cyclomatic_Complexity;\n");
    	bw.write("\t Case -> Cyclomatic_Complexity;\n");
    	
    	bw.write("\t}\n");//Fin del tercer subgrafo	
    	
    	//Inicio del Cuarto subgrafo (indice de mantenibilidad)
        bw.write("\tsubgraph cluster3{\n");
        bw.write("\tlabel = \"Maintainability_Index\";\n"); //Nombre del subgrafo
		bw.write("\tcolor = \"blue\";\n");
		//Atributos para cada nodo de cuarto subgrafo
		bw.write("\t Maintainability [shape = octagon,  peripheries=3, " 
    			+ "color=\"" + calculateColor( (int)maintainabilityM.getMaintainabilityIndex() ) + "\"];\n");
		
		//Relaciones existentes para los nodos del cuarto subgrafo
		bw.write("\t Lines -> Maintainability;\n");
    	bw.write("\t Cyclomatic_Complexity -> Maintainability;\n");
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
		} catch (InterruptedException exception) {
			System.out.println("ERROR: Wait Problem");
		}
	    System.out.println("The file was created");
	}
	
	public void openFile(String outputFileFormat) throws IOException {
		
		Process q = Runtime.getRuntime().exec("cmd /C start graphGCM."+ outputFileFormat);
		
	}

}