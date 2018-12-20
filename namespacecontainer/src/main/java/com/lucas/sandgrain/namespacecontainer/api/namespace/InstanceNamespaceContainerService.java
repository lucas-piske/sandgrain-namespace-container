package com.lucas.sandgrain.namespacecontainer.api.namespace;

import java.util.Optional;
import java.util.Set;

import com.lucas.sandgrain.namespacecontainer.domain.model.namespace.container.InstanceNamespaceContainerLockedException;

/**
 * A collection that stores all safely usable Instance Namespace Identifications. No nonsafe identification should ever be stored in this collection.
 * Safely usable identifications are the ones that already passed through the quarantine process.
 * 
 * @author Lucas Piske
 *
 */
public interface InstanceNamespaceContainerService {
	/***
	 * Adds the specified Instance Namespace Identification if its not already contained in collection.
	 * 
	 * @param namespace A safe Instance Namespace Identification to be stored in the collection.
	 * @throws InstanceNamespaceContainerLockedException The collection is currently locked.
	 */
	public void addIfAbsent(int namespace) throws InstanceNamespaceContainerLockedException;
	
	/***
	 * Removes the specified Instance Namespace Identification if its contained in the collection. If its not contained nothing happens to the collection.
	 * 
	 * @param namespace A Instance Namespace Identification to be removed from the collection.
	 * @throws InstanceNamespaceContainerLockedException The collection is currently locked.
	 */
	public void remove(int namespace) throws InstanceNamespaceContainerLockedException;
	
	/***
	 * Removes all the contained Instance Namespace Identifications.
	 * This operation can be made even when the collection is locked.
	 * 
	 */
	public void clear();
	
	/***
	 * Selects a random contained Instance Namespace Identification except for the ones passed as arguments.
	 * 
	 * @param exceptFor A set of unacceptable Instance Namespace Identifications to be selected from the collection.
	 * @return A randomly selected Instance Namespace Identification if acceptable one is available.
	 * @throws InstanceNamespaceContainerLockedException The collection is currently locked.
	 */
	public Optional<Integer> getOne(Set<Integer> exceptFor) throws InstanceNamespaceContainerLockedException;
}
