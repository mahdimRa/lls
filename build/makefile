JFLAGS = -g
JC = javac

CLASS_FILES = l_append/LogAppend.class l_append/Hash.class l_append/EncrypAndDecrypt.class l_append/LogPersonLine.class l_append/ParserCommandi.class l_append/ValidateInput.class l_append/WorkingFile.class l_read/LogRead.class l_read/CoreLogread.class l_read/EncrypAndDecryptLogRead.class l_read/ParserCommandiLogRead.class l_read/ValidateInputLogRead.class l_read/WorkingFileLogRead.class

Default: $(CLASS_FILES) create_bin

%.class: %.java
	$(JC) $<

create_bin:
	jar -cvmf MANIFEST_LA.MF logappend.jar l_append/LogAppend.class l_append/EncrypAndDecrypt.class l_append/LogPersonLine.class l_append/ParserCommandi.class l_append/ValidateInput.class l_append/WorkingFile.class l_append/Hash.class
	cat stub.sh logappend.jar > logappend && chmod +x logappend

	jar -cvmf MANIFEST_LR.MF logread.jar l_read/LogRead.class l_read/CoreLogread.class l_read/EncrypAndDecryptLogRead.class l_read/ParserCommandiLogRead.class l_read/ValidateInputLogRead.class l_read/WorkingFileLogRead.class
	cat stub.sh logread.jar > logread && chmod +x logread

clean:
	$(RM) l_read/*.class l_append/*.class log* 