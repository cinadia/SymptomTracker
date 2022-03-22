//package ui;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
///**
// * Initializes the home page of the GUI
// */
//public class HomePageGUI {
//
//    @SuppressWarnings("methodlength")
//    public HomePageGUI() {
//
//        // initialize frame
//        JFrame frame = new JFrame("Home");
//
//        // initialize panel
//        JPanel panel = new JPanel();
//
//        // add icon
//        BufferedImage icon = null;
//        try {
//            icon = ImageIO.read(new File("images/cat.jpg"));
//        } catch (IOException e) {
//            System.out.println("Image not found");
//        }
//        JLabel iconLabel = new JLabel(new ImageIcon(icon));
//        panel.add(iconLabel);
//
//        // add View Logs button
//        JButton btnViewLogs = new JButton("View Logs");
//        btnViewLogs.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                new ShowAllLogs();
//            }
//        });
//        panel.add(btnViewLogs);
//
//        // add the Add Logs button
//        JButton btnAddLogs = new JButton("Add Logs");
//        btnViewLogs.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // TODO initialize add logs window
//            }
//        });
//        panel.add(btnAddLogs);
//
//        // add Save button
//        JButton btnSave = new JButton("Save");
//        btnViewLogs.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // TODO save
//            }
//        });
//        panel.add(btnSave);
//
//        // add Load from File button
//        JButton btnLoad = new JButton("Load From File");
//        btnViewLogs.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // TODO load from file
//            }
//        });
//        panel.add(btnLoad);
//
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        // TODO: center align
//        // panel.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        frame.add(panel);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setPreferredSize(new Dimension(400, 400));
//        frame.pack();
//
//
//
//
//
//    }
//
//
//}
