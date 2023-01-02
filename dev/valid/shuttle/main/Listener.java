package dev.valid.shuttle.main;

import java.util.function.Consumer;

/**
 * 
 * @author Valid
 *
 * @param <T>
 */
final class Listener<T> {
	
	private final Consumer<T> consumer;
	
	private final Class<? extends T> type;
	
	public Listener(final Consumer<T> consumer, final Class<? extends T> type) {
		this.consumer = consumer;
		this.type = type;
	}
	
	public void invoke(final T t) {
		this.consumer.accept(t);
	}
	
	public Class<? extends T> getType() {
		return type;
	}
}
