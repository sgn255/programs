package nl.vu.cs.s2.simbadtest;

public class Wrapper {
	
	private static int NORTH=0;
    private static int EAST=1;
    private static int SOUTH=2;
    private static int WEST=3;
	Wrapper(){
		
	}
	
	public Coordinate convertFree(double xval, double zval){
		System.out.println(xval+"xfree");
		System.out.println(zval+"y");
		int x = (int) Math.ceil(xval);
		int y = (int) Math.ceil(zval);
		Coordinate result = new Coordinate(x+10,y+10);
		System.out.println(x+9+"newxfree");
		System.out.println(y+9+"newy");
		return result;	
	}
	public Coordinate convertWall(double xval, double zval,int direction){
		System.out.println(xval+"xwall");
		System.out.println(zval+"y");
		int x = 0;
		int y = 0;
		switch (direction){
		case 0:x = (int) Math.floor(xval);////'/north
					y = (int) Math.floor(zval);
					break;
		case 3: x = (int) Math.floor(xval);////west
					y = (int) Math.floor(zval);
					break;
		case 1: x = (int) Math.ceil(xval+1);////east
				y = (int) Math.floor(zval);
				break;
		case 2: x = (int) Math.floor(xval);////south
				y = (int) Math.ceil(zval+1);
				break;
		}
		Coordinate result = new Coordinate(x+10,y+10);
		System.out.println(x+9+"newxwall");
		System.out.println(y+9+"newy");
		return result;
		
	}
}
