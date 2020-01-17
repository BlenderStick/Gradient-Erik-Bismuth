package br.com.bismuthfernandes.geb;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPanelUI;

public class GEBPanelUI extends BasicPanelUI {
	public static ComponentUI createUI(JComponent c){
		return new GEBPanelUI();
	}
}
