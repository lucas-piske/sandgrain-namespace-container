package com.lucas.sandgrain.namespacecontainer.component;

import com.google.common.eventbus.EventBus;

/***
 * Instantiates the {@link com.lucas.sandgrain.namespacecontainer.component.NamespaceContainerComponent component} following the provided configuration.
 * 
 * @see com.lucas.sandgrain.namespacecontainer.component.NamespaceContainerComponent
 * 
 * @author Lucas Piske
 *
 */
public interface NamespaceContainerComponentFactory {
	/**
	 * Instantiates the {@link com.lucas.sandgrain.namespacecontainer.component.NamespaceContainerComponent component}
	 * 
	 * @see com.lucas.sandgrain.namespacecontainer.component.NamespaceContainerComponent
	 * 
	 * @param globalEventBus Used to exchange events between the different modules.
	 * @return The instantiated component following the specified configuration.
	 */
	public NamespaceContainerComponent create(
			EventBus globalEventBus);
	
}
