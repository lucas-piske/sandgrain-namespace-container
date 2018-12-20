package com.lucas.sandgrain.namespacecontainer.api.namespace;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.lucas.sandgrain.namespacecontainer.domain.model.namespace.InstanceNamespace;
import com.lucas.sandgrain.namespacecontainer.domain.model.namespace.container.InstanceNamespaceContainer;
import com.lucas.sandgrain.namespacecontainer.domain.model.namespace.container.InstanceNamespaceContainerLockedException;

public final class InstanceNamespaceContainerServiceImpl implements InstanceNamespaceContainerService, InstanceNamespaceContainerLockingService {
	
	private final InstanceNamespaceContainer namespaceContainer;
	
	public InstanceNamespaceContainerServiceImpl(
			InstanceNamespaceContainer namespaceContainer) {
		this.namespaceContainer = namespaceContainer;
	}

	@Override
	public void addIfAbsent(int intNamespace) throws InstanceNamespaceContainerLockedException {
		InstanceNamespace namespace = new 
				InstanceNamespace(intNamespace);
		
		namespaceContainer.add(namespace);
	}

	@Override
	public void remove(int intNamespace) throws InstanceNamespaceContainerLockedException {
		InstanceNamespace namespace = new 
				InstanceNamespace(intNamespace);
		
		namespaceContainer.remove(namespace);
	}

	@Override
	public void clear() {
		namespaceContainer.clear();
	}

	@Override
	public Optional<Integer> getOne(Set<Integer> intExceptFor) throws InstanceNamespaceContainerLockedException {
		Set<InstanceNamespace> exceptFor = intExceptFor
				.stream()
				.map(ief -> new InstanceNamespace(ief))
				.collect(Collectors.toSet());
		
		Optional<InstanceNamespace> bestNamespace = 
				namespaceContainer
				.getOne(exceptFor);
		
		return bestNamespace.map(n -> n.toInt());
	}

	@Override
	public boolean lock() {
		return namespaceContainer.lock();
	}

	@Override
	public boolean unlock() {
		return namespaceContainer.unlock();
	}

}
