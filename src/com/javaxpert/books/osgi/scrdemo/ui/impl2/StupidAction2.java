package com.javaxpert.books.osgi.scrdemo.ui.impl2;

import aQute.bnd.annotation.component.Component;
import com.javaxpert.books.osgi.scrdemo.ui.api.IAction;

@Component
public class StupidAction2 implements IAction {
	public StupidAction2(){
		System.out.println("StupidAction2 instanciée");
	}
	@Override
	public String doSomething() {
		return "Hello World from StupidACtion2";
	}
	@Override
	public String toString() {
		return "StupidAction2";
	}
	@Override
	public String getName() {
		return toString();
	}

}
