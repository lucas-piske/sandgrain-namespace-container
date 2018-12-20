package com.lucas.sandgrain.namespacecontainer.component;

import com.google.common.eventbus.EventBus;
import com.lucas.sandgrain.namespacecontainer.api.namespace.InstanceNamespaceContainerServiceImpl;
import com.lucas.sandgrain.namespacecontainer.domain.model.namespace.container.InstanceNamespaceContainer;

public final class NamespaceContainerComponentFactoryImpl implements NamespaceContainerComponentFactory {

	@Override
	public NamespaceContainerComponent create(
			EventBus globalEventBus) {
		InstanceNamespaceContainerServiceImpl containerServiceImpl = 
				new InstanceNamespaceContainerServiceImpl(
						new InstanceNamespaceContainer());
		
		return new NamespaceContainerComponentImpl(
				containerServiceImpl,
				containerServiceImpl,
				globalEventBus);
	}

}
