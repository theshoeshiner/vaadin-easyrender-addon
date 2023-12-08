package org.vaadin.addons.thshsh.easyrender;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Locale;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.IconFactory;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.function.SerializableSupplier;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.Router;
import com.vaadin.flow.server.VaadinService;

public class EasyRender {
	
	/*
	 * Temporals
	 */
	
	public static <Source,Target extends Temporal> ValueProvider<Source,String> temporal(ValueProvider<Source, Target> valueProvider){
		return temporal(valueProvider, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	}
	
	public static <Source,Target extends Temporal> ValueProvider<Source,String> temporal(ValueProvider<Source, Target> valueProvider,DateTimeFormatter formatter){
		return temporal(valueProvider, ()->formatter);
	}

	public static <Source,Target extends Temporal> ValueProvider<Source,String> temporal(ValueProvider<Source, Target> valueProvider,SerializableSupplier<DateTimeFormatter> formatter){
		return (source) -> {
			Target temporal = valueProvider.apply(source);
			return temporal == null ? StringUtils.EMPTY
		            : formatter.get().format(temporal);
		};
	}
	
	/*
	 * Durations
	 */
	
	public static <Source> ValueProvider<Source,String> duration(ValueProvider<Source, Duration> valueProvider,String format){
		return (source) -> {
			Duration duration = valueProvider.apply(source);
			return duration == null ? StringUtils.EMPTY
	                : DurationFormatUtils.formatDuration(duration.toMillis(),format);
		};
	}
	
	/*
	 * Numbers
	 */
	
	public static <Source,Target extends Number> ValueProvider<Source,String> number(ValueProvider<Source, Target> valueProvider,NumberFormat formatter,String nullRepresentation,Boolean zeroAsNull){
		return number(valueProvider,()->formatter,nullRepresentation,zeroAsNull);
	}
	
	public static <Source,Target extends Number> ValueProvider<Source,String> number(ValueProvider<Source, Target> valueProvider,SerializableSupplier<NumberFormat> formatter,String nullRepresentation,Boolean zeroAsNull){
		return (source) -> {
			Number value = valueProvider.apply(source);
			if(value == null) return nullRepresentation;
			else {
				if(zeroAsNull && value.longValue() == 0l) return nullRepresentation;
				else return formatter.get().format(value);
			}
		};
	}
	
	/*
	 * Links
	 */
	
	public static <Source,Target extends Number> LitRenderer<Source> router(
			Class<? extends Component> navigationTarget,
			ValueProvider<Source, ?> idProvider,
			ValueProvider<Source, ?> nameProvider,
			String paramName
          ){
		return router(navigationTarget, idProvider, nameProvider, paramName, null);
	}
	
	public static <Source,Target extends Number> LitRenderer<Source> router(
			 Class<? extends Component> navigationTarget,
			ValueProvider<Source, ?> idProvider,
			ValueProvider<Source, ?> nameProvider,
			String paramName,
			String target
          ){
		return router(navigationTarget,idProvider,nameProvider,null,null,paramName,target);
	}

	public static <Source,Target extends Number> LitRenderer<Source> router(
			 Class<? extends Component> navigationTarget,
			ValueProvider<Source, ?> idProvider,
			ValueProvider<Source, ?> nameProvider,
			ValueProvider<Source, ?> iconNameProvider,
			ValueProvider<Source, ?> cssClassProvider,
			String paramName,
			String target
           ){
		
		Router router = VaadinService.getCurrent().getRouter();
		
		boolean href = navigationTarget != null && idProvider != null && paramName != null;
		boolean icon = iconNameProvider != null;
		boolean value = nameProvider != null;
		boolean cssClass = cssClassProvider != null;
		
		LitRenderer<Source> renderer = LitRenderer
				.<Source>of("<a router-link"
						+ (href ? " href='${item.url}'" : "")
						+ (cssClass ? " class='${item.class}'" : "")
						+">"
						+ (value ? "${item.value}" : "")
						+ (icon ? "<vaadin-icon icon='${item.icon}' />" : "")
						+ "</a>"
						);
		if(value) 
			renderer = renderer.withProperty("value", source -> {
					return nameProvider.apply(source);
				});
		
		if(cssClass) 
			renderer = renderer.withProperty("class", source -> {
					return cssClassProvider.apply(source);
				});
		
		if(icon) 
			renderer = renderer.withProperty("icon", source -> {
					String iconName = Objects.toString(iconNameProvider.apply(source), null);
					return iconName;

				});
		
		if(href) {
			renderer = renderer.withProperty("url", source -> {
					String url = RouteConfiguration
							.forRegistry(router.getRegistry())
		                    .getUrl(navigationTarget, RouteParameters.empty());
					return url +"?"+paramName+"="+idProvider.apply(source).toString();

			});
		}
		return renderer;
	}
	
	
	/*
	 * Icon
	 */
	
	public static <Source,I extends Enum<I> & IconFactory> LitRenderer<Source> icon(I icon){
		return icon(s -> icon,"vaadin");
	}
	
	public static <Source,I extends Enum<I> & IconFactory> LitRenderer<Source> icon(ValueProvider<Source, I> valueProvider){
		return icon(valueProvider,"vaadin");
	}
	
	public static <Source,I extends Enum<I> & IconFactory> LitRenderer<Source> icon(ValueProvider<Source, I> iconProvider,String collection){

		return LitRenderer.<Source>of("<vaadin-icon icon='${item.icon}' />")
			.withProperty("icon", source -> {
				I icon = iconProvider.apply(source);
				if(icon != null) {
					String name = icon.name().toLowerCase(Locale.ENGLISH).replace('_', '-');
					return collection +":"+name;
				}
				else return "";
			});
	}
	
	
	/*
	 * Boolean icon
	 */
	
	public static <Source,I extends Enum<I> & IconFactory> LitRenderer<Source> booleanIcon(
			ValueProvider<Source, Boolean> valueProvider,
			I trueIcon,
			I falseIcon,
			I nullIcon){
		return booleanIcon(valueProvider, s -> trueIcon,s -> falseIcon, s -> nullIcon, "vaadin");
	}
	
	public static <Source,I extends Enum<I> & IconFactory> LitRenderer<Source> booleanIcon(
			ValueProvider<Source, Boolean> valueProvider,
			ValueProvider<Source, I> trueIconProvider,
			ValueProvider<Source, I> falseIconProvider,
			ValueProvider<Source, I> nullIconProvider){
		return booleanIcon(valueProvider, trueIconProvider,falseIconProvider, nullIconProvider, "vaadin");
	}
	
	public static <Source,I extends Enum<I> & IconFactory> LitRenderer<Source> booleanIcon(
			ValueProvider<Source, Boolean> valueProvider,
			ValueProvider<Source, I> trueIconProvider,
			ValueProvider<Source, I> falseIconProvider,
			ValueProvider<Source, I> nullIconProvider,
			String collection
			){
		return booleanIcon(valueProvider, trueIconProvider, s -> collection, falseIconProvider, s -> collection, nullIconProvider, s -> collection);
	}
			

	public static <Source,I extends Enum<I> & IconFactory> LitRenderer<Source> booleanIcon(
			ValueProvider<Source, Boolean> valueProvider,
			ValueProvider<Source, I> trueIconProvider,
			ValueProvider<Source, String> trueCollectionProvider,
			ValueProvider<Source, I> falseIconProvider,
			ValueProvider<Source, String> falseCollectionProvider,
			ValueProvider<Source, I> nullIconProvider,
			ValueProvider<Source, String> nullCollectionProvider){

		return LitRenderer.<Source>of("<vaadin-icon icon='${item.icon}' />")
			.withProperty("icon", source -> {
				Boolean value = valueProvider.apply(source);
				
				ValueProvider<Source, I> iconProvider;
				ValueProvider<Source, String> collectionProvider;
				I icon = null;
				String iconName = null;
				String iconCollection = null;
				
				if(Boolean.TRUE.equals(value)) {
					iconProvider = trueIconProvider;
					collectionProvider = trueCollectionProvider;
				}
				else if(Boolean.FALSE.equals(value)) {
					iconProvider = falseIconProvider;
					collectionProvider = falseCollectionProvider;
				}
				else  {
					iconProvider = nullIconProvider;
					collectionProvider = nullCollectionProvider;
				}
				
				if(iconProvider != null) {
					icon = iconProvider.apply(source);
					if(icon != null) {
						iconName = icon.name().toLowerCase(Locale.ENGLISH).replace('_', '-');
						if(collectionProvider != null) {
							iconCollection = collectionProvider.apply(source);
							return iconCollection+":"+iconName;
						}
						
					}
				}
				
				
				return StringUtils.EMPTY;
		});
	}
	
	/*public static <Source,Target> LitRenderer<Source> nullableValue(ValueProvider<Source, Target> valueProvider,String template,String nullRepresentation){
	
		return LitRenderer.<Source>of(template)
			.withProperty("value", source -> {
				
		});
	}*/
}

