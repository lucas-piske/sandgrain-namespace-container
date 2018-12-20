package com.lucas.sandgrain.namespacecontainer.listeners;

import com.google.common.eventbus.Subscribe;
import com.lucas.sandgrain.namespacecontainer.api.namespace.InstanceNamespaceContainerService;
import com.lucas.sandgrain.namespacecontainer.domain.model.namespace.container.InstanceNamespaceContainerLockedException;
import com.lucas.sandgrain.namespacequarantine.api.InstanceNamespacesLeftQuarantineEvent;

/***
 * Called when safe Instance Namespace Identifications leave quarantine.
 * 
 * @author Lucas Piske
 *
 */
public class InstanceNamespaceQuarantineLeavingListener {

	private final InstanceNamespaceContainerService containerService;
	
	public InstanceNamespaceQuarantineLeavingListener(
			InstanceNamespaceContainerService containerService) {
		this.containerService = containerService;
	}

	/***
	 * Stores the newly safe Instance Namespace Identifications in the Instance Namespace Container.
	 * 
	 * @param event Describes Instance Namespace Identifications leaving quarantine.
	 * @throws InstanceNamespaceContainerLockedException The container is currently locked.
	 */
	@Subscribe
	public void onLeaving(InstanceNamespacesLeftQuarantineEvent event) throws InstanceNamespaceContainerLockedException {
		for(Integer namespace : event.getNamespaces()) {
			containerService.addIfAbsent(namespace);
		}
	}
	
}
