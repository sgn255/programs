package RootElement;

import robots.Robot;

public class AttachedDevices {
	private Camera camera;
	private CarbonDioxideSensor carbonSensor;
	
	public AttachedDevices(Robot r){
		camera = new Camera(r);
		carbonSensor = new CarbonDioxideSensor();
	}
	
	public Camera getCamera(){
		return this.camera;
	}
	
	public CarbonDioxideSensor getCarbonDioxideSensor(){
		return this.carbonSensor;
	}

}
