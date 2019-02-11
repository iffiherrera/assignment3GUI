import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;
import javax.swing.JComponent;

public class GraphComponent extends JComponent {

	private int height = 560;
	private int width = 700;
	private int radius = 3;
	private Controller controller;
	public ArrayList<Double> xPoints;
	public ArrayList<Double> yPoints;
	public ArrayList<Shape> points = new ArrayList<Shape>();
	String [] ticks = new String[] {"0","1","2","3","4","5","6","7","8","9","0","1","2","3","4","5","6","7","8","9"};
	public int columnX = 0;
	public int columnY = 1;
	
	private BondTrades b;
	private boolean paint = false;
	public Graphics2D g2;

	
	public GraphComponent() {
		super();
		this.setSize(width, height);

	}
	// paint component instantiates the methods comprising the graph and data points.
	public void paintComponent(Graphics g) {
		g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		backGround(g2);
		drawAxes(g2);
		tickMarks(g2, ticks);

		points.clear();
		// Paint only if there is data read from a file.
		if (paint) {

			addPoint(g2); // paint ellipses when there is a file.
			tickLabel(); // change the tick labels to the data chosen.
		}
	}
	// axes set for the graph.
	public void drawAxes(Graphics2D g) {

		g.setColor(Color.BLACK);
		g.drawRect(100, 40, width, height);

	}
	// method for setting the graph area background to white.
	public void backGround(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(100, 40, width, height);
	}

	// data points using coordinates from the read file.
	public void addPoint(Graphics2D g) {
		g.setColor(Color.BLUE);
		checkColumns(columnX, 'x');
		checkColumns(columnY, 'y');
		double differenceX = Collections.max(xPoints) - Collections.min(xPoints);
		double differenceY = Collections.max(yPoints) - Collections.min(yPoints);

		for (int i = 0;i < xPoints.size(); i++) { // loops for the length of the data column.

			double xCoord = makeCoordinatesX(xPoints.get(i), differenceX);
			xCoord = xCoord + 100; // coordinates for X set.

			double yCoord = makeCoordinatesY(yPoints.get(i), differenceY);
			yCoord = (height - yCoord) + 40; // coordinates for Y set.

			// cast double values to int for correct graph plotting.
			points.add(new Ellipse2D.Double((int)xCoord - radius, (int)yCoord - radius, radius * 2, radius *2));

			Shape a = points.get(i); // creates as many points as the length of the data column.
			g2.fill(a); // paints the filled ellipses.

		
			repaint();
		}
	}
	// method for creating the X coordinates used in addPoint()
	public double makeCoordinatesX(double number, double diffX) {

		double pix = ((number - Collections.min(xPoints))/ diffX) * width;
		double coordinateX = Math.round(pix * 100.0) / 100.0;
		return coordinateX;
	}

	// method for creating the Y coordinates used in addPoint()
	public double makeCoordinatesY(double number, double diffY) {
		double pix = ((number - Collections.min(yPoints))/diffY) * height;
		double coordinateY = Math.round(pix * 100.0) / 100.0;
		return coordinateY;
	}
	// Method establishing the list array for X and Y from the read file.
	public void checkColumns(int column, char axis) {
		if(axis == 'x') {
			xPoints = getList(column);
		} else if(axis == 'y') {
			yPoints = getList(column);
		}
	}
	
	// Method for obtaining all three array lists and set to columns to be used in checkColumns method.
	public ArrayList<Double> getList(int column) {
		if(column == 0) {
			return b.getYield();		
		} else if (column == 1) {
			return b.getMaturity();
		} else if (column == 2) {
			return b.getAmount();		
		}
		return null; // Over writing null points exceptions.

	}
	// method to set and repaint the labels on x and y axis
	private void tickLabel() {
		for (int i = 0; i < 10 ; i++) {
			setTick(i, Collections.min(xPoints), Collections.max(xPoints), 0);
			setTick(i, Collections.min(yPoints), Collections.max(yPoints), 10);
		}

		repaint();
	}
	// sets the tick labels to a format of 2 decimal points.
	private void setTick(int index, double min, double max, int offset) {
		double valueIncreasePerTick = (max - min)/9; // the difference between max and min divided by the remaining ticks excluding min value.
		double valueIncreaseFromMin = valueIncreasePerTick * index;
		ticks[index + offset] = String.format("%.2f", min + valueIncreaseFromMin);
	}

	public void tickMarks(Graphics2D t, String[] marks) {

		// ==================================== 10 ticks drawn and set for Y Axis.

		t.drawString(marks[10], 30, 600);
		t.drawLine(90, 600, 100, 600);

		t.drawString(marks[11], 30, 540);
		t.drawLine(90, 540, 100, 540);

		t.drawString(marks[12], 30, 480);		
		t.drawLine(90, 480, 100, 480);

		t.drawString(marks[13], 30, 420);		
		t.drawLine(90, 409, 100, 409);

		t.drawString(marks[14], 30, 360);		
		t.drawLine(90, 350, 100, 350);

		t.drawString(marks[15], 30, 300);		
		t.drawLine(90, 291, 100, 291);

		t.drawString(marks[16], 30, 240);		
		t.drawLine(90, 232, 100, 232);

		t.drawString(marks[17], 30, 180);		
		t.drawLine(90, 173, 100, 173);

		t.drawString(marks[18], 30, 120);		
		t.drawLine(90, 114, 100, 114);

		t.drawString(marks[19], 30, 50);	
		t.drawLine(90, 40, 100, 40);

		// ====================================== 10 ticks drawn and set for X axis.		


		t.drawString(marks[0], 100, 650);
		t.drawLine(100, 600, 100, 610);

		t.drawString(marks[1], 175, 650);	
		t.drawLine(175, 600, 175, 610);

		t.drawString(marks[2], 250, 650);
		t.drawLine(250, 600, 250, 610);


		t.drawString(marks[3], 325, 650);
		t.drawLine(325, 600, 325, 610);


		t.drawString(marks[4], 400, 650);
		t.drawLine(400, 600, 400, 610);


		t.drawString(marks[5], 475, 650);
		t.drawLine(475, 600, 475, 610);


		t.drawString(marks[6], 550, 650);
		t.drawLine(550, 600, 550, 610);


		t.drawString(marks[7], 625, 650);
		t.drawLine(625, 600, 625, 610);


		t.drawString(marks[8], 700, 650);
		t.drawLine(700, 600, 700, 610);


		t.drawString(marks[9], 790, 650);
		t.drawLine(800, 600, 800, 610);

	}

	public void setBondTrades(BondTrades b) {
		this.b = b;
	}
	public void setPaint() {
		paint = true;
		repaint();
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}


}
