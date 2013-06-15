package com.pinguo.mcamera4fun.test;

import junit.framework.AssertionFailedError;
import junit.framework.Test;

public class TestListener implements junit.framework.TestListener{
	
	public static enum EDisplayEvent {  
        START_TEST,  
        END_TEST,  
        ERROR,  
        FAILURE,  
    } 
	
	public static interface IViewUpdater {
		public void onUpdate(EDisplayEvent ev);
	}
	
	private IViewUpdater updater;

	public IViewUpdater getUpdater() {
		return updater;
	}

	public void setUpdater(IViewUpdater updater) {
		this.updater = updater;
	}

	@Override
	public void addError(Test test, Throwable t) {
		updater.onUpdate(EDisplayEvent.ERROR);
	}

	@Override
	public void addFailure(Test test, AssertionFailedError t) {
		updater.onUpdate(EDisplayEvent.FAILURE);
	}

	@Override
	public void endTest(Test test) {
		updater.onUpdate(EDisplayEvent.END_TEST);
	}

	@Override
	public void startTest(Test test) {
		updater.onUpdate(EDisplayEvent.START_TEST);
	}

}
