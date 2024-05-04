
class OffArg extends Argument{
	private int numberOfBits;
	private int minValue;
	private int maxValue;
	
	

	public OffArg(int numberOfBits) {
		super();
		this.numberOfBits = numberOfBits;
		this.minValue = (int) (Math.pow(2,numberOfBits-1)*-1);
		this.maxValue = (int) (Math.pow(2,numberOfBits-1) - 1);
	}
	
	@Override
	protected boolean validArgument(String originalArgument) throws InvalidArgumentException {
		String argument = originalArgument.toUpperCase();
		if(!argument.startsWith("0X"))
			throw new InvalidArgumentException(String.format("Expected a hex offset but got '%s' instead: Offset must start with '0x'",originalArgument));
		
		argument = argument.substring(2);
	
		int offsetValue;
		try{
			offsetValue = Integer.parseInt(argument,16);
			if ((offsetValue & (int) Math.pow(2,numberOfBits-1)) != 0) {
				offsetValue = ((int) (Math.pow(2,numberOfBits)-1) ^ offsetValue ) + 1;
				offsetValue = -1*offsetValue;
			}
		}catch(NumberFormatException e) {
			throw new InvalidArgumentException(String.format("Expected hex offset but got '%s' instead: Offset must be an integer",originalArgument));	
		}
		
		if((offsetValue<minValue) || (offsetValue>maxValue)) {
			throw new InvalidArgumentException(String.format("Expected a hex offset but got '%s' instead: Offset value (must be between 0x%s and 0x%s)",originalArgument, Integer.toHexString(minValue).toUpperCase(), Integer.toHexString(maxValue).toUpperCase()));
		}
			
		return true;
	}
	
	@Override
	public String convertToBinString(String argument) {
		argument = argument.substring(2);
		int offsetValue = Integer.parseInt(argument,16);
		String binaryArg = Integer.toBinaryString(offsetValue);
		binaryArg = String.format("%1$"+numberOfBits+"s", binaryArg).replace(' ', '0');
		return binaryArg;
	}
}
