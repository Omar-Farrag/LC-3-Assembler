import java.util.ArrayList;

class Instruction {
	
	private final int numOfCodeBits = 4;
	
	private String instructionName;
	private int instructionCode;
	private int numRegArguments;
	private int numImmArguments;
	private int numOffArguments;
	private int numNZPArguments;
	private int totalUnfixedArguments;
	private ArrayList<Argument> expectedArguments;

	public Instruction(String instructionName, int instructionCode, int numRegArguments, int numImmArguments,
			int numOffArguments,  int numNZPArguments, int numFixedArguments) {
		super();
		this.instructionName = instructionName;
		this.instructionCode = instructionCode;
		this.numRegArguments = numRegArguments;
		this.numImmArguments = numImmArguments;
		this.numOffArguments = numOffArguments;
		this.numNZPArguments = numNZPArguments;
		this.totalUnfixedArguments = this.numRegArguments+this.numImmArguments+this.numOffArguments+this.numNZPArguments;
		this.expectedArguments = new ArrayList<Argument>();
	}
	
	public Instruction addExpectedArguments(Argument[] arguments) {
		for(Argument arg : arguments)
			expectedArguments.add(arg);
		
		return this;
	}
	
	public boolean matches(String argument) {
		return instructionName.equalsIgnoreCase(argument);
	}
	
	public String decodeInstruction(String arguments) throws InvalidArgumentException{
		String binaryInstruction = Integer.toBinaryString(instructionCode);
		binaryInstruction =  String.format("%1$"+numOfCodeBits+"s",binaryInstruction).replace(' ', '0');
		
		int numReceivedArgs;
		int receivedArgsCounter = 0;
		String[] receivedArgs = arguments.split(",");
		
		if(arguments.isEmpty()) numReceivedArgs = 0;
		else numReceivedArgs = receivedArgs.length;
			
		
		if(numReceivedArgs < totalUnfixedArguments) {
			throw new InvalidArgumentException(String.format("Too few arguments. '%s' expects %d arguments. Ensure there are commas between arguments",instructionName,totalUnfixedArguments));
		}
		
		if(numReceivedArgs > totalUnfixedArguments) {
			throw new InvalidArgumentException(String.format("Too many arguments. '%s' expects %d arguments",instructionName,totalUnfixedArguments));
		}
		
		for(int i = 0; i<expectedArguments.size();i++) {
			if(expectedArguments.get(i).getClass() == FixedArg.class)
				binaryInstruction += expectedArguments.get(i).convertToBinString("");
			else if(expectedArguments.get(i).validArgument(receivedArgs[receivedArgsCounter].trim()))	
				binaryInstruction += expectedArguments.get(i).convertToBinString(receivedArgs[receivedArgsCounter++].trim());
		}
		return binaryInstruction;
	}
	
}
