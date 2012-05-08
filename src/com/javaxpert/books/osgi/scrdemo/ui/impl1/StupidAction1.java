package com.javaxpert.books.osgi.scrdemo.ui.impl1;

import aQute.bnd.annotation.component.Component;

import com.javaxpert.books.osgi.scrdemo.ui.api.IAction;

@Component
public class StupidAction1 implements IAction {
	@Override
	public String toString() {
		return "StupidAction1";
	}
	public StupidAction1(){
		System.out.println("StupidAction1 instanciée");
	}
	@Override
	public String doSomething() {
		return "action stupide lancée!!";
	}
	@Override
	public String getName() {
		return toString();
	}
}
