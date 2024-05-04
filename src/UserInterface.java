
class UserInterface {
	
	private final Assembler assembler = new Assembler();
	private final String terminationString = "exit";
	private final String welcomeMessage = "                        _         _____            ____                           \r\n"
			+ "                       | |       / ____|          |___ \\                          \r\n"
			+ "                       | |      | |       ______    __) |                         \r\n"
			+ "                       | |      | |      |______|  |__ <                          \r\n"
			+ "                       | |____  | |____            ___) |                         \r\n"
			+ "                       |______|  \\_____|          |____/                          \r\n"
			+ "                                                                                  \r\n"
			+ "                                                                                  \r\n"
			+ "              _____    _____   ______   __  __   ____    _        ______   _____  \r\n"
			+ "     /\\      / ____|  / ____| |  ____| |  \\/  | |  _ \\  | |      |  ____| |  __ \\ \r\n"
			+ "    /  \\    | (___   | (___   | |__    | \\  / | | |_) | | |      | |__    | |__) |\r\n"
			+ "   / /\\ \\    \\___ \\   \\___ \\  |  __|   | |\\/| | |  _ <  | |      |  __|   |  _  / \r\n"
			+ "  / ____ \\   ____) |  ____) | | |____  | |  | | | |_) | | |____  | |____  | | \\ \\ \r\n"
			+ " /_/    \\_\\ |_____/  |_____/  |______| |_|  |_| |____/  |______| |______| |_|  \\_\\\r\n"
			+ "                                                                                  \r\n"
			+ "                                                                                  ";
	
	private final String goodbyeMessage = "\r\n"
			+ "   _____ _    _ _______ ______ _   _   _______       _____ \r\n"
			+ "  / ____| |  | |__   __|  ____| \\ | | |__   __|/\\   / ____|\r\n"
			+ " | |  __| |  | |  | |  | |__  |  \\| |    | |  /  \\ | |  __ \r\n"
			+ " | | |_ | |  | |  | |  |  __| | . ` |    | | / /\\ \\| | |_ |\r\n"
			+ " | |__| | |__| |  | |  | |____| |\\  |    | |/ ____ \\ |__| |\r\n"
			+ "  \\_____|\\____/   |_|  |______|_| \\_|    |_/_/    \\_\\_____|\r\n"
			+ "                                                           \r\n"
			+ "                                                           \r\n"
			+ ""
			+ "";
			
	private final String continuousMessage = String.format("Enter an LC-3 assembly instruction or type '%s' to exit: ",terminationString);		
	
	public void begin() {
		
		IO_Delegate.println(welcomeMessage);
		IO_Delegate.print(continuousMessage);
		String userInput;
		while(!(userInput = IO_Delegate.readln()).equalsIgnoreCase(terminationString)) {
						
			try {
				String binaryCode = assembler.decodeAssemblyInstruction(userInput);
				String hexCode = assembler.convertBinToHex(binaryCode);
				
				IO_Delegate.println(String.format("Binary Machine Code: %s",binaryCode));
				IO_Delegate.println(String.format("Hex Machine Code: %s",hexCode));
				
				
			}catch(InvalidArgumentException | InvalidInstructionException e) {
				IO_Delegate.println(e.getMessage());
				
			}
			
			IO_Delegate.println("");
			IO_Delegate.print(continuousMessage);
		}
		IO_Delegate.println("");
		IO_Delegate.println(goodbyeMessage);
	}

}
