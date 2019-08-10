package RootElement;

import simbad.gui.*;
import simbad.sim.*;
import javax.vecmath.Vector3d;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import environments.*;
import robots.RobotFactory;

/**
  Derivate your own code from this example.
 */


public class Main {
	private static EnvironmentDescription environment;

    public static void main(String[] args) {
        // request anti-aliasing so that diagonal lines are not "stair-like"
        System.setProperty("j3d.implicitAntialiasing", "true");
        environment = new EnvironmentDuarte();
        
        FileServer mainFileServer = FileServer.getInstance();
        RobotFactory robotFactory = RobotFactory.getInstance();
        
        environment.add(robotFactory.createRobot(new Vector3d(7,0,-5), "MapperBot1", mainFileServer));
        environment.add(robotFactory.createRobot(new Vector3d(-7,0,5), "MapperBot2", mainFileServer));

        // here we create an instance of the whole Simbad simulator and we assign the newly created environment 
        Simbad frame = new Simbad(environment, false);
        frame.update(frame.getGraphics());
        
    }
}