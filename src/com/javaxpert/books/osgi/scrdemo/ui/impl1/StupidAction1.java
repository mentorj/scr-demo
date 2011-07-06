package com.javaxpert.books.osgi.scrdemo.ui.impl1;

import aQute.bnd.annotation.component.Component;

import com.javaxpert.books.osgi.scrdemo.ui.api.IAction;

@Component(immediate=true)
public class StupidAction1 implements IAction {

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
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
}
