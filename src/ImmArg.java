
class ImmArg extends Argument {

	private int numberOfBits;
	private int minValue;
	private int maxValue;
	
	

	public ImmArg(int numberOfBits) {
		super();
		this.numberOfBits = numberOfBits;
		this.minValue = (int) (-1* Math.pow(2,numberOfBits-1));
		this.maxValue = (int) (Math.pow(2,numberOfBits-1) - 1);
	}
	
	@Override
	protected boolean validArgument(String originalArgument) throws InvalidArgumentException {
		if(originalArgument == null) throw new InvalidArgumentException("Expected an immediate but got null instead");
		String argument = originalArgument.toUpperCase();
		if(!argument.startsWith("#"))
			throw new InvalidArgumentException(String.format("Expected an immediate but got '%s' instead: Immediate must start with '#'", originalArgument));
		
		argument = argument.substring(1);
	
		int immediateValue;
		try{
			immediateValue = Integer.parseInt(argument);
			
		}catch(NumberFormatException e) {
			throw new InvalidArgumentException(String.format("Expected an immediate but got '%s' instead: Immediate must be an integer",originalArgument));	
		}
		
		if((immediateValue<minValue) || (immediateValue>maxValue)) {
			throw new InvalidArgumentException(String.format("Expected an immediate but got '%s' instead: Immediate value (must be between %d and %d)",originalArgument,minValue,maxValue));
		}
			
		return true;
	}
	
	@Override
	public String convertToBinString(String argument) {
		argument = argument.substring(1);
		int immediateValue = Integer.parseInt(argument);
		String binaryArg = Integer.toBinaryString(immediateValue);
		
		if(binaryArg.length()>numberOfBits)	binaryArg = binaryArg.substring(binaryArg.length()-numberOfBits);
		
		binaryArg = String.format("%1$"+numberOfBits+"s", binaryArg).replace(' ', '0');
		return binaryArg;
	}

}
