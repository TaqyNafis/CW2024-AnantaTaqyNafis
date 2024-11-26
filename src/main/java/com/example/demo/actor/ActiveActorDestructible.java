package com.example.demo.actor;

public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	private boolean isDestroyed;

	protected ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	@Override
	public abstract void updatePosition();

	public abstract void updateActor();

	@Override
	public void destroy() {
		isDestroyed=true;
	}
	public boolean isDestroyed() {
		return isDestroyed;
	}
	
}
