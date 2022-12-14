package l_append;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class LogPersonLine {

    private String Timestamp;
    private String Token;
    private String Name;
    private String Role;
    private String Action;
    private String Room_id = "-99";
    private String Log;
    private String Mg;
    private String mghash;
    private String encryptedString;
    private String content_decrypt_prior_file;

    public void setTimestamp(String newName) {
        this.Timestamp = newName;
    }

    public String getTimestamp() {
        return this.Timestamp;
    }

    public void setToken(String newName) {
        this.Token = newName;
    }

    public String getToken() {
        return this.Token;
    }

    public void setName(String newName) {
        this.Name = newName;
    }

    public String getName() {
        return this.Name;
    }

    public void setRole(String newName) {
        this.Role = newName;
    }

    public String getRole() {
        return this.Role;
    }

    public void setAction(String newName) {

        this.Action = newName;
        // System.out.println("this iss action: " + this.Action);

    }

    public String getAction() {

        return this.Action;
    }

    public void setRoom_id(String newName) {
        int temprmid = Integer.parseInt(newName);
        String Roomidcorect = Integer.toString(temprmid);
        this.Room_id = Roomidcorect;
        // System.out.println("this room is : " + this.Room_id);

    }

    public String getRoom_id() {

        return Room_id;
    }

    public void setLog(String newName) {
        this.Log = newName;
    }

    public String getLog() {
        // System.out.println("this iss logggg: " + Log);

        return Log;
    }

    private void mgcreate() {

        if (this.Timestamp != null) {
            this.Mg = this.Timestamp + ";";
        }
        if (this.Name != null) {
            this.Mg = this.Mg + this.Name + ";";
        }
        if (this.Role != null) {
            this.Mg = this.Mg + this.Role + ";";
        }

        if (this.Action != null) {
            this.Mg = this.Mg + this.Action + ";";
        }
        if (this.Room_id != null) {
            this.Mg = this.Mg + this.Room_id + ";";
        } else {
            if (this.Room_id == null) {
                this.Mg = this.Mg + "-99";
            }
        }

    }

    // public void hashmg() {
    // mghash = Hash.create_hash(mg);
    // }
    private static void delete_last_line_log(String namelog) {
        try {
            RandomAccessFile f = new RandomAccessFile(namelog, "rw");
            long length = f.length() - 1;
            byte b;
            do {
                length -= 1;
                f.seek(length);
                b = f.readByte();
            } while (b != 10);
            f.setLength(length + 1);
            f.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public boolean file() {
        this.mgcreate();

        // WorkingFile.readFile(A);

        File f = new File(Log);
        if (f.exists()) {

            List<String> priorlogfile_org = new ArrayList<>();
            priorlogfile_org = WorkingFile.readFile(Log);

            String integrity_check_line = EncrypAndDecrypt
                    .decryptPriorFile(priorlogfile_org.get(priorlogfile_org.size() - 1), Token);

            Boolean can_decript_ch = false;

            String cheksum = "";

            for (int i = 0; i < priorlogfile_org.size() - 1; i++) {
                can_decript_ch = false;
                String content_decripted_prior_log = EncrypAndDecrypt.decryptPriorFile(priorlogfile_org.get(i), Token);
                if (content_decripted_prior_log == null) {
                    System.out.println("invalid token or integrity is false");
                    System.exit(255);
                    return false;
                } else {
                    cheksum = cheksum + content_decripted_prior_log;
                }
            }
            // System.out.println("checksum:"+ cheksum);

            
            if (!Hash.create_hash(cheksum).equals(
                    EncrypAndDecrypt.decryptPriorFile(priorlogfile_org.get(priorlogfile_org.size() - 1), Token))) {
                Hash.create_hash(cheksum);
                System.out.println("1: " + Hash.create_hash(cheksum));
                System.out.println("2: " + EncrypAndDecrypt.decryptPriorFile(priorlogfile_org.get(priorlogfile_org.size() - 1), Token));

                System.out.println("invalid integrity");
                System.exit(255);
            }

            String checkhash = Hash.create_hash(cheksum);

            // String checksize = Integer.toString(priorlogfile_org.size() + 1);

            // if (!integrity_check_line.equals(Integer.toString(priorlogfile_org.size())))

            boolean can_decript = false;
            List<String> priorlogfile = new ArrayList<>();
            priorlogfile = WorkingFile.readFile(Log);
            // System.out.println("size progfile: " + priorlogfile.size());
            List<List<String>> csvList = new ArrayList<List<String>>();

            for (int i = 0; i < priorlogfile.size() - 1; i++) {
                can_decript = false;
                String content_decripted_prior_log = EncrypAndDecrypt.decryptPriorFile(priorlogfile.get(i), Token);
                if (content_decripted_prior_log == null) {
                    System.out.println("invalid token or integrity is false");
                    System.exit(255);
                    return false;
                } else {
                    can_decript = true;
                }
                // System.out.println("content_decripted_prior_log:" +
                // content_decripted_prior_log);
                String[] result = content_decripted_prior_log.split(";");
                List<String> line = new ArrayList<>();

                for (int j = 0; j < result.length; j++) {
                    line.add(result[j]);
                }
                csvList.add(line);
                // whole_line_sp.put(i, result);
            }
            // System.out.println("svList: \n" + csvList + "\n");
            // System.out.println("size major: \n \n" + csvList.size() + "\n \n");

            // System.out.println("size minor: \n \n" + csvList.get(0).size() + "\n \n");

            String last_action = "no";
            String last_room = "no";

            if (can_decript == true) {

                if (Integer.parseInt(this.Timestamp) > Integer
                        .parseInt(csvList.get(csvList.size() - 1).get(0))) {

                    for (int i = 0; i < csvList.size(); i++) {
                        if (csvList.get(i).get(1).equals(this.Name) && csvList.get(i).get(2).equals(this.Role)) {
                            last_action = csvList.get(i).get(3);
                            last_room = csvList.get(i).get(4);
                        }
                    }

                } else {
                    System.out.println("invalid timestamp");
                    System.exit(255);
                }

                // System.out.println("last_action:" + last_action);
                // System.out.println("last_room:" + last_room);
                // System.out.println("this action:" + this.Action);
                // System.out.println("this room:" + this.Room_id);

                if (last_action.equals("no") && this.Room_id.equals("-99") && this.Action.equals("arrival")) {
                    delete_last_line_log(Log);
                    encryptedString = EncrypAndDecrypt.toEncryptLog(Mg, Token);
                    WorkingFile.write_file(Log, encryptedString);
                    String count_line_check = EncrypAndDecrypt.toEncryptLog(Hash.create_hash(cheksum+Mg), Token);
                    WorkingFile.write_file(Log, count_line_check);
                    return true;
                } else {
                    if (last_action.equals("no") && !this.Room_id.equals("-99") && !this.Action.equals("arrival")) {
                        System.out.println("invalid action");
                        System.exit(255);
                    }
                }

                // if (last_action.equals(this.Action) && last_action.equals("departure") &&
                // !this.Room_id.equals("-99")) {
                // System.out.println("invalid action");
                // System.exit(255);
                // } else {
                // encryptedString = EncrypAndDecrypt.toEncryptLog(Mg, Token);
                // WorkingFile.write_file(Log, encryptedString);
                // return true;
                // }

                if (last_action.equals(this.Action) && last_action.equals("arrival")) {
                    if (this.Room_id.equals("-99") && last_room.equals("-99")) {
                        System.out.println("invalid action");
                        System.exit(255);
                    }
                }

                if (last_action.equals(this.Action) && last_action.equals("arrival")) {
                    if (last_room.equals("-99") && !this.Room_id.equals("-99")) {
                        delete_last_line_log(Log);
                        encryptedString = EncrypAndDecrypt.toEncryptLog(Mg, Token);
                        WorkingFile.write_file(Log, encryptedString);
                        String count_line_check = EncrypAndDecrypt.toEncryptLog(Hash.create_hash(cheksum+Mg), Token);
                        WorkingFile.write_file(Log, count_line_check);
                        return true;
                    }
                }

                if (last_action.equals("arrival") && this.Action.equals("departure")
                        && last_room.equals(this.Room_id)) {
                    delete_last_line_log(Log);
                    encryptedString = EncrypAndDecrypt.toEncryptLog(Mg, Token);
                    WorkingFile.write_file(Log, encryptedString);
                    String count_line_check = EncrypAndDecrypt.toEncryptLog(Hash.create_hash(cheksum+Mg), Token);
                    WorkingFile.write_file(Log, count_line_check);
                    return true;
                }

                if (last_action.equals("departure") && this.Action.equals("arrival") && last_room.equals("-99")
                        && this.Room_id.equals("-99")) {
                    delete_last_line_log(Log);
                    encryptedString = EncrypAndDecrypt.toEncryptLog(Mg, Token);
                    WorkingFile.write_file(Log, encryptedString);
                    String count_line_check = EncrypAndDecrypt.toEncryptLog(Hash.create_hash(cheksum+Mg), Token);
                    WorkingFile.write_file(Log, count_line_check);
                    return true;
                }

                if (last_action.equals("departure") && this.Action.equals("arrival") && last_room.equals("-99")
                        && !this.Room_id.equals("-99")) {
                    System.out.println("invalid action");
                    System.exit(255);
                }

                if (last_action.equals("departure") && this.Action.equals("arrival") && !last_room.equals("-99")
                        && this.Room_id.equals("-99")) {
                    System.out.println("invalid action");
                    System.exit(255);
                }

                if (last_action.equals("departure") && this.Action.equals("arrival") && !last_room.equals("-99")
                        && !this.Room_id.equals("-99")) {
                    delete_last_line_log(Log);
                    encryptedString = EncrypAndDecrypt.toEncryptLog(Mg, Token);
                    WorkingFile.write_file(Log, encryptedString);
                    String count_line_check = EncrypAndDecrypt.toEncryptLog(Hash.create_hash(cheksum+Mg), Token);
                    WorkingFile.write_file(Log, count_line_check);
                    return true;
                }

                // encryptedString = EncrypAndDecrypt.toEncryptLog(Mg, Token);
                // WorkingFile.write_file(Log, encryptedString);
                // return true;
                System.out.println("invalid action");
                System.exit(255);
                return false;
            } else {
                System.out.println("invalid action");
                System.exit(255);
                return false;
            }
        } else {
            if (this.Action.equals("arrival") && this.Room_id.equals("-99")) {
                WorkingFile.createFile(Log);
                encryptedString = EncrypAndDecrypt.toEncryptLog(Mg, Token);
                WorkingFile.write_file(Log, encryptedString);
                String hash_history = Hash.create_hash(Mg);
                // System.out.println("first hash: "+hash_history);

                String count_line_check = EncrypAndDecrypt.toEncryptLog(hash_history, Token);
                // System.out.println("first hash enc: "+count_line_check);

                WorkingFile.write_file(Log, count_line_check);
                return true;
            }
            System.out.println("invalid action");
            System.exit(255);
            return false;

        }

    }

}