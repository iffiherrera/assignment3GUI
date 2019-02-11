import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

/*
 *The View class sets the parameters for the JFrame where the scatter plot and all  
 * related components sit. It links with the Controller and the Model (BondTrades) and 
 * sets the action listeners used for the performed actions in Controller.
 */

public class View extends JFrame implements ActionListener{

	public JPanel top, bottom, centre;
	public JButton button1, button2;
	public JTextField file;
	JTextArea file1;
	public JComboBox xBox, yBox;
	public JLabel xLabel = new JLabel("X");
	JLabel yLabel = new JLabel("Y");
	String[] combo1 = {""};
	String[] combo2 = {""};
	Controller controller;
	//Listeners listener;
	BondTrades b;
	public GraphComponent graph = new GraphComponent();



	public View() {
		JPanel mainPanel = new JPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900,900);
		this.setLocation(200,200);
		this.setTitle("Scatterplot");
		this.layoutComponents();

	}
	public void layoutComponents() {
		// top panel with Open button, Quit button & name on file chosen.
		top = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
		button1 = new JButton("Open");
		file = new JTextField("Open file name entered");
		file.setSize(300,20);
		button2 = new JButton("Close");
		top.add(button1);
		top.add(file);
		top.add(button2);
		this.add(top,BorderLayout.NORTH);


		// Centre panel including the graph component
		centre = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
		centre.setVisible(true);
		centre.add(graph);
		this.add(centre,BorderLayout.CENTER);
		this.getContentPane().add(graph);


		// bottom panel includes combo boxes for x and y axis and a text field for the trade data.
		bottom = new JPanel();	

		// combo boxes with the three field names of the csv file.
		xBox = new JComboBox(combo1);	
		xBox.addItem("Yield");
		xBox.addItem("Days to Maturity");
		xBox.addItem("Amount CHF (000)");
		xBox.setSelectedIndex(1); // shows default yield data for x axis.
		
		// combo boxes with the three field names of the csv file.
		yBox = new JComboBox(combo2);	
		yBox.addItem("Yield");
		yBox.addItem("Days to Maturity");
		yBox.addItem("Amount CHF (000)");
		yBox.setSelectedIndex(2); // shows default maturity data for Y axis.
		
		file1 = new JTextArea("Details of the Selected Trade");
		file1.setSize(400,40);
		file1.setEnabled(isEnabled());
		
		bottom.add(xLabel);
		bottom.add(xBox);
		bottom.add(yLabel);
		bottom.add(yBox);
		bottom.add(file1);
		this.add(bottom, BorderLayout.SOUTH);

	}
	public void actionPerformed(ActionEvent e) {

	}
	// Action listeners instantiated in the Controller class.
	public void setController(Controller c) {
		this.controller = c;

		controller.setComp(this.graph);
		button1.addActionListener(controller);
		button2.addActionListener(controller);

	}
	// Action Listeners used to create new inner classes in Controller.
	public void comboListenerX(ActionListener x) {
		xBox.addActionListener(x);
	}
	public void comboListenerY(ActionListener y) {
		yBox.addActionListener(y);
	}
	public void mouseGraph(MouseListener m) {
		graph.addMouseListener(m);
	}
}


