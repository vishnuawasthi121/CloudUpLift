package com.poc.ignite.app.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteException;
import org.apache.ignite.lifecycle.LifecycleBean;
import org.apache.ignite.lifecycle.LifecycleEventType;
import org.apache.ignite.resources.IgniteInstanceResource;

public class IgniteLifecycleEventBean implements LifecycleBean {

	 @IgniteInstanceResource
     private Ignite ignite;

	@Override
	public void onLifecycleEvent(LifecycleEventType evt) throws IgniteException {

		// before node startup routine. Node is not initialized and cannot be used.
		if (evt == LifecycleEventType.BEFORE_NODE_START) {
			System.out.format("Before node startup routine (consistentId = %s).\n",
					ignite.cluster().node().consistentId());
		}

		if (evt == LifecycleEventType.AFTER_NODE_START) {
			System.out.format("After the node (consistentId = %s) starts.\n", ignite.cluster().node().consistentId());
		}

		// Invoked before node stopping routine. Node is fully functional at this point.
		if (evt == LifecycleEventType.BEFORE_NODE_STOP) {
			System.out.format("Before node stopping routine (consistentId = %s) stopping.\n",
					ignite.cluster().node().consistentId());
		}

		// after node had stopped. Node is stopped and cannot be used
		/*
		 * if (evt == LifecycleEventType.AFTER_NODE_STOP) {
		 * System.out.format("after node had stopped (consistentId = %s) stopped.\n",
		 * ignite.cluster().node().consistentId()); }
		 */
	}

}
