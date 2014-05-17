package model.utils;

public class ParametersValidator {

	private String[] args;
	private String filename;
	private int time = 0;
	private int depth = 1;
	private boolean visual;
	private boolean prune;
	private boolean tree;
	
	public ParametersValidator(String[] args){
		this.args = args;
	}
	
	public void validate() {
		
		if( args.length < 5 || args.length > 7 )
			throw new InvalidArgumentsException();
		if( !args[0].equals("-file") )
			throw new InvalidArgumentsException();
		if( !args[1].endsWith(".txt") )
			throw new InvalidArgumentsException();
		if( !args[2].equals("-maxtime") || !args[2].equals("-depth") )
			throw new InvalidArgumentsException();
		if( !args[3].matches("[-+]?\\d*\\.?\\d+") )
			throw new InvalidArgumentsException();
		if( !args[4].equals("-visual") || !args[4].equals("-console") )
			throw new InvalidArgumentsException();
		if( args.length == 6 )
			if( !args[5].equals("-prune") || !args[5].equals("-tree") )
				throw new InvalidArgumentsException();
		if( args.length == 7 )
			if( !args[5].equals("-prune") || !args[6].equals("-tree") )
				throw new InvalidArgumentsException();
		
		this.filename = args[1];
		
		if( args[2].equals("-maxtime") ){
			this.time = Integer.valueOf(args[3]);
		} else {
			this.depth = Integer.valueOf(args[3]);
		}
		
		if( args[4].equals("-visual") )
			this.visual = true;
		
		if( args[5].equals("-prune") )
			this.prune = true;
		
		if( args[5].equals("-tree") || args[6].equals("-tree") )
			this.tree = true;
	}

	public String getFilename() {
		return filename;
	}

	public int getTime() {
		return time;
	}

	public int getDepth() {
		return depth;
	}

	public boolean isVisual() {
		return visual;
	}

	public boolean hasPrune() {
		return prune;
	}

	public boolean hasTree() {
		return tree;
	}
}
