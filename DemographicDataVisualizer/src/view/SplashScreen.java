package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.FileImportController;
import controller.MainController;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;

// Aditya
// progress of loading all the files with multiple locks
public class SplashScreen extends JFrame implements Runnable {

	private JPanel contentPane;
	// create a lock
	private Object lock;
	private JProgressBar progressBar;

	private List<Runnable> actions;

	/**
	 * Create the frame.
	 * 
	 * @throws MalformedURLException
	 */
	public SplashScreen() throws Exception {
		setUndecorated(true);
		setBounds(new Rectangle(100, 100, 450, 450));
		lock = new Object();

		initGUI();

		// Creates the actions list
		actions = new ArrayList<>();

		// Add the lambda expressions
		actions.add(FileImportController::readWageRecords);
		actions.add(FileImportController::readPartTimeRecords);
		actions.add(FileImportController::readDurationRecords);
		actions.add(FileImportController::readMaritalAgeData);
		actions.add(FileImportController::readMaritalStatusData);
		actions.add(() -> FileImportController.readDemographicRecords(true));
		actions.add(() -> FileImportController.readDemographicRecords(false));
	}

	// Creates the GUI
	private void initGUI() throws Exception {
		// Sets the bounds
		setBounds(100, 100, 450, 450);

		// Creates the content for the splash screen
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Create the jlabel
		File background = new File("images/background.png");

		URL url = background.toURI().toURL();

		// Create title
		JEditorPane title = new JEditorPane();
		title.setEditable(false);
		title.setContentType("text/html");
		title.setText("<html><body style='background-image: url(" + url.toString()
				+ ");'><h1><bold><center>Demographic Data Viewer App</center></bold></h1></body></html>");
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(title, BorderLayout.CENTER);
		setContentPane(contentPane);

		// Create the bottom panel
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(Color.WHITE);

		// Add the progress label and progress bar to the panel
		JLabel progressLabel = new JLabel("Progress:");
		panel.add(progressLabel);
		progressBar = new JProgressBar(0, 100);
		panel.add(progressBar);

		// Set visible and nullFs
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}

	public void loadApplication() throws InterruptedException {
		// Create a new thread
		Thread thread = new Thread(this);

		thread.start();

		// Wait until object has been notified
		for (int i = 0; i < actions.size(); i++) {
			synchronized (lock) {
				lock.wait();
			}

			// Set the value of the progress bar
			progressBar.setValue(progressBar.getValue() + (100 / actions.size()));

		}

		// Set value to 100% done
		progressBar.setValue(100);

		// Wait for another few more seconds
		synchronized (lock) {
			lock.wait(200);
		}

		// Run this on completion
		onComplete();
	}

	@Override
	public void run() {
		// Run all the heavy tasks in a separate thread

		try {
			// Load all data before hand
			for (int i = 0; i < actions.size(); i++) {
				// run the action
				actions.get(i).run();

				// Unlock the lock
				synchronized (lock) {
					lock.notify();
				}

			}

		} catch (Exception e) {
			System.err.println("Could not wait");
			e.printStackTrace();
			return;
		}
	}

	// Runs this method when this has been completed
	private void onComplete() {
		// Dispose this window
		this.dispose();

		new MainController();
	}

}
