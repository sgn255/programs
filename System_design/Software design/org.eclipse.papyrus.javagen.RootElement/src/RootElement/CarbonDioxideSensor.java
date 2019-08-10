package RootElement;

public class CarbonDioxideSensor {
	
	public CarbonDioxideSensor(){
		//Any initialization goes here 
	}
	
	/**
	 * Carbondioxide consentration in ppm.
	 * @return
	 */
	public double getReading(){
		return (Math.random()* 500) + 50;
	}
}
