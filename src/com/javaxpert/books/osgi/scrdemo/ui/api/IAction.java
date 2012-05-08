package com.javaxpert.books.osgi.scrdemo.ui.api;

/**
 * <p>
 * interface minimaliste implémentée par différents bundles et
 * injectée par le SCR au sein d'une interface graphique simple
 * </p>
 * @author romje,jerome@javaxpert.com
 *
 */
public interface IAction {
	public String doSomething();

	public String getName();
}
