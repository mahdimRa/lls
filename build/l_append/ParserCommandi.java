package l_append;

class ParserCommandi {
    public static Boolean input_parser(String args[]) {
        String tim = null;
        String tok = null;
        String emnam = null;
        String gunam = null;
        String anm = null;
        String lnm = null;
        String roid = null;
        String logfile = null;

        for (int i = 0; i < args.length - 2; i++) {

            if (args[i].equals("-T")) {
                tim = args[i + 1];
                Boolean result = ValidateInput.timestamp(tim);
                if (result == false) {
                    return false;
                }
            }

            if (args[i].equals("-K")) {
                tok = args[i + 1];
                Boolean result = ValidateInput.token(tok);
                if (result == false) {
                    return false;
                }
            }

            if (args[i].equals("-E")) {
                emnam = args[i + 1];
                Boolean result = ValidateInput.employee_name(emnam);
                if (result == false) {
                    return false;
                }
            }

            if (args[i].equals("-G")) {
                gunam = args[i + 1];
                Boolean result = ValidateInput.guest_name(gunam);
                if (result == false) {
                    return false;
                }
            }

            if (args[i].equals("-A")) {
                anm = args[i];
                Boolean result = ValidateInput.a_method(anm);
                if (result == false) {
                    return false;
                }
            }

            if (args[i].equals("-L")) {
                lnm = args[i];
                Boolean result = ValidateInput.l_method(lnm);
                if (result == false) {
                    return false;
                }
            }

            if (args[i].equals("-R")) {
                roid = args[i + 1];
                Boolean result = ValidateInput.room_id(roid);
                if (result == false) {
                    return false;
                }
            }

        }

        logfile = args[args.length - 1];
        Boolean isdir = ValidateInput.log(logfile);
        if (isdir == false) {
            return false;
        }

        if (tim != null && tok != null && logfile != null) {
            if (emnam != null || gunam != null) {
                LogPersonLine obj = new LogPersonLine();
                if (tim != null)
                    obj.setTimestamp(tim);
                if (tok != null)
                    obj.setToken(tok);
                if (emnam != null)
                    obj.setEmployee_namel(emnam);
                if (gunam != null)
                    obj.setGuest_name(gunam);
                if (anm != null)
                    obj.setA(anm);
                if (lnm != null)
                    obj.setL(lnm);
                if (roid != null)
                    obj.setRoom_id(roid);
                if (logfile != null)
                    obj.setLog(logfile);
                System.out.println("logfile: " + obj.getLog());

                // if (obj.encrypti() == false) {
                // return false;
                // }
                // obj.decripti();
                Boolean resp = obj.file();
                if (resp == false)
                    return false;

            } else {
                return false;
            }
        } else {
            return false;
        }

        return true;

    }

}