package com.lucas.sandgrain.namespacecontainer.domain.model.namespace.container;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.lucas.sandgrain.namespacecontainer.domain.model.namespace.InstanceNamespace;

public class InstanceNamespaceContainer {
	
	private static final ImmutableSet<InstanceNamespace> EMPTY_EXCEPT_FOR_SET = 
			ImmutableSet.of();
	
	private final ReadWriteLock containerLock = new ReentrantReadWriteLock();
	private boolean locked = false;
	private final Set<InstanceNamespace> namespaces = Sets.newHashSet();
		
	public InstanceNamespaceContainer() {}
	
	public InstanceNamespaceContainer(Collection<InstanceNamespace> namespaces) {
		namespaces.stream().forEach(n -> namespaces.add(n));
	}
	
	public boolean add(InstanceNamespace namespace) throws InstanceNamespaceContainerLockedException {
		try {
			containerLock.writeLock().lock();
			checkLock();
			return namespaces.add(namespace);
		} finally {
			containerLock.writeLock().unlock();
		}
	}
	
	public boolean remove(InstanceNamespace namespace) throws InstanceNamespaceContainerLockedException {
		try {
			containerLock.writeLock().lock();
			checkLock();
			return namespaces.remove(namespace);
		} finally {
			containerLock.writeLock().unlock();
		}
	}
	
	public void clear() {
		try {
			containerLock.writeLock().lock();
			namespaces.clear();
		} finally {
			containerLock.writeLock().unlock();
		}
	}
	
	public boolean lock() {
		try {
			containerLock.writeLock().lock();
			boolean alreadyLocked = locked;
			locked = true;
			return !alreadyLocked;
		} finally {
			containerLock.writeLock().unlock();
		}
	}
	
	public boolean unlock() {
		try {
			containerLock.writeLock().lock();
			boolean alreadyUnlocked = !locked;
			locked = false;
			return !alreadyUnlocked;
		} finally {
			containerLock.writeLock().unlock();
		}
	}
	
	private void checkLock() throws InstanceNamespaceContainerLockedException {
		if(locked)
			throw new InstanceNamespaceContainerLockedException();
	}
	
	public Optional<InstanceNamespace> getOne() throws InstanceNamespaceContainerLockedException {
		return getOne(EMPTY_EXCEPT_FOR_SET);
	}
	
	public Optional<InstanceNamespace> getOne(Set<InstanceNamespace> exceptFor) throws InstanceNamespaceContainerLockedException {
		try {
			containerLock.readLock().lock();
			checkLock();
			
			Set<InstanceNamespace> acceptableNamespaces = 
					Sets.difference(namespaces, exceptFor);
		
			if(!acceptableNamespaces.isEmpty()) {
				Object[] acceptableNamespacesArr = acceptableNamespaces.toArray();
				int randAcceptableNamespaceIndex = 
						ThreadLocalRandom.current().nextInt(
								0,
								acceptableNamespacesArr.length);
				return Optional.of(
						(InstanceNamespace) acceptableNamespacesArr[randAcceptableNamespaceIndex]);
			} else {
				return Optional.empty();
			}
		} finally {
			containerLock.readLock().unlock();
		}
	}
	
}
