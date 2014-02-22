package demmonic.container.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @author Demmonic
 *
 */
public class ReflectionMethod {

	private Method method;
	
	/**
	 * @param method
	 */
	public ReflectionMethod(Method method) {
		this.method = method;
	}
	
	/**
	 * @return This method's name
	 */
	public String getName() {
		return method.getName();
	}
	
	/**
	 * @return This method's parameter types
	 */
	public Object[] getParameters() {
		return method.getParameterTypes();
	}
	
	/**
	 * Invokes this method with the provided parameters
	 * @param instance
	 * @param params
	 */
	public void invoke(Object instance, Object... params) {
		if (!method.isAccessible())
			method.setAccessible(true);
		
		try {
			method.invoke(instance, params);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
