package Snake;

import ui.SnakeUserInterface;
import ui.UserInterfaceFactory;
import ui.Event;
import java.util.Scanner;
import ui.UIAuxiliaryMethods;
import Snake.SnakeArray;


public class Snake {
	public static final int NUMBER_OF_ROWS = 32;
	public static final int NUMBER_OF_COLUMNS = 24;
	public static final int FOOD = 3;
	public static final int SNAKE = 2;
	public static final int WALL = 1;
	public static final int EMPTY = 0;
	public static final double FRAMES_PER_SECOND =15.0;
	int appleX;
	int appleY;
	
	public int direction;//1=L,2=R,3=U,4=D.
	//PrintStream out;
	Scanner in;
	SnakeUserInterface ui;
	
	Snake(){
	}
	
	void locateApple(){
		appleX = UIAuxiliaryMethods.getRandom(0,NUMBER_OF_ROWS);
		appleY = UIAuxiliaryMethods.getRandom(0,NUMBER_OF_COLUMNS);
		ui.place(appleX, appleY, FOOD);
		ui.showChanges();
	}
	void checkApple(){
		
		SnakeArray snake= new SnakeArray();
		//Coordinate[] secondArray =  newClassObj.getArray();
		if(appleX==snake.snakeArray[0].x && appleY==snake.snakeArray[0].y){
			snake.snakeGrows(direction);
		}
		
	}
	/*void gameOver(){
		ui.clear();
		out.printf("Game Over");
	}*/
	void checkCollision(){
		SnakeArray result=new SnakeArray();
		for (int i=1;i<=result.numberOfElements;i++){
			if (result.snakeArray[0]==result.snakeArray[i]);
			//gameOver();
		}
		//return result;
	}
	void processAlarm(){
		SnakeArray snake =new SnakeArray();
		snake.checkApple(appleX,appleY,direction);
		checkCollision();
		SnakeArray result = new SnakeArray();
		if (direction == 1){
			result.snakeMoveLeft();
		}else if (direction ==2){
			result.snakeMoveRight();
		}else if (direction ==3){
			result.snakeMoveUp();
		}else if (direction ==4){
			result.snakeMoveDown();
		}
		for (int i=0;i<=result.numberOfElements;i++){
			ui.place(result.snakeArray[0].x,result.snakeArray[0].y,SNAKE);
		}
		ui.showChanges();
		ui.place(result.snakeArray[result.numberOfElements].x,result.snakeArray[result.numberOfElements].y, EMPTY);		
	}
	void processDirection(String data){
		if(data == "L"){
			if(direction!=2){
			direction = 1;
			}
		}else if (data == "R"){
			if(direction!=1){
			}
			direction = 2;
		}else if (data =="U"){
			if(direction!=4){
			}
			direction = 3;
		}else if (data =="D"){
			if(direction!=3){
			}
			direction = 4;
		}
	}
	void processEvent(Event event){
		if(event.name.equals("click")){
			return;
		}else if (event.name.equals("other_key")){
			//ui.clear();
			//ui.showChanges();
			return;
		}else if (event.name.equals("alarm")){
			processAlarm();
		}else if (event.name.equals("mouseover")){
			processAlarm();
			return;	
		}else if (event.name.equals("arrow")){
			processDirection(event.data);
		}
	}
	void initialise(){
		SnakeArray snake = new SnakeArray();
		snake.startArray();
		//ui.place(0, 0, SNAKE);
		ui.place(1, 1, SNAKE);
		locateApple();
		direction = 2;
	}
	void start(){
		ui = UserInterfaceFactory.getSnakeUI(NUMBER_OF_ROWS,NUMBER_OF_COLUMNS);
		ui.setFramesPerSecond(FRAMES_PER_SECOND);
		initialise();
		while (true){
		Event event = ui.getEvent();	
		processEvent(event);
		
		}
	}
	public static void main(String[] args) {
		new Snake().start();
	}
}