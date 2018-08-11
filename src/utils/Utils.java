package utils;

import model.Lap;
import model.Pilot;

import java.io.File;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public static List<Pilot> extractPilots(String source) {
        List<Pilot> pilots = new ArrayList<>();
        try {
            File file = new File(source);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String row = cleanLine(sc.nextLine());

                if (!row.startsWith("Hora")) {
                    String[] split = row.split("/");

                    try {

                        if (pilots.contains(new Pilot(split[2], Integer.valueOf(split[1])))) {
                            addLap(pilots.get(pilots.indexOf(new Pilot(split[2], Integer.valueOf(split[1])))), split);
                        } else {
                            pilots.add(createPilot(split));
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pilots;
    }

    private static Pilot createPilot(String[] row) throws ParseException {
        Pilot pilot = new Pilot(row[2], Integer.valueOf(row[1]));
        addLap(pilot, row);
        return pilot;
    }

    private static void addLap(Pilot pilot, String[] row) throws ParseException {
        SimpleDateFormat durationFormat = new SimpleDateFormat("m:ss.SSS");
        SimpleDateFormat finishTime = new SimpleDateFormat("HH:mm:ss.SSS");
        Duration dur = Duration.ofMillis(durationFormat.parse(row[4]).getTime());
        dur = dur.minus(Duration.ofHours(3L));
        Lap lap = new Lap(Integer.parseInt(row[3]), dur, new  Time(finishTime.parse(row[0]).getTime()), Float.valueOf(row[5]));
        pilot.addLap(lap);
    }

    private static String cleanLine(String s) {
        s = s.replace(" – ", "/");

        while (s.contains("  ")) {
            s = s.replace("  ", " ");
        }

        s = s.replace(" ", "/");
        s = s.replace("\t", "/");
        s = s.replace("//", "");
        s = s.replace(",", ".");
        return s;
    }
}
