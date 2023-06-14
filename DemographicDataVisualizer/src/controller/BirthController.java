package controller;

import view.BirthView;
import model.BirthData;
import model.DemographicData;
import model.ImmigrationRecord;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xy.XYDataset;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.event.ChangeListener;

import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.ui.RectangleInsets;

// JACK
public class BirthController {

	// create new view
	private BirthView view;

	// view methods, for graphing
	public BirthController(BirthView view) {

		this.view = view;

		Graph();

	}

	// graph methods
	public void Graph() {

		// create a new JFreeChart
		JFreeChart chart = refreshData();
		
		chart.setBackgroundImage(new ImageIcon("images/background.png").getImage());
		XYPlot plot = chart.getXYPlot();
		DateAxis domain = new DateAxis("Date");
		domain.setDateFormatOverride(new SimpleDateFormat("MM-YYYY"));
		plot.setDomainAxis(domain);
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);

		XYItemRenderer r = plot.getRenderer();
		if (r instanceof XYLineAndShapeRenderer) {
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
			renderer.setBaseShapesVisible(true);
			renderer.setBaseShapesFilled(true);
		}

		view.getChartPanel().setChart(chart);
	}

	// filter data methods
	public List<DemographicData> getBirthData() {
		// read the data from the file
		List<DemographicData> data = FileImportController.readDemographicRecords(true);

		// Get the start and end year
		int startYear = Integer.parseInt(view.getStartYearField().getText());
		int endYear = Integer.parseInt(view.getEndYearField().getText());

		// get other data
		String city = (String) view.getLocationButton().getSelectedItem();

		return data.stream().filter(record -> record.getCity().equalsIgnoreCase(city)).filter(record -> {

			return record.getYear() >= startYear && record.getYear() <= endYear;
		}).collect(Collectors.toList());

	}

	// create data set methods
	private XYDataset createDataset() {
		// get the filted data
		List<DemographicData> data = getBirthData();
		// time in months
		TimeSeries dataset = new TimeSeries("Number of Births per Date", Month.class);
		// for loop, import the data into the data set
		for (DemographicData value : data) {
			// x is month-year, y is total value
			dataset.add(new TimeSeriesDataItem(new Month(value.getMonthInNumber(), value.getYear()), value.getTotal()));
		}

		// return
		return new TimeSeriesCollection(dataset);
	}

	// refresh method
	private JFreeChart refreshData() {

		// create the chart
		return ChartFactory.createXYAreaChart("Birth rate in years", "Year", "Birth", createDataset(), // dataset
				PlotOrientation.VERTICAL, // orientation
				true, // legend
				true, // tooltips
				false// urls
		);
	}

}