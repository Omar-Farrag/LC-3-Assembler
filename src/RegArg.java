
class RegArg extends Argument {
	
	private int numberOfBits;
	private int minValue;
	private int maxValue;
	
	

	public RegArg(int numberOfBits) {
		super();
		this.numberOfBits = numberOfBits;
		this.minValue = 0;
		this.maxValue = (int) (Math.pow(2,numberOfBits) - 1);
	}

	@Override
	protected boolean validArgument(String originalArgument) throws InvalidArgumentException {
		String argument = originalArgument.toUpperCase();
		if(!argument.startsWith("R"))
			throw new InvalidArgumentException(String.format("Expected a register number but got '%s' instead: Register number must start with 'R'",originalArgument));
		
		argument = argument.substring(1);
	
		int registerNumber;
		try{
			registerNumber = Integer.parseInt(argument);
		}catch(NumberFormatException e) {
			throw new InvalidArgumentException(String.format("Expected a register number but got '%s' instead: Register number must be an integer",originalArgument));	
		}
		
		if((registerNumber<minValue) || (registerNumber>maxValue)) {
			throw new InvalidArgumentException(String.format("Expected a register number but got '%s' instead: Invalid register number (must be between %d and %d)", originalArgument, minValue,maxValue));
		}
			
		return true;
	}
	
	@Override
	public String convertToBinString(String argument) {
		argument = argument.substring(1);
		int registerNumber = Integer.parseInt(argument);
		String binaryArg = Integer.toBinaryString(registerNumber);
		binaryArg = String.format("%1$"+numberOfBits+"s", binaryArg).replace(' ', '0');
		return binaryArg;
	}	
}
