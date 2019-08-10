package nl.vu.cs.s2.simbadtest;


import simbad.gui.*;
import simbad.sim.*;
import javax.vecmath.Vector3d;

/**
  Derivate your own code from this example.
 */


public class ExampleMain {
	private static EnvironmentDescription environment;

    public static void main(String[] args) throws RobotFormatException {
        // request anti-aliasing so that diagonal lines are not "stairry"
        System.setProperty("j3d.implicitAntialiasing", "true");
        
        // creation of the environment containing all obstacles and robots
        environment = new EnvironmentDuarte();
        
        addRobotToEnvironment("R2:rname:-6,0,-6");
        addRobotToEnvironment("R2:rname:0,0,0");
        addRobotToEnvironment("R2:rname:6,0,5");
        addRobotToEnvironment("R2:rname:-7,0,5");
        addRobotToEnvironment("R2:rname:7,0,-5");


        // here we create an instance of the whole Simbad simulator and we assign the newly created environment 
        Simbad frame = new Simbad(environment, false);
        frame.update(frame.getGraphics());
    	
    }
    
    public static Agent addRobotToEnvironment(String robotInfo) throws RobotFormatException{
    	String[] parts = robotInfo.split(":");
    	
    	Agent result = null;
    	
    	if(parts.length != 3){
    		throw new RobotFormatException("Robot format error: too many parameters use a:b.");
    	}

    	switch(parts[0]){
	    	case "R1": 
	            ExampleRobot r1 = new ExampleRobot(v3d(parts[2]), parts[1]);
	            result = r1;
	            environment.add(r1);
	            break;
	    	case "R2": 
	    		ExampleRobot2 r2 = new ExampleRobot2(v3d(parts[2]), parts[1]);
	    		result = r2;
	    		environment.add(r2);
	    		break; 
	    	default: throw new RobotFormatException("Invalid robot class provided.");
    	}
    	
    	return result;
    }
    
    
    public static Vector3d v3d(String vals) throws RobotFormatException{
    	String[] parts = vals.split(",");
    	
    	if(parts.length != 3){
    		throw new RobotFormatException("Too many parameters for Vector3d.");
    	}
    	
    	try{
	    	double x = Double.parseDouble(parts[0]);
	    	double y = Double.parseDouble(parts[1]);
	    	double z = Double.parseDouble(parts[2]);
	
			return new Vector3d(x,y,z);
    	}catch(NumberFormatException e){
    		throw new RobotFormatException("Coordinates not correct.");
    	}
    }

} 