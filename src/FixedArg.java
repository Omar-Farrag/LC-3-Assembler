class FixedArg extends Argument{

	private String argument;
	
	
	public FixedArg(String argument) {
		super();
		this.argument = argument;
	}

	@Override
	protected boolean validArgument(String argument) throws InvalidArgumentException {
		return true;
	}

	@Override
	public String convertToBinString(String dontCare) {
		return this.argument;
	}

}
