/*
 * The main method instantiates the View, Model & Controller classes and makes them visible.
 * 
 * */

public class Main {

	public static void main(String[] args) {
		View graphView = new View();
		BondTrades graphModel = new BondTrades("bondData100reals.csv");

		Controller graphController = new Controller(graphView, graphModel);
		graphView.setController(graphController);


		graphView.setVisible(true);


	}

}

