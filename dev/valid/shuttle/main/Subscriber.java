package dev.valid.shuttle.main;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * 
 * @author Valid
 *
 */
public abstract class Subscriber {
	
	private static final Shuttle SHUTTLE = Shuttle.getShuttle();
	
	private static final ArrayList<Listener<?>> LISTENER_CACHE = new ArrayList<>();
	
	protected static void registerListeners() {
		SHUTTLE.registerListenerGroup(LISTENER_CACHE);
		LISTENER_CACHE.clear();
	}
	
	protected static <T> void createListener(final Consumer<T> consumer, final Class<? extends T> type) {
		LISTENER_CACHE.add(new Listener<T>(consumer, type));
	}
}