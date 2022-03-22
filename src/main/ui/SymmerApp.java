package ui;

import model.LogHistory;
import model.entries.Entry;
import model.entries.Remedy;
import model.entries.Symptom;
import model.logs.RemedyLog;
import model.logs.SymptomLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import com.toedter.calendar.JDateChooser;



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

    String newDate;
    String newLocation;
    String newSensation;
    int newDuration;
    int newSeverity;
    String newRemedy;

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
        symLog = new SymptomLog();
        remLog = new RemedyLog();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        homePage();

        // For Console Only:
        //init();
        //runSymmer();

    }

    // EFFECTS: displays the Symmer home screen
    @SuppressWarnings("methodlength")
    private void homePage() {
        // initialize frame
        JFrame frame = new JFrame("Home");
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        // initialize panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // add icon
        c.gridx = 0;
        c.gridy = 0;
        BufferedImage icon = null;
        try {
            icon = ImageIO.read(new File("images/cat.jpg"));
        } catch (IOException e) {
            System.out.println("Image not found");
        }
        JLabel iconLabel = new JLabel(new ImageIcon(icon));
        panel.add(iconLabel, c);

        // add View Logs button
        c.gridx = 0;
        c.gridy = 1;
        JButton btnViewLogs = new JButton("View Logs");
        btnViewLogs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Viewing logs");
                showAllLogsGUI();
            }
        });
        panel.add(btnViewLogs, c);

        // add the Add Logs button
        c.gridx = 0;
        c.gridy = 2;
        JButton btnAddLogs = new JButton("Add Logs");
        btnAddLogs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Adding logs");
                addLogsGUI();
            }
        });
        panel.add(btnAddLogs, c);

        // add Save button
        c.gridx = 0;
        c.gridy = 3;
        JButton btnSave = new JButton("Save");

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveLogs();
            }
        });
        panel.add(btnSave, c);

        // add Load from File button
        c.gridx = 0;
        c.gridy = 4;
        JButton btnLoad = new JButton("Load From File");
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadLogs();
            }
        });
        panel.add(btnLoad, c);

        frame.add(panel);

        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    // EFFECTS: displays all logs
    private void showAllLogsGUI() {
        // initialize frame
        JFrame frame = new JFrame("Showing All Logs");

        // initialize panel
        JPanel panel = viewAllLogsToString();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // add panel to scroll pane
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // add scroll pane to frame
        frame.add(scrollPane);

        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        frame.setSize(400, 800);
        frame.setLocationRelativeTo(null);
    }

    // MODIFIES: this
    // EFFECTS: displays option to edit either symptom or remedy logs
    private void addLogsGUI() {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setLocationRelativeTo(null);

        Object[] options = {"Symptom Log", "Remedy Log"};

        int value = JOptionPane.showOptionDialog(frame,
                "Which log would you like to add to?",
                "Choose an option",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        if (value == JOptionPane.YES_OPTION) {
            addSymptomLogGUI();
            System.out.println("Adding a symptom log");
        } else if (value == JOptionPane.NO_OPTION) {
            addRemedyLogGUI();
            System.out.println("Adding a remedy log");
        }
    }

    // MODIFIES: this
    // EFFECTS: GUI to add a remedy to this history log
    @SuppressWarnings("methodlength")
    private void addRemedyLogGUI() {
        JFrame frame = new JFrame();

        frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // add labels
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        JLabel lblDate = new JLabel("Date");
        frame.getContentPane().add(lblDate, c);

        c.gridx = 0;
        c.gridy = 1;
        JLabel lblLocation = new JLabel("Location");
        frame.getContentPane().add(lblLocation, c);

        c.gridx = 0;
        c.gridy = 2;
        JLabel lblRemedy = new JLabel("Remedy");
        frame.getContentPane().add(lblRemedy, c);


        // add other elements

        // date picker
        c.gridx = 1;
        c.gridy = 0;
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
                    public void propertyChange(PropertyChangeEvent e) {
                        if ("date".equals(e.getPropertyName())) {
                            java.util.Date utilDate = (java.util.Date) e.getNewValue();
                            newDate = utilDate.toString();
                        }
                    }
                });
        frame.getContentPane().add(dateChooser, c);
        frame.setVisible(true);


        // location dropdown selection
        c.gridx = 1;
        c.gridy = 1;

        JComboBox cbLocation = new JComboBox(getLocations());
        cbLocation.setSelectedItem(null);
        cbLocation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // get selected String and set location
                JComboBox cb = (JComboBox)arg0.getSource();
                newLocation = (String)cb.getSelectedItem();
            }
        });
        frame.getContentPane().add(cbLocation, c);

        // remedy dropdown selection
        c.gridx = 1;
        c.gridy = 2;
        JComboBox cbRemedies = new JComboBox(getRemedies());
        cbRemedies.setSelectedItem(null);
        cbRemedies.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // get selected String and set sensation
                JComboBox cb = (JComboBox)arg0.getSource();
                newRemedy = (String)cb.getSelectedItem();
            }
        });
        frame.getContentPane().add(cbRemedies, c);

        // save button
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        JButton saveBtn = new JButton("Save");
        frame.getContentPane().add(saveBtn, c);
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remLog.add(new Remedy(newLocation, newRemedy, newDate));
                JOptionPane.showMessageDialog(frame, "Saving the following new remedy:"
                        + "\nDate: " + newDate
                        + "\nLocation: " + newLocation
                        + "\nRemedy: " + newRemedy);
            }
        });
        frame.getContentPane().add(saveBtn, c);

        frame.pack();
        frame.setVisible(true);

        frame.setPreferredSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: GUI to add a symptom to this history log
    @SuppressWarnings("methodlength")
    private void addSymptomLogGUI() {
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // add labels
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        JLabel lblDate = new JLabel("Date");
        frame.getContentPane().add(lblDate, c);

        c.gridx = 0;
        c.gridy = 1;
        JLabel lblLocation = new JLabel("Location");
        frame.getContentPane().add(lblLocation, c);

        c.gridx = 0;
        c.gridy = 2;
        JLabel lblSensation = new JLabel("Sensation");
        frame.getContentPane().add(lblSensation, c);

        c.gridx = 0;
        c.gridy = 3;
        JLabel lblDuration = new JLabel("Duration (press enter)");
        frame.getContentPane().add(lblDuration, c);

        c.gridx = 0;
        c.gridy = 4;
        JLabel lblSeverity = new JLabel("Severity");
        frame.getContentPane().add(lblSeverity, c);

        // add other elements

        // date picker
        c.gridx = 1;
        c.gridy = 0;
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
                    public void propertyChange(PropertyChangeEvent e) {
                        if ("date".equals(e.getPropertyName())) {
                            java.util.Date utilDate = (java.util.Date) e.getNewValue();
                            newDate = utilDate.toString();
                        }
                    }
                });
        frame.getContentPane().add(dateChooser, c);
        frame.setVisible(true);


        // location dropdown selection
        c.gridx = 1;
        c.gridy = 1;

        JComboBox cbLocation = new JComboBox(getLocations());
        cbLocation.setSelectedItem(null);
        cbLocation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // get selected String and set location
                JComboBox cb = (JComboBox)arg0.getSource();
                newLocation = (String)cb.getSelectedItem();
            }
        });
        frame.getContentPane().add(cbLocation, c);

        // sensation dropdown selection
        c.gridx = 1;
        c.gridy = 2;
        JComboBox cbSensation = new JComboBox(getSensations());
        cbSensation.setSelectedItem(null);
        cbSensation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // get selected String and set sensation
                JComboBox cb = (JComboBox)arg0.getSource();
                newSensation = (String)cb.getSelectedItem();
            }
        });
        frame.getContentPane().add(cbSensation, c);

        // duration text box
        c.gridx = 1;
        c.gridy = 3;
        JTextField txtDuration = new JTextField();
        txtDuration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String text = txtDuration.getText();

                try {
                    Integer dur = Integer.parseInt(text);
                    if (dur < 0) {
                        JOptionPane.showMessageDialog(frame, "Whoops! Positive numbers only!");
                    } else {
                        newDuration = dur;
                        System.out.println(newDuration);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Whoops! Valid numbers only!");
                }
            }
        });
        frame.getContentPane().add(txtDuration, c);

        // severity dropbox selection
        c.gridx = 1;
        c.gridy = 4;
        JComboBox cbSeverity = new JComboBox(getSeverities());
        cbSeverity.setSelectedItem(null);
        cbSeverity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // get selected String and set severity
                JComboBox cb = (JComboBox)arg0.getSource();
                newSeverity = (Integer)cb.getSelectedItem();

            }
        });
        frame.getContentPane().add(cbSeverity, c);

        // save button
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        JButton saveBtn = new JButton("Save");
        frame.getContentPane().add(saveBtn, c);
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                symLog.add(new Symptom(newLocation, newSensation, newSeverity, newDuration, newDate));
                JOptionPane.showMessageDialog(frame, "Saving the following new symptom:"
                        + "\nDate: " + newDate
                        + "\nLocation: " + newLocation
                        + "\nSensation: " + newSensation
                        + "\nSeverity: " + newSeverity
                        + "\nDuration: " + newDuration);
            }
        });
        frame.getContentPane().add(saveBtn, c);

        frame.pack();
        frame.setVisible(true);

        frame.setPreferredSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: saves this log on file
    private void saveLogs() {
        JFrame frame = new JFrame("Saving Logs");

        lh = new LogHistory("Your Log History", symLog, remLog);
        try {
            jsonWriter.open();
            jsonWriter.write(lh);
            jsonWriter.close();
            JOptionPane.showMessageDialog(frame, "Logs saved.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads this log from the one on file
    private void loadLogs() {
        JFrame frame = new JFrame("Saving Logs");

        try {
            lh = jsonReader.read();
            symLog = (SymptomLog) lh.getSymptomLogs();
            remLog = (RemedyLog) lh.getRemedyLogs();
            JOptionPane.showMessageDialog(frame, "Loaded " + lh.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: returns this history log's entries as a JPanel
    @SuppressWarnings("methodlength")
    public JPanel viewAllLogsToString() {
        JPanel panel = new JPanel();

        // add symptoms
        JLabel symTitle = new JLabel("~~~~~SHOWING SYMPTOM LOG~~~~~~");
        panel.add(symTitle);
        for (int i = 0; i < symLog.getLog().size(); i++) {
            Entry viewing = symLog.getLog().get(i);
            int oneBasedIndex = i + 1;

            JLabel label = new JLabel("");
            JLabel indexLabel = new JLabel("--ENTRY " + oneBasedIndex + "--");
            JLabel dateLabel = new JLabel("\tDate: " + viewing.getDate());
            JLabel locationLabel = new JLabel("\tLocation: " + viewing.getLocation());
            JLabel sensationLabel = new JLabel("\tSensation: " + viewing.getSensation());
            JLabel severityLabel = new JLabel("\tSeverity: " + viewing.getSeverity());
            JLabel durationLabel = new JLabel("\tDuration: " + viewing.getDuration());
            JLabel scoreLabel = new JLabel("\tScore: " + viewing.getScore());

            panel.add(label);
            panel.add(indexLabel);
            panel.add(dateLabel);
            panel.add(locationLabel);
            panel.add(sensationLabel);
            panel.add(severityLabel);
            panel.add(durationLabel);
            panel.add(scoreLabel);
        }

        // add remedies
        JLabel remTitle = new JLabel("~~~~~SHOWING REMEDY LOG~~~~~~");
        panel.add(remTitle);

        for (int i = 0; i < remLog.getLog().size(); i++) {
            Entry viewing = remLog.getLog().get(i);
            int oneBasedIndex = i + 1;

            JLabel label = new JLabel("");
            JLabel indexLabel = new JLabel("--ENTRY " + oneBasedIndex + "--");
            JLabel dateLabel = new JLabel("\tDate: " + viewing.getDate());
            JLabel locationLabel = new JLabel("\tLocation: " + viewing.getLocation());
            JLabel remedyLabel = new JLabel("\tRemedy: " + viewing.getRemedy());

            panel.add(label);
            panel.add(indexLabel);
            panel.add(dateLabel);
            panel.add(locationLabel);
            panel.add(remedyLabel);
        }
        return panel;
    }

    // EFFECTS: returns a vector of locations
    private Vector<String> getLocations() {
        Vector<String> ret = new Vector<>();

        for (String l : LOCATIONS) {
            ret.add(l);
        }
        return ret;
    }

    // EFFECTS: returns a vector of possible severities
    private Vector<Integer> getSeverities() {
        Vector<Integer> ret = new Vector<>();
        for (Integer i : SEVERITY) {
            ret.add(i);
        }
        return ret;
    }

    // EFFECTS: returns a vector of sensations
    private Vector<String> getSensations() {
        Vector<String> ret = new Vector<>();

        for (String s : SENSATIONS) {
            ret.add(s);
        }
        return ret;
    }

    // EFFECTS: returns a vector of remedies
    private Vector<String> getRemedies() {
        Vector<String> ret = new Vector<>();
        for (String r : REMEDIES) {
            ret.add(r);
        }
        return ret;
    }





    // -----------------------------------------------------------------

    // The following methods are not included in GUI phase 3

    // Adapted from WorkRoomApp class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
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

    // Adapted from WorkRoomApp class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
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



}
