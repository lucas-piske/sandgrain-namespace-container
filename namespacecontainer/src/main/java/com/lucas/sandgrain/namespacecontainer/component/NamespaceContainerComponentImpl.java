package com.lucas.sandgrain.namespacecontainer.component;

import com.google.common.eventbus.EventBus;
import com.lucas.sandgrain.namespacecontainer.api.namespace.InstanceNamespaceContainerLockingService;
import com.lucas.sandgrain.namespacecontainer.api.namespace.InstanceNamespaceContainerService;
import com.lucas.sandgrain.namespacecontainer.domain.model.namespace.container.InstanceNamespaceContainerLockedException;
import com.lucas.sandgrain.namespacecontainer.listeners.InstanceNamespaceQuarantineLeavingListener;
import com.lucas.sandgrain.namespacecontainer.listeners.InstanceNamespaceQuarantineRemovalListener;

public final class NamespaceContainerComponentImpl implements NamespaceContainerComponent {

	private InstanceNamespaceContainerService containerService;
	private InstanceNamespaceContainerLockingService containerLockingService;
	private EventBus globalEventBus;
	
	private InstanceNamespaceQuarantineLeavingListener namespaceReceivalListener;
	private InstanceNamespaceQuarantineRemovalListener namespaceRemovalListener;
	
	public NamespaceContainerComponentImpl(
			InstanceNamespaceContainerService containerService,
			InstanceNamespaceContainerLockingService containerLockingService,
			EventBus globalEventBus) {
		this.containerService = containerService;
		this.containerLockingService = containerLockingService;
		this.globalEventBus = globalEventBus;
	}
	
	public InstanceNamespaceContainerService getContainerService() {
		return containerService;
	}
	
	@Override
	public void start() {
		this.namespaceReceivalListener = 
				new InstanceNamespaceQuarantineLeavingListener(containerService);
		this.namespaceRemovalListener = 
				new InstanceNamespaceQuarantineRemovalListener(containerService);
		
		globalEventBus.register(namespaceReceivalListener);
		globalEventBus.register(namespaceRemovalListener);
	}
	
	@Override
	public void stop() throws InstanceNamespaceContainerLockedException {
		containerLockingService.lock();
		globalEventBus.unregister(namespaceReceivalListener);
		globalEventBus.unregister(namespaceRemovalListener);
		containerService.clear();
		this.containerLockingService = null;
		this.containerService = null;
	}

}
