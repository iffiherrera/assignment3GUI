/*
 * The Controller class for the scatter plot instantiates a View instance 
 * and a Model instance (BondTrades) in the constructor in line with MVC best practices. 
 * It takes input from multiple action listeners in the View and 
 * performs the button click actions, combo box changes and chooses a file.
 * 
 * */


import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Controller extends MouseAdapter implements ActionListener{

	private View graphView;
	private BondTrades graphModel;
	private GraphComponent comp;
	private String file;
	MouseAdapter m;


	// Constructor with instances of view and model.
	public Controller(View graphView, BondTrades graphModel) {
		this.graphView = graphView;
		this.graphModel = graphModel;

		this.graphView.comboListenerX(new XCombo());
		this.graphView.comboListenerY(new YCombo());
		this.graphView.mouseGraph(new MouseClicker());

	}
	// Mouse adapter used to output the data details of the graph points.
	class MouseClicker extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			
			// loop around the points array list in graph component that paints ellipses.
			for(int j=0; j<comp.points.size(); j++) {
				Shape s = comp.points.get(j);
				
				if (s.contains(e.getPoint())) { // checks if the points has x and y coordinates.
					int i = comp.points.indexOf(s);  // finds the index of that point.
					
					// sets the text of the text area in View to the coordinates of the 3 lines in the data file.
					String output = "Details: " + "\n" + "Yield: " + (graphModel.yield.get(i) + "\n " + "Maturity: " + graphModel.maturity.get(i) + "\n " + "Amount:" + graphModel.amount.get(i));
					graphView.file1.setText(output);
				}				
			}
		}
	}
	// X combo box changes, changes when user chooses from options.
	class XCombo implements ActionListener {
		public void actionPerformed (ActionEvent e) {


			comp.points.clear(); // clear the default points when changing graph data.

			if ((JComboBox)e.getSource() == graphView.xBox ) {	
				
				String x = (String)graphView.xBox.getSelectedItem();
				
				if (x.equals("Yield")) { // compares the the index value equivalent to yield.
					comp.columnX = 0; // sets the index of yield to 0 for plotting.
		
				}
				else if (x.equals("Days to Maturity")) {// compares the the index value equivalent to maturity.
					comp.columnX  = 1; 
					
					
				} 
				else if (x.equals("Amount CHF (000)")) { // compares the the index value equivalent to amount.
					comp.columnX = 2;
				
					
				}

				comp.repaint(); // repaints the values once combo boxes have been changed.
				graphView.mouseGraph(new MouseClicker()); // reinitialises the mouse clicker for the graph.
			}
		}



	} // same as XCombo but for Y combo box.
	class YCombo implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			comp.points.clear(); // clear the default points when changing graph data.

			if ((JComboBox)e.getSource() == graphView.yBox ) {	
				String y = (String)graphView.yBox.getSelectedItem();
			

				if (y.equals("Yield")) {
					comp.columnY = 0;
				}
				else if (y.equals("Days to Maturity")) {
					comp.columnY = 1;
				} 
				else if (y.equals("Amount CHF (000)")) {
					comp.columnY = 2;

				}

				comp.repaint();
				graphView.mouseGraph(new MouseClicker());
			}
		}

	}
	public void actionPerformed(ActionEvent e) { 

		if (e.getSource() == graphView.button1) { // open button opening file chooser.
			readFile();

		} else if (e.getSource() == graphView.button2) {
			System.exit(0); // exit when clicking Quit.
		}
	}

	// method for obtaining file from user
	public void readFile() {

		JFileChooser bondData = new JFileChooser("GraphComponent.java");

		// set the file choosing options to csv files only.
		FileNameExtensionFilter f = new FileNameExtensionFilter("csv", "CSV");
		bondData.setFileFilter(f);

		double returnValue = bondData.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = bondData.getSelectedFile();
			file = selectedFile.getPath();
			BondTrades csv = new BondTrades(file);
			graphView.file.setText(bondData.getSelectedFile().getName());
			comp.setBondTrades(csv); // sets the details of the file in graph component.
			comp.setPaint();	// turns the boolean of file read to true.
		}
	}

	public void setComp(GraphComponent comp) {
		this.comp = comp;	
	}

}
