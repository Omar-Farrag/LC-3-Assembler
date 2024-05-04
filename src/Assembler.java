import java.util.ArrayList;

public class Assembler {

	private final int wordLength = 16;
    private final Argument regArg = new RegArg(3);
	
	private final Argument fixedArg_3Zeros = new FixedArg("000");
	private final Argument fixedArg_1One = new FixedArg("1");
	private final Argument fixedArg_6Zeros = new FixedArg("000000");
	private final Argument fixedArg_6Ones = new FixedArg("111111");
	private final Argument fixedArg_3Zeros_3Ones_6Zeros = new FixedArg("000111000000");
	private final Argument fixedArg_12Zeros = new FixedArg("000000000000");
	private final Argument fixedArg_4Zeros = new FixedArg("0000");
	
	private final Argument immArg = new ImmArg(5);
	
	private final Argument nzpArg = new NZPArg();
	
	private final Argument offArg_6bits = new OffArg(6);
	private final Argument offArg_8bits = new OffArg(8);
	private final Argument offArg_9bits = new OffArg(9);
	private final Argument offArg_11bits = new OffArg(11);
	
	ArrayList<Instruction> instructions= new ArrayList<Instruction>() 
	{
		private static final long serialVersionUID = 1L;

	{					//			  r i o n f
	    add(new Instruction("ADD",  1,3,0,0,0,1).addExpectedArguments(new Argument[] {regArg,regArg,fixedArg_3Zeros,regArg}));
		add(new Instruction("ADD",  1,2,1,0,0,1).addExpectedArguments(new Argument[] {regArg,regArg,fixedArg_1One,immArg}));
		add(new Instruction("AND",  5,3,0,0,0,1).addExpectedArguments(new Argument[] {regArg,regArg,fixedArg_3Zeros,regArg}));
		add(new Instruction("AND",  5,2,1,0,0,1).addExpectedArguments(new Argument[] {regArg,regArg,fixedArg_1One,immArg}));
		add(new Instruction("BR",   0,0,0,1,1,0).addExpectedArguments(new Argument[] {nzpArg,offArg_9bits}));
		add(new Instruction("JMP", 12,1,0,0,0,2).addExpectedArguments(new Argument[] {fixedArg_3Zeros,regArg,fixedArg_6Zeros}));
		add(new Instruction("JSR",  4,0,0,1,0,1).addExpectedArguments(new Argument[] {fixedArg_1One,offArg_11bits}));
		add(new Instruction("JSRR", 4,1,0,0,0,2).addExpectedArguments(new Argument[] {fixedArg_3Zeros,regArg,fixedArg_6Zeros}));
		add(new Instruction("LD",   2,1,0,1,0,0).addExpectedArguments(new Argument[] {regArg,offArg_9bits}));
		add(new Instruction("LDI", 10,1,0,1,0,0).addExpectedArguments(new Argument[] {regArg,offArg_9bits}));
		add(new Instruction("LDR",  6,2,0,1,0,0).addExpectedArguments(new Argument[] {regArg,regArg,offArg_6bits}));
		add(new Instruction("LEA", 14,1,0,1,0,0).addExpectedArguments(new Argument[] {regArg,offArg_9bits}));
		add(new Instruction("NOT",  9,2,0,0,0,1).addExpectedArguments(new Argument[] {regArg,regArg,fixedArg_6Ones}));
		add(new Instruction("RET", 12,0,0,0,0,1).addExpectedArguments(new Argument[] {fixedArg_3Zeros_3Ones_6Zeros}));
		add(new Instruction("RTI",  8,0,0,0,0,1).addExpectedArguments(new Argument[] {fixedArg_12Zeros}));
		add(new Instruction("ST",   3,1,0,1,0,0).addExpectedArguments(new Argument[] {regArg,offArg_9bits}));
		add(new Instruction("STI", 11,1,0,1,0,0).addExpectedArguments(new Argument[] {regArg,offArg_9bits}));
		add(new Instruction("STR",  7,2,0,1,0,0).addExpectedArguments(new Argument[] {regArg,regArg,offArg_6bits}));
		add(new Instruction("TRAP",15,0,0,1,0,1).addExpectedArguments(new Argument[] {fixedArg_4Zeros,offArg_8bits}));
	}};
	
	
	public String decodeAssemblyInstruction(String input) throws InvalidArgumentException, InvalidInstructionException {
		
		String[] instructionFragments = input.split(" ",2);
		String instructionName = instructionFragments[0];
		int numAdds = 2;
		int numAnds = 2;
		
		int remainingAdds = numAdds;
		int remainingAnds = numAnds;
		
		ArrayList<String> exceptionMessages = new ArrayList<String>();
		exceptionMessages.add(String.format("If you meant %s R#, R#, R# : ", instructionName.toUpperCase()));
		exceptionMessages.add(String.format("If you meant %s R#, R#, #Imm : ", instructionName.toUpperCase()));
		
		for(Instruction instruction : instructions) {
			if(instruction.matches(instructionName)) {
				try {
					if(instructionName.equalsIgnoreCase("ADD"))
						remainingAdds--;
					else if(instructionName.equalsIgnoreCase("AND"))
						remainingAnds--;
					return instruction.decodeInstruction((instructionFragments.length == 1) ? "" : instructionFragments[1]);					
				}catch(InvalidArgumentException e) {
					if(remainingAdds == 2 && remainingAnds == 2)
						throw e;

					if(remainingAdds == 0 || remainingAnds == 0) {
						exceptionMessages.set(1, exceptionMessages.get(1) + e.getMessage());
						throw new InvalidArgumentException(String.join("\n",exceptionMessages));						
					}
					
					exceptionMessages.set(0, exceptionMessages.get(0) + e.getMessage());
						
				}
			}
			
		}
		throw new InvalidInstructionException(String.format("'%s' is not a valid instruction. Expecting a valid LC-3 instruction",instructionName));
	}
	
	public String convertBinToHex(String binaryCode) {
       
		int decimal = Integer.parseInt(binaryCode, 2);
        String hexString = String.format("0x%1$"+ wordLength/4 + "s", Integer.toHexString(decimal).toUpperCase()).replace(' ', '0');
        return hexString;
	}
}
