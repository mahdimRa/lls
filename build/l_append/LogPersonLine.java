package l_append;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes.Name;

class LogPersonLine {

    private String Timestamp;
    private String Token;
    private String Employee_namel;
    private String Guest_name;
    private String A;
    private String L;
    private String Room_id;
    private String Log;
    private String mg;
    private String mghash;
    private String encryptedString;
    private String content_decrypt_prior_file;

    public void setTimestamp(String newName) {
        this.Timestamp = newName;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setToken(String newName) {
        this.Token = newName;
    }

    public String getToken() {
        return Token;
    }

    public void setEmployee_namel(String newName) {
        this.Employee_namel = newName;
    }

    public String getEmployee_namel() {
        return Employee_namel;
    }

    public void setGuest_name(String newName) {
        this.Guest_name = newName;
    }

    public String getGuest_name() {
        return Guest_name;
    }

    public void setA(String newName) {
        this.A = newName;
    }

    public String getA() {
        return A;
    }

    public void setL(String newName) {
        this.L = newName;
    }

    public String getL() {
        return L;
    }

    public void setRoom_id(String newName) {
        int temprmid = Integer.parseInt(newName);
        String Roomidcorect = Integer.toString(temprmid);
        this.Room_id = Roomidcorect;
    }

    public String getRoom_id() {
        return Room_id;
    }

    public void setLog(String newName) {
        this.Log = newName;
    }

    public String getLog() {
        System.out.println("this iss  logggg: " + Log);

        return Log;
    }

    private void mgcreate() {

        if (Timestamp != null) {
            mg = "-T " + Timestamp;
        }
        if (Employee_namel != null) {
            mg = mg + " -E " + Employee_namel;
        }
        if (Token != null) {
            mg = mg + " -K " + Token;
        }
        if (Guest_name != null) {
            mg = mg + " -G " + Guest_name;
        }
        if (A != null) {
            mg = mg + " -A ";
        }
        if (L != null) {
            mg = mg + " -L ";
        }
        if (Room_id != null) {
            mg = mg + " -R " + Room_id;
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
            System.out.println(priorlogfile);

            for (int i = 0; i < priorlogfile.size(); i++) {
                can_decript = false;
                String content_decripted_prior_log = EncrypAndDecrypt.decryptPriorFile(priorlogfile.get(i), Token);
                if (content_decripted_prior_log == null) {
                    System.out.println("hacked");
                    return false;
                }else{
                    can_decript = true;
                }
            }

            if (can_decript == true) {
                encryptedString = EncrypAndDecrypt.toEncryptLog(mg, Token);
                WorkingFile.write_file(Log, encryptedString);
                return true;
            } else {
                return false;
            }
        } else {
            WorkingFile.createFile(Log);
            encryptedString = EncrypAndDecrypt.toEncryptLog(mg, Token);
            WorkingFile.write_file(Log, encryptedString);
            return true;
        }

    }

}