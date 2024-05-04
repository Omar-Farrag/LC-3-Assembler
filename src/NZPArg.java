import java.util.ArrayList;
import java.util.Arrays;

class NZPArg extends Argument {

	
	@Override
	protected boolean validArgument(String originalArgument) throws InvalidArgumentException {
		String argument = originalArgument.toUpperCase();
		ArrayList<String> possibleAlternations = new ArrayList<String>(Arrays.asList(
				"N","Z","P","NZ","NP","ZP","NZP"));
		
		if(!possibleAlternations.contains(argument))
			throw new InvalidArgumentException(String.format("Expected a combination of N,Z,P but got '%s' instead: Accepted arguments are {'%s'}"
					,originalArgument,String.join("', '", possibleAlternations)));
				
		return true;
	}
	
	
	@Override
	public String convertToBinString(String argument) {
		
		switch(argument) {
			case "N":
				return "100";
			case "Z":
				return "010";
			case "P":
				return "001";
			case "NZ":
				return "110";
			case "NP":
				return "101";
			case "ZP":
				return "011";
			case "NZP":
				return "111";
		}
		return "Big man ting you didn't properly validate the argument";
	}



}
