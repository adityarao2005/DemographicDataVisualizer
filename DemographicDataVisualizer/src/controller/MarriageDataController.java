package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import model.MaritalAgeData;
import model.MaritalStatusData;
import view.MarriageDataPanel;

//Rhett Williams
//this class controls all the functions with filtering the data and handles actions performed with each button click
public class MarriageDataController implements ActionListener{
	
	//fields
	private MarriageDataPanel panel;
	private boolean shownData = true;
	private ArrayList<MaritalAgeData> ageData = FileImportController.readMaritalAgeData();
	private ArrayList<MaritalStatusData> statusData = FileImportController.readMaritalStatusData();
	private int startYear;
	private int endYear;
	
	//contructor
	public MarriageDataController(MarriageDataPanel panel) {
		super();
		this.panel = panel;
		panel.getApplyButton().addActionListener(this);
		panel.getChangeDataButton().addActionListener(this);
	}

	//performs actions when a button is clicked
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//if the back button is clicked it goes to main menu
		if (e.getSource().equals(panel.getBackButton())) {
			panel.setVisible(false);
			//mainMenu.setVisible(true);
		}
		
		//when the change data button is clicked it shows the alternative data set
		if (e.getSource().equals(panel.getChangeDataButton())) {
			
			//displays the status data
			if (shownData) {
				
				//changes the text on the components
				panel.getChangeDataButton().setText("Age Data");
				panel.getCategorySelectionButton()[0].setText("Spouse 1 Status");
				panel.getCategorySelectionButton()[1].setText("Spouse 2 Status");
				
				//updates the graph
				panel.getChartPanel().setChart(panel.getBarChartStatusData());
				panel.getCategorySelectionButton()[0].setSelected(true);
				getStatusDataInCategories(statusData);
				shownData = false;
			}
			
			//displays the age data
			else {
				
				//changes the text on the components
				panel.getChangeDataButton().setText("Status Data");
				panel.getCategorySelectionButton()[0].setText("Spouse 1 Age");
				panel.getCategorySelectionButton()[1].setText("Spouse 2 Age");
				
				//updates the graph
				panel.getChartPanel().setChart(panel.getBarChartAgeData());
				panel.getCategorySelectionButton()[0].setSelected(true);
				getAgeDataInCategories(ageData);
				shownData = true;
			}
			
		}
		
		//displays the filtered data based on the option selected
		if (e.getSource().equals(panel.getApplyButton())) {
			
			//finds the users start and end years
			startYear = Integer.valueOf(panel.getYearTextArea()[0].getText());
			endYear = Integer.valueOf(panel.getYearTextArea()[1].getText());
			
			//checks if the user has entered valid inputs for the years
			if (startYear < 2012  || endYear > 2019 || startYear > endYear)
				JOptionPane.showMessageDialog(panel, "Please enter a year range within 2012 to 2019 and select at least one category to display");
			
			else {
				
				//displays the filtered data
				if (shownData)
					getAgeDataInCategories(getAgeDataInYear(startYear, endYear));
				else
					getStatusDataInCategories(getStatusDataInYear(startYear, endYear));
				
			}
			
		}
		
	}

	//filters data based on the age category selected
	private void getAgeDataInCategories(ArrayList<MaritalAgeData> arrayData) {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		int age;
		
		//filters data based on the first spouse's age group
		if (panel.getCategorySelectionButton()[0].isSelected()) {
			
			//checks the 9 age groups in the array
			for (int index = 0; index < arrayData.size()/9; index++) {
				age = 0;
				
				//adds the number of marriages in each age group to get a total for each group
				for (int count = 0; count < 9; count++)
					age += arrayData.get(count + index * 9).getNumOfMarriages();
				
				//adds the total to the dataset
				dataset.addValue(age, Integer.toString(arrayData.get(index*9).getYear()), arrayData.get(index*9).getAge1());
			}
		
		//filters data based on the second spouse's age group
		} else {
			
			//loops through dataset based on the number of years
			for (int year = 0; year < endYear - startYear; year++) {
				
				//goes through each of the 9 categories
				for (int category = 0; category < 9; category++) {
					age = 0;
					
					//adds the number of marriages in each age group to get a total for each group
					for (int count = 0; count < 9; count++)
						age += arrayData.get(count * 9 + category + year * 81).getNumOfMarriages();
					
					//adds the total to the dataset
					dataset.addValue(age, Integer.toString(arrayData.get(year*81).getYear()), arrayData.get(category).getAge2());
				}
			}
		}
			
		//creates the chart and updates the panel
		JFreeChart barChartAgeData = ChartFactory.createBarChart("Marriage Age Data Graph", "Year", "Number of Marriages", 
				dataset, PlotOrientation.VERTICAL, true, true, false);
		panel.getChartPanel().setChart(barChartAgeData);
		
		//formats the labels
		CategoryPlot plot = barChartAgeData.getCategoryPlot();
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
		
	}
	
	//filters data based on the age category selected
	private void getStatusDataInCategories (ArrayList<MaritalStatusData> arrayData) {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		int status;
		
		//filters data based on the first spouse's status
		if (panel.getCategorySelectionButton()[0].isSelected()) {
			
			//checks the 4 status groups in the array
			for (int index = 0; index < arrayData.size()/4; index++) {
				status = 0;
				
				//adds the number of marriages in each age group to get a total for each group
				for (int count = 0; count < 4; count++)
					status += arrayData.get(count + index * 4).getNumOfMarriages();
				
				//adds the total to the dataset
				dataset.addValue(status, Integer.toString(arrayData.get(index*4).getYear()), arrayData.get(index*4).getStatus1());
			}
			
		//filters data based on the second spouse's status
		} else {
			
			//loops through dataset based on the number of years
			for (int year = 0; year < endYear - startYear; year++) {
				
				//goes through each of the 4 categories
				for (int category = 0; category < 4; category++) {
					status = 0;
					
					//adds the number of marriages in each age group to get a total for each group
					for (int count = 0; count < 4; count++)
						status += arrayData.get(count * 4 + category + year * 16).getNumOfMarriages();
					
					//adds the total to the dataset
					dataset.addValue(status, Integer.toString(arrayData.get(year*16).getYear()), arrayData.get(category).getStatus2());
				}
			}
		}
		
		//creates the chart and updates the panel
		JFreeChart barChartStatusData = ChartFactory.createBarChart("Marriage Status Data Graph", "Year", "Number of Marriages", 
				dataset, PlotOrientation.VERTICAL, true, true, false);
		panel.getChartPanel().setChart(barChartStatusData);
		
		//formats the labels
		CategoryPlot plot = barChartStatusData.getCategoryPlot();
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
		
	}
	
	//filters the data based on the year range given for age data
	private ArrayList<MaritalAgeData> getAgeDataInYear(int startYear, int endYear) {
		
		ArrayList<MaritalAgeData> dataArray = new ArrayList<MaritalAgeData>();
		
		//goes through the array of data
		for (int index = 0; index< ageData.size(); index++)
			
			//adds any data that fits within the year range to a data array
			if (ageData.get(index).getYear() >= startYear && ageData.get(index).getYear() <= endYear)
				dataArray.add(ageData.get(index));
		
		return dataArray;
		
	}
	
	//filters the data based on the year range given for status data
	private ArrayList<MaritalStatusData> getStatusDataInYear(int startYear, int endYear) {
		
		ArrayList<MaritalStatusData> dataArray = new ArrayList<MaritalStatusData>();
		
		//goes through the array of data
		for (int index = 0; index< statusData.size(); index++)
			
			//adds any data that fits within the year range to a data array
			if (statusData.get(index).getYear() >= startYear && statusData.get(index).getYear() <= endYear)
				dataArray.add(statusData.get(index));
		
		return dataArray;
		
	}
	
	//getters and setters
	public MarriageDataPanel getPanel() {
		return panel;
	}

	public void setPanel(MarriageDataPanel panel) {
		this.panel = panel;
	}

	public boolean isShownData() {
		return shownData;
	}

	public void setShownData(boolean shownData) {
		this.shownData = shownData;
	}

	public ArrayList<MaritalAgeData> getAgeData() {
		return ageData;
	}

	public void setAgeData(ArrayList<MaritalAgeData> ageData) {
		this.ageData = ageData;
	}

	public ArrayList<MaritalStatusData> getStatusData() {
		return statusData;
	}

	public void setStatusData(ArrayList<MaritalStatusData> statusData) {
		this.statusData = statusData;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	
	

}