package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.ui.RectangleInsets;

import model.DurationOfUnemploymentRecord;
import model.EmploymentRecord;
import model.PartTimeEmploymentRecord;
import model.WageLevelRecord;
import view.EmploymentStatsView;

// ADITYA
public class EmploymentStatsController implements ActionListener, ChangeListener {
	// Fields
	// The view that will be controlled
	private EmploymentStatsView view;

	// CONSTANTS
	private static final String DATA_NOT_FOUND = "No data was recorded for the conditions in the filters";

	// Constructor
	public EmploymentStatsController(EmploymentStatsView view) {
		// Set the view
		this.view = view;

		// Set the listeners to this
		view.getFilterPane().addChangeListener(this);
		view.getReloadButton().addActionListener(this);

		// Reload
		stateChanged(null);

	}

	// When the tab has changed, make sure that the graph changes
	@Override
	public void stateChanged(ChangeEvent e) {
		// Gets the filter pane
		JTabbedPane filterPane = view.getFilterPane();

		// Get tab title
		String title = filterPane.getTitleAt(filterPane.getSelectedIndex());

		if (title.equals(EmploymentStatsView.DURATION_TAB_NAME)) {

			view.getLocationCombo().setModel(new DefaultComboBoxModel<String>(DurationOfUnemploymentRecord.LOCATIONS));
			view.getAgeGroupCombo().setModel(new DefaultComboBoxModel<String>(DurationOfUnemploymentRecord.AGE_GROUPS));

			// If the tab is part time tab name
		} else if (title.equals(EmploymentStatsView.PART_TIME_TAB_NAME)) {
			view.getLocationCombo().setModel(new DefaultComboBoxModel<String>(PartTimeEmploymentRecord.LOCATIONS));
			view.getAgeGroupCombo().setModel(new DefaultComboBoxModel<String>(PartTimeEmploymentRecord.AGE_GROUPS));

			// Else its the wages tab name
		} else {

			view.getLocationCombo().setModel(new DefaultComboBoxModel<String>(WageLevelRecord.LOCATIONS));
			view.getAgeGroupCombo().setModel(new DefaultComboBoxModel<String>(WageLevelRecord.AGE_GROUPS));
		}

		reload();
	}

	// When the reload button is clicked, make sure the graph changes
	@Override
	public void actionPerformed(ActionEvent e) {
		reload();
	}

	// Reloads the graph
	private void reload() {
		// Gets the filter pane
		JTabbedPane filterPane = view.getFilterPane();

		// Get tab title
		String title = filterPane.getTitleAt(filterPane.getSelectedIndex());

		// Reload graph based on tab
		// If the tab is duration tab name
		JFreeChart chart = null;

		// Update graph data based on title
		try {
			if (title.equals(EmploymentStatsView.DURATION_TAB_NAME)) {

				chart = refreshWithDurationData();

				// If the tab is part time tab name
			} else if (title.equals(EmploymentStatsView.PART_TIME_TAB_NAME)) {

				chart = refreshWithPartTimeData();

				// Else its the wages tab name
			} else {

				chart = refreshWithWagesData();

			}
		} catch (NumberFormatException ex) {
			// Print error message
			JOptionPane.showMessageDialog(view, "Cannot convert text to a year", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Set the background image
		chart.setBackgroundImage(new ImageIcon("images/background.png").getImage());

		// Set plot values
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);

		// Set shape and line visible true for item renderer
		XYItemRenderer r = plot.getRenderer();
		if (r instanceof XYLineAndShapeRenderer) {
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
			renderer.setBaseShapesVisible(true);
			renderer.setBaseShapesFilled(true);
		}

		// Get date axis and format
		DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setDateFormatOverride(new SimpleDateFormat("yyyy"));

		// Set the chart
		view.getGraph().setChart(chart);

	}

	// refresh with duration data
	private JFreeChart refreshWithPartTimeData() {

		// Get the duration data
		List<PartTimeEmploymentRecord> filteredData = getPartTimeData();

		// Create the time series
		TimeSeriesCollection dataset = createGraphDataSet(filteredData);

		dataset.getDomainBounds(true);

		// Create the jfreechart
		try {
			return ChartFactory.createTimeSeriesChart("Employment Data", // Title
					"Years", // x-axis label
					"Number of People", // y-axis label
					dataset, // Dataset
					true, // show legend
					true, // show tooltips
					false // show urls
			);
		} finally {

			// Print error message
			if (filteredData.isEmpty())
				JOptionPane.showMessageDialog(this.view, DATA_NOT_FOUND);

		}

	}

	// Returns a filtered list
	private List<PartTimeEmploymentRecord> getPartTimeData() {

		// Read the data from a file
		List<PartTimeEmploymentRecord> data = FileImportController.readPartTimeRecords();

		// Get the start year
		int startYear = Integer.parseInt(view.getStartDateField().getText());
		int endYear = Integer.parseInt(view.getEndDateField().getText());

		// Get the values from Combo
		String ageGroup = (String) view.getAgeGroupCombo().getSelectedItem();
		String location = (String) view.getLocationCombo().getSelectedItem();

		// Get the duration and reason
		String status = (String) view.getStatusCombo().getSelectedItem();
		String reason = (String) view.getReasonCombo().getSelectedItem();

		// Create a stream
		return data.stream()
				// Filter the list by years
				.filter(record -> record.getYear() <= endYear && record.getYear() >= startYear)
				// Filter the list by age group
				.filter(record -> record.getAgeGroup().equals(ageGroup))
				// Filter the list by location
				.filter(record -> record.getLocation().equals(location))
				// Filter the list by immigration status
				.filter(record -> record.getImmigrationStatus().equals(status))
				// Filter the list by reason
				.filter(record -> record.getReason().equals(reason))
				// Collect the stream to a list
				.collect(Collectors.toList());

	}

	// refresh with duration data
	private JFreeChart refreshWithDurationData() {

		// Get the duration data
		List<DurationOfUnemploymentRecord> filteredData = getDurationData();

		// Create the time series
		TimeSeriesCollection dataset = createGraphDataSet(filteredData);

		dataset.getDomainBounds(true);

		try {
			// Create the jfreechart
			return ChartFactory.createTimeSeriesChart("Employment Data", // Title
					"Years", // x-axis label
					"Number of People", // y-axis label
					dataset, // Dataset
					true, // show legend
					true, // show tooltips
					false // show urls
			);

		} finally {

			// Print error message
			if (filteredData.isEmpty())
				JOptionPane.showMessageDialog(this.view, DATA_NOT_FOUND);

		}
	}

	// Returns a filtered list
	private List<DurationOfUnemploymentRecord> getDurationData() {
		// Read the data from a file
		List<DurationOfUnemploymentRecord> data = FileImportController.readDurationRecords();

		// Get the start year
		int startYear = Integer.parseInt(view.getStartDateField().getText());
		int endYear = Integer.parseInt(view.getEndDateField().getText());

		// Get the values from Combo
		String ageGroup = (String) view.getAgeGroupCombo().getSelectedItem();
		String location = (String) view.getLocationCombo().getSelectedItem();

		// Get the duration and status
		String status = (String) view.getStatusCombo_1().getSelectedItem();
		String duration = (String) view.getDurationCombo().getSelectedItem();

		// Create a stream
		return data.stream()
				// Filter the list by years
				.filter(record -> record.getYear() <= endYear && record.getYear() >= startYear)
				// Filter the list by age group
				.filter(record -> record.getAgeGroup().equals(ageGroup))
				// Filter the list by location
				.filter(record -> record.getLocation().equals(location))
				// Filter the list by immigration status
				.filter(record -> record.getImmigrationStatus().equals(status))
				// Filter the list by duration
				.filter(record -> record.getDuration().equals(duration))
				// Collect the stream to a list
				.collect(Collectors.toList());

	}

	// refresh with duration data
	private JFreeChart refreshWithWagesData() {

		// Get the duration data
		List<WageLevelRecord> filteredData = getWagesData();

		// Create the time series
		TimeSeriesCollection dataset = createGraphDataSet(filteredData);

		dataset.getDomainBounds(true);

		// Create the jfreechart
		try {
			return ChartFactory.createTimeSeriesChart("Employment Data", // Title
					"Years", // x-axis label
					"Number of People", // y-axis label
					dataset, // Dataset
					true, // show legend
					true, // show tooltips
					false // show urls
			);
		} finally {
			// Print error message
			if (filteredData.isEmpty())
				JOptionPane.showMessageDialog(this.view, DATA_NOT_FOUND);

		}

	}

	// Returns a filtered list
	private List<WageLevelRecord> getWagesData() {
		// Read the data from a file
		List<WageLevelRecord> data = FileImportController.readWageRecords();

		// Get the start year
		int startYear = Integer.parseInt(view.getStartDateField().getText());
		int endYear = Integer.parseInt(view.getEndDateField().getText());

		// Get the values from Combo
		String ageGroup = (String) view.getAgeGroupCombo().getSelectedItem();
		String location = (String) view.getLocationCombo().getSelectedItem();

		// Get the wages, level, type of work
		String wages = (String) view.getWageCombo().getSelectedItem();
		String level = (String) view.getLevelCombo().getSelectedItem();
		String typeOfWork = (String) view.getWorkCombo().getSelectedItem();

		// Create a stream
		return data.stream()
				// Filter the list by years
				.filter(record -> record.getYear() <= endYear && record.getYear() >= startYear)
				// Filter the list by age group
				.filter(record -> record.getAgeGroup().equals(ageGroup))
				// Filter the list by location
				.filter(record -> record.getLocation().equals(location))
				// Filter the list by wages
				.filter(record -> record.getWage().equals(wages))
				// Filter the list by level
				.filter(record -> record.getLevelOfEducation().equals(level))
				// Filter the list by type of work
				.filter(record -> record.getTypeOfWork().equals(typeOfWork))
				// Collect the stream to a list
				.collect(Collectors.toList());

	}

	// Creates a dataset
	private TimeSeriesCollection createGraphDataSet(List<? extends EmploymentRecord> filteredData) {
		// Create the timeseries
		TimeSeries bothGenders = new TimeSeries("Both Genders", Year.class);
		TimeSeries males = new TimeSeries("Males", Year.class);
		TimeSeries females = new TimeSeries("Females", Year.class);

		// Go through all the data sets
		for (EmploymentRecord record : filteredData) {
			// Add the record to the time series

			bothGenders.add(new Year(record.getYear()), record.getBothGenders());
			males.add(new Year(record.getYear()), record.getMales());
			females.add(new Year(record.getYear()), record.getFemales());
		}

		// Create the dataset
		TimeSeriesCollection dataSet = new TimeSeriesCollection();
		dataSet.addSeries(bothGenders);
		dataSet.addSeries(females);
		dataSet.addSeries(males);

		// return the dataset
		return dataSet;
	}
}
