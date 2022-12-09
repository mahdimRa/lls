package l_read;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class LogRead {

	// public static void line_mode(String argsl[]) {
	// 	if (ParserCommandi.input_parser(argsl) == false) {
	// 		return255_invalid();
	// 	}
	// }

	// public static void batch_mode(String batchfile) {
	// 	try {
	// 		File myObj = new File(batchfile);
	// 		Scanner myReader = new Scanner(myObj);

	// 		while (myReader.hasNextLine()) {
	// 			String data = myReader.nextLine();
	// 			String[] strSplit = data.split(" ");
	// 			Boolean line_parse_b = ParserCommandi.input_parser(strSplit);
	// 			if (line_parse_b == false) {
	// 				System.out.println("invalid");
	// 			}
	// 		}

	// 		myReader.close();
	// 	} catch (FileNotFoundException e) {
	// 		return255_invalid();
	// 	}
	// }

	// public static void return255_invalid() {
	// 	System.out.println("invalid");
	// 	System.exit(255);
	// }

	public static void main(String args[]) {

		// if (args[0].equals("-S")) {
		// 	if (args.length == 2) {
		// 		batch_mode(args[1]);
		// 	} else {
		// 		return255_invalid();
		// 	}
		// } else {
		// 	line_mode(args);
		// }

	}
}
