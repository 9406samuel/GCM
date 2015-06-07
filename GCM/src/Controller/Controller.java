package Controller;

import java.awt.EventQueue;
import java.util.ArrayList;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import ANTLR4_code.*;
import Model.ANTLRAnalysis;
import Model.Model;
import View.Application;

public class Controller {

	private static Application window;
	private static Model model;
	
	public static Application getWindow() {
		return window;
	}

	public static void setWindow(Application window) {
		Controller.window = window;
	}

	public static Model getModel() {
		return model;
	}

	public static void setModel(Model model) {
		Controller.model = model;
	}

	public static void main(String[] args) throws Exception {
		try {
			model = new Model();
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						window = new Application();
						window.getFrame().setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		} catch (Exception e) {
			System.err.println("Error (Test): " + e);
		}
	}
}