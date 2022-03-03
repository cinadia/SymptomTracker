package ui;

import model.LogHistory;
import model.entries.Entry;
import model.entries.Remedy;
import model.entries.Symptom;
import model.logs.RemedyLog;
import model.logs.SymptomLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Symmer symptom tracker application
 */
public class SymmerApp {
    private static final String JSON_STORE = "./data/loghistory.json";

    private SymptomLog symLog;
    private RemedyLog remLog;
    private Scanner input;

    private LogHistory lh;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // TODO: change these to enum?
    private static final String DATE_FORMAT = "YYYY-MM-DD";
    private static final ArrayList<String> LOCATIONS = new ArrayList<String>() {
        {
            add("head");
            add("eye");
            add("ear");
            add("jaw");
            add("teeth");
            add("neck");
            add("shoulder");
            add("chest");
            add("upper back");
            add("middle back");
            add("lower back");
            add("abdomen");
            add("arm");
            add("elbow");
            add("wrist");
            add("hand");
            add("finger");
            add("hip");
            add("pelvis");
            add("genitals");
            add("thigh");
            add("calf");
            add("ankle");
            add("foot");
            add("toe");
        }
    };
    private static final ArrayList<String> SENSATIONS = new ArrayList<String>() {
        {
            add("sore");
            add("stiff");
            add("sharp");
            add("throb");
            add("ache");
            add("stab");
            add("numb");
            add("tingle");
            add("burning");
            add("sting");
            add("cramp");
            add("shooting");
            add("itchy");
            add("swelling");
            add("hot");
            add("cold");
            add("spasm");
        }
    };
    private static final ArrayList<Integer> SEVERITY = new ArrayList<Integer>() {
        {
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }
    };
    private static final ArrayList<String> REMEDIES = new ArrayList<String>() {
        {
            add("hot pack");
            add("cold pack");
            add("massage");
            add("stretch");
            add("painkillers");
            add("prescription medicine");
            add("yoga");
            add("exercise");
            add("meditation");
            add("acupuncture");
            add("physiotherapy");
            add("chiropractor");
            add("traditional medicine");
            add("surgery or medical procedure");
        }
    };




    // EFFECTS: runs the Symmer application
    public SymmerApp() {
        init();
        runSymmer();

    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runSymmer() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        saveLogHistory();
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes logs
    private void init() {
        System.out.println("Welcome to Symmer!");
        symLog = new SymptomLog();
        remLog = new RemedyLog();
        input = new Scanner(System.in);
        //lh = new LogHistory("Your Log History");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tload -> log history from file");

        System.out.println("\tadd sym -> add symptom entry");
        System.out.println("\tadd rem -> add remedy entry");

        System.out.println("\tedit sym -> edit symptom entry");
        System.out.println("\tedit rem -> edit remedy entry");

        System.out.println("\tdel sym -> delete symptom entry");
        System.out.println("\tdel rem -> delete remedy entry");

        System.out.println("\tview all sym -> view all symptom logs");
        System.out.println("\tview all rem -> view remedy logs");
        System.out.println("\tview all logs -> view remedy and symptom logs");

        System.out.println("\tview sym date -> view symptom logs on a certain date");
        System.out.println("\tview rem date -> view remedy logs on a certain date");

        System.out.println("\tsave -> save (program will also automatically save upon exit)");
        System.out.println("\tq -> quit");

    }

    // MODIFIES: this
    // EFFECTS: process user command
    @SuppressWarnings("methodlength")
    private void processCommand(String command) {
        if (command.equals("load")) {
            loadLogHistory();
        } else if (command.equals("add sym")) {
            addEntry(true);
        } else if (command.equals("add rem")) {
            addEntry(false);
        } else if (command.equals("edit sym")) {
            editEntry(true);
        } else if (command.equals("edit rem")) {
            editEntry(false);
        } else if (command.equals("del sym")) {
            deleteEntry(true);
        } else if (command.equals("del rem")) {
            deleteEntry(false);
        } else if (command.equals("view all sym")) {
            viewAllLogs(true);
        } else if (command.equals("view all rem")) {
            viewAllLogs(false);
        } else if (command.equals("view all logs")) {
            printLogHistory();
        } else if (command.equals("view sym date")) {
            viewLogOnDate(true);
        } else if (command.equals("view rem date")) {
            viewLogOnDate(false);
        } else if (command.equals("save")) {
            saveLogHistory();
        } else {
            System.out.println("Selection is not valid.");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds an entry to the log: adds symptom if addSymptom == true, else adds remedy
    @SuppressWarnings("methodlength")
    private void addEntry(Boolean addSymptom) {
        if (addSymptom) {
            String location;
            String sensation;
            int severity;
            int duration;
            String date;

            System.out.println("Select the symptom location from the following list:");
            printLocations();
            location = input.next().toLowerCase();

            System.out.println("Select the symptom sensation from the following list:");
            printSensations();
            sensation = input.next().toLowerCase();

            System.out.println("Select the severity of the symptom the following scale:");
            printSeverity();
            severity = input.nextInt();

            System.out.println("Input the duration the symptom lasted for in minutes:");
            duration = input.nextInt();

            System.out.println("Input the date of the symptom's occurrence in the following format:");
            System.out.println(DATE_FORMAT);
            date = input.next();

            System.out.println("Saving the following as a symptom entry: ");
            System.out.println("Location: " + location);
            System.out.println("Sensation: " + sensation);
            System.out.println("Severity: " + severity);
            System.out.println("Duration: " + duration);
            System.out.println("Date: " + date);
            System.out.println("Score: " + severity * duration);

            symLog.add(new Symptom(location, sensation, severity, duration, date));

            System.out.println("Symptom entry saved.");

        } else {
            String location;
            String remedy;
            String date;

            System.out.println("Select the remedy location from the following list:");
            printLocations();
            location = input.next().toLowerCase();

            System.out.println("Select the type of remedy from the following list:");
            printRemedies();
            remedy = input.next().toLowerCase();

            System.out.println("Input the date that the remedy was used in the following format:");
            System.out.println(DATE_FORMAT);
            date = input.next();

            System.out.println("Saving the following as a remedy entry: ");
            System.out.println("Location: " + location);
            System.out.println("Remedy: " + remedy);
            System.out.println("Date: " + date);

            remLog.add(new Remedy(location,remedy, date));

            System.out.println("Saved remedy entry.");
        }
    }

    // MODIFIES: this
    // EFFECTS: edits an entry in the log: edits a symptom if addSymptom == true, else edits a remedy
    @SuppressWarnings("methodlength")
    private void editEntry(Boolean editSymptom) {
        if (editSymptom) {
            String newLocation;
            String newSensation;
            int newSeverity;
            int newDuration;
            String newDate;

            String choice;

            System.out.println("Choose the number of the symptom you would like to edit:");
            viewAllLogs(true);
            int index = input.nextInt() - 1;
            int apparentIndex = index + 1;
            System.out.println("You are editing entry " + apparentIndex);
            Entry editing = symLog.getLog().get(index);
            String oldLocation = editing.getLocation();
            String oldSensation = editing.getSensation();
            int oldSeverity = editing.getSeverity();
            int oldDuration = editing.getDuration();
            String oldDate = editing.getDate();

            System.out.println("If you do not wish to change the current location, " + oldLocation + ", input 'n/a'. "
                    + "Otherwise, choose a new location from the following list: ");
            printLocations();
            choice = input.next().toLowerCase();
            if (choice.equals("n/a")) {
                newLocation = null;
                System.out.println("You chose to not change the current location.");
            } else {
                newLocation = choice;
                System.out.println("You chose to replace the old location with " + newLocation);

            }

            System.out.println("If you do not wish to change the current sensation, " + oldSensation + ", input 'n/a'. "
                    + "Otherwise, choose a new sensation from the following list: ");
            printSensations();
            choice = input.next().toLowerCase();
            if (choice.equals("n/a")) {
                newSensation = null;
                System.out.println("You chose to not change the current sensation.");
            } else {
                newSensation = choice;
                System.out.println("You chose to replace the old sensation with " + newSensation);

            }

            System.out.println("If you do not wish to change the current severity, " + oldSeverity + ", input 'n/a'. "
                    + "Otherwise, choose a new severity from the following list: ");
            printSeverity();
            choice = input.next();
            if (choice.equals("n/a")) {
                newSeverity = -1;
                System.out.println("You chose to not change the current severity.");
            } else {
                newSeverity = Integer.valueOf(choice);
                System.out.println("You chose to replace the old severity with " + newSeverity);

            }

            System.out.println("If you do not wish to change the current duration, " + oldDuration + ", input 'n/a'. "
                    + "Otherwise, input a new duration in whole minutes.");
            choice = input.next();
            if (choice.equals("n/a")) {
                newDuration = -1;
                System.out.println("You chose to not change the current duration.");
            } else {
                newDuration = Integer.valueOf(choice);
                System.out.println("You chose to replace the old duration with " + newDuration);

            }

            System.out.println("If you do not wish to change the current date, " + oldDate + ", input 'n/a'."
                    + "Otherwise, input a new date in the following format: ");
            System.out.println(DATE_FORMAT);
            choice = input.next();
            if (choice.equals("n/a")) {
                newDate = null;
                System.out.println("You chose to not change the current date.");
            } else {
                newDate = choice;
                System.out.println("You chose to replace the old date with " + newDate);

            }

            System.out.println("Editing symptom entry number " + apparentIndex + ".");

            symLog.editLogByIndex(index, new Symptom(newLocation, newSensation, newSeverity, newDuration, newDate));

            // TODO: add an assertion? would require editLogByIndex to return a success boolean
            System.out.println("Symptom entry edited and saved.");
        } else {
            String newLocation;
            String newRemedy;
            String newDate;

            String choice;

            System.out.println("Choose the number of the remedy you would like to edit:");
            viewAllLogs(false);
            int index = input.nextInt() - 1;
            int apparentIndex = index + 1;
            System.out.println("You are editing entry " + apparentIndex);

            Entry editing = remLog.getLog().get(index);
            String oldLocation = editing.getLocation();
            String oldRemedy = editing.getRemedy();
            String oldDate = editing.getDate();

            System.out.println("If you do not wish to change the current location, " + oldLocation + ", input 'n/a'. "
                    + "Otherwise, choose a new location from the following list: ");
            printLocations();
            choice = input.next().toLowerCase();
            if (choice.equals("n/a")) {
                newLocation = null;
                System.out.println("You chose to not change the current location.");
            } else {
                newLocation = choice;
                System.out.println("You chose to replace the old location with " + newLocation);

            }

            System.out.println("If you do not wish to change the current remedy, " + oldRemedy + ", input 'n/a'. "
                    + "Otherwise, choose a new remedy from the following list: ");
            printRemedies();
            choice = input.next().toLowerCase();
            if (choice.equals("n/a")) {
                newRemedy = null;
                System.out.println("You chose to not change the current sensation.");
            } else {
                newRemedy = choice;
                System.out.println("You chose to replace the old sensation with " + newRemedy);

            }

            System.out.println("If you do not wish to change the current date, " + oldDate + ", input 'n/a'."
                    + "Otherwise, input a new date in the following format: ");
            System.out.println(DATE_FORMAT);
            choice = input.next();
            if (choice.equals("n/a")) {
                newDate = null;
                System.out.println("You chose to not change the current date.");
            } else {
                newDate = choice;
                System.out.println("You chose to replace the old date with " + newDate);

            }

            System.out.println("Editing remedy entry number " + apparentIndex + ".");

            remLog.editLogByIndex(index, new Remedy(newLocation, newRemedy, newDate));

            // TODO: add an assertion? would require editLogByIndex to return a success boolean
            System.out.println("Remedy entry edited and saved.");
        }

    }

    // MODIFIES: this
    // EFFECTS: deletes an entry in the log: deletes a symptom if delSymptom == true, else deletes a remedy
    private void deleteEntry(Boolean delSymptom) {
        if (delSymptom) {
            System.out.println("Choose the number of the symptom you would like to delete:");
            viewAllLogs(true);
            int index = input.nextInt() - 1;
            int apparentIndex = index + 1;
            System.out.println("You are deleting entry " + apparentIndex);
            symLog.delete(index);
            System.out.println("Entry deleted.");
            viewAllLogs(true);

        } else {
            System.out.println("Choose the number of the remedy you would like to delete:");
            viewAllLogs(false);
            int index = input.nextInt() - 1;
            int apparentIndex = index + 1;
            System.out.println("You are deleting entry " + apparentIndex);
            remLog.delete(index);
            System.out.println("Entry deleted.");
            viewAllLogs(false);

        }
    }

    // EFFECTS: shows this log: shows symLog if viewSymptoms == true, else shows remLog
    @SuppressWarnings("methodlength")
    private void viewAllLogs(Boolean viewSymptoms) {
        if (viewSymptoms) {
            System.out.println("~~~~~SHOWING SYMPTOM LOG~~~~~~");

            for (int i = 0; i < symLog.getLog().size(); i++) {
                Entry viewing = symLog.getLog().get(i);
                int oneBasedIndex = i + 1;
                System.out.println("--ENTRY " + oneBasedIndex + "--");
                System.out.println("\tDate: " + viewing.getDate());
                System.out.println("\tLocation: " + viewing.getLocation());
                System.out.println("\tSensation: " + viewing.getSensation());
                System.out.println("\tSeverity: " + viewing.getSeverity());
                System.out.println("\tDuration: " + viewing.getDuration());
                System.out.println("\tScore: " + viewing.getScore());

                System.out.println();
            }
        } else {
            System.out.println("~~~~~SHOWING REMEDY LOG~~~~~~");

            for (int i = 0; i < remLog.getLog().size(); i++) {
                Entry viewing = remLog.getLog().get(i);
                int oneBasedIndex = i + 1;
                System.out.println("--ENTRY " + oneBasedIndex + "--");
                System.out.println("\tDate: " + viewing.getDate());
                System.out.println("\tLocation: " + viewing.getLocation());
                System.out.println("\tRemedy: " + viewing.getRemedy());

                System.out.println();
            }
        }

    }

    // EFFECTS: shows all logs recorded on a certain date: shows symptom(s) if showSymptom == true, else shows remedy(s)
    @SuppressWarnings("methodlength")
    private void viewLogOnDate(Boolean showSymptom) {
        if (showSymptom) {
            System.out.println("Choose the date that you would like to view the symptoms logged on. Ensure it is "
                            + "in the following format: ");
            System.out.println(DATE_FORMAT);
            String date = input.next();

            System.out.println("~~~~~SHOWING SYMPTOM LOG ENTRIES ON " + date + "~~~~~~");

            for (int i = 0; i < symLog.getLog().size(); i++) {
                Entry viewing = symLog.getLog().get(i);
                int oneBasedIndex = i + 1;

                if (date.equals(symLog.getLog().get(i).getDate())) {
                    System.out.println("--ENTRY " + oneBasedIndex + "--");
                    System.out.println("\tDate: " + viewing.getDate());
                    System.out.println("\tLocation: " + viewing.getLocation());
                    System.out.println("\tSensation: " + viewing.getSensation());
                    System.out.println("\tSeverity: " + viewing.getSeverity());
                    System.out.println("\tDuration: " + viewing.getDuration());
                    System.out.println("\tScore: " + viewing.getScore());

                    System.out.println();
                }
            }
        } else {
            System.out.println("Choose the date that you would like to view the remedies logged on. Ensure it is "
                    + "in the following format: ");
            System.out.println(DATE_FORMAT);
            String date = input.next();

            System.out.println("~~~~~SHOWING REMEDY LOG ENTRIES ON " + date + "~~~~~~");

            for (int i = 0; i < remLog.getLog().size(); i++) {
                Entry viewing = remLog.getLog().get(i);
                int oneBasedIndex = i + 1;

                if (date.equals(remLog.getLog().get(i).getDate())) {
                    System.out.println("--ENTRY " + oneBasedIndex + "--");
                    System.out.println("\tDate: " + viewing.getDate());
                    System.out.println("\tLocation: " + viewing.getLocation());
                    System.out.println("\tRemedy: " + viewing.getRemedy());

                    System.out.println();
                }
            }
        }
    }


    // EFFECTS: prints the list of locations
    private void printLocations() {
        for (String l : LOCATIONS) {
            System.out.println(l);
        }
    }

    // EFFECTS: prints the list of sensations
    private void printSensations() {
        for (String s : SENSATIONS) {
            System.out.println(s);
        }
    }

    // EFFECTS: prints the scale of severity
    private void printSeverity() {
        System.out.println("--Least Severe--");
        for (Integer i : SEVERITY) {
            System.out.println(i);
        }
        System.out.println("--Most Severe--");
    }

    // EFFECTS: prints the list of remedies
    private void printRemedies() {
        for (String r : REMEDIES) {
            System.out.println(r);
        }
    }

    // EFFECTS: prints the entire log history
    private void printLogHistory() {
        System.out.println("~~~~~SHOWING LOG HISTORY~~~~~~");
        viewAllLogs(true);
        viewAllLogs(false);
    }

    // EFFECTS: saves the current logs to file
    private void saveLogHistory() {
        lh = new LogHistory("Your Log History", symLog, remLog);
        try {
            jsonWriter.open();
            jsonWriter.write(lh);
            jsonWriter.close();
            System.out.println("Saved " + lh.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads log history from file
    private void loadLogHistory() {
        try {
            lh = jsonReader.read();
            symLog = (SymptomLog) lh.getSymptomLogs();
            remLog = (RemedyLog) lh.getRemedyLogs();
            System.out.println("Loaded " + lh.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
