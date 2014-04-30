package engineManagers;

import java.util.ArrayList;
import java.util.List;

import objects.GameObject;
import saladConstants.SaladConstants;
import statistics.GameStats;
import util.AttributeMaker;
import util.SaladUtil;
/**
 * Manage the Change of the blood of all Game Objects
 * For Player, manage the blood for its each life
 * 
 * @author Main Justin (Zihao) Zhang
 *
 */
public class BloodManager extends StatisticsManager{
	
	public static final int NOT_USED_BLOOD = 0;
	
	public BloodManager(){
		super();
	}

	@Override
	public void update(String info, GameObject victim, GameObject hitter) {
		int hitterColid = checkIfSideDetectorColid(hitter);
		int victimColid = checkIfSideDetectorColid(victim);
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, 
				info, victimColid, hitterColid);
		if(!myMap.containsKey(condition)) return;
		victim.changeBlood(myMap.get(condition));
		GameStats.update(hitter.getObjectName() + " " + SaladConstants.BLOOD, myMap.get(condition));
	}
	
	public void update(String info, GameObject victim, int tileColid){
		int victimColid = checkIfSideDetectorColid(victim);
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, 
				info, victimColid, tileColid);
		if(!myMap.containsKey(condition)) return;
		victim.changeBlood(myMap.get(condition));
		GameStats.update(victim.getObjectName() + " " + SaladConstants.BLOOD, myMap.get(condition));
	}

	public void update(String info, int oldLevelOrSceneID, GameObject object) {
		String condition = SaladUtil.convertArgsToString(SaladConstants.SEPARATOR, 
				info, oldLevelOrSceneID);
		if(!myMap.containsKey(condition)) return;
		object.changeBlood(myMap.get(condition));
		GameStats.update(object.getObjectName() + " " + SaladConstants.BLOOD, myMap.get(condition));
	}

	public void update(String condition, GameObject object) {
		if(!myMap.containsKey(condition)) return;
		object.changeBlood(myMap.get(condition));
		GameStats.update(object.getObjectName() + " " + SaladConstants.BLOOD, myMap.get(condition));
	}

	@Override
	public List<String> getAttributes() {
		List<String> answer = new ArrayList<String>();
		for(String condition: myMap.keySet()){
			String type = null;
			StringBuilder param = new StringBuilder();
			param.append(myMap.get(condition) + SaladConstants.SEPARATOR);
			param.append(condition);
			List<Object> params = SaladUtil.convertStringListToObjectList(SaladUtil.convertStringArrayToList(
					param.toString().split(SaladConstants.SEPARATOR)));
			if(condition.startsWith(SaladConstants.COLLISION)) type = SaladConstants.SET_COLLISION_BLOOD;
			else if(condition.startsWith(SaladConstants.TILE_COLLISION)) type = SaladConstants.SET_TILE_COLLISION_BLOOD;
			else if(condition.startsWith(SaladConstants.LEVEL_DONE) || condition.startsWith(SaladConstants.SCENE_DONE)) 
				type = SaladConstants.SET_TRANSITION_BLOOD;
			answer.add(AttributeMaker.addAttribute(SaladConstants.MODIFY_BLOOD_MANAGER, type, false, params));
		}
		return answer;
	}

}
