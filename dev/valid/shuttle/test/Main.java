package dev.valid.shuttle.test;

import java.util.function.Consumer;

import dev.valid.shuttle.main.Shuttle;
import dev.valid.shuttle.main.Subscriber;

final class Main {
	
	private static final Shuttle SHUTTLE = Shuttle.getShuttle();
	
	public static void main(String[] args) {
		new SubTest();
		final long time = System.currentTimeMillis();
		for(int i = 0; i < 1000000; ++i)
			SHUTTLE.fire(new EventA());
		
		System.out.println("Time " + (System.currentTimeMillis() - time));
	}
	
	private static final class SubTest extends Subscriber {
		private SubTest() {
			super.createListener(new Consumer<EventA>() {
				@Override
				public void accept(final EventA t) {
				}
			}, EventA.class);
			super.registerListeners();
		}
	}
}
