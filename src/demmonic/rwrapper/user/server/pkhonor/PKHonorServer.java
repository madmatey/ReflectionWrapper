package demmonic.rwrapper.user.server.pkhonor;

import java.io.FileInputStream;
import java.io.InputStream;

import demmonic.rwrapper.container.reflect.ReflectionClass;
import demmonic.rwrapper.user.Server;
import demmonic.rwrapper.user.server.pkhonor.command.ClearCollisionFlagsCommand;
import demmonic.rwrapper.user.server.pkhonor.command.GetOpenChatboxInterfaceCommand;
import demmonic.rwrapper.user.server.pkhonor.command.GetInvOverlayCommand;
import demmonic.rwrapper.user.server.pkhonor.command.GetOpenInterfaceCommand;
import demmonic.rwrapper.user.server.pkhonor.command.SetOpenChatboxInterfaceCommand;
import demmonic.rwrapper.user.server.pkhonor.command.SetOpenInterfaceCommand;
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
		add(new SetOpenInterfaceCommand());
		add(new GetOpenInterfaceCommand());
		add(new GetOpenChatboxInterfaceCommand());
		add(new SetOpenChatboxInterfaceCommand());
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
