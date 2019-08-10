package nl.vu.cs.s2.simbadtest;

public class Storage {
	
	static int [][] map;
	int WALL = 1;
	static int FREE = 0;
	int UNKNOWN= 5;
	int limit;
	int marked;
	
	Storage(){
		map=new int[20+2][20+2];
		limit = (int) ((21*21)*0.8);
		marked=0;
		initMap();
		
	}
	public void initMap(){
		for (int i=0;i<map.length;i++){
			for (int k=0;k<map[i].length;k++){
				map[i][k]=UNKNOWN;
			}
		}
	}
	public void addCoords(Coordinate coords){
		if (map[coords.y][coords.x]!=FREE){
			map[coords.y][coords.x]=FREE;
			marked+=1;
			printMap();
		}
		
	}
	public void addWall(Coordinate coords){
		
		if (map[coords.y][coords.x]==UNKNOWN){
			map[coords.y][coords.x]=WALL;
			marked+=1;
			printMap();
		}else if (map[coords.y][coords.x]==FREE){
			map[coords.y][coords.x]=WALL;
		}
	}
	public void printMap(){
		for (int i=0;i<map.length;i++){
			for (int k=0;k<map[i].length;k++){
				System.out.print(map[i][k]);
			}
			System.out.println();
			
	}
	}

}
