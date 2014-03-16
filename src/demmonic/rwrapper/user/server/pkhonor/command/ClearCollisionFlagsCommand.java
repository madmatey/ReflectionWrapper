package demmonic.rwrapper.user.server.pkhonor.command;

import demmonic.rwrapper.Loader;
import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.user.command.Command;

/**
 * 
 * @author Demmonic
 *
 */
public class ClearCollisionFlagsCommand extends Command {

	public ClearCollisionFlagsCommand() {
		super("clearcollisionflags");
	}

	@Override
	public void parse(String input) {
		ReflectionClass client = Loader.getClass("pkhonor.Client");

		Object[] collisionMaps = (Object[]) client.getField("iy", "[Lpkhonor.i;").getValue();
		for (int plane = 0; plane < collisionMaps.length; plane++) {
			Object o = collisionMaps[plane];
			
			ReflectionClass collisionMap = new ReflectionClass(o.getClass(), o);
			int[][] collisionFlags = (int[][]) collisionMap.getField("a", "[[I").getValue();
			for (int x = 0; x < collisionFlags.length; x++) {
				for (int y = 0; y < collisionFlags[x].length; y++) {
					collisionFlags[x][y] = 0;
				}
			}
		}
	}

	@Override
	public String getSyntax() {
		return "clearcollisionflags";
	}
	
}
