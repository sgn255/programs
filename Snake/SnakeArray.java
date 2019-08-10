package Snake;
import java.util.Scanner;

public class SnakeArray {
	public static final int MAX_NO_OF_ELEMENTS= 1000;
	public static final int NUMBER_OF_ROWS = 32;
	public static final int NUMBER_OF_COLUMNS = 24;
	public static final int GOOD=1;
	public static final int CRASH=2;
	
	Coordinate[] snakeArray;
	public int numberOfElements;
	int insertPosition = 0;
	int result;
	
	Scanner in;
	
	SnakeArray(){
		snakeArray = new Coordinate[MAX_NO_OF_ELEMENTS];
		//Coordinate[]
		numberOfElements = 0;	
	}
	Coordinate splitCoordinates(int x, int y){
		Coordinate result = new Coordinate();
		
		result.x = x;
		result.y = y;		
		return result;
	}
	public Coordinate[] getArray(){
		return this.snakeArray;
	}
// void checkApple1(int appleX, int appleY, int direction){
		
		//SnakeArray snake= new SnakeArray();
		//Coordinate[] result = snake.getArray();
		//Coordinate[] secondArray =  newClassObj.getArray();
		//if(appleX==snakeArray[0].x && appleY==snakeArray[0].y){
		//	snakeGrows(direction);
	//	}
		
	//}
	public void startArray(){
		Coordinate result = new Coordinate();
		for (int i=0;i<1;i++){
			int x=1;
			int y=1;
			result=splitCoordinates(x,y);
			snakeArray[numberOfElements]=result;
			numberOfElements+=1;
			x+=1;
		}
	}
	public int checkSnakeBounds(int a){
		switch (a){
		case 1:
			if(snakeArray[1].x >0){
				return GOOD;
			}else if (snakeArray[1].x==0){
				return CRASH;	
			}
		case 2:
			if(snakeArray[1].x<NUMBER_OF_ROWS-1){
				return GOOD;
			}else if(snakeArray[1].x==NUMBER_OF_ROWS){
				return CRASH;	
			}
		case 3:
			if(snakeArray[1].y>0){
				return GOOD;
			}else if(snakeArray[1].y==0){
				return CRASH;
			}
		case 4:
			if (snakeArray[1].y<NUMBER_OF_COLUMNS-1){
				return GOOD;
			}else if (snakeArray[1].y==NUMBER_OF_COLUMNS-1){
				return CRASH;
			}
		}
		return  0;
	}
	public void snakeMoveDown(){
		
		for(int i = numberOfElements; i >= (insertPosition); i--){
            snakeArray[i+1] = snakeArray[i];
		}
		result = checkSnakeBounds(4);
		if (result%2!=0){
			snakeArray[insertPosition].x=snakeArray[1].x;
			snakeArray[insertPosition].y=snakeArray[1].y+=1;
		}else{
			snakeArray[insertPosition].x=snakeArray[1].x;
			snakeArray[insertPosition].y=0;
		}
	}
	public void snakeMoveLeft(){
		for(int i = numberOfElements; i >= (insertPosition); i--){
            snakeArray[i+1] = snakeArray[i];
		}
		result = checkSnakeBounds(1);
		if (result%2!=0){
			snakeArray[insertPosition].x=snakeArray[1].x-=1;
			snakeArray[insertPosition].y=snakeArray[1].y;
		}else{
			snakeArray[insertPosition].x=NUMBER_OF_ROWS;
			snakeArray[insertPosition].y=snakeArray[1].y;
		}
	}
	public void snakeMoveRight(){
		for(int i = numberOfElements; i >= (insertPosition); i--){
            snakeArray[i+1] = snakeArray[i];
		}
		result = checkSnakeBounds(2);
		if (result%2!=0){
			snakeArray[insertPosition].x=snakeArray[1].x+=1;
			snakeArray[insertPosition].y=snakeArray[1].y;
		}else{
			snakeArray[insertPosition].x=0;
			snakeArray[insertPosition].y=snakeArray[1].y;
		}	
	}
	public void snakeMoveUp(){
		for(int i = numberOfElements; i >= (insertPosition); i--){
            snakeArray[i+1] = snakeArray[i];
		}
		result = checkSnakeBounds(3);
		if (result%2!=0){
			snakeArray[insertPosition].x=snakeArray[1].x;
			snakeArray[insertPosition].y=snakeArray[1].y-=1;
		}else{
			snakeArray[insertPosition].x=snakeArray[1].x;
			snakeArray[insertPosition].y=NUMBER_OF_COLUMNS;
		}
	}
	public void snakeGrows(int direction){
			snakeArray[numberOfElements+=1]=snakeArray[numberOfElements];
			numberOfElements+=1;   
	}
	public void checkApple(int appleX, int appleY, int direction) {
		if(appleX==snakeArray[0].x && appleY==snakeArray[0].y){
			snakeGrows(direction);
		}
		
	}
	
		
	}

	
