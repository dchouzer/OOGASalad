package stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objects.GameObject;
import saladConstants.SaladConstants;

/**
 * 
 * @author Justin (Zihao) Zhang, DavidChou
 */

public class Scene {
	
	public static final double DEFAULT_PLAYER_X = 0;
	public static final double DEFAULT_PLAYER_Y = 0;
	
	protected int myID;
	protected String myBackground;
	protected Map<Integer, GameObject> myObjectMap;
	protected double initPlayerX;
	protected double initPlayerY;
//	protected GoalManager myGoalManager;
	
	public Scene(int id) {
		myID = id;
		initPlayerX = DEFAULT_PLAYER_X;
		initPlayerY = DEFAULT_PLAYER_Y;
		myObjectMap = new HashMap<Integer, GameObject>();
	}
		
	public int getID(){
		return myID; 
	}
	
	public void addObject(GameObject object) {
		myObjectMap.put(object.getID(), object );
	}
	
	public void setPlayerInitPosition(double xpos, double ypos){
		initPlayerX = xpos;
		initPlayerY = ypos;
	}
	
	public double[] getPlayerInitPosition(){
		double[] position = new double[2];
		position[0] = initPlayerX;
		position[1] = initPlayerY;
		return position;
	}
	
	/**
	 * Called by GameEngine to display the GameObjects
	 * @return a list of Game Objects for the current scene
	 */
	public List<GameObject> getGameObjects() {
		List<GameObject> answer = new ArrayList<GameObject>();
		for(int id: myObjectMap.keySet()){
			answer.add(myObjectMap.get(id));
		}
		return answer;
	}
	
	//need check
	public void setBackgroundImage(String imageName) {
		myBackground = imageName;
	}
	
	public String getBackgroundImage() {
		return myBackground;
	}
	
	public GameObject getObject(int objectID) {
		return myObjectMap.get(objectID);
	}

	public List<GameObject> getObjectsByColid(int colid){
		List<GameObject> objects = new ArrayList<GameObject>();
		for(int objectID: myObjectMap.keySet()){
			GameObject object = myObjectMap.get(objectID);
			if(object.colid == colid) objects.add(object);
		}
		return objects;
	}
	
	public List<String> getAttributes() {
		List<String> answer = new ArrayList<String>();
		answer.add(SaladConstants.CREATE_SCENE + "," + SaladConstants.ID + "," + myID + "," + SaladConstants.BACKGROUND + "," + myBackground);
		answer.add(SaladConstants.SWITCH_SCENE + "," + SaladConstants.ID + "," + myID);
		for(int a: myObjectMap.keySet()){
			answer.addAll(myObjectMap.get(a).getAttributes());
		}
		answer.add(SaladConstants.MODIFY_SCENE + "," + SaladConstants.ID + "," + myID + "," + SaladConstants.PLAYER_INITIAL_POSITION + "," + initPlayerX + "," + initPlayerY);
		return answer;
	}
}
