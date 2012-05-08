package com.javaxpert.books.osgi.scrdemo.ui.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.border.Border;
import javax.swing.event.ListDataListener;
import org.osgi.framework.BundleContext;
import com.javaxpert.books.osgi.scrdemo.ui.api.IAction;
import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

@Component(immediate = true)
public class MainGUI extends JFrame {
    private CopyOnWriteArrayList<IAction> actions;
    private JButton jbLaunchAction;
    private final JTextArea jtaResult;
    private final JList jlActions;

    private class MyModel implements ListModel {

	@Override
	public void addListDataListener(ListDataListener arg0) {
	}

	@Override
	public synchronized Object getElementAt(int arg0) {
	    return actions.get(arg0);
	}

	@Override
	public synchronized int getSize() {
	    return actions.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
	}

    }

    public MainGUI() {
	super("Demo SCR");
	System.out.println("lancement de la GUI");
	actions = new CopyOnWriteArrayList<IAction>();
	jtaResult = new JTextArea("");
	// DefaultListModel model = new DefaultListModel();
	// for(IAction action:actions)
	// model.addElement(action);
	MyModel model = new MyModel();
	jlActions = new JList(model);
	jlActions.setVisibleRowCount(4);
	jlActions.setVisible(true);
    }

    @Reference(dynamic = true, optional = true, multiple = true)
    public void setAction(IAction action) {

	if (!actions.contains(action)) {
	    System.out.println("ajout d'une action dans la liste");
	    actions.add(action);
	}
	getContentPane().invalidate();
	getContentPane().validate();
	pack();

    }

    public void unsetAction(IAction action) {
	if (actions.contains(action)) {
	    System.out.println("suppression d'un plugin");
	    actions.remove(action);
	    action = null;
	}
	getContentPane().invalidate();
	getContentPane().validate();
	pack();
    }

    @Activate
    public void start(BundleContext bc) {

	bootstrap();
    }

    private void bootstrap() {
	JPanel panel = (JPanel) getContentPane();
	panel.setLayout(new BorderLayout());
	JPanel button_panel = new JPanel();
	Border buttonPanelBorder = BorderFactory
		.createTitledBorder("Invoking action");
	button_panel.setBorder(buttonPanelBorder);
	button_panel.add(jbLaunchAction = new JButton("Invoke action"));

	panel.add("North", button_panel);
	jbLaunchAction.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		int selected_action_index = jlActions.getSelectedIndex();
		Object obj = jlActions.getModel().getElementAt(
			selected_action_index);
		IAction action = (IAction) obj;
		String result = action.doSomething();
		jtaResult.setText(result);

	    }
	});
	JPanel plugins_panel = new JPanel();
	Border plugins_border = BorderFactory
		.createTitledBorder("Available plugins");
	plugins_panel.setBorder(plugins_border);
	plugins_panel.add(jlActions);

	panel.add("Center", plugins_panel);
	panel.add("South", jtaResult);
	pack();
	setVisible(true);
    }
}
