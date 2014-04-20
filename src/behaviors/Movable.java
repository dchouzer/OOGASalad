package behaviors;

import java.util.List;

import objects.GameObject;

public abstract class Movable {
	protected GameObject myObject; 
	
	protected Movable(GameObject o){
		myObject = o;
	}
	
	public abstract void move(List<Object> objects);
}
