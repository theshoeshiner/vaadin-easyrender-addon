package org.vaadin.addons.thshsh.easyrender;

import java.text.NumberFormat;
import java.util.Locale;

import com.vaadin.flow.function.ValueProvider;

/**
 * Extension of {@link com.vaadin.flow.data.renderer.NumberRenderer} that allows specifying that zeros
 * should be treated as null to avoid rendering tables with lots of zeros.
 * @deprecated Use LitRenderer {@link org.vaadin.addons.thshsh.easyrender.EasyRender#number}
 * @param <SOURCE>
 */
public class NumberRenderer<SOURCE> extends com.vaadin.flow.data.renderer.NumberRenderer<SOURCE>{

    private static final long serialVersionUID = -3081955270029706948L;
    
    protected Boolean zeroAsNull = false;
	
	public NumberRenderer(ValueProvider<SOURCE, Number> valueProvider, Locale locale) {
		super(valueProvider, locale);
	}

	public NumberRenderer(ValueProvider<SOURCE, Number> valueProvider, NumberFormat numberFormat, String nullRepresentation,Boolean zeroAsNull) {
		super(valueProvider, numberFormat, nullRepresentation);
		this.zeroAsNull = zeroAsNull;
	}

	public NumberRenderer(ValueProvider<SOURCE, Number> valueProvider, NumberFormat numberFormat) {
		super(valueProvider, numberFormat);
	}

	public NumberRenderer(ValueProvider<SOURCE, Number> valueProvider, String formatString, Locale locale, String nullRepresentation,Boolean zeroAsNull) {
		super(valueProvider, formatString, locale, nullRepresentation);
		this.zeroAsNull = zeroAsNull;
	}

	public NumberRenderer(ValueProvider<SOURCE, Number> valueProvider, String formatString, Locale locale) {
		super(valueProvider, formatString, locale);
	}

	public NumberRenderer(ValueProvider<SOURCE, Number> valueProvider, String formatString) {
		super(valueProvider, formatString);
	}

	 @Override
	 protected String getFormattedValue(Number value) {
		 if(value != null && zeroAsNull && value.intValue()==0) value = null;
		 return super.getFormattedValue(value);
	 }
	
	
}
