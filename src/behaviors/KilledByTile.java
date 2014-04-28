package behaviors;

import java.util.List;

import objects.GameObject;
/**
 * No parameter needed
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class KilledByTile extends TileCollision {

	public KilledByTile(GameObject o) {
		super(o);
	}

	/**
	 * @param tilecid
	 * @param tx
	 * @param ty
	 * @param txsize
	 * @param tysize
	 */
	@Override
	public void collide(List<Object> objects) {
		myObject.ground();
		myObject.die();
		updateManagers((Integer) objects.get(0));
	}

}
