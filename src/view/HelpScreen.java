package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

// Aditya
// Help screen
public class HelpScreen extends JFrame implements ActionListener {

	// Fields
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton birthViewButton;
	private JRadioButton deathViewButton;
	private JRadioButton marriageViewButton;
	private JRadioButton employmentViewButton;
	private JLabel instructionImage;
	private JTextArea editorPane;

	// Help Data to be viewed
	private HashMap<String, String> helpDescriptions = new HashMap<>();
	private HashMap<String, ImageIcon> helpImages = new HashMap<>();
	private String[] keys = { "Birth View", "Death View", "Marriage View", "Employment View" };

	// Constructor
	public HelpScreen() {
		createUI();

		createData();
		
		actionPerformed(new ActionEvent(birthViewButton, 0, null));
	}

	// Scales the images
	private static ImageIcon scaleToImageLabel(ImageIcon icon) {
		return new ImageIcon(
				icon.getImage().getScaledInstance(385, 280, Image.SCALE_SMOOTH));
	}

	// Creates the data
	private void createData() {

		helpDescriptions.put(keys[0], "1) This is the graph which represents the number of births per year for a specific city\n 2) The domain of the graph has a start year and end year. This text field allows you to change the start year\n 3) This text field allows you to change the ned year\n 4) This combo box represents the city in which the birth rate is being graphed.\n 5) Press this button to reload or refresh the graph with new data\n6) Goes back to the home screen");
		helpImages.put(keys[0], scaleToImageLabel(new ImageIcon("images/helpImage-B.png")));

		helpDescriptions.put(keys[1], "1) This pie chart depicts the comparison of number of deaths per city with a specific leading character. For example, if we choose the letter 'T' then all cities which start with the letter 'T' will be graphed.\n 2) This is the reload/refresh button. Click this to refresh the graph.\n 3) This textfield looks at the year in which the comparison of death rates occurs.\n 4) This combobox looks at the month in which the comparison occurs.\n5) Selects the first letter to be looked at\n6) Goes back to the home screen");
		helpImages.put(keys[1], scaleToImageLabel(new ImageIcon("images/helpImage-D.png")));

		helpDescriptions.put(keys[2], "1) This bar graph shows 2 sets of data: 1 which is the status of the marriages, 2 the ages at which people married.\n2) This selects teh start year\n3) This selects the end year\n4) This selects the graph for either spouse 1 or 2\n 5) This is the reload/refresh button\n 6) This changes the graph to either statuses or ages\n 7) Goes back to the home screen ");
		helpImages.put(keys[2], scaleToImageLabel(new ImageIcon("images/helpImage-M.png")));

		helpDescriptions.put(keys[3], "1) This line graph shows the employment/unemployment rate over time for males, females, and both genders\n 2) These comboboxes allow to select specific filters regarding the current data which is being graphed (to change, click the tab as desired)\n 3) These are the general combobox filters which have varying datasets as well such as different locations and age groups for the data recorded\n 4) These filter out the domain of the graph, the start and end year.\n 5) This reloads/refreshes the graph\n 6) Goes back to home screen");
		helpImages.put(keys[3], scaleToImageLabel(new ImageIcon("images/helpImage-E.png")));

	}

	// Creates the UI
	private void createUI() {
		// Create the defaults for the frame
		setTitle("Help");
		setResizable(false);
		setBounds(100, 100, 935, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Set the content and layout
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// Create the info page
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(Color.CYAN);
		contentPane.add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);

		// Create the label to hold the images for instructions
		instructionImage = new JLabel("");
		instructionImage.setBackground(new Color(255, 255, 255));
		sl_contentPanel.putConstraint(SpringLayout.NORTH, instructionImage, 20, SpringLayout.NORTH, contentPanel);
		instructionImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		sl_contentPanel.putConstraint(SpringLayout.WEST, instructionImage, 111, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, instructionImage, 300, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, instructionImage, -111, SpringLayout.EAST, contentPanel);
		contentPanel.add(instructionImage);

		// Create the textarea for descriptions and instructions
		editorPane = new JTextArea();
		editorPane.setEditable(false);
		
		// Add it to scrollpane and layout
		JScrollPane sp = new JScrollPane(editorPane);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, sp, 10, SpringLayout.SOUTH, instructionImage);
		sl_contentPanel.putConstraint(SpringLayout.WEST, sp, 0, SpringLayout.WEST, instructionImage);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, sp, -10, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, sp, 0, SpringLayout.EAST, instructionImage);
		contentPanel.add(sp);

		// Create the selection panel
		JPanel selectionPanel = new JPanel();
		selectionPanel.setPreferredSize(new Dimension(300, 400));
		contentPane.add(selectionPanel, BorderLayout.WEST);
		SpringLayout sl_selectionPanel = new SpringLayout();
		selectionPanel.setLayout(sl_selectionPanel);

		// Create the title for the page
		JLabel titleLabel = new JLabel("Select Page for Help");
		sl_selectionPanel.putConstraint(SpringLayout.EAST, titleLabel, -20, SpringLayout.EAST, selectionPanel);
		titleLabel.setOpaque(true);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		sl_selectionPanel.putConstraint(SpringLayout.NORTH, titleLabel, 10, SpringLayout.NORTH, selectionPanel);
		sl_selectionPanel.putConstraint(SpringLayout.WEST, titleLabel, 60, SpringLayout.WEST, selectionPanel);
		selectionPanel.add(titleLabel);

		// Create the radio buttons
		// Create the first radiobutton
		birthViewButton = new JRadioButton(keys[0]);
		birthViewButton.addActionListener(this);
		birthViewButton.setSelected(true);
		buttonGroup.add(birthViewButton);
		sl_selectionPanel.putConstraint(SpringLayout.NORTH, birthViewButton, 5, SpringLayout.SOUTH, titleLabel);
		birthViewButton.setBackground(new Color(255, 255, 255));
		sl_selectionPanel.putConstraint(SpringLayout.WEST, birthViewButton, 60, SpringLayout.WEST, selectionPanel);
		selectionPanel.add(birthViewButton);

		// Create the second radio button
		deathViewButton = new JRadioButton(keys[1]);
		deathViewButton.addActionListener(this);
		buttonGroup.add(deathViewButton);
		sl_selectionPanel.putConstraint(SpringLayout.NORTH, deathViewButton, 5, SpringLayout.SOUTH, birthViewButton);
		sl_selectionPanel.putConstraint(SpringLayout.WEST, deathViewButton, 60, SpringLayout.WEST, selectionPanel);
		selectionPanel.add(deathViewButton);

		// Create the third radio button
		marriageViewButton = new JRadioButton(keys[2]);
		marriageViewButton.addActionListener(this);
		buttonGroup.add(marriageViewButton);
		sl_selectionPanel.putConstraint(SpringLayout.NORTH, marriageViewButton, 5, SpringLayout.SOUTH, deathViewButton);
		sl_selectionPanel.putConstraint(SpringLayout.WEST, marriageViewButton, 60, SpringLayout.WEST, selectionPanel);
		selectionPanel.add(marriageViewButton);

		// Create the fourth radio button
		employmentViewButton = new JRadioButton(keys[3]);
		employmentViewButton.addActionListener(this);
		buttonGroup.add(employmentViewButton);
		sl_selectionPanel.putConstraint(SpringLayout.NORTH, employmentViewButton, 5, SpringLayout.SOUTH,
				marriageViewButton);
		sl_selectionPanel.putConstraint(SpringLayout.WEST, employmentViewButton, 60, SpringLayout.WEST, selectionPanel);
		selectionPanel.add(employmentViewButton);

		// Create the background image
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon("images/menuBackground.png"));
		sl_selectionPanel.putConstraint(SpringLayout.NORTH, backgroundImage, 0, SpringLayout.NORTH, selectionPanel);
		sl_selectionPanel.putConstraint(SpringLayout.WEST, backgroundImage, 0, SpringLayout.WEST, selectionPanel);
		sl_selectionPanel.putConstraint(SpringLayout.SOUTH, backgroundImage, 0, SpringLayout.SOUTH, selectionPanel);
		sl_selectionPanel.putConstraint(SpringLayout.EAST, backgroundImage, 0, SpringLayout.EAST, selectionPanel);
		selectionPanel.add(backgroundImage);
	}

	// Listen to the button clicks
	@Override
	public void actionPerformed(ActionEvent e) {
		// Get the key
		String key = ((JRadioButton) e.getSource()).getText();

		// Set the values
		editorPane.setText(helpDescriptions.get(key));
		instructionImage.setIcon(helpImages.get(key));
	}

}
