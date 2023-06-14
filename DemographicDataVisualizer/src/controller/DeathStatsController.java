package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import model.DemographicData;
import view.DeathPanel;

// ISMIT
public class DeathStatsController implements ActionListener {

	public DeathPanel panel;

	public DeathStatsController(DeathPanel panel) {
		this.panel = panel;

		panel.getApplyButton().addActionListener(this);

		actionPerformed(null);

	}

	// Method #1: Filter out data
	// Get data in form of list from fileimportcontroller
	// Create another list and loop through the data to find all the records
	// that
	// satisfy the conditions from our filters
	// Return the new filtered list
	public List<DemographicData> filterData() {

		List<DemographicData> data = FileImportController.readDemographicRecords(false);

		List<DemographicData> filter = new ArrayList<>();

		for (DemographicData record : data) {

			if (record.getYear() == Integer.parseInt(panel.getYearComboBox().getText())
					&& record.getMonth().toUpperCase()
							.equals(((String) panel.getMonthComboBox().getSelectedItem()).toUpperCase())
					&& String.valueOf(record.getCity().charAt(0)).toUpperCase()
							.equals((String) panel.getCityLetterCombo().getSelectedItem())) {

				filter.add(record);

			}

		}

		return filter;

	}

	// Method #2: Create our dataset
	// Turn the List into a categorydataset (not by casting but by looping)
	// Return the dataset
	private PieDataset createDataset(List<DemographicData> list) {
		DefaultPieDataset dataset = new DefaultPieDataset();

		for (DemographicData number : list) {

			dataset.setValue(number.getCity(), number.getTotal());

		}

		return dataset;
	}

	// Method #3: Creates the jfreechart
	// Create the jfreechart using the dataset - refer to exemplar

	public void chart() {

		JFreeChart chart = ChartFactory.createPieChart("Death Rates in Ontario", createDataset(filterData()), true,
				true, false);

		panel.getChartPanel().setChart(chart);

	}

	// Method #5: actionPerformed for the button -> update graph (method #4)
	public void actionPerformed(ActionEvent e) {
		chart();
	}

}