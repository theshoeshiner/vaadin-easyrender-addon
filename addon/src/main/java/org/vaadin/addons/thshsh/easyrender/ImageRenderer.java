package org.vaadin.addons.thshsh.easyrender;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.ValueProvider;


/**
 * 
 * Renders an Image with url, alt text, width, and height, specified via {@link ValueProvider}s
 *
 * @param <Source>
 */
public class ImageRenderer<Source> extends ComponentRenderer<Image,Source> {
	
    private static final long serialVersionUID = 5161427541175341534L;

    protected ValueProvider<Source, String> widthProvider;
    protected ValueProvider<Source, String> heightProvider;
    protected ValueProvider<Source, String> urlProvider;
    protected ValueProvider<Source,String> altProvider;
	
	public ImageRenderer(ValueProvider<Source,String> urlProvider,ValueProvider<Source,String> altProvider,String width,String height) {
		this(urlProvider,altProvider,s -> width, s-> height);
	}
	
    public ImageRenderer(ValueProvider<Source, String> urlProvider,ValueProvider<Source,String> altProvider, ValueProvider<Source, String> widthProvider,ValueProvider<Source, String> heightProvider) {
        super();
        this.urlProvider = urlProvider;
        this.altProvider = altProvider;
        this.widthProvider = widthProvider;
        this.heightProvider = heightProvider;
        
    }

    @Override
    public Image createComponent(Source source) {
        Image image = new Image(urlProvider.apply(source), altProvider!=null? altProvider.apply(source):StringUtils.EMPTY);
        image.setWidth(widthProvider.apply(source));
        image.setHeight(heightProvider.apply(source));
        return image;
    }
	
   

}
