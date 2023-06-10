package org.vaadin.addons.thshsh.easyrender;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.IconFactory;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.ValueProvider;

/**
 * Renders a configurable Icon in place of a boolean value.
 *
 * @param <Source>
 */
public class BooleanIconRenderer<Source> extends ComponentRenderer<com.vaadin.flow.component.Component,Source> {
	
    private static final long serialVersionUID = 1087506089286659976L;

    public BooleanIconRenderer(IconFactory icon, ValueProvider<Source, Boolean> valProvider) {
		this(icon,null,icon,null,valProvider);
	}
	
	public BooleanIconRenderer(IconFactory trueIcon,IconFactory falseIcon,IconFactory nullIcon,String className, ValueProvider<Source, Boolean> valProvider) {
		super(source -> {
			Icon i = null;
			Boolean b = valProvider.apply(source);
			if(b == null && nullIcon != null) return nullIcon.create();
			if(Boolean.TRUE.equals(b)) {
				if(trueIcon != null) {
					i = trueIcon.create();
				}
			}
			else {
				if(falseIcon != null) {
					i = falseIcon.create();
				}
			}
			if(i!=null && className!=null)i.addClassName(className);
			
			if(i != null) return i;
			else return new Span();
		});

	}

	
}