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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static List<Pilot> extractPilots(String source) {
        List<Pilot> pilots = new ArrayList<>();
        try {
            File file = new File(source);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = cleanLine(sc.nextLine());
                Pattern pattern = Pattern.compile("(?<time>\\d{2}:\\d{2}:\\d{2}\\.\\d{3})[\\s|\\t]+(?<code>\\d{3}).{3}(?<name>\\w\\.\\w+)[\\s|\\t]+(?<lap>\\d)[\\s|\\t]+(?<duration>\\d+:\\d{2}\\.\\d{3})[\\s|\\t]+(?<speed>\\d+\\.\\d+)");
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    try {
                        Pilot p = new Pilot(matcher.group("name"), Integer.valueOf(matcher.group("code")));
                        if (pilots.contains(p)) {
                            addLap(pilots.get(pilots.indexOf(p)), matcher);
                        } else {
                            pilots.add(createPilot(matcher));
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

    private static Pilot createPilot(Matcher matcher) throws ParseException {
        Pilot pilot = new Pilot(matcher.group("name"), Integer.valueOf(matcher.group("code")));
        addLap(pilot, matcher);
        return pilot;
    }

    private static void addLap(Pilot pilot, Matcher matcher) throws ParseException {
        SimpleDateFormat durationFormat = new SimpleDateFormat("m:ss.SSS");
        SimpleDateFormat finishTime = new SimpleDateFormat("HH:mm:ss.SSS");
        Duration dur = Duration.ofMillis(durationFormat.parse(matcher.group("duration")).getTime());
        dur = dur.minus(Duration.ofHours(3L));
        Lap lap = new Lap(Integer.parseInt(matcher.group("lap")), dur, new  Time(finishTime.parse(matcher.group("time")).getTime()), Float.valueOf(matcher.group("speed")));
        pilot.addLap(lap);
    }

    private static String cleanLine(String s) {
        s = s.replace(",", ".");
        s = s.replace("\n", "");
        s = s.replace("\r", "");
        return s;
    }
}
