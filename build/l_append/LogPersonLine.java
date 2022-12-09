package l_append;

import java.io.File;
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
        System.out.println("this iss  action: " + this.Action);

    }

    public String getAction() {

        return this.Action;
    }

    public void setRoom_id(String newName) {
        int temprmid = Integer.parseInt(newName);
        String Roomidcorect = Integer.toString(temprmid);
        this.Room_id = Roomidcorect;
        System.out.println("this room is : " + this.Room_id);

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

    public boolean file() {
        this.mgcreate();

        // WorkingFile.readFile(A);

        File f = new File(Log);
        if (f.exists()) {
            boolean can_decript = false;
            List<String> priorlogfile = new ArrayList<>();
            priorlogfile = WorkingFile.readFile(Log);
            // System.out.println("size progfile: " + priorlogfile.size());
            List<List<String>> csvList = new ArrayList<List<String>>();
            for (int i = 0; i < priorlogfile.size(); i++) {
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
            System.out.println("svList: \n" + csvList + "\n");
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

                System.out.println("last_action:" + last_action);
                System.out.println("last_room:" + last_room);
                System.out.println("this action:" + this.Action);
                System.out.println("this room:" + this.Room_id);

                if (last_action.equals("no") && this.Room_id.equals("-99")) {
                    encryptedString = EncrypAndDecrypt.toEncryptLog(Mg, Token);
                    WorkingFile.write_file(Log, encryptedString);
                    return true;
                } else {
                    if (last_action.equals("no") && !this.Room_id.equals("-99")) {
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
                        encryptedString = EncrypAndDecrypt.toEncryptLog(Mg, Token);
                        WorkingFile.write_file(Log, encryptedString);
                        return true;
                    }
                }

                if (last_action.equals("arrival") && this.Action.equals("departure")
                        && last_room.equals(this.Room_id)) {
                    encryptedString = EncrypAndDecrypt.toEncryptLog(Mg, Token);
                    WorkingFile.write_file(Log, encryptedString);
                    return true;
                }

                if (last_action.equals("departure") && this.Action.equals("arrival") && last_room.equals("-99")
                        && this.Room_id.equals("-99")) {
                    encryptedString = EncrypAndDecrypt.toEncryptLog(Mg, Token);
                    WorkingFile.write_file(Log, encryptedString);
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
                    encryptedString = EncrypAndDecrypt.toEncryptLog(Mg, Token);
                    WorkingFile.write_file(Log, encryptedString);
                    return true;
                }

                // encryptedString = EncrypAndDecrypt.toEncryptLog(Mg, Token);
                // WorkingFile.write_file(Log, encryptedString);
                // return true;
                return false;
            } else {
                return false;
            }
        } else {
            if (this.Action.equals("arrival") && this.Room_id.equals("-99")) {
                WorkingFile.createFile(Log);
                encryptedString = EncrypAndDecrypt.toEncryptLog(Mg, Token);
                WorkingFile.write_file(Log, encryptedString);
                return true;
            }
            System.out.println("invalid action");
            System.exit(255);
            return false;

        }

    }

}