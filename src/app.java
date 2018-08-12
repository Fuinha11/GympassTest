
import model.Pilot;
import model.Race;
import utils.Utils;
import view.RankingView;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class app {
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {

            JFrame frame = new JFrame("Race Ranking");
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Please select a .txt file to open");
            File workingDirectory = new File(System.getProperty("user.dir")+"\\src\\files");
            chooser.setCurrentDirectory(workingDirectory);

            int resp = chooser.showOpenDialog(null);
            if (resp == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                List<Pilot> list = Utils.extractPilots(selectedFile.getAbsolutePath());
                if (list.size() != 0) {
                    Race race = new Race(4, list);
                    frame.setContentPane(new RankingView(race).panel1);
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "It was impossible to parse File");
                }
            } else {
                System.exit(0);
            }
        });
    }
}
