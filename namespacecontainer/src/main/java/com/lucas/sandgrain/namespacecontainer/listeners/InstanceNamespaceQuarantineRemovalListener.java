package com.lucas.sandgrain.namespacecontainer.listeners;

import com.google.common.eventbus.Subscribe;
import com.lucas.sandgrain.namespacecontainer.api.namespace.InstanceNamespaceContainerService;
import com.lucas.sandgrain.namespacecontainer.domain.model.namespace.container.InstanceNamespaceContainerLockedException;
import com.lucas.sandgrain.namespacequarantine.api.InstanceNamespacesRemovedFromQuarantineEvent;

/***
 * Called when Instance Namespace Identifications are removed from quarantine.
 * 
 * @author Lucas Piske
 *
 */
public class InstanceNamespaceQuarantineRemovalListener {

	private final InstanceNamespaceContainerService containerService;
	
	public InstanceNamespaceQuarantineRemovalListener(
			InstanceNamespaceContainerService containerService) {
		this.containerService = containerService;
	}

	/***
	 * Removes Instance Namespace Identifications that were removed from quarantine from the Instance Namespace Container.
	 * 
	 * @param event Describes Instance Namespace Identifications removal from quarantine.
	 * @throws InstanceNamespaceContainerLockedException The container is currently locked.
	 */
	@Subscribe
	public void onRemoval(InstanceNamespacesRemovedFromQuarantineEvent event) throws InstanceNamespaceContainerLockedException {
		for(Integer namespace : event.getNamespaces()) {
			containerService.remove(namespace);
		}
	}
	
}
