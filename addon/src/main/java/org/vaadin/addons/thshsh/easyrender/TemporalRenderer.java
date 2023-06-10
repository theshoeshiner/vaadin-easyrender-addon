package org.vaadin.addons.thshsh.easyrender;

import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

import com.vaadin.flow.function.SerializableSupplier;
import com.vaadin.flow.function.ValueProvider;

/**
 * Renderer that formats a {@link java.time.temporal.Temporal} using the provided {@link java.time.format.DateTimeFormatter}  
 *
 * @param <Source>
 */
public class TemporalRenderer<Source,T extends Temporal> extends NullableBasicRenderer<Source,T> { 

    private static final long serialVersionUID = -4526832464940635351L;
    
    protected SerializableSupplier<DateTimeFormatter> formatter;
    protected String nullRepresentation;
    
    public TemporalRenderer(ValueProvider<Source, T> valueProvider) {
        this(valueProvider,() -> DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        
    }

    public TemporalRenderer(ValueProvider<Source, T> valueProvider, DateTimeFormatter f) {
        this(valueProvider,() -> f);
        
    }
    
    public TemporalRenderer(ValueProvider<Source, T> valueProvider,SerializableSupplier<DateTimeFormatter> formatter) {
        super(valueProvider);
        this.formatter = formatter;
    }
    
	
    @Override
    protected String getFormattedValue(Temporal dateTime) {
        return dateTime == null ? nullRepresentation
                : formatter.get().format(dateTime);
    }
}
