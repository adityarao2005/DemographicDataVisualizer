package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartPanel;

import controller.DeathStatsController;

// ISMIT
public class DeathPanel extends JPanel {

	// CategoryDataset deathdata =
	// FileImportController.readDemographicRecordss(false);

	String[] months = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };

	String[] CHARS = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
			"T", "U", "V", "W", "X", "Y", "Z" };

	JPanel DeathPanel = new JPanel();
	JButton backButton = new JButton("Back");
	JButton applyButton = new JButton("Apply");
	JComboBox<String> monthComboBox = new JComboBox<>(months);
	JComboBox<String> cityLetterCombo = new JComboBox<>(CHARS);
	JTextField yearComboBox = new JTextField("2012");
	JLabel Title = new JLabel("DEATH DATA");
	JLabel yearTitle = new JLabel("Years (1940-2022)");
	JLabel monthTitle = new JLabel("Months");
	JLabel Filters = new JLabel("FILTERS");
	JLabel cityLabel = new JLabel("First Letter of the city");

	ChartPanel chartPanel = new ChartPanel(null);

	DeathStatsController controller;

	public DeathPanel() {
		super();

		setLayout(null);
		setVisible(true);
		setBounds(0, 0, 1000, 600);

		add(applyButton);
		applyButton.setVisible(true);
		applyButton.setBounds(800, 30, 80, 20);

		add(chartPanel);
		chartPanel.setVisible(true);
		chartPanel.setBounds(25, 120, 650, 400);
//		 chartPanel.setBackground(Color.black);

		add(Title);
		Title.setVisible(true);
		Title.setBounds(400, 25, 160, 18);
		Title.setFont(new Font("arial", Font.PLAIN, 24));

		add(backButton);
		backButton.setVisible(true);
		backButton.setBounds(45, 35, 80, 20);

		add(yearTitle);
		yearTitle.setVisible(true);
		yearTitle.setBounds(700, 170, 200, 15);
		yearTitle.setFont(new Font("arial", Font.PLAIN, 19));
		
		add(cityLabel);
		cityLabel.setBounds(700, 230, 250, 15);
		cityLabel.setFont(new Font("arial", Font.PLAIN, 19));

		add(monthTitle);
		monthTitle.setVisible(true);
		monthTitle.setBounds(870, 170, 65, 15);
		monthTitle.setFont(new Font("arial", Font.PLAIN, 19));

		add(Filters);
		Filters.setVisible(true);
		Filters.setBounds(742, 135, 90, 18);
		Filters.setFont(new Font("arial", Font.PLAIN, 19));

		add(monthComboBox);
		monthComboBox.setSelectedIndex(0);
		monthComboBox.setVisible(true);
		monthComboBox.setBounds(870, 195, 80, 19);

		add(yearComboBox);
		yearComboBox.setVisible(true);
		yearComboBox.setBounds(700, 195, 85, 19);
		
		
		add(cityLetterCombo);
		cityLetterCombo.setVisible(true);
		cityLetterCombo.setBounds(700, 255, 85, 19);
		
		JLabel background = new JLabel(new ImageIcon("images/background.png"));
		add(background);
		background.setBounds(0, 0, 1000, 600);

		controller = new DeathStatsController(this);

	}

	public String[] getMonths() {
		return months;
	}

	public void setMonths(String[] months) {
		this.months = months;
	}

	public JPanel getDeathPanel() {
		return DeathPanel;
	}

	public void setDeathPanel(JPanel deathPanel) {
		DeathPanel = deathPanel;
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

	public JComboBox<String> getMonthComboBox() {
		return monthComboBox;
	}

	public void setMonthComboBox(JComboBox<String> monthComboBox) {
		this.monthComboBox = monthComboBox;
	}
	
	public JComboBox<String> getCityLetterCombo() {
		return cityLetterCombo;
	}

	public void setCityLetterCombo(JComboBox<String> cityLetterCombo) {
		this.cityLetterCombo = cityLetterCombo;
	}

	public JTextField getYearComboBox() {
		return yearComboBox;
	}

	public void setYearComboBox(JTextField yearComboBox) {
		this.yearComboBox = yearComboBox;
	}

	public JLabel getTitle() {
		return Title;
	}

	public void setTitle(JLabel title) {
		Title = title;
	}

	public JLabel getYearTitle() {
		return yearTitle;
	}

	public void setYearTitle(JLabel yearTitle) {
		this.yearTitle = yearTitle;
	}

	public JLabel getMonthTitle() {
		return monthTitle;
	}

	public void setMonthTitle(JLabel monthTitle) {
		this.monthTitle = monthTitle;
	}

	public JLabel getFilters() {
		return Filters;
	}

	public void setFilters(JLabel filters) {
		Filters = filters;
	}

	public ChartPanel getChartPanel() {
		return chartPanel;
	}

	public void setChartPanel(ChartPanel chartPanel) {
		this.chartPanel = chartPanel;
	}

}