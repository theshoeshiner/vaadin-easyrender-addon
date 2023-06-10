package org.vaadin.addons.thshsh.easyrender;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.ValueProvider;


/**
 * 
 * Renderer that returns an Image based on url sourced from a ValueProvider, with a static width and height.
 * Useful when entities contain a url to a source image.
 *
 * @param <Source>
 */
public class ImageRenderer<Source> extends ComponentRenderer<Image,Source> {
	
    private static final long serialVersionUID = 5161427541175341534L;
    
    protected String width;
    protected String height;
	
	public ImageRenderer(ValueProvider<Source,String> urlProvider,ValueProvider<Source,String> altProvider,String width,String height) {
		super(source -> {
			Image image = new Image(urlProvider.apply(source), altProvider!=null? altProvider.apply(source):StringUtils.EMPTY);
			image.setWidth(width);
			image.setHeight(height);
			return image;
		});
		this.width = width;
		this.height = height;
	}
	

}
