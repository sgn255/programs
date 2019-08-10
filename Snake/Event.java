package Snake;

import java.util.Scanner;
public class Event {
	String name;
	String data;
	
	Event(){
		name=null;
		data=null;
	}
	Event event(Scanner in){
		Event result = new Event();
		this.name = in.next();
		this.data = in.next();		
		return result;
	}
}
