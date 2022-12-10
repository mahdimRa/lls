package l_read;

class ParserCommandiLogRead {

    public static void iput_parse_s_mode(String args[]){
        String logfile;
        String token = null;
        logfile = args[args.length - 1];
        Boolean isdir = ValidateInputLogRead.log(logfile);
        if (isdir == false) {
            System.out.println("invalid there is no log file'S path");
            System.exit(255);
        }

		for (int i = 0; i < args.length - 2; i++) {

			if (args[i].equals("-K")) {
				token = args[i+1];
			}
		}

        Boolean toke_check = ValidateInputLogRead.token(logfile);
        if (toke_check == false) {
            System.out.println("invalid charecters of toke is not vail");
            System.exit(255);
        }
        CoreLogread.coreSMode(token,logfile);
    }


    public static void iput_parse_r_mode(String args[]){
        String logfile;
        String token = null;
        String dashE = null;
        String ename = null;
        String dashG = null;
        String gname = null;

        logfile = args[args.length - 1];
        Boolean isdir = ValidateInputLogRead.log(logfile);
        if (isdir == false) {
            System.out.println("invalid there is no log file'S path");
            System.exit(255);
        }

        for (int i = 0; i < args.length - 2; i++) {

			if (args[i].equals("-K")) {
				token = args[i+1];
			}

            if (args[i].equals("-E")) {
                dashE = "employee";
				ename = args[i+1];
			}
            if (args[i].equals("-G")) {
                dashG = "guest";
				gname = args[i+1];
			}
		}

        Boolean toke_check = ValidateInputLogRead.token(logfile);
        if (toke_check == false) {
            System.out.println("invalid charecters of toke is not vail");
            System.exit(255);
        }
        if(dashE != null && dashG == null){
            CoreLogread.coreRMode(token,dashE,ename,logfile);

        }
        if(dashG != null && dashE == null){
            CoreLogread.coreRMode(token,dashG,gname,logfile);

        }

    }


    public static Boolean input_parser(String args[]) {

        String tim = null;
        String tok = null;
        String emnam = null;
        String gunam = null;
        String anm = null;
        String lnm = null;
        String roid = null;
        String logfile = null;

        Integer count_cmmand = 0;

        for (int i = 0; i < args.length - 2; i++) {

            if (args[i].equals("-T")) {
                tim = args[i];
                count_cmmand +=1;
                // Boolean result =  ValidateInputLogRead.timestamp(tim);
                // if (result == false) {
                //     return false;
                // }
            }

            if (args[i].equals("-K")) {
                tok = args[i + 1];
                count_cmmand +=2;
                Boolean result = ValidateInputLogRead.token(tok);
                if (result == false) {
                    return false;
                }
            }

            if (args[i].equals("-E")) {
                emnam = args[i + 1];
                count_cmmand +=2;
                Boolean result = ValidateInputLogRead.employee_name(emnam);
                if (result == false) {
                    return false;
                }
            }

            if (args[i].equals("-G")) {
                gunam = args[i + 1];
                count_cmmand +=2;
                Boolean result = ValidateInputLogRead.guest_name(gunam);
                if (result == false) {
                    return false;
                }
            }

            if (args[i].equals("-S")) {
                anm = args[i];
                count_cmmand +=1;
                Boolean result = ValidateInputLogRead.a_method(anm);
                if (result == false) {
                    return false;
                }
            }

            if (args[i].equals("-I")) {
                lnm = args[i];
                count_cmmand +=1;
                Boolean result = ValidateInputLogRead.l_method(lnm);
                if (result == false) {
                    return false;
                }
            }

            if (args[i].equals("-R")) {
                roid = args[i];
                count_cmmand +=1;
                // Boolean result = ValidateInputLogRead.room_id(roid);
                // if (result == false) {
                //     return false;
                // }
            }

        }

        if (count_cmmand != (args.length - 1)){
            System.out.println("invalid command");
            System.exit(255);
        }

        logfile = args[args.length - 1];
        Boolean isdir = ValidateInputLogRead.log(logfile);
        if (isdir == false) {
            return false;
        }

        if (emnam != null && gunam != null) {
            System.out.println("invalid there are employee and guest in a command ");
            System.exit(255);
        }

        if (tim != null && tok != null && logfile != null) {
            if ((emnam != null || gunam != null) && (anm != null || lnm != null)) {
                // LogPersonLine obj = new LogPersonLine();
                // if (tim != null)
                //     obj.setTimestamp(tim);
                // if (tok != null)
                //     obj.setToken(tok);
                // if (emnam != null) {
                //     obj.setName(emnam);
                //     obj.setRole("employee");
                // }
                // if (gunam != null)
                // {
                //     obj.setName(gunam);
                //     obj.setRole("guest");
                // }
                // if (anm != null)
                //     obj.setAction("arrival");
                // if (lnm != null)
                //     obj.setAction("departure");
                // if (roid != null)
                //     obj.setRoom_id(roid);
                // if (logfile != null)
                //     obj.setLog(logfile);
                // // System.out.println("logfile: " + obj.getLog());

                // // if (obj.encrypti() == false) {
                // // return false;
                // // }
                // // obj.decripti();
                // Boolean resp = obj.file();
                // if (resp == false)
                //     return false;

            } else {
                return false;
            }
        } else {
            return false;
        }

        return true;

    }

}