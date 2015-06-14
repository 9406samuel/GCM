package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.antlr.runtime.Token;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;

import ANTLR4_code.JavaParser;
import ANTLR4_code.JavaParser.VariableDeclaratorContext;

public class SizeMetric extends Metric{
	
	private int numMethods;
	private int numPackage;
	private int numImport;
	private int numLocalVars;
	private int numFields;
	private int numInstanceClass;
	private int numPrimitiveVars;
	private int numLinesOfCode;
	private int numFather;
	private int numImplementations;
	private HashMap< String, Integer > operandsList;
	private HashMap< String, Integer > numAccessModifiers;
	private HashMap< String, Integer > numStatements;
	List<String> primitiveTypes;
	
	
	public SizeMetric(){
		
		numMethods = 0;
		numPackage = 0;
		numFather = 0;
		primitiveTypes = new ArrayList<>();
		numAccessModifiers = new HashMap<>();
		numStatements = new HashMap<>();
		operandsList = new HashMap<>();
		initPrimitiveTypes();
		initStatements();
	}
	
	
	
	private void initStatements(){
		numStatements.put( "try", 0 );
		numStatements.put( "assert", 0 );
		numStatements.put( "if", 0 );
		numStatements.put( "for", 0 );
		numStatements.put( "while", 0 );
		numStatements.put( "do", 0 );
		numStatements.put( "switch", 0 );
		numStatements.put( "synchronized", 0 );
		numStatements.put( "return", 0 );
		numStatements.put( "throw", 0 );
		numStatements.put( "break", 0 );
		numStatements.put( "continue", 0 );
	}
	
	private void initPrimitiveTypes(){
		primitiveTypes.add("boolean");
		primitiveTypes.add("int");
		primitiveTypes.add("double");
		primitiveTypes.add("char");
		primitiveTypes.add("float");
		primitiveTypes.add("byte");
		primitiveTypes.add("long");
		primitiveTypes.add("short");
	}
	
	public HashMap<String, Integer> getOperandsList() {
		return operandsList;
	}
	public void setOperandsList(HashMap<String, Integer> operandsList) {
		operandsList = operandsList;
	}
	public int getNumMethods() {
		return numMethods;
	}
	
	public HashMap<String, Integer> getNumAccessModifiers() {
		return numAccessModifiers;
	}
	
	public void setNumAccessModifiers(HashMap<String, Integer> numAccessModifiers) {
		this.numAccessModifiers = numAccessModifiers;
	}
	
	public HashMap<String, Integer> getNumStatements() {
		return numStatements;
	}
	
	public void setNumStatements(HashMap<String, Integer> numStatements) {
		this.numStatements = numStatements;
	}
	
	public int getNumFather() {
		return numFather;
	}
	
	public void setNumFather(int numFather) {
		this.numFather = numFather;
	}
	
	public int getNumImplementations() {
		return numImplementations;
	}
	
	public void setNumImplementations(int numImplementations) {
		this.numImplementations = numImplementations;
	}
	
	public int getNumLinesOfCode() {
		return numLinesOfCode;
	}

	public void setNumLinesOfCode(int numLinesOfCode) {
		this.numLinesOfCode = numLinesOfCode;
	}

	public void setPrimitiveTypes(List<String> primitiveTypes) {
		this.primitiveTypes = primitiveTypes;
	}
	
	public List<String> getPrimitiveTypes() {
		return primitiveTypes;
	}
	
	public int getNumInstanceClass() {
		return numInstanceClass;
	}

	public void setNumInstanceClass(int numInstanceClass) {
		this.numInstanceClass = numInstanceClass;
	}

	public int getNumPrimitiveVars() {
		return numPrimitiveVars;
	}

	public void setNumPrimitiveVars(int numPrimitiveVars) {
		this.numPrimitiveVars = numPrimitiveVars;
	}
	
	
	public int getNumLocalVars() {
		return numLocalVars;
	}

	public int getNumFields() {
		return numFields;
	}

	public void setNumFields(int numFields) {
		this.numFields = numFields;
	}

	public void setNumLocalVars(int numLocalVars) {
		this.numLocalVars = numLocalVars;
	}
	
	public int getNumImport() {
		return numImport;
	}

	public void setNumImport(int numImport) {
		this.numImport = numImport;
	}

	public int getNumPackage() {
		return numPackage;
	}

	public void setNumPackage(int numPackage) {
		this.numPackage = numPackage;
	}
	
	public void setNumMethods(int numFunctions) {
		this.numMethods = numFunctions;
	}
		
	@Override
	public void enterMethodDeclaration(@NotNull JavaParser.MethodDeclarationContext ctx) {
		setNumMethods( getNumMethods() + 1 );
		operandsList.put( ctx.Identifier().getText(), 0 );
	}
		
	@Override 
	public void enterPackageDeclaration(@NotNull JavaParser.PackageDeclarationContext ctx) { 
		setNumPackage( getNumPackage() + 1 );
	}
	
	@Override
	public void enterImportDeclaration(@NotNull JavaParser.ImportDeclarationContext ctx) { 
		setNumImport( getNumImport() + 1 );
	}
	
	@Override 
	public void enterLocalVariableDeclaration(@NotNull JavaParser.LocalVariableDeclarationContext ctx) {
		
		List<VariableDeclaratorContext> l = ctx.variableDeclarators().variableDeclarator();
		for( VariableDeclaratorContext x: l ){
			operandsList.put(x.variableDeclaratorId().getText(), 0 );
		}
		
		if(checkPrimitiveType(ctx.type().getText()))
			setNumPrimitiveVars( getNumPrimitiveVars() + 1 );
		else
			setNumInstanceClass( getNumInstanceClass() + 1 );
		
			setNumLocalVars( getNumLocalVars() + 1 );
	}
	
	@Override 
	public void enterFieldDeclaration(@NotNull JavaParser.FieldDeclarationContext ctx) {
		
		List<VariableDeclaratorContext> l = ctx.variableDeclarators().variableDeclarator();
		for( VariableDeclaratorContext x: l ){
			operandsList.put(x.variableDeclaratorId().getText(), 0 );
		}
		
		if( checkPrimitiveType( ctx.type().getText() ) )
			setNumPrimitiveVars( getNumPrimitiveVars() + 1 );
		else
			setNumInstanceClass( getNumInstanceClass() + 1 );
		
		setNumFields( getNumFields() + 1 );
	}
	
	public boolean checkPrimitiveType( String str ){
		return primitiveTypes.contains( str ) ? true : false;
	}
	
	@Override
	public void enterLiteral(@NotNull JavaParser.LiteralContext ctx) {
		try{
			Double.parseDouble(ctx.getText());
			operandsList.put( ctx.getText(), 0 );
		}catch( Exception e){
			
		}
	}
	
	@Override
	public void enterClassDeclaration(@NotNull JavaParser.ClassDeclarationContext ctx) {
		try{
		if( ctx.type().getText() != null ){
			setNumFather( getNumFather() + 1 );
		}
		}catch( Exception e ){
			setNumFather( 0 );
		}
		try{
		if( ctx.typeList().depth() > 0){
			for( int i = 0; i < ctx.typeList().getChildCount(); i++ ){
				if( ctx.typeList().getChild(i) instanceof JavaParser.TypeContext )
					setNumImplementations( getNumImplementations() + 1 );
			}
		}
		}catch( Exception e ){
			setNumImplementations( 0 );
		}
	}
	
	@Override
	public void enterClassOrInterfaceModifier(@NotNull JavaParser.ClassOrInterfaceModifierContext ctx) {
		if( ctx.getText().equals("private")){
			if(!numAccessModifiers.containsKey("private"))
				numAccessModifiers.put("private", 1 );
			else
				numAccessModifiers.put("private",numAccessModifiers.get("private") + 1);
				
		}
		if( ctx.getText().equals("public")){
			if(!numAccessModifiers.containsKey("public"))
				numAccessModifiers.put("public", 1 );
			else
				numAccessModifiers.put("public",numAccessModifiers.get("public") + 1);
				
		}
		if( ctx.getText().equals("protected")){
			if(!numAccessModifiers.containsKey("protected"))
				numAccessModifiers.put("protected", 1 );
			else
				numAccessModifiers.put("protected",numAccessModifiers.get("protected") + 1);	
		}
	}
	
	@Override
	public void enterStatement(@NotNull JavaParser.StatementContext ctx) { 
		try{
			String x = ctx.getChild(0).getText();
			numStatements.put(x, numStatements.get(x) + 1);
		}catch( Exception e ){
			
		}
	}
	
	@Override
	public void exitCompilationUnit(@NotNull JavaParser.CompilationUnitContext ctx) { 
		setNumLinesOfCode(findNumLinesOfCode(ctx));
		
	}
	
	public int findNumLinesOfCode( @NotNull ParserRuleContext ctx ){
		org.antlr.v4.runtime.Token t = ctx.getStop();
		if ( t != null ) 
			return t.getLine();
		else
			return 0;
		}

	@Override
	public String toString() {
		return "SIZE METRICS:\n"
				+ "Methods: " + numMethods + "\n"
				+ "Packages: " + numPackage + "\n"
				+ "Imports: " + numImport + "\n"
				+ "Local variables: " + numLocalVars + "\n"
				+ "Class fields: " + numFields + "\n"
				+ "Class instances: " + numInstanceClass + "\n"
				+ "Primitive variables: " + numPrimitiveVars +"\n"
				+ "Lines of code: " + numLinesOfCode + "\n"
				+ "Extended classes: " + numFather + "\n"
				+ "Implemented interfaces: " + numImplementations + "\n"
				+ "Access modifiers: " + numAccessModifiers.size() + "\n"
				+ "\n";
	}
}