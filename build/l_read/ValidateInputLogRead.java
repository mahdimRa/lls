package l_read;

class ValidateInputLogRead {

    public static boolean timestamp(String ttm) {
        try {
            int number = Integer.parseInt(ttm);
            if (number >= 1 && number <= 1073741823) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static boolean token(String tok) {
        boolean result_tok = tok.matches("^[a-zA-Z0-9]+$");
        if (result_tok) {
            return true;
        } else {
            System.out.println("invalid tokeen have invalid characters. allowd correcter: (a-z, A-Z, and 0-9)");
            System.exit(255);
            return false;
        }
    }

    public static boolean employee_name(String emp) {
        boolean result = emp.matches("[a-zA-Z]+");
        if (result) {
            return true;
        } else {
            return false;
        }

    }

    public static Boolean guest_name(String gst) {
        boolean result_gst = gst.matches("[a-zA-Z]+");
        if (result_gst) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean a_method(String amt) {
        return true;
    }

    public static boolean l_method(String lmt) {
        return true;
    }

    public static boolean room_id(String rmid) {
        // rmid = rmid.replaceFirst("^0+(?!$)", "");
        try {
            int roomidd = Integer.parseInt(rmid);
            if (roomidd >= 0 && roomidd <= 1073741823) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static boolean log(String lg) {
        // boolean resulti = lg.matches("^[a-zA-Z0-9_\\.]+$");

        boolean resulti = lg.matches("^[a-zA-Z0-9/_\\.]+$");
        boolean exis_directory = WorkingFileLogRead.checkDirIsexist(lg);

        if (resulti) {

            if (exis_directory) {
                // System.out.println("log is okkkkey");
                return true;
            } else {
                // System.out.println("log is badddddd !!!");
                return false;
            }
        }else{
            return false;
        }

    }

}