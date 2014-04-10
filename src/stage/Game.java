package stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engineManagers.InputManager;
import engineManagers.ScoreManager;
import engineManagers.TimerManager;
import objects.GameObject;
import objects.Player;
/**
 * 
 * @author Main DavidChou, Justin (Zihao) Zhang
 * 
 */
public class Game {

	public static final int DEFAULT_SCORE = 0;

	protected Map<Integer, Level> myLevelMap;
	protected ScoreManager myScoreManager;
	protected InputManager myInputManager;
	protected TimerManager myTimerManager;
	protected Player myPlayer;

	public Game(){
		myLevelMap = new HashMap<Integer, Level>();
		myScoreManager = new ScoreManager(DEFAULT_SCORE);
		myInputManager = new InputManager();
		myTimerManager = new TimerManager();
	}

	/**
	 * @param the level ID that you want to add
	 * @return nothing
	 */
	public void addLevel(int levelID) {
		Level level = new Level(levelID);
		myLevelMap.put(levelID, level);
	}

	/**
	 * Called to add a new scene to a particular level 
	 * @param the level ID that the new scene belongs to 
	 * @param the new scene ID
	 * @return nothing
	 */
	public void addScene(int levelID, int sceneID){
		myLevelMap.get(levelID).addScene(sceneID);
	}
	
	/**
	 * Called to add a new Game Object to a particular scene of a particular level
	 * @param the level ID that the new Game Object belongs to 
	 * @param the new scene ID that the new Game Object belongs to
	 * @param the new Game Object
	 * @return nothing
	 */
	public void addObject(int levelID, int sceneID, GameObject object){
		myLevelMap.get(levelID).addObject(sceneID, object);
	}

	/**
	 * Called to get an existing Game Object from a particular scene of a particular level
	 * @param the level ID that the Game Object belongs to 
	 * @param the scene ID that the Game Object belongs to
	 * @param the object ID
	 * @return the Game Object that matched with the input IDs
	 */
	public GameObject getGameObject(int levelID, int sceneID, int objectID){
		return myLevelMap.get(levelID).getObject(sceneID, objectID);
	}

	/**
	 * Called to get an existing scene from a particular level
	 * @param the level ID that the scene belongs to 
	 * @param the scene ID
	 * @return the scene that matched with the input IDs
	 */
	public Scene getScene(int levelID, int sceneID){
		return myLevelMap.get(levelID).getScene(sceneID);
	}

	/**
	 * Called to remove an existing scene from a particular level
	 * @param the level ID that the scene belongs to 
	 * @param the scene ID
	 * @return nothing
	 */
	public void removeScene(int levelID, int sceneID) {
		myLevelMap.get(levelID).removeScene(sceneID);
	}

	/**
	 * Called to remove an existing level 
	 * @param the level ID 
	 * @return nothing
	 */
	public void removeLevel(int levelID) {
		myLevelMap.remove(levelID);
	}

	/**
	 * Called to reset a new level ID to an existing level if the new level ID has not been used
	 * @param the current level ID of the level
	 * @param the new level ID 
	 * @return nothing
	 */
	public void resetLevelID(int currentLevelID, int newLevelID) throws ResetLevelException{
		if(myLevelMap.containsKey(newLevelID)) throw new ResetLevelException();
		Level level = myLevelMap.get(currentLevelID);
		level.resetID(newLevelID);
		myLevelMap.remove(currentLevelID);
		myLevelMap.put(newLevelID, level);
	}
	
	/**
	 * Called to switch an existing scene from a level to another existing level 
	 * @param the current level ID which the scene belongs to
	 * @param the new level ID which the scene is going to belong to
	 * @param the scene ID
	 * @return nothing
	 */
	public void switchSceneToLevel(int currentLevelID, int newLevelID, int sceneID){
		Scene scene = getScene(currentLevelID, sceneID);
		removeScene(currentLevelID, sceneID);
		Level level = myLevelMap.get(newLevelID);
		level.addScene(sceneID, scene);
	}

	/**
	 * Called to get Game Objects (including Player) that matched with a certain collision ID from the whole game
	 * @param the collision ID
	 * @return a list of Game Objects
	 */
	public List<GameObject> getObjectsByColid(int colid){
		List<GameObject> objects = new ArrayList<GameObject>();
		for(int levelID: myLevelMap.keySet()){
			Level level = myLevelMap.get(levelID);
			objects.addAll(level.getObjectsByColid(colid));
		}
		if(myPlayer.colid == colid) objects.add(myPlayer);
		return objects;
	}
	
	/**
	 * Called to set the Player for the Game
	 * @param Player Object
	 * @return nothing
	 */
	public void setPlayer(Player player){
		myPlayer = player;
	}
	
	/**
	 * Called to get the Player from the Game
	 * @return Player Object
	 */
	public Player getPlayer(){
		return myPlayer;
	}

	/**
	 * Called to get the Attributes of the whole Game
	 * @return a list of Objects that matched with the GAE Data Format
	 */
	public List<String> getAttributes() {
		List <String> answer = new ArrayList<String>();
		answer.addAll(myScoreManager.getAttributes()); 
		answer.addAll(myInputManager.getAttributes()); 
		answer.addAll(myTimerManager.getAttributes()); 
		answer.addAll(myPlayer.getAttributes());
		for(Integer key: myLevelMap.keySet()){
			answer.addAll(myLevelMap.get(key).getAttributes()); 
		}
		return answer;
	}
}
