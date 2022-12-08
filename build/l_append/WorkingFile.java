package l_append;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class WorkingFile {

    public static boolean checkDirIsexist(String log) {
        File f = new File(log);
        if (f.exists()) {
            // System.out.println("file before Exists");
            return true;

        } else {
            // System.out.println("File dos not exists before but checki directory exist or not ...");
            try {
                if (f.createNewFile()) {
                    // System.out.println("Directory before Exists");
                    f.delete();
                    return true;
                }
            } catch (IOException e) {
                // System.out.println("ERRROOOOR!!!!!!!  Directory Dosnot Exists!!!!!!");
                return false;
            }
        }
        return false;
    }

    public static void createFile(String filename) {

        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                // System.out.println("File created: " + myObj.getName());
            } else {
                // System.out.println("File already exists.");
            }
        } catch (IOException e) {
            // System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // public static String readFile(String filename) {

    // String str = null;
    // Path fileName = Path.of(filename);

    // try {
    // str = Files.readString(fileName);
    // } catch (IOException e) {
    // e.printStackTrace();
    // }

    // return str;

    // }

    public static  List<String> readFile(String filename) {
        List<String> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            list = br.lines().collect(Collectors.toList());
            // System.out.println(list);
        } catch (IOException ex) {
            // e.printStackTrace();
        }

        return list;
    }

    public static void write_file(String Log, String encryptedString) {

        try {
            // // hashmg();

            // // FileWriter myWriterhash = new FileWriter(Log + "hash");
            // // myWriterhash.write(mghash);
            // // myWriterhash.close();

            PrintWriter myWriter = new PrintWriter(new FileWriter(Log, true));
            myWriter.write(encryptedString);
            myWriter.write("\n");
            myWriter.close();
        } catch (IOException ex) {
            // e.printStackTrace();
        }
        // try {

        // if (myObj.createNewFile()) {
        // System.out.println("File created: " + myObj.getName());
        // } else {
        // System.out.println("File already exists.");
        // }

        // FileWriter myWriter = new FileWriter(Log);
        // myWriter.write(encryptedString);
        // myWriter.close();
        // } catch (IOException ex) {
        // System.out.println("errrorr in file write");
        // }

        // try {
        // File myObj = new File(Log);

        // if (myObj.createNewFile()) {
        // System.out.println("File created: " + myObj.getAbsoluteFile());
        // } else {
        // System.out.println("File already exists.");
        // }
        // System.out.println("beforeeeee");

        // FileWriter myWriter = new FileWriter(Log);
        // myWriter.write(encryptedString);
        // myWriter.close();
        // } catch (IOException ex) {
        // // TODO: handle exception
        // System.out.println("errrorr in file write");

        // }

    }
}