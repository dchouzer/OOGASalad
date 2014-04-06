package visual_gui_testing;

import java.awt.Robot;

public class KeyReleaseCommand extends UserInputCommand {

	private int myKeyCode;
	
	public KeyReleaseCommand(int keyCode) {
		myKeyCode = keyCode;
	}
	
	@Override
	public void execute(Robot r) {
		r.keyRelease(myKeyCode);
	}

}
