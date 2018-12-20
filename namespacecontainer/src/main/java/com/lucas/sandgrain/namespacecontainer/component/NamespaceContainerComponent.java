package com.lucas.sandgrain.namespacecontainer.component;

import java.io.IOException;

import com.lucas.sandgrain.namespacecontainer.api.namespace.InstanceNamespaceContainerService;
import com.lucas.sandgrain.namespacecontainer.domain.model.namespace.container.InstanceNamespaceContainerLockedException;

/***
 * Provides ways to start and stop the component and also to access the services implemented by this component.
 * 
 * @author Lucas Piske
 *
 */
public interface NamespaceContainerComponent {
	/**
	 * Gives access to the initialized service. 
	 * 
	 * @see com.lucas.sandgrain.namespacecontainer.api.namespace.InstanceNamespaceContainerService
	 * 
	 * @return The service initialized by the component.
	 */
	public InstanceNamespaceContainerService getContainerService();
	
	/***
	 * Starts this component.
	 * 
	 * This method will return when the component is successfully started.
	 */
	public void start();
	
	/***
	 * Stops this component
	 * 
	 * This method will return when the component is successfully stopped.
	 * 
	 * @throws IOException
	 */
	public void stop() throws InstanceNamespaceContainerLockedException;
}
