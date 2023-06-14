package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextField;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.CategoryDataset;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controller.FileImportController;
import controller.BirthController;
import model.BirthData;
import model.DemographicData;

public class BirthView extends JPanel {

	// fields
	private TextField startYearField = new TextField();
	private TextField endYearField = new TextField();
	private JButton back = new JButton();
	private JButton graph = new JButton();
	private JLabel startYear = new JLabel();
	private JLabel endYear = new JLabel();
	private JLabel locationLabel = new JLabel();
	private JComboBox<String> locationButton = new JComboBox<>();
	private ChartPanel chartPanel;
	private BirthController controller;

	public BirthView() {
		// setup
		setPreferredSize(new Dimension(1000, 600));
		setVisible(true);
		setLayout(null);
		Border border = BorderFactory.createLineBorder(Color.black, 1);
		setBorder(border);
		createGraph();

		// info
		locationButton.setModel(new DefaultComboBoxModel<>(BirthData.LOCATIONS));
		locationButton.setEditable(true);
		startYearField.setText("1900");
		endYearField.setText("2020");

		// new controller
		controller = new BirthController(this);
		// listeners
		graph.addActionListener(e -> controller.Graph());

		// set up
		startYear.setBounds(885, 10, 135, 10);
		endYear.setBounds(885, 100, 135, 10);
		startYear.setText("Start Year");
		endYear.setText("End Year");
		startYearField.setBounds(845, 30, 135, 20);
		endYearField.setBounds(845, 130, 135, 20);
		locationLabel.setBounds(845, 200, 135, 20);
		locationLabel.setText("Location:");
		locationButton.setBounds(845, 250, 135, 50);
		graph.setBounds(845, 400, 135, 50);
		graph.setText("Graph / Reset");
		back.setBounds(845, 500, 135, 50);
		back.setText("Back");
		add(startYearField);
		add(endYearField);
		add(back);
		add(graph);
		add(startYear);
		add(endYear);
		add(locationLabel);
		add(locationButton);

	}

	// method to create the graph
	private void createGraph() {
		// create graph panel
		chartPanel = new ChartPanel(null);
		// setup
		chartPanel.setBounds(0, 0, 820, 560);
		Border border = BorderFactory.createLineBorder(Color.black, 1);
		chartPanel.setBorder(border);
		add(chartPanel);

	}

	// getters and setters
	public TextField getStartYearField() {
		return startYearField;
	}

	public void setStartYearField(TextField startYearField) {
		this.startYearField = startYearField;
	}

	public TextField getEndYearField() {
		return endYearField;
	}

	public void setEndYearField(TextField endYearField) {
		this.endYearField = endYearField;
	}

	public JButton getGraph() {
		return graph;
	}

	public void setGraph(JButton graph) {
		this.graph = graph;
	}

	public JLabel getStartYear() {
		return startYear;
	}

	public void setStartYear(JLabel startYear) {
		this.startYear = startYear;
	}

	public JLabel getEndYear() {
		return endYear;
	}

	public void setEndYear(JLabel endYear) {
		this.endYear = endYear;
	}

	public JLabel getLocationLabel() {
		return locationLabel;
	}

	public void setLocationLabel(JLabel locationLabel) {
		this.locationLabel = locationLabel;
	}

	public JComboBox<String> getLocationButton() {
		return locationButton;
	}

	public void setLocationButton(JComboBox<String> locationButton) {
		this.locationButton = locationButton;
	}

	public ChartPanel getChartPanel() {
		return chartPanel;
	}

	public void setChartPanel(ChartPanel chartPanel) {
		this.chartPanel = chartPanel;
	}

	public JButton getBack() {
		return back;
	}

	public void setBack(JButton back) {
		this.back = back;
	}

	public BirthController getController() {
		return controller;
	}

	public void setController(BirthController controller) {
		this.controller = controller;
	}

}