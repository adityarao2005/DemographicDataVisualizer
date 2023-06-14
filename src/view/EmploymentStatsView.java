package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import org.jfree.chart.ChartPanel;

import controller.EmploymentStatsController;
import model.DurationOfUnemploymentRecord;
import model.ImmigrationRecord;
import model.PartTimeEmploymentRecord;
import model.WageLevelRecord;

import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Font;

import javax.swing.border.BevelBorder;

// ADITYA
// Displays the employment statistics view
public class EmploymentStatsView extends JPanel {
	// Fields

	// UI Components
	private ChartPanel graph;
	// Tabbed Panes
	private JTabbedPane filterPane;
	// Text Fields
	private JTextField startDateField;
	private JTextField endDateField;
	// Buttons
	private JButton reloadButton;
	private JButton backButton;
	// Combo Boxes
	private JComboBox<String> locationCombo;
	private JComboBox<String> ageGroupCombo;
	private JComboBox<String> wageCombo;
	private JComboBox<String> levelCombo;
	private JComboBox<String> workCombo;
	private JComboBox<String> statusCombo;
	private JComboBox<String> reasonCombo;
	private JComboBox<String> statusCombo_1;
	private JComboBox<String> durationCombo;

	// Constants
	public static final String PART_TIME_TAB_NAME = "Part Time Work of Immigrants";
	public static final String DURATION_TAB_NAME = "Duration of Unemployment";
	public static final String WAGES_TAB_NAME = "Wages Based on Education Level";

	// Controller
	private EmploymentStatsController controller;

	/**
	 * Create the panel.
	 */
	public EmploymentStatsView() {
		// Initializes the views
		initView();

		// Create the controller
		controller = new EmploymentStatsController(this);
	}

	// Creates the view
	private void initView() {
		// Sets layout basis
		setPreferredSize(new Dimension(1000, 600));
		setBackground(new Color(0, 255, 255));
		setLayout(new BorderLayout(0, 0));

		// Calls the createContent method
		createContentPanel();

		// Calls the createGraph method
		createGraph();
	}

	// UI Creation
	// Creates the filtering controls
	private void createContentPanel() {
		// Creates the content
		JPanel contentPanel = new JPanel();
		add(contentPanel, BorderLayout.EAST);
		contentPanel.setLayout(new BorderLayout(0, 0));

		// Creates the general controls
		createGeneralControls(contentPanel);

		// Create the tabbed controls
		createTabbedControls(contentPanel);
	}

	// Creates all the general content for filtering
	private void createGeneralControls(JPanel contentPanel) {
		// Create the general panel
		JPanel generalControlsPanel = new JPanel();
		generalControlsPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		generalControlsPanel.setPreferredSize(new Dimension(400, 200));
		generalControlsPanel.setBackground(new Color(255, 255, 255));
		// Assign layout and positions
		contentPanel.add(generalControlsPanel, BorderLayout.SOUTH);
		SpringLayout sl_generalControlsPanel = new SpringLayout();
		generalControlsPanel.setLayout(sl_generalControlsPanel);

		// Create the title label
		JLabel generalLabel = new JLabel("General Filters");
		sl_generalControlsPanel.putConstraint(SpringLayout.WEST, generalLabel, 100, SpringLayout.WEST,
				generalControlsPanel);
		generalLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		generalControlsPanel.add(generalLabel);

		// Create the location labels
		JLabel locationLabel = new JLabel("Location:");
		sl_generalControlsPanel.putConstraint(SpringLayout.WEST, locationLabel, 6, SpringLayout.WEST,
				generalControlsPanel);
		generalControlsPanel.add(locationLabel);

		// Create the location comboboxes
		locationCombo = new JComboBox<>();
		sl_generalControlsPanel.putConstraint(SpringLayout.SOUTH, generalLabel, -6, SpringLayout.NORTH, locationCombo);
		sl_generalControlsPanel.putConstraint(SpringLayout.WEST, locationCombo, 67, SpringLayout.WEST,
				generalControlsPanel);
		generalControlsPanel.add(locationCombo);

		// Crete the age group label
		JLabel ageGroupLabel = new JLabel("Age Group:");
		sl_generalControlsPanel.putConstraint(SpringLayout.NORTH, ageGroupLabel, 10, SpringLayout.SOUTH, generalLabel);
		sl_generalControlsPanel.putConstraint(SpringLayout.EAST, locationCombo, -6, SpringLayout.WEST, ageGroupLabel);
		generalControlsPanel.add(ageGroupLabel);

		// Create the age group combobox
		ageGroupCombo = new JComboBox<>();
		sl_generalControlsPanel.putConstraint(SpringLayout.EAST, ageGroupCombo, -10, SpringLayout.EAST,
				generalControlsPanel);
		sl_generalControlsPanel.putConstraint(SpringLayout.EAST, ageGroupLabel, -6, SpringLayout.WEST, ageGroupCombo);
		generalControlsPanel.add(ageGroupCombo);

		// Create the start date label
		JLabel startDateLabel = new JLabel("Start Year:");
		sl_generalControlsPanel.putConstraint(SpringLayout.WEST, startDateLabel, 6, SpringLayout.WEST,
				generalControlsPanel);
		sl_generalControlsPanel.putConstraint(SpringLayout.SOUTH, locationLabel, -22, SpringLayout.NORTH,
				startDateLabel);
		generalControlsPanel.add(startDateLabel);

		// Create the end date labels
		JLabel endDateLabel = new JLabel("End Year:");
		sl_generalControlsPanel.putConstraint(SpringLayout.NORTH, endDateLabel, 0, SpringLayout.NORTH, startDateLabel);
		generalControlsPanel.add(endDateLabel);

		// Create the start date text field
		startDateField = new JTextField();
		sl_generalControlsPanel.putConstraint(SpringLayout.WEST, endDateLabel, 6, SpringLayout.EAST, startDateField);
		sl_generalControlsPanel.putConstraint(SpringLayout.WEST, startDateField, 6, SpringLayout.EAST, startDateLabel);
		sl_generalControlsPanel.putConstraint(SpringLayout.NORTH, startDateLabel, 2, SpringLayout.NORTH,
				startDateField);
		sl_generalControlsPanel.putConstraint(SpringLayout.NORTH, startDateField, 75, SpringLayout.NORTH,
				generalControlsPanel);
		sl_generalControlsPanel.putConstraint(SpringLayout.SOUTH, locationCombo, -15, SpringLayout.NORTH,
				startDateField);
		startDateField.setText("0");
		generalControlsPanel.add(startDateField);
		startDateField.setColumns(10);

		// Create the end Date text field
		endDateField = new JTextField();
		sl_generalControlsPanel.putConstraint(SpringLayout.WEST, endDateField, 6, SpringLayout.EAST, endDateLabel);
		sl_generalControlsPanel.putConstraint(SpringLayout.EAST, endDateField, -10, SpringLayout.EAST,
				generalControlsPanel);
		sl_generalControlsPanel.putConstraint(SpringLayout.NORTH, endDateField, 77, SpringLayout.NORTH,
				generalControlsPanel);
		sl_generalControlsPanel.putConstraint(SpringLayout.SOUTH, ageGroupCombo, -17, SpringLayout.NORTH, endDateField);
		endDateField.setText("2023");
		endDateField.setColumns(10);
		generalControlsPanel.add(endDateField);

		// Create the reload button
		reloadButton = new JButton("Reload");
		sl_generalControlsPanel.putConstraint(SpringLayout.NORTH, reloadButton, 16, SpringLayout.SOUTH, startDateField);
		sl_generalControlsPanel.putConstraint(SpringLayout.WEST, reloadButton, 6, SpringLayout.WEST,
				generalControlsPanel);
		generalControlsPanel.add(reloadButton);

		// Create the back button
		backButton = new JButton("Back");
		sl_generalControlsPanel.putConstraint(SpringLayout.SOUTH, backButton, 0, SpringLayout.SOUTH, reloadButton);
		sl_generalControlsPanel.putConstraint(SpringLayout.EAST, backButton, -10, SpringLayout.EAST,
				generalControlsPanel);
		generalControlsPanel.add(backButton);

	}

	// Creates the tabbed controls
	private void createTabbedControls(JPanel contentPanel) {

		// Create the tabbed pane
		filterPane = new JTabbedPane(JTabbedPane.TOP);
		contentPanel.add(filterPane, BorderLayout.CENTER);

		// Creates the tabs
		createWagesTab(filterPane);
		createPartTimeTab(filterPane);
		createDurationTab(filterPane);
	}

	// Creates the wages pane
	private void createWagesTab(JTabbedPane filterPane) {
		// Create the wages panel
		JPanel wagesPanel = new JPanel();
		wagesPanel.setBackground(new Color(255, 255, 255));
		filterPane.addTab(WAGES_TAB_NAME, null, wagesPanel, null);
		SpringLayout sl_wagesPanel = new SpringLayout();
		wagesPanel.setLayout(sl_wagesPanel);

		// Create the level label
		JLabel levelLabel = new JLabel("Education level:");
		wagesPanel.add(levelLabel);

		// Create the wages combo box
		wageCombo = new JComboBox<>();
		sl_wagesPanel.putConstraint(SpringLayout.EAST, wageCombo, -10, SpringLayout.EAST, wagesPanel);
		sl_wagesPanel.putConstraint(SpringLayout.NORTH, levelLabel, 6, SpringLayout.SOUTH, wageCombo);
		wageCombo.setModel(new DefaultComboBoxModel<>(WageLevelRecord.WAGES));
		wagesPanel.add(wageCombo);

		// Create the level combo box
		levelCombo = new JComboBox<>();
		sl_wagesPanel.putConstraint(SpringLayout.NORTH, levelCombo, -4, SpringLayout.NORTH, levelLabel);
		sl_wagesPanel.putConstraint(SpringLayout.WEST, levelCombo, 5, SpringLayout.EAST, levelLabel);
		levelCombo.setModel(new DefaultComboBoxModel<>(WageLevelRecord.LEVELS_OF_EDUCATION));
		wagesPanel.add(levelCombo);

		// Create the wages label
		JLabel wageLabel = new JLabel("Wages:");
		sl_wagesPanel.putConstraint(SpringLayout.WEST, levelLabel, 0, SpringLayout.WEST, wageLabel);
		sl_wagesPanel.putConstraint(SpringLayout.NORTH, wageCombo, -4, SpringLayout.NORTH, wageLabel);
		sl_wagesPanel.putConstraint(SpringLayout.WEST, wageCombo, 6, SpringLayout.EAST, wageLabel);
		sl_wagesPanel.putConstraint(SpringLayout.NORTH, wageLabel, 133, SpringLayout.NORTH, wagesPanel);
		sl_wagesPanel.putConstraint(SpringLayout.WEST, wageLabel, 10, SpringLayout.WEST, wagesPanel);
		wagesPanel.add(wageLabel);

		// Create the title pane
		JTextPane wagesTitlePane = new JTextPane();
		sl_wagesPanel.putConstraint(SpringLayout.EAST, levelCombo, 0, SpringLayout.EAST, wagesTitlePane);
		sl_wagesPanel.putConstraint(SpringLayout.SOUTH, wagesTitlePane, 122, SpringLayout.NORTH, wagesPanel);
		sl_wagesPanel.putConstraint(SpringLayout.WEST, wagesTitlePane, 10, SpringLayout.WEST, wagesPanel);
		sl_wagesPanel.putConstraint(SpringLayout.EAST, wagesTitlePane, -10, SpringLayout.EAST, wagesPanel);
		sl_wagesPanel.putConstraint(SpringLayout.NORTH, wagesTitlePane, 10, SpringLayout.NORTH, wagesPanel);
		wagesTitlePane.setText("Wages based on Education Level");
		wagesTitlePane.setFont(new Font("Dialog", Font.BOLD, 25));
		wagesPanel.add(wagesTitlePane);

		// Create the type of work label
		JLabel workLabel = new JLabel("Type of Work:");
		sl_wagesPanel.putConstraint(SpringLayout.NORTH, workLabel, 13, SpringLayout.SOUTH, levelLabel);
		sl_wagesPanel.putConstraint(SpringLayout.WEST, workLabel, 0, SpringLayout.WEST, levelLabel);
		wagesPanel.add(workLabel);

		// Create the type of work combo box
		workCombo = new JComboBox<>();
		sl_wagesPanel.putConstraint(SpringLayout.NORTH, workCombo, 6, SpringLayout.SOUTH, levelCombo);
		sl_wagesPanel.putConstraint(SpringLayout.WEST, workCombo, 6, SpringLayout.EAST, workLabel);
		sl_wagesPanel.putConstraint(SpringLayout.EAST, workCombo, -10, SpringLayout.EAST, wagesPanel);
		workCombo.setModel(new DefaultComboBoxModel<>(WageLevelRecord.TYPES_OF_WORK));
		wagesPanel.add(workCombo);
	}

	// Creates the part time tab
	private void createPartTimeTab(JTabbedPane filterPane) {
		// Create the part time panel
		JPanel partTimePanel = new JPanel();
		partTimePanel.setBackground(new Color(255, 255, 255));
		filterPane.addTab(PART_TIME_TAB_NAME, null, partTimePanel, null);
		SpringLayout sl_partTimePanel = new SpringLayout();
		partTimePanel.setLayout(sl_partTimePanel);

		// Create the reasons title
		JTextPane reasonsTitlePane = new JTextPane();
		reasonsTitlePane.setFont(new Font("Dialog", Font.BOLD, 25));
		sl_partTimePanel.putConstraint(SpringLayout.NORTH, reasonsTitlePane, 10, SpringLayout.NORTH, partTimePanel);
		sl_partTimePanel.putConstraint(SpringLayout.WEST, reasonsTitlePane, 10, SpringLayout.WEST, partTimePanel);
		sl_partTimePanel.putConstraint(SpringLayout.SOUTH, reasonsTitlePane, 85, SpringLayout.NORTH, partTimePanel);
		reasonsTitlePane.setText("Part Time work based on Immigration Status");
		partTimePanel.add(reasonsTitlePane);

		// Create the immigration status label
		JLabel statusLabel = new JLabel("Immigration Status:");
		sl_partTimePanel.putConstraint(SpringLayout.NORTH, statusLabel, 34, SpringLayout.SOUTH, reasonsTitlePane);
		sl_partTimePanel.putConstraint(SpringLayout.WEST, statusLabel, 6, SpringLayout.WEST, partTimePanel);
		sl_partTimePanel.putConstraint(SpringLayout.EAST, statusLabel, -274, SpringLayout.EAST, partTimePanel);
		partTimePanel.add(statusLabel);

		// Create the immigration status combobox
		statusCombo = new JComboBox<>();
		sl_partTimePanel.putConstraint(SpringLayout.NORTH, statusCombo, 34, SpringLayout.SOUTH, reasonsTitlePane);
		sl_partTimePanel.putConstraint(SpringLayout.EAST, reasonsTitlePane, 0, SpringLayout.EAST, statusCombo);
		sl_partTimePanel.putConstraint(SpringLayout.WEST, statusCombo, 6, SpringLayout.EAST, statusLabel);
		sl_partTimePanel.putConstraint(SpringLayout.EAST, statusCombo, -10, SpringLayout.EAST, partTimePanel);
		statusCombo.setModel(new DefaultComboBoxModel<>(ImmigrationRecord.STATUSES));
		partTimePanel.add(statusCombo);

		// Create the reason label
		JLabel reasonLabel = new JLabel("Reason:");
		sl_partTimePanel.putConstraint(SpringLayout.WEST, reasonLabel, 10, SpringLayout.WEST, partTimePanel);
		partTimePanel.add(reasonLabel);

		// Create the reason combo box
		reasonCombo = new JComboBox<>();
		sl_partTimePanel.putConstraint(SpringLayout.NORTH, reasonCombo, 153, SpringLayout.NORTH, partTimePanel);
		sl_partTimePanel.putConstraint(SpringLayout.SOUTH, statusLabel, -9, SpringLayout.NORTH, reasonCombo);
		sl_partTimePanel.putConstraint(SpringLayout.EAST, reasonCombo, -10, SpringLayout.EAST, partTimePanel);
		sl_partTimePanel.putConstraint(SpringLayout.NORTH, reasonLabel, 3, SpringLayout.NORTH, reasonCombo);
		sl_partTimePanel.putConstraint(SpringLayout.WEST, reasonCombo, 65, SpringLayout.WEST, partTimePanel);
		reasonCombo.setModel(new DefaultComboBoxModel<>(PartTimeEmploymentRecord.REASONS));
		partTimePanel.add(reasonCombo);
	}

	// Creates the duration tab
	private void createDurationTab(JTabbedPane filterPane) {

		// Creates the duration panel
		JPanel durationOfUnemployment = new JPanel();
		durationOfUnemployment.setBackground(new Color(255, 255, 255));
		filterPane.addTab(DURATION_TAB_NAME, null, durationOfUnemployment, null);
		SpringLayout sl_durationOfUnemployment = new SpringLayout();
		durationOfUnemployment.setLayout(sl_durationOfUnemployment);

		// Create the immigration status label
		JLabel statusLabel_1 = new JLabel("Immigration Status:");
		sl_durationOfUnemployment.putConstraint(SpringLayout.WEST, statusLabel_1, 10, SpringLayout.WEST,
				durationOfUnemployment);
		durationOfUnemployment.add(statusLabel_1);

		// Create the immigration status combo box
		statusCombo_1 = new JComboBox<>();
		sl_durationOfUnemployment.putConstraint(SpringLayout.NORTH, statusCombo_1, -4, SpringLayout.NORTH,
				statusLabel_1);
		sl_durationOfUnemployment.putConstraint(SpringLayout.WEST, statusCombo_1, 6, SpringLayout.EAST, statusLabel_1);
		durationOfUnemployment.add(statusCombo_1);
		statusCombo_1.setModel(new DefaultComboBoxModel<>(ImmigrationRecord.STATUSES));

		// Create the duration combobox
		durationCombo = new JComboBox<>();
		sl_durationOfUnemployment.putConstraint(SpringLayout.SOUTH, statusLabel_1, -6, SpringLayout.NORTH,
				durationCombo);
		sl_durationOfUnemployment.putConstraint(SpringLayout.NORTH, durationCombo, 155, SpringLayout.NORTH,
				durationOfUnemployment);
		sl_durationOfUnemployment.putConstraint(SpringLayout.EAST, durationCombo, 0, SpringLayout.EAST, statusCombo_1);
		sl_durationOfUnemployment.putConstraint(SpringLayout.WEST, durationCombo, 69, SpringLayout.WEST,
				durationOfUnemployment);
		durationCombo.setModel(new DefaultComboBoxModel<>(DurationOfUnemploymentRecord.DURATIONS));
		durationOfUnemployment.add(durationCombo);

		// Create the duration label
		JLabel durationLabel = new JLabel("Duration:");
		sl_durationOfUnemployment.putConstraint(SpringLayout.NORTH, durationLabel, 10, SpringLayout.SOUTH,
				statusLabel_1);
		sl_durationOfUnemployment.putConstraint(SpringLayout.WEST, durationLabel, 10, SpringLayout.WEST,
				durationOfUnemployment);
		durationOfUnemployment.add(durationLabel);

		// Create the durations title
		JTextPane durationsTitlePane = new JTextPane();
		sl_durationOfUnemployment.putConstraint(SpringLayout.EAST, statusCombo_1, 0, SpringLayout.EAST,
				durationsTitlePane);
		sl_durationOfUnemployment.putConstraint(SpringLayout.NORTH, durationsTitlePane, 10, SpringLayout.NORTH,
				durationOfUnemployment);
		sl_durationOfUnemployment.putConstraint(SpringLayout.WEST, durationsTitlePane, 10, SpringLayout.WEST,
				durationOfUnemployment);
		sl_durationOfUnemployment.putConstraint(SpringLayout.SOUTH, durationsTitlePane, -16, SpringLayout.NORTH,
				statusLabel_1);
		sl_durationOfUnemployment.putConstraint(SpringLayout.EAST, durationsTitlePane, -10, SpringLayout.EAST,
				durationOfUnemployment);
		durationsTitlePane.setText("Duration of Unemployment based on Immigration Status");
		durationsTitlePane.setFont(new Font("Dialog", Font.BOLD, 25));
		durationOfUnemployment.add(durationsTitlePane);

	}

	// Creates the TimeSeries Graph
	private void createGraph() {

		// Create the chart
		graph = new ChartPanel(null);
		graph.setBackground(new Color(0, 255, 255));

		// add the chart to the panel
		add(graph, BorderLayout.CENTER);

	}

	// Getters and Setters
	public JComboBox<String> getLocationCombo() {
		return locationCombo;
	}

	public void setLocationCombo(JComboBox<String> locationCombo) {
		this.locationCombo = locationCombo;
	}

	public JComboBox<String> getAgeGroupCombo() {
		return ageGroupCombo;
	}

	public void setAgeGroupCombo(JComboBox<String> ageGroupCombo) {
		this.ageGroupCombo = ageGroupCombo;
	}

	public JTextField getStartDateField() {
		return startDateField;
	}

	public void setStartDateField(JTextField startDateField) {
		this.startDateField = startDateField;
	}

	public JTextField getEndDateField() {
		return endDateField;
	}

	public void setEndDateField(JTextField endDateField) {
		this.endDateField = endDateField;
	}

	public JButton getReloadButton() {
		return reloadButton;
	}

	public void setReloadButton(JButton reloadButton) {
		this.reloadButton = reloadButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}

	public JTabbedPane getFilterPane() {
		return filterPane;
	}

	public void setFilterPane(JTabbedPane filterPane) {
		this.filterPane = filterPane;
	}

	public JComboBox<String> getWageCombo() {
		return wageCombo;
	}

	public void setWageCombo(JComboBox<String> wageCombo) {
		this.wageCombo = wageCombo;
	}

	public JComboBox<String> getLevelCombo() {
		return levelCombo;
	}

	public void setLevelCombo(JComboBox<String> levelCombo) {
		this.levelCombo = levelCombo;
	}

	public JComboBox<String> getWorkCombo() {
		return workCombo;
	}

	public void setWorkCombo(JComboBox<String> workCombo) {
		this.workCombo = workCombo;
	}

	public JComboBox<String> getStatusCombo() {
		return statusCombo;
	}

	public void setStatusCombo(JComboBox<String> statusCombo) {
		this.statusCombo = statusCombo;
	}

	public JComboBox<String> getReasonCombo() {
		return reasonCombo;
	}

	public void setReasonCombo(JComboBox<String> reasonCombo) {
		this.reasonCombo = reasonCombo;
	}

	public JComboBox<String> getStatusCombo_1() {
		return statusCombo_1;
	}

	public void setStatusCombo_1(JComboBox<String> statusCombo_1) {
		this.statusCombo_1 = statusCombo_1;
	}

	public JComboBox<String> getDurationCombo() {
		return durationCombo;
	}

	public void setDurationCombo(JComboBox<String> durationCombo) {
		this.durationCombo = durationCombo;
	}

	public ChartPanel getGraph() {
		return graph;
	}

	public void setGraph(ChartPanel graph) {
		this.graph = graph;
	}

	public EmploymentStatsController getController() {
		return controller;
	}

	public void setController(EmploymentStatsController controller) {
		this.controller = controller;
	}

}
