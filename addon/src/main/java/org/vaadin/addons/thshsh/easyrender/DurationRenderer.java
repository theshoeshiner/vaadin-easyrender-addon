package org.vaadin.addons.thshsh.easyrender;

import java.time.Duration;

import org.apache.commons.lang3.time.DurationFormatUtils;

import com.vaadin.flow.function.ValueProvider;

/**
 * Renders a Duration object according to a format string. {@link org.apache.commons.lang3.time.DurationFormatUtils}
 * @author daniel.watson
 * @deprecated Use LitRenderer {@link org.vaadin.addons.thshsh.easyrender.EasyRender#duration}
 * @param <Source>
 */
public class DurationRenderer<Source> extends NullableBasicRenderer<Source,Duration> { 

    private static final long serialVersionUID = -4668050243515310478L;

    protected String format;
    
    /**
     * 
     * @param valueProvider value provider for source
     * @param format format string as specified by {@link org.apache.commons.lang3.time.DurationFormatUtils}
     */
	public DurationRenderer(ValueProvider<Source, Duration> valueProvider,String format) {
	    super(valueProvider);
	    this.format = format;
	}

    @Override
    protected String getFormattedValue(Duration duration) {
        if(duration != null) {
            return DurationFormatUtils.formatDuration(duration.toMillis(),format);
        }
        else return "";
    }
	
	
	
}