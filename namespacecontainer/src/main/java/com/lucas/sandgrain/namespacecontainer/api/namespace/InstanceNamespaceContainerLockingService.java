package com.lucas.sandgrain.namespacecontainer.api.namespace;

/***
 * Permits to lock and unlock the Instance Namespace Container.
 * 
 * @see com.lucas.sandgrain.namespacecontainer.api.namespace.InstanceNamespaceContainerService
 * 
 * @author Lucas Piske
 *
 */
public interface InstanceNamespaceContainerLockingService {
	/***
	 * Locks the Instance Namespace Container.
	 * 
	 * @return Returns true when the locking process is performed successfully.
	 */
	public boolean lock();
	
	/***
	 * Unlocks the Instance Namespace Container.
	 * 
	 * @return Returns true when the unlocking process is performed successfully.
	 */
	public boolean unlock();
}
