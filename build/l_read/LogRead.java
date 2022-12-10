package l_read;

class LogRead {

	public static void line_mode(String argsl[]) {
		boolean S_mode = false;
		boolean R_mode = false;
		boolean T_mode = false;
		boolean I_mode = false;

		for (int i = 1; i < argsl.length - 1; i++) {

			if (argsl[i].equals("-S")) {
				S_mode = true;
			}
			if (argsl[i].equals("-R")) {
				R_mode = true;
			}
			if (argsl[i].equals("-T")) {
				T_mode = true;
			}
			if (argsl[i].equals("-I")) {
				I_mode = true;
			}
		}

		if (S_mode) {
			if (argsl.length == 4) {
				// call parser S mode
				ParserCommandiLogRead.iput_parse_s_mode(argsl);
			} else {
				System.out.println("invalid command S mode");
				System.exit(255);
			}
		}

		if (R_mode) {
			if (argsl.length == 6) {
				// call parser R mode
				ParserCommandiLogRead.iput_parse_r_mode(argsl);
			} else {
				System.out.println("invalid command R_mode");
				System.exit(255);
			}
		}

		if (T_mode) {
			if (argsl.length == 6) {
				// call parser T mode
				ParserCommandiLogRead.iput_parse_t_mode(argsl);

				// System.out.println("unimplemented");
				System.exit(255);
			} else {
				System.out.println("invalid command T_mode");
				System.exit(255);
			}
		}

		if (I_mode) {
			// call parser I mode
			System.out.println("unimplemented");
			System.exit(255);
		}

		// System.out.println("S mode: "+S_mode);
		// System.out.println("R mode: "+R_mode);
		// System.out.println("T mode: "+T_mode);
		// System.out.println("I mode: "+I_mode);

		// System.out.println(argsl.length);
		// System.out.println("invalid command noone mode");
		System.exit(255);

	}

	public static void main(String args[]) {
		line_mode(args);

	}
}
