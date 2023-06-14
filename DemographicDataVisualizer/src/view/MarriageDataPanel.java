package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;

import controller.MarriageDataController;

//Rhett Williams
//this class creates the components of the panel that will be displayed and creates the JFreeChart graph
@SuppressWarnings("serial")
public class MarriageDataPanel extends JPanel {

	// fields
	private JLabel title = new JLabel("Marriage Data");
	private JLabel filterSubheading = new JLabel("Filter");
	private JLabel[] yearRangeHeading = new JLabel[2];
	private JLabel categoryHeading = new JLabel("Category Selection:");
	private JTextArea[] yearTextArea = new JTextArea[2];
	private JRadioButton[] categorySelectionButton = new JRadioButton[2];
	private JButton backButton = new JButton("Back");
	private JButton applyButton = new JButton("Apply");
	private JButton changeDataButton = new JButton("Status Data");
	private JFreeChart barChartAgeData;
	private JFreeChart barChartStatusData;
	private ChartPanel chartPanel;
	private MarriageDataController marriageController;
	private JPanel filterPanel = new JPanel();
	private ButtonGroup buttonGroup = new ButtonGroup();

	// constructor
	public MarriageDataPanel() {
		super();

		// set up the panel
		setVisible(true);
		setBounds(0, 0, 1000, 600);
		setLayout(null);

		// sets up the title label
		add(title);
		title.setVisible(true);
		title.setFont(new Font("arial", Font.PLAIN, 24));
		title.setBounds(425, 25, 200, 75);

		// adds the panel for filter options components
		add(filterPanel);
		filterPanel.setVisible(true);
		filterPanel.setBounds(685, 110, 290, 425);
		filterPanel.setLayout(null);

		// sets up the subheadings
		filterPanel.add(filterSubheading);
		filterSubheading.setVisible(true);
		filterSubheading.setFont(new Font("arial", Font.BOLD, 22));
		filterSubheading.setBounds(125, -15, 150, 75);

		filterPanel.add(categoryHeading);
		categoryHeading.setVisible(true);
		categoryHeading.setFont(new Font("arial", Font.PLAIN, 16));
		categoryHeading.setBounds(15, 175, 150, 75);

		// creates the buttons
		add(backButton);
		backButton.setVisible(true);
		backButton.setFont(new Font("arial", Font.PLAIN, 16));
		backButton.setBounds(50, 25, 125, 70);

		filterPanel.add(applyButton);
		applyButton.setVisible(true);
		applyButton.setFont(new Font("arial", Font.PLAIN, 16));
		applyButton.setBounds(150, 340, 125, 70);

		filterPanel.add(changeDataButton);
		changeDataButton.setVisible(true);
		changeDataButton.setFont(new Font("arial", Font.PLAIN, 16));
		changeDataButton.setBounds(15, 340, 125, 70);

		// sets up the radio buttons, labels, and text area
		for (int index = 0; index < 2; index++) {

			// creates the year range text areas
			yearTextArea[index] = new JTextArea();
			filterPanel.add(yearTextArea[index]);
			yearTextArea[index].setVisible(true);
			yearTextArea[index].setBounds(100 + 140 * index, 100, 40, 30);

			// creates the labels for each year range text area
			yearRangeHeading[index] = new JLabel();
			filterPanel.add(yearRangeHeading[index]);
			yearRangeHeading[index].setVisible(true);
			yearRangeHeading[index].setFont(new Font("arial", Font.PLAIN, 16));
			yearRangeHeading[index].setBounds(15 + 140 * index, 75, 150, 75);

			// sets up the radio buttons to choose the categories
			categorySelectionButton[index] = new JRadioButton();
			categorySelectionButton[index].setFont(new Font("arial", Font.PLAIN, 14));
			categorySelectionButton[index].setOpaque(false);
			filterPanel.add(categorySelectionButton[index]);
			categorySelectionButton[index].setVisible(true);
			categorySelectionButton[index].setBounds(155, 175 + 50 * index, 150, 75);
			buttonGroup.add(categorySelectionButton[index]);
		}

		// adds text to the components
		yearTextArea[0].setText("2012");
		yearTextArea[1].setText("2019");
		yearRangeHeading[0].setText("Start Year:");
		yearRangeHeading[1].setText("End Year:");
		categorySelectionButton[0].setText("Spouse 1 Age");
		categorySelectionButton[1].setText("Spouse 2 Age");
		categorySelectionButton[0].setSelected(true);

		marriageController = new MarriageDataController(this);

		// creates the bar chart for the age dataset
		barChartAgeData = ChartFactory.createBarChart("Marriage Age Data Graph", "Ages", "Number of Marriages", null,
				PlotOrientation.VERTICAL, true, true, false);

		// formats the text diagonally to make it easier to read
		CategoryPlot agePlot = barChartAgeData.getCategoryPlot();
		CategoryAxis ageDomainAxis = agePlot.getDomainAxis();
		ageDomainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));

		// create the panel for the bar chart
		chartPanel = new ChartPanel(barChartAgeData);
		chartPanel.setBounds(25, 100, 600, 450);
		add(chartPanel);
		chartPanel.setVisible(true);

		JLabel background = new JLabel(new ImageIcon("images/background.png"));
		add(background);
		background.setBounds(0, 0, 1000, 600);
		

		JLabel filterBackground = new JLabel(new ImageIcon("images/menuBackground.png"));
		filterPanel.add(filterBackground);
		filterBackground.setBounds(0, 0, 290, 425);

		// updates the graph to display the data
		applyButton.doClick();

	}

	// getters and setters
	public JLabel getTitle() {
		return title;
	}

	public void setTitle(JLabel title) {
		this.title = title;
	}

	public JLabel getFilterSubheading() {
		return filterSubheading;
	}

	public void setFilterSubheading(JLabel filterSubheading) {
		this.filterSubheading = filterSubheading;
	}

	public JLabel[] getYearRangeHeading() {
		return yearRangeHeading;
	}

	public void setYearRangeHeading(JLabel[] yearRangeHeading) {
		this.yearRangeHeading = yearRangeHeading;
	}

	public JLabel getCategoryHeading() {
		return categoryHeading;
	}

	public void setCategoryHeading(JLabel categoryHeading) {
		this.categoryHeading = categoryHeading;
	}

	public JTextArea[] getYearTextArea() {
		return yearTextArea;
	}

	public void setYearTextArea(JTextArea[] yearTextArea) {
		this.yearTextArea = yearTextArea;
	}

	public JRadioButton[] getCategorySelectionButton() {
		return categorySelectionButton;
	}

	public void setCategorySelectionButton(JRadioButton[] categorySelectionButton) {
		this.categorySelectionButton = categorySelectionButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}

	public JButton getApplyButton() {
		return applyButton;
	}

	public void setApplyButton(JButton applyButton) {
		this.applyButton = applyButton;
	}

	public JButton getChangeDataButton() {
		return changeDataButton;
	}

	public void setChangeDataButton(JButton changeDataButton) {
		this.changeDataButton = changeDataButton;
	}

	public JFreeChart getBarChartAgeData() {
		return barChartAgeData;
	}

	public void setBarChartAgeData(JFreeChart barChartAgeData) {
		this.barChartAgeData = barChartAgeData;
	}

	public JFreeChart getBarChartStatusData() {
		return barChartStatusData;
	}

	public void setBarChartStatusData(JFreeChart barChartStatusData) {
		this.barChartStatusData = barChartStatusData;
	}

	public ChartPanel getChartPanel() {
		return chartPanel;
	}

	public void setChartPanel(ChartPanel chartPanel) {
		this.chartPanel = chartPanel;
	}

	public MarriageDataController getMarriageController() {
		return marriageController;
	}

	public void setMarriageController(MarriageDataController marriageController) {
		this.marriageController = marriageController;
	}

	public JPanel getFilterPanel() {
		return filterPanel;
	}

	public void setFilterPanel(JPanel filterPanel) {
		this.filterPanel = filterPanel;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public void setButtonGroup(ButtonGroup buttonGroup) {
		this.buttonGroup = buttonGroup;
	}

}