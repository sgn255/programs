// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package RootElement;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import mappingEntities.Coordinate;
import mappingEntities.CoordinateRow;
import robots.RobotStatus;

/************************************************************/
/**
 * 
 */
public class FileServer
{
	/**
	 * 
	 */
	private static ArrayList<BufferedImage> pictures;
	
	/**
	 * 
	 */
	private static CoordinateRow wallCoordinates;
	
	/**
	 * A hashmap aka. dictionary with key:value pairs representing: "name of robot" : "the status of specified robot"
	 * This is very easy way of mapping a status to every robot.  
	 */
	private static HashMap<String, RobotStatus> robotStatuses;
	
	private static HashMap<String, Coordinate> robotCurrentCoordinates;

	/**
	 * 
	 */
	private static ArrayList<Double> carbonDioxideReadings;

	private static Plotter plotter;
	
	private static FileServer instance = null;

	
	private FileServer(){
		
	}
	
	/**
	 * 
	 * @param coordinate 
	 */
	public static boolean saveWallCoordinate(Coordinate coordinate) {
		if(!wallCoordinates.contains(coordinate)){
			wallCoordinates.push(coordinate);
			plotter.updateChart(wallCoordinates);
			return true;
		}else{
			return false;
		}
		
	}


	/**
	 * 
	 * @param picture 
	 */
	public static void savePicture(BufferedImage picture) {
		pictures.add(picture);
	}
	
	/**
	 * 
	 * @param picture 
	 */
	public static void saveCarbonDioxideReading(double reading) {
		carbonDioxideReadings.add(reading);
	}

	/**
	 * 
	 * @return 
	 */
	public static HashMap<String, RobotStatus> getStatuses() {
		return robotStatuses;
	}
	
	/**
	 * 
	 * @return 
	 */
	public static HashMap<String, Coordinate> getRobotCurrentCoordinates() {
		return robotCurrentCoordinates;
	}
	
	public static void saveStatus(String name, RobotStatus status){
		robotStatuses.put(name, status);
	}
	
	public static void saveRobotCurrentCoordinate(String name, Coordinate coor){
		robotCurrentCoordinates.put(name, coor);
	}

	/**
	 * 
	 * @return 
	 */
	public static ArrayList<Double> getCarbonDioxideReadings() {
		return carbonDioxideReadings;
	}

	
	/**
	 * 
	 * @return 
	 */
	public static CoordinateRow getWallCoordinates() {
		return wallCoordinates;
	}
	
	/**
	 * 
	 * @return 
	 */
	public static ArrayList<BufferedImage> getPictures() {
		return pictures;
	}

	/**
	 * 
	 * @return 
	 */
	public static FileServer getInstance() {
		if(instance == null){
			pictures = new ArrayList<BufferedImage>();
			robotStatuses = new HashMap<String, RobotStatus>();
			robotCurrentCoordinates = new HashMap<String, Coordinate>();
	
			wallCoordinates = new CoordinateRow();
			carbonDioxideReadings = new ArrayList<Double>();
			plotter = new Plotter(wallCoordinates);
			
			instance = new FileServer();
		}
		
		return instance;
	}
};