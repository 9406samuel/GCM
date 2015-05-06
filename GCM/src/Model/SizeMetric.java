package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import ANTLR4_code.JavaParser;

public class SizeMetric extends Metric{
	
	public SizeMetric(){
		
		numFunctions = 0;
		numPackage = 0;
		pTypes = new ArrayList<>();
		initPTypes();
	}
	
	List<String> pTypes;
	private int numFunctions;
	private int numPackage;
	private int numImport;
	private int numLocalVars;
	private int numFields;
	private int numInstanceClass;
	private int numPrimitiveVars;
	
	private void initPTypes(){
		pTypes.add("boolean");
		pTypes.add("int");
		pTypes.add("double");
		pTypes.add("char");
		pTypes.add("float");
		pTypes.add("byte");
		pTypes.add("long");
		pTypes.add("short");
	}
	
	public List<String> getpTypes() {
		return pTypes;
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
	
	public int getNumFunctions() {
		return numFunctions;
	}

	public void setNumFunctions(int numFunctions) {
		this.numFunctions = numFunctions;
	}
		
	@Override
	public void enterMethodDeclaration(@NotNull JavaParser.MethodDeclarationContext ctx) {
		setNumFunctions( getNumFunctions() + 1 );

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
		
		if(checkPrimitiveType(ctx.type().getText()))
			setNumPrimitiveVars( getNumPrimitiveVars() + 1 );
		else
			setNumInstanceClass( getNumInstanceClass() + 1 );
		
			setNumLocalVars( getNumLocalVars() + 1 );
	}
	
	@Override 
	public void enterFieldDeclaration(@NotNull JavaParser.FieldDeclarationContext ctx) {
		
		if(checkPrimitiveType(ctx.type().getText()))
			setNumPrimitiveVars( getNumPrimitiveVars() + 1 );
		else
			setNumInstanceClass( getNumInstanceClass() + 1 );
		
		setNumFields( getNumFields() + 1 );
	}
	
	public boolean checkPrimitiveType( String str ){
		return pTypes.contains( str ) ? true : false;
	}
	
	@Override
	public void exitCompilationUnit(@NotNull JavaParser.CompilationUnitContext ctx) { 
		System.out.println("num local vars: " + getNumLocalVars());
		System.out.println("num fields: " + getNumFields());
		System.out.println("num primitive: " + getNumPrimitiveVars());
		System.out.println("num intance class: " + getNumInstanceClass());


		
	}
	
	

}
