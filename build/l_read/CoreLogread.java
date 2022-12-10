package l_read;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CoreLogread {

    private static List<List<String>> file_read(String token, String logfile) {

        File f = new File(logfile);
        if (f.exists()) {

            boolean can_decript = false;

            List<String> priorlogfile = new ArrayList<>();

            priorlogfile = WorkingFileLogRead.readFile(logfile);

            List<List<String>> csvList = new ArrayList<List<String>>();

            for (int i = 0; i < priorlogfile.size(); i++) {
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
            System.out.println("svList: \n" + csvList + "\n");

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

}