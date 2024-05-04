
abstract class Argument {
	
	
	protected abstract boolean validArgument(String argument) throws InvalidArgumentException;
	protected abstract String convertToBinString(String argument);	

	
}
