package nl.vu.cs.s2.simbadtest;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.vecmath.Vector3d;
import simbad.sim.Agent;
import simbad.sim.CameraSensor;
import simbad.sim.RangeSensorBelt;
import simbad.sim.RobotFactory;


public class ExampleRobot2 extends Agent {

	private String currentMode;
	private boolean foundWall;
	PrintStream out;
	
	RangeSensorBelt sonar;
	RangeSensorBelt bumper;
	CameraSensor camera;
    BufferedImage cameraImage;
    
    LinkedList<Event> events;
    

    public ExampleRobot2(Vector3d position, String name) {
        super(position, name);
        
        foundWall = false;
        
        out = new PrintStream(System.out);
        
        // Add bumpers
         bumper = RobotFactory.addBumperBeltSensor(this, 12);
        // Add sonars
         sonar = RobotFactory.addSonarBeltSensor(this, 4);     
         
         camera = RobotFactory.addCameraSensor(this);
         // reserve space for image capturee
         cameraImage = camera.createCompatibleImage();
         
         events = new LinkedList<Event>();

    }

    /** This method is called by the simulator engine on reset. */
    public void initBehavior() {
        System.out.println("I exist and my name is " + this.name);
        foundWall = false;
        events = new LinkedList<Event>();

    }

    /** This method is called cyclically (20 times per second) by the simulator engine. */
    public void performBehavior() {
    	if(this.getCounter() % 20 == 0){


    		if(foundWall){
    			checkBumperCollision();

    			if(stuckAtCorner()){
        			foundWall = false;
    			}
    			
    			if(getRightSonarMeasurement() > 0.1){
    				rotate(-90);
    			}
    			
    			if(robotFrontTooClose()){
    				recordEvent(Event.CORNER);
    	    		
    				takePictureFromCorner();
        			rotate(90);
    			}
    		}else{
    			checkBumperCollision();

        		if(robotFrontTooClose()){        			
    				recordEvent(Event.WALL);
        			rotate(90);
        			foundWall = true;
        		}
    		}
			recordEvent(Event.FORWARD);
        	this.setTranslationalVelocity(1);

    	}
    }
    
    private void checkBumperCollision() {
		if(frontBumperCollision()){
			foundWall = false;
			rotate(180);
			out.println("BUMPER DETECT");
		}
		
	}

	private boolean stuckAtCorner() {
    	int cornerCount = 0;
    	
    	for(int i = 0; i < events.size(); i++){
    		if(events.get(i) == Event.CORNER){
    			cornerCount += 1;
    		}
    	}
    	
		return cornerCount >= 4;
	}

	private void recordEvent(Event ev) {
		events.push(ev);
		System.out.println(events.peek());
		if(events.size() > 10){
			events.removeLast();
		}
	}

	private void takePictureFromCorner() {
    	rotate(135);
    	camera.copyVisionImage(cameraImage); 
    	
    	File outputfile = new File("saved.png");
        try {
			ImageIO.write(cameraImage, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	rotate(-135);
	}

	private double getFrontSonarMeasurement(){
    	return sonar.getMeasurement(0);
    }
    
    private double getRightSonarMeasurement(){
    	return sonar.getMeasurement(3);
    }
    
    private double getBackSonarMeasurement(){
    	return sonar.getMeasurement(2);
    }
    
    private double getLeftSonarMeasurement(){
    	return sonar.getMeasurement(1);
    }
	
	private void rotate(double angle){
		this.rotateY((2 * Math.PI * angle) / 360);
	}

	private boolean frontBumperCollision(){

		return bumper.getMeasurement(1) < 0.2 || bumper.getMeasurement(11) < 0.2;
	}
	
	private boolean robotFrontTooClose() {
//		for(int i = 0; i < sonar.getNumSensors(); i++){
//	    	
//		}
		if(getFrontSonarMeasurement() < 0.5){
    		return true;
    	}
		return false;
	}
}