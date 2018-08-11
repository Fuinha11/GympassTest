import javafx.stage.FileChooser;
import model.Lap;
import model.Pilot;
import model.Race;
import utils.Utils;
import view.RankingView;

import javax.swing.*;
import java.io.File;
import java.sql.Time;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class app {
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {

            JFrame frame = new JFrame("Race Ranking");
            JFileChooser chooser = new JFileChooser();
//            chooser.setTitle("Please select a file to open");

            int resp = chooser.showOpenDialog(null);
            if (resp != 1) {
                File selectedFile = chooser.getSelectedFile();
                List<Pilot> list = Utils.extractPilots(selectedFile.getAbsolutePath());
                Race race = new Race(4, list);
                frame.setContentPane(new RankingView(race).panel1);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            } else {
                System.exit(0);
            }
        });
    }

    private static Race mockRace() {
        List<Pilot> list = new ArrayList<>();
        Pilot p = new Pilot("Jãolão", 69);
        p.addLap(new Lap(1, Duration.ofMinutes(1).plus(Duration.ofSeconds(27)), Time.valueOf("04:01:32"), 50F));
        long winnerTime = 5000;
        p.addLap(new Lap(2, Duration.ofMinutes(8), new Time(winnerTime), 100F));
        list.add(p);

        Pilot p2 = new Pilot("Jãolão2", 69);
        p2.addLap(new Lap(1, Duration.ofMillis(38555), Time.valueOf("04:01:02"), 50F));
        p2.addLap(new Lap(2, Duration.ofMinutes(102000), new Time(winnerTime + (long) (Math.random() * 10000)), 100F));
        list.add(p2);

        System.out.println(list.contains(p2));

        Pilot p3 = new Pilot("Jãolão3", 69);
        p3.addLap(new Lap(1, Duration.ofMinutes(2), Time.valueOf("04:01:02"), 50F));
        list.add(p3);

        for (int i = 1; i < 30; i++) {
            Pilot px = new Pilot("Automatico " + i, 100 + i);
            px.addLap(new Lap(1, Duration.ofMillis((long) (Math.random() * 10000)), new Time(winnerTime + (long) (Math.random() * i * 10000)), (float) (Math.random() * 100)));
            px.addLap(new Lap(2, Duration.ofMillis((long) (Math.random() * 10000)), new Time(winnerTime + (long) (Math.random() * i * 10000)), (float) (Math.random() * 100)));
            list.add(px);
        }

        return new Race(2, list);
    }
}
