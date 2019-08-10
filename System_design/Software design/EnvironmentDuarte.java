package nl.vu.cs.s2.simbadtest;

import java.awt.Color;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import simbad.sim.Box;
import simbad.sim.EnvironmentDescription;
import simbad.sim.Wall;

public class EnvironmentDuarte extends EnvironmentDescription {
	public EnvironmentDuarte() {
		
		// turn on the lights
        this.light1IsOn = true;
        this.light2IsOn = true;
        
        // enable the physics engine in order to have better physics effects on the objects
        this.setUsePhysics(true);
        
        // show the axes so that we know where things are
        this.showAxis(true);
        
        this.setWorldSize(20);
        
        Wall w1 = new Wall(new Vector3d(10, 0, 0), 20, 2, this);
        w1.setColor(new Color3f(Color.BLUE));
        w1.rotate90(1);
        add(w1);
        
        Wall w2 = new Wall(new Vector3d(-10, 0, 0), 20, 2, this);
        w2.setColor(new Color3f(Color.BLUE));
        w2.rotate90(1);
        add(w2);
        
        Wall w3 = new Wall(new Vector3d(0, 0, -10), 20, 2, this);
        w3.setColor(new Color3f(Color.GREEN));
        add(w3);
        
        Wall w4 = new Wall(new Vector3d(0, 0, 10), 20, 2, this);
        w4.setColor(new Color3f(Color.GREEN));
        add(w4);
        
        Wall roomWall1 = new Wall(new Vector3d(0, 0, 6), 8, 1, this);
        roomWall1.setColor(new Color3f(Color.RED));
        roomWall1.rotate90(1);
        add(roomWall1);
        
        Wall roomWall2 = new Wall(new Vector3d(0, 0, -6), 8, 1, this);
        roomWall2.setColor(new Color3f(Color.RED));
        roomWall2.rotate90(1);
        add(roomWall2);
        
        Wall roomWall3 = new Wall(new Vector3d(-6, 0, 2), 8, 1, this);
        roomWall3.setColor(new Color3f(Color.ORANGE));
        roomWall3.rotate90(1);
        add(roomWall3);
        
        Wall roomWall4 = new Wall(new Vector3d(6, 0, -2), 8, 1, this);
        roomWall4.setColor(new Color3f(Color.ORANGE));
        roomWall4.rotate90(1);
        add(roomWall4);
        
        Wall roomWall5 = new Wall(new Vector3d(-3, 0, -2), 6, 1, this);
        roomWall5.setColor(new Color3f(Color.BLUE));
        add(roomWall5);
        
        Wall roomWall6 = new Wall(new Vector3d(3, 0, 2), 6, 1, this);
        roomWall6.setColor(new Color3f(Color.BLUE));
        add(roomWall6);
        
        Wall roomWall7 = new Wall(new Vector3d(-3, 0, 4), 4, 1, this);
        roomWall7.setColor(new Color3f(Color.BLUE));
        roomWall7.rotate90(1);
        add(roomWall7);
        
        Wall roomWall8 = new Wall(new Vector3d(-5, 0, -5), 4, 1, this);
        roomWall8.setColor(new Color3f(Color.BLUE));
        add(roomWall8);
        
        Box box1 = new Box(new Vector3d(3, 0, -8), new Vector3f(2, 1, 2), this);
        box1.setColor(new Color3f(Color.BLACK));
        add(box1);
        
        Box box2 = new Box(new Vector3d(6, 0, 7), new Vector3f(2, 1, 1), this);
        box2.setColor(new Color3f(Color.BLACK));
        add(box2);
        
        Box box3 = new Box(new Vector3d(-4, 0, -6.5), new Vector3f(1, 1, 3), this);
        box3.setColor(new Color3f(Color.BLACK));
        add(box3);
        
        for(int i = 1; i <= 10; i++){
        	
        }
        
    }
}
