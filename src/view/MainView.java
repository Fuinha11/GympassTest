package view;

import model.Race;

import javax.swing.*;

public class MainView {
    private Race race;
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTable table1;

    public MainView(Race race) {
        this.race = race;
    }
}
