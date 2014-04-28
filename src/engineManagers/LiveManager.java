package engineManagers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objects.GameObject;
import objects.Player;
import saladConstants.SaladConstants;
import util.AttributeMaker;
import util.SaladUtil;
/**
 * Manage the overall lives and change of the lives for the Players throughout the whole Game
 * @author Main Justin (Zihao) Zhang
 *
 */
public class LiveManager extends StatisticsManager {
	
	public static final int DEFAULT_INITIAL_LIVES = 5;
	public static final int DEFAULT_NULL_LIVES = 0;
	
	protected boolean myRestore;
	protected Map<Integer, Player> myPlayerMap;
	protected Map<Player, Integer> myInitLifeMap;
	protected Map<Player, Integer> myCurrentLifeMap;
	
	public LiveManager(){
		myPlayerMap = new HashMap<Integer, Player>();
		myInitLifeMap = new HashMap<Player, Integer>();
		myCurrentLifeMap = new HashMap<Player, Integer>();
		myRestore = true;
	}
	
	public void setInitLives(int lives, int playerID){
		if(!myPlayerMap.containsKey(playerID)) return;
		myInitLifeMap.put(myPlayerMap.get(playerID), lives);
	}
	
	public int getCurrentLife(int playerID){
		if(!myPlayerMap.containsKey(playerID)) return DEFAULT_NULL_LIVES;
		return myCurrentLifeMap.get(myPlayerMap.get(playerID));
	}
	
	public void addPlayer(Player player){
		myPlayerMap.put(player.getID(), player);
		myInitLifeMap.put(player, DEFAULT_INITIAL_LIVES);
		restore();
	}
	
	public void setRestore(boolean ifRestore){
		myRestore = ifRestore;
	}

	/**
	 * Called by engine to update live while finishing a level
	 */
	public void updateLevelDoneLives(){
		if(myRestore){ restore(); }
	}
	
	/**
	 * Do not call this method directly
	 * Used to restore every player's current live to its initial live
	 */
	protected void restore(){
		for(Player o: myPlayerMap.values()){
			myCurrentLifeMap.put(o, myInitLifeMap.get(o));
		}
	}

	public List<String> getAttributes() {
		List<String> answer = new ArrayList<String>();
		for(Player player: myInitLifeMap.keySet()){
			String type = null;
			StringBuilder param = new StringBuilder();
			param.append(myInitLifeMap.get(player) + SaladConstants.SEPARATOR);
			param.append(player.getID());
			List<Object> params = SaladUtil.convertStringListToObjectList(SaladUtil.convertStringArrayToList(
					param.toString().split(SaladConstants.SEPARATOR)));
			answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_BLOOD_MANAGER, type, false, params));
		}
		answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_BLOOD_MANAGER, SaladConstants.RESTORE_LIFE_BY_LEVEL, myRestore));
		return answer;
	}

	@Override
	public void update(String info, GameObject victim, GameObject hitter) {
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, 
				info, victim.colid, hitter.colid);
		if(!myMap.containsKey(condition)) return;
		if(hitter instanceof Player){
			Player p = (Player) hitter;
			int changeLive = myMap.get(condition);
			int finalLive = myCurrentLifeMap.get(p) + changeLive;
			myCurrentLifeMap.put(p, finalLive);	
		}
	}
	
	public void update(String info, GameObject victim, int tileColid){
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, victim.colid, tileColid);
		if(!myMap.containsKey(condition)) return;
		if(victim instanceof Player){
			Player p = (Player) victim;
			int changeLive = myMap.get(condition);
			int finalLive = myCurrentLifeMap.get(p) + changeLive;
			myCurrentLifeMap.put(p, finalLive);	
		}
	}
}
