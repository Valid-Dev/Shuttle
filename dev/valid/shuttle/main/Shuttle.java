package dev.valid.shuttle.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author Valid
 *
 */
public final class Shuttle {
	
	private static final Shuttle SHUTTLE = new Shuttle();
	
	private Shuttle() {}
	
	private final HashMap<Class<?>, List<Listener<?>>> listeners = new HashMap<>();
	
	/**
	 * 
	 * @param listener
	 */
	public void registerListener(final Listener<?> listener) {
		final Class<?> type = listener.getType();
		if(!this.listeners.containsKey(type))
			this.listeners.put(type, new ArrayList<>());
		this.listeners.get(listener.getType()).add(listener);
	}
	
	/**
	 * 
	 * @param listenerGroup, array of listeners to be registered
	 */
	public void registerListenerGroup(final Listener<?>...listenerGroup) {
		final int size = listenerGroup.length;
		for(int i = 0; i < size; ++i) {
			final Listener<?> listener = listenerGroup[i];
			final Class<?> type = listener.getType();
			if(!this.listeners.containsKey(type))
				this.listeners.put(type, new ArrayList<>());
			
			final List<Listener<?>> listeners = this.listeners.get(listener.getType());
			listeners.add(listener);
		}
	}
	
	/**
	 * 
	 * @param listenerGroup, list of listeners to be registered
	 */
	public void registerListenerGroup(final List<Listener<?>> listenerGroup) {
		final int size = listenerGroup.size();
		for(int i = 0; i < size; ++i) {
			final Listener<?> listener = listenerGroup.get(i);
			final Class<?> type = listener.getType();
			if(!this.listeners.containsKey(type))
				this.listeners.put(type, new ArrayList<>());
			
			final List<Listener<?>> listeners = this.listeners.get(listener.getType());
			listeners.add(listener);
		}
	}
	
	/**
	 * 
	 * @param listener, listener to be removed
	 */
	public void removeListener(final Listener<?> listener) {
		final Class<?> type = listener.getType();
		if(!this.listeners.containsKey(type))
			return;
		
		this.listeners.get(type).remove(listener);
	}
	
	/**
	 * 
	 * @param listenerGroup, array of listeners to be removed
	 */
	public void removeListenerGroup(final Listener<?>...listenerGroup) {
		final int size = listenerGroup.length;
		for(int i = 0; i < size; ++i) {
			final Listener<?> listener = listenerGroup[i];
			final Class<?> type = listener.getType();
			if(!this.listeners.containsKey(type))
				continue;
			
			final List<Listener<?>> listeners = this.listeners.get(type);
			listeners.remove(listener);
		}
	}
	
	/**
	 * 
	 * @param listenerGroup, list of listeners to be registered
	 */
	public void removeListenerGroup(final List<Listener<?>> listenerGroup) {
		final int size = listenerGroup.size();
		for(int i = 0; i < size; ++i) {
			final Listener<?> listener = listenerGroup.get(i);
			final Class<?> type = listener.getType();
			if(!this.listeners.containsKey(type))
				this.listeners.put(type, new ArrayList<>());
			
			final List<Listener<?>> listeners = this.listeners.get(listener.getType());
			listeners.remove(listener);
		}
	}
	
	/**
	 * 
	 * @param <T>
	 * @param Event
	 */
	@SuppressWarnings("unchecked")
	public <T> void fire(final T event) {
		final Class<? extends T> clazz = (Class<? extends T>) event.getClass();
		if(!this.listeners.containsKey(clazz))
			this.listeners.put(clazz, new ArrayList<Listener<?>>());
		
		final List<Listener<?>> listeners = this.listeners.get(clazz);
		final int size = listeners.size();
		for(int i = 0; i < size; ++i) {
			final Listener<T> listener = (Listener<T>) listeners.get(i);
			listener.invoke(event);
		}
	}
	
	/**
	 * 
	 * @return Shuttle instance
	 */
	public static Shuttle getShuttle() {
		return SHUTTLE;
	}
}
