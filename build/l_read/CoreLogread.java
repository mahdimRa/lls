package l_read;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CoreLogread {

    private static List<List<String>> file_read(String token, String logfile) {

        File f = new File(logfile);
        if (f.exists()) {

            List<String> priorlogfile_org = new ArrayList<>();
            priorlogfile_org = WorkingFileLogRead.readFile(logfile);

            String integrity_check_line = EncrypAndDecryptLogRead
                    .decryptPriorFile(priorlogfile_org.get(priorlogfile_org.size() - 1), token);

            Boolean can_decript_ch = false;

            String cheksum = "";

            for (int i = 0; i < priorlogfile_org.size() - 1; i++) {
                can_decript_ch = false;
                String content_decripted_prior_log = EncrypAndDecryptLogRead.decryptPriorFile(priorlogfile_org.get(i),
                        token);
                if (content_decripted_prior_log == null) {
                    System.out.println("invalid token or integrity is false");
                    System.exit(255);
                    return null;
                } else {
                    cheksum = cheksum + content_decripted_prior_log;
                }
            }
            // System.out.println("checksum:" + cheksum);

            if (!HashR.create_hash(cheksum).equals(
                    EncrypAndDecryptLogRead.decryptPriorFile(priorlogfile_org.get(priorlogfile_org.size() - 1),
                            token))) {
                HashR.create_hash(cheksum);
                System.out.println("1: " + HashR.create_hash(cheksum));
                System.out.println("2: " + EncrypAndDecryptLogRead
                        .decryptPriorFile(priorlogfile_org.get(priorlogfile_org.size() - 1), token));

                System.out.println("invalid integrity");
                System.exit(255);
            }

            String checkhash = HashR.create_hash(cheksum);

            boolean can_decript = false;

            List<String> priorlogfile = new ArrayList<>();

            priorlogfile = WorkingFileLogRead.readFile(logfile);

            List<List<String>> csvList = new ArrayList<List<String>>();

            for (int i = 0; i < priorlogfile.size() - 1; i++) {
                can_decript = false;
                String content_decripted_prior_log = EncrypAndDecryptLogRead.decryptPriorFile(priorlogfile.get(i),
                        token);
                if (content_decripted_prior_log == null) {
                    System.out.println("invalid token or integrity is false");
                    System.exit(255);
                } else {
                    can_decript = true;
                }

                String[] result = content_decripted_prior_log.split(";");
                List<String> line = new ArrayList<>();

                for (int j = 0; j < result.length; j++) {
                    line.add(result[j]);
                }
                csvList.add(line);
            }
            // System.out.println("svList: \n" + csvList + "\n");

            if (can_decript) {
                return csvList;
            } else {
                System.out.println("invalid token or integrity is false");
                System.exit(255);
            }

        } else {
            if (!f.exists()) {
                System.out.println("invalid log file does not exsit.");
                System.exit(255);
            }
        }
        System.out.println("invalid log file does not exsit.");
        System.exit(255);
        return null;
    }

    public static void coreSMode(String token, String logfile) {

        List<List<String>> list_log = new ArrayList<List<String>>();

        list_log = CoreLogread.file_read(token, logfile);

        System.out.println("in Core Mode: \n" + list_log + "\n");

    }

    public static void coreRMode(String token, String dashrole, String name, String logfile) {

        List<List<String>> list_log = new ArrayList<List<String>>();

        list_log = CoreLogread.file_read(token, logfile);

        // System.out.println("in R Core Mode: \n" + list_log + "\n");
        // System.out.println("token:" + token + " dashrole:" + dashrole + " name:" +
        // name + " logfile:" + logfile);

        HashSet<String> chronological_room = new HashSet<String>();

        // String gallery = "gallery";

        for (int i = 0; i < list_log.size(); i++) {

            if ((list_log.get(i).get(1).equals(name)) && (list_log.get(i).get(2).equals(dashrole))) {
                // System.out.println("ROOME NUMBER" + list_log.get(i).get(4));

                if (list_log.get(i).get(4).equals("-99")) {
                    chronological_room.add("gallery");

                } else {
                    chronological_room.add(list_log.get(i).get(4));

                }

            }

        }

        // LinkedList<String> list = new LinkedList<>(chronological_room);
        // Iterator<String> itr = list.descendingIterator();

        List<String> listrev = new ArrayList<>(chronological_room);
        Collections.reverse(listrev);

        for (String item : listrev) {
            System.out.print(item + " ");

        }
        // while (itr.hasNext()) {
        // // String item = itr.next();
        // System.out.print(itr.next() + " ");

        // // do something
        // }

        // Iterator itr = chronological_room.iterator();

        // // check element is present or not. if not loop will
        // // break.
        // while (itr.hasNext()) {
        // System.out.print(itr.next() + " ");
        // }

        System.out.println("\n");

    }

    public static void coreTMode(String token, String dashrole, String name, String logfile) {

        List<List<String>> list_log = new ArrayList<List<String>>();

        list_log = CoreLogread.file_read(token, logfile);

        // System.out.println("in R Core Mode: \n" + list_log + "\n");
        // System.out.println("token:" + token + " dashrole:" + dashrole + " name:" +
        // name + " logfile:" + logfile);

        String first_time_person = "";
        String last_time_person = "";

        boolean flag_first_time = false;

        String last_room = "";

        // String gallery = "gallery";

        for (int i = 0; i < list_log.size(); i++) {

            if ((list_log.get(i).get(1).equals(name)) && (list_log.get(i).get(2).equals(dashrole))) {
                // System.out.println("ROOME NUMBER" + list_log.get(i).get(4));
                if (flag_first_time) {
                    last_time_person = list_log.get(i).get(0);
                    last_room = list_log.get(i).get(4);
                }
                if (!flag_first_time) {
                    first_time_person = list_log.get(i).get(0);
                    flag_first_time = true;
                }

            }

        }
        // System.out.println("first_time_person: "+first_time_person);

        // System.out.println("last_time_person: "+last_time_person);


        if (!first_time_person.equals("") && last_time_person.equals("")) {
            // System.out.println("firstime is and last time not");

            System.out.println(
                    Integer.parseInt(list_log.get(list_log.size() - 1).get(0)) - Integer.parseInt(first_time_person));

        }

        if (!first_time_person.equals("") && !last_time_person.equals("") && last_room.equals("-99")) {
            // System.out.println("firstime is and last time is room -99");

            System.out.println(Integer.parseInt(last_time_person) - Integer.parseInt(first_time_person));

        }

        if (!first_time_person.equals("") && !last_time_person.equals("") && !last_room.equals("-99")) {
            // System.out.println("firstime is and last time is room not !99");

            System.out.println(
                    Integer.parseInt(list_log.get(list_log.size() - 1).get(0)) - Integer.parseInt(first_time_person));
        }
        if (first_time_person.equals("noti") && last_time_person.equals("noti")) {
            System.out.println("");

        }

    }

}