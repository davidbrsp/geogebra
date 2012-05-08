package geogebra.factories;

import geogebra.common.euclidian.Drawable;
import geogebra.common.gui.inputfield.AutoCompleteTextField;
import geogebra.common.javax.swing.Box;
import geogebra.common.javax.swing.JLabel;
import geogebra.common.main.AbstractApplication;
import geogebra.javax.swing.JComboBoxDesktop;

public class SwingFactory extends geogebra.common.factories.SwingFactory {

	// TODO: find another place for this function
	@Override
	public AutoCompleteTextField newAutoCompleteTextField(int length,
			AbstractApplication application, Drawable drawTextField) {
		return new geogebra.gui.inputfield.AutoCompleteTextField(length, application, drawTextField);
	}

	@Override
	public JLabel newJLabel(String string) {
		return new geogebra.javax.swing.JLabel(string);
	}

	@Override
	public Box createHorizontalBox() {
		return new geogebra.javax.swing.Box(javax.swing.Box.createHorizontalBox());
	}

	@Override
	public JComboBoxDesktop newJComboBox() {
		return new geogebra.javax.swing.JComboBoxDesktop();
	}

}
