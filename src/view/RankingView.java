package view;

import model.Pilot;
import model.Race;
import model.Ranking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RankingView extends javax.swing.JFrame {
    private Race race;
    public JPanel panel1;
    private JTextField winnerName;
    private JTextField winnerTime;
    private JTable racersTable;
    private JTextField winnerCode;

    public RankingView(Race race) {
        this.race = race;
        initView();
        initTable();
    }

    private void initTable() {
        int numberOfColumns = 7;
        String[] collunms = {"Position", "Code", "Name", "Best Lap", "Average Speed", "Laps", "Time From First"};
        DefaultTableModel dtm = new DefaultTableModel(0, race.getRanking().size());
        dtm.setColumnIdentifiers(collunms);

        for (Ranking r : race.getRanking()) {
            Object[] rowData = new Object[numberOfColumns];
            rowData[0] = r.getPosition();
            rowData[1] = r.getPilot().getPilotCode();
            rowData[2] = r.getPilot().getPilotName();
            rowData[3] = r.getPilot().bestLap().lapTime.toString().substring(2).toLowerCase();
            rowData[4] = r.getPilot().getAverageSpeed();
            rowData[5] = r.getPilot().totalLaps();

            if (r.completedRace())
                rowData[6] = "+ " + r.getTimeAfter().toString().substring(2).toLowerCase();
            else
                rowData[6] = "NOT FINISHED";

            dtm.addRow(rowData);
        }

        racersTable.setModel(dtm);

        racersTable.setAutoCreateRowSorter(true);
        dtm.fireTableDataChanged();
        racersTable.updateUI();
    }

    private void initView() {
        Pilot winner = race.getWinner();
        winnerName.setText(winner.getPilotName());
        winnerCode.setText(winner.getPilotCode().toString());
        winnerTime.setText(winner.bestLap().lapTime.toString().substring(2).toLowerCase());
    }
}
