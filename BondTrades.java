/*
 * This is the Model class for this Scatter plot component.
 * The Model does not have instances of View and Controller which is 
 * in line with MVC best practices.
 * 
 * It reads the file imported from the Controller when clicking on 'Open'
 * and will separate the data from a csv file into different columns at the ,.
 * 
 * */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class BondTrades {

	private String selectedFile;
	public ArrayList <Double> yield = new ArrayList <Double>();
	public ArrayList <Double> maturity = new ArrayList <Double>();
	public ArrayList <Double> amount = new ArrayList <Double>();
	private String csv;
	public String [] headers;
	public ArrayList<String> rowArray;


	public BondTrades(String csv) {
		this.csv = csv;
		setUpArray();
	}
	public void setUpArray() {

		FileReader fr = null;
		Scanner s = null;
		String inputLine = "";
		String lineSplit = ",";

		try {

			s = new Scanner(new FileReader(csv));

			// takes the first row of headers to be used in combo boxes.
			headers = s.nextLine().split(" ");

			while (s.hasNextLine()) {
				inputLine = s.nextLine();

				// splits the input from the CSV into arrays at the commas.
				rowArray = new ArrayList<String>(Arrays.asList(inputLine.split(lineSplit)));

				// sets values into 3 different array lists. 
				yield.add(Double.parseDouble(rowArray.get(0)));
				maturity.add(Double.parseDouble(rowArray.get(1)));
				amount.add(Double.parseDouble(rowArray.get(2)));
			}

		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if (fr !=null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void setFile(String selectedFile) {
		this.selectedFile = selectedFile;
	}
	// getter method created for obtaining the headers from the imported file.
	public String[] getHeader() {
		return headers;	
	}
	// first row in the imported file, used to visualise the data when clicking on a point.
	public ArrayList<String> getRows(){
		return rowArray;
	}
	// getters for the values of yield, maturity & amount.
	public ArrayList<Double> getYield() {
		return yield;
	}
	public ArrayList<Double> getMaturity() {
		return maturity;
	}
	public ArrayList<Double> getAmount() {
		return amount;
	}



}

