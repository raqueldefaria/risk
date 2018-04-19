package com.projectIsep.risk;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Menu extends JFrame {

    private String mapOption;
    private int numberOfPlayers;



    //------------------Constructor------------------//
    public Menu() {
        this.setTitle("Le jeu du Risk");
        this.setSize(500,330);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelContent(this));

        this.setVisible(true);
    }

    //------------------Getters & Setters------------------//

    public String getMapOption() {
        return mapOption;
    }

    public void setMapOption(String mapOption) {
        this.mapOption = mapOption;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }


    //------------------Methods------------------//

    public JPanel panelContent(JFrame frame){
        JPanel panel = new JPanel();
        try {
            // -------------- Risk logo --------------  //
            JPanel logoPanel = new JPanel();
            logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.LINE_AXIS));

            Image img = ImageIO.read(new File("src/img/risk_logo.png"));

            JLabel logoLabel = new JLabel();
            logoLabel.setSize(250,150);

            Image dimg = img.getScaledInstance(logoLabel.getWidth(),logoLabel.getHeight(),Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);

            logoLabel.setIcon(imageIcon);

            logoPanel.add(logoLabel);


            // -------------- Option to chose the map --------------  //
            JPanel mapOptionPanel = new JPanel();
            mapOptionPanel.setLayout(new BoxLayout(mapOptionPanel, BoxLayout.LINE_AXIS));
            mapOptionPanel.setBorder(new EmptyBorder(0,0,20,0));

            JLabel mapsLabel = new JLabel("Please choose a map :  ");

            String[] tab = {"World map", "Europe map", "GOT map"};
            JComboBox<String> mapOptions = new JComboBox<>(tab);

            mapOptionPanel.add(mapsLabel);
            mapOptionPanel.add(mapOptions);

            // -------------- Option to chose the number of players --------------  //
            JPanel playerOptionPanel = new JPanel();
            playerOptionPanel.setLayout(new BoxLayout(playerOptionPanel, BoxLayout.LINE_AXIS));
            playerOptionPanel.setBorder(new EmptyBorder(0,0,30,0));

            JLabel playerLabel = new JLabel("Please the number of players :  ");

            JComboBox<Integer> playerOptions = new JComboBox<>();
            playerOptions.addItem(1);
            playerOptions.addItem(2);
            playerOptions.addItem(3);
            playerOptions.addItem(4);
            playerOptions.addItem(5);
            playerOptions.addItem(6);

            playerOptionPanel.add(playerLabel);
            playerOptionPanel.add(playerOptions);


            // -------------- Play Button --------------  //
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

            JButton button = new JButton("Play");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mapOption = (String) mapOptions.getSelectedItem();
                    numberOfPlayers = (int) playerOptions.getSelectedItem();
                    frame.dispose();
                    Map map = new Map();
                    map.worldMap();
                }
            });

            buttonPanel.add(button);


            // -------------- The panel with all the panels --------------  //
            JPanel finalMenuPanel = new JPanel();
            finalMenuPanel.setLayout(new BoxLayout(finalMenuPanel, BoxLayout.PAGE_AXIS));
            finalMenuPanel.add(logoPanel);
            finalMenuPanel.add(mapOptionPanel);
            finalMenuPanel.add(playerOptionPanel);
            finalMenuPanel.add(buttonPanel);

            // -------------- Adding everything to the final panel --------------  //
            panel.add(finalMenuPanel);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return panel;
    }


}
