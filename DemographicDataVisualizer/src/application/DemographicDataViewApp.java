package application;

import view.SplashScreen;

/*
Name(s): Aditya Rao (27%), Jack Pengyue Xu (26%), Rhett Williams (25%), Ismit Akkush (22%)
Aditya
- EmploymentRecord class (100%)
- ImmigrationRecord class (100%)
- PartTimeEmploymentRecord class (100%)
- WageLevelRecord class (100%)
- DurationOfUnemploymentRecord class (100%)
- EmploymentStatsView class (100%)
- SplashScreen class (100%)
- MainController class (50%)
- HelpScreen class (100%)
Jack
- DemographicData class (50%)
- BirthData class (100%)
- BirthView class (100%)
- BirthController class (100%)
- MainController class (50%)
Rhett
- MaritalAgeData class (100%)
- MaritalStatusData class (100%)
- MarriageDataPanel class (100%)
- MarriageDataController class (100%)
Ismit
- DemographicData class (50%)
- DeathData class (100%)
- DeathPanel class (100%)
- MainController class (50%)
- DeathStatsController class (50%)


Date: May 28th, 2023
Course Code: ICS4UI-03, Mr.Fernandes
Title: Demographic Data Visualizer
Description: This app allows the user to view demographic data from Ontario's open data database.
Features:
 - Help screen
 - Splash Screen
 - Birth Data Screen
 - Death Data Screen
 - Marriage Data Screen
 - Employment Data Screen
Major Skills:
- OOP
- Filtering
- Collections API
- Stream API
- File IO
- Swing GUI + Layout Managers
- JFreeChart and using external API

Areas of Concern:
- For some datasets/screens, either error checking is not implemented or there is no data recorded for that time
 */
public class DemographicDataViewApp {

	// Runs the program
	public static void main(String[] args) throws Exception {
		new SplashScreen().loadApplication();
	}

}
