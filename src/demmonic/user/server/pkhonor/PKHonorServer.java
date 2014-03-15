package demmonic.user.server.pkhonor;

import java.io.FileInputStream;
import java.io.InputStream;

import demmonic.container.reflect.ReflectionClass;
import demmonic.user.Server;
import demmonic.user.server.pkhonor.command.GetChatboxInterfaceCommand;
import demmonic.user.server.pkhonor.command.GetInvOverlayCommand;
import demmonic.user.server.pkhonor.command.GetOpenInterfaceCommand;
import demmonic.user.server.pkhonor.command.OpenChatboxInterfaceCommand;
import demmonic.user.server.pkhonor.command.OpenInterfaceCommand;
import demmonic.user.server.pkhonor.command.PushMessageCommand;
import demmonic.user.server.pkhonor.command.SetInputCommand;
import demmonic.user.server.pkhonor.command.SetInvOverlayCommand;
import demmonic.user.server.pkhonor.command.SetUsernameCommand;

/**
 * 
 * @author Demmonic
 *
 */
public class PKHonorServer extends Server {

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
