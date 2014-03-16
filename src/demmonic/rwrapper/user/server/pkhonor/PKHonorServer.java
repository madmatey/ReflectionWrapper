package demmonic.rwrapper.user.server.pkhonor;

import java.io.FileInputStream;
import java.io.InputStream;

import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.user.Server;
import demmonic.rwrapper.user.server.pkhonor.command.ClearCollisionFlagsCommand;
import demmonic.rwrapper.user.server.pkhonor.command.GetChatboxInterfaceCommand;
import demmonic.rwrapper.user.server.pkhonor.command.GetInvOverlayCommand;
import demmonic.rwrapper.user.server.pkhonor.command.GetOpenInterfaceCommand;
import demmonic.rwrapper.user.server.pkhonor.command.OpenChatboxInterfaceCommand;
import demmonic.rwrapper.user.server.pkhonor.command.OpenInterfaceCommand;
import demmonic.rwrapper.user.server.pkhonor.command.PushMessageCommand;
import demmonic.rwrapper.user.server.pkhonor.command.SetInputCommand;
import demmonic.rwrapper.user.server.pkhonor.command.SetInvOverlayCommand;
import demmonic.rwrapper.user.server.pkhonor.command.SetUsernameCommand;

/**
 * 
 * @author Demmonic
 *
 */
public final class PKHonorServer extends Server {

	public PKHonorServer() {
		add(new PushMessageCommand());
		add(new OpenInterfaceCommand());
		add(new GetOpenInterfaceCommand());
		add(new GetChatboxInterfaceCommand());
		add(new OpenChatboxInterfaceCommand());
		add(new SetUsernameCommand());
		add(new SetInputCommand());
		add(new SetInvOverlayCommand());
		add(new GetInvOverlayCommand());
		add(new ClearCollisionFlagsCommand());
	}
	
	@Override
	public InputStream getClient() {
		try {
			return new FileInputStream("./data/pkhonor.jar");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public ReflectionClass getMainClass() {
		return get("pkhonor.Client");
	}

}
