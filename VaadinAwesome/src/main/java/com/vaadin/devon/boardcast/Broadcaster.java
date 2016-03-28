package com.vaadin.devon.boardcast;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.vaadin.ui.UI;

public class Broadcaster implements Serializable {
	static ExecutorService executorService = Executors.newSingleThreadExecutor();

	// static ExecutorService executorService =
	// Executors.newFixedThreadPool(10);
	public interface BroadcastListener {
		void receiveBroadcast(String message);
	}

	private static LinkedList<BroadcastListener> listeners = new LinkedList<BroadcastListener>();

	public static synchronized void register(BroadcastListener listener) {

		listeners.add(listener);
	}

	public static synchronized void unregister(BroadcastListener listener) {
		listeners.remove(listener);
	}

	public static synchronized void broadcast(final String message) {
		for (final BroadcastListener listener : listeners)
			executorService.execute(new Runnable() {
				@Override
				public void run() {

					listener.receiveBroadcast(message);
				}
			});
	}
}