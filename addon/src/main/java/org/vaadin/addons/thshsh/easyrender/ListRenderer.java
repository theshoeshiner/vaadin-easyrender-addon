package org.vaadin.addons.thshsh.easyrender;

import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.data.renderer.BasicRenderer;
import com.vaadin.flow.function.ValueProvider;

/**
 * Renderer that calls an {@link com.vaadin.flow.component.ItemLabelGenerator} and separates values with a separator
 */
@SuppressWarnings("serial")
public class ListRenderer<V,SOURCE,U extends List<V>,T> extends BasicRenderer<SOURCE,U> {

	
	public static final String DEFAULT_SEPARATOR = ", ";
	
	protected ItemLabelGenerator<V> labelGenerator;
	protected String separator;

	public ListRenderer(ValueProvider<SOURCE, U> valueProvider) {
        this(valueProvider,String::valueOf,DEFAULT_SEPARATOR);
    }
	
	public ListRenderer(ValueProvider<SOURCE, U> valueProvider,ItemLabelGenerator<V> labelGenerator) {
        this(valueProvider,labelGenerator,DEFAULT_SEPARATOR);
    }

	public ListRenderer(ValueProvider<SOURCE, U> valueProvider,ItemLabelGenerator<V> labelGenerator,String separator) {
	    super(valueProvider);
	    this.labelGenerator = labelGenerator;
	    this.separator = separator;
	}

    @Override
    protected String getFormattedValue(U list) {
        String l = list.stream().map(labelGenerator::apply).collect(Collectors.joining(separator));
        return l;
    }

    
}