package org.liubey.example.effectivejava;

public class StaticSingletonFactory {
	
	private static class Holder {
		private static final StaticSingletonFactory instance = new StaticSingletonFactory();
	}
	
	private StaticSingletonFactory() {}
	
	public static StaticSingletonFactory getInstance() {
		return Holder.instance;
	}

}
