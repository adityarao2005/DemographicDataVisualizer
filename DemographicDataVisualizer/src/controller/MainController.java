package controller;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import view.BirthView;
import view.DeathPanel;
import view.EmploymentStatsView;
import view.HelpScreen;
import view.MarriageDataPanel;

import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JInternalFrame;

// ADITYA & JACK
public class MainController implements ActionListener {

	// FIELDS
	private JFrame frame;
	private JPanel homePanel;
	private JPanel[] navigationPanels = new JPanel[4];
	private JPanel menuPanel;
	private ImageIcon menuBackgroundImage;
	private JLabel menuBackground;
	private ImageIcon backgroundImage;
	private JLabel background;
	private JButton help;
	private HelpScreen helpScreen;

	// Main controller
	public MainController() {
		initialize();
	}

	// Initializes the frame
	private void initialize() {
		// Create the frame
		frame = new JFrame();
		frame.setSize(1000, 600);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Demographic Data Visualizer");

		// Create the main panel
		homePanel = new JPanel();
		homePanel.setLayout(null);

		// Create the menu image
		menuBackgroundImage = new ImageIcon("images/menuBackground.png");
		menuBackground = new JLabel(menuBackgroundImage);
		menuBackground.setBounds(1, 1, 238, 298);
		menuBackground.setOpaque(true);
		menuPanel = new JPanel();
		menuPanel.setBounds(380, 200, 240, 300);
		Border border = BorderFactory.createLineBorder(Color.black, 1);
		menuPanel.setBorder(border);
		menuPanel.setLayout(null);

		// Create the background image
		backgroundImage = new ImageIcon("images/background.png");
		background = new JLabel(backgroundImage);
		background.setBounds(0, 0, 1000, 600);
		background.setOpaque(true);

		// title label
		JLabel titleLabel = new JLabel("Canada lives Data Visualizer");
		titleLabel.setFont(new Font("MV Boli", Font.PLAIN, 40));
		titleLabel.setBounds(250, 40, 700, 100);
		homePanel.add(titleLabel);

		// Create the navigation panel
		JPanel navigationPanel = new JPanel();
		FlowLayout fl_navigationPanel = (FlowLayout) navigationPanel.getLayout();
		fl_navigationPanel.setHgap(20);
		navigationPanel.setBorder(new EmptyBorder(50, 0, 50, 0));

		// Create the buttons
		// Create the birth button
		JButton birthButton = new JButton("Show Birth Data");
		birthButton.addActionListener(this);
		birthButton.setActionCommand("0");
		navigationPanels[0] = new BirthView();
		((BirthView) navigationPanels[0]).getBack().addActionListener(this);
		birthButton.setBounds(35, 20, 170, 20);
		menuPanel.add(birthButton);

		// Create the death button
		JButton deathButton = new JButton("Show Death Data");
		deathButton.addActionListener(this);
		deathButton.setActionCommand("1");
		navigationPanels[1] = new DeathPanel();
		((DeathPanel) navigationPanels[1]).getBackButton().addActionListener(this);
		deathButton.setBounds(35, 70, 170, 20);
		menuPanel.add(deathButton);

		// Create the marriage button
		JButton marriageButton = new JButton("Show Marriage Data");
		marriageButton.addActionListener(this);
		marriageButton.setActionCommand("2");
		navigationPanels[2] = new MarriageDataPanel();
		((MarriageDataPanel) navigationPanels[2]).getBackButton().addActionListener(this);
		marriageButton.setBounds(35, 120, 170, 20);
		menuPanel.add(marriageButton);

		// Create the employment button
		JButton employmentButton = new JButton("Show Employment Data");
		employmentButton.addActionListener(this);
		employmentButton.setActionCommand("3");
		navigationPanels[3] = new EmploymentStatsView();
		((EmploymentStatsView) navigationPanels[3]).getBackButton().addActionListener(this);
		employmentButton.setBounds(35, 170, 170, 20);
		menuPanel.add(employmentButton);

		// Create the help button
		help = new JButton("Help");
		help.setActionCommand("Help");
		help.addActionListener(this);
		help.setBounds(35, 230, 170, 20);
		menuPanel.add(help);
		helpScreen = new HelpScreen();

		// Add the background and the panels
		menuPanel.add(menuBackground);
		frame.setContentPane(homePanel);

		// Add to the frame
		frame.add(menuPanel);
		frame.add(background);
		frame.setVisible(true);
	}

	// Handle button clicks
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Back")) {
			frame.setContentPane(homePanel);
		} else if (e.getActionCommand().equals("Help")) {
			helpScreen.setVisible(true);
		} else {
			int page = Integer.parseInt(e.getActionCommand());

			if (navigationPanels[page] != null) {
				frame.setContentPane(navigationPanels[page]);
			}
		}

		SwingUtilities.updateComponentTreeUI(frame);
	}

}