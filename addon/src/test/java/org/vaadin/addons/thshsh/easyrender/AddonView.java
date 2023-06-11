package org.vaadin.addons.thshsh.easyrender;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

@Route("")
public class AddonView extends Div {
	
	String[] names = {"Rob","Ashley","Victoria","Nate","Rosie","Axl","Caleb","Lucas","Trey","Dana","Beth"};
	//String[] images = {"Rob","Ashley","Victoria","Nate","Rosie","Axl","Caleb","Lucas","Trey","Dana","Beth"};
	Random random = new Random(System.currentTimeMillis());

    public AddonView() {

        List<Person> data = new ArrayList<>();
        for(int i=0;i<names.length;i++) {
        	data.add(new Person(names[i],
        	       Duration.ofMinutes(random.nextInt(60)+5),
        	       random.nextBoolean(),
        	       random.nextInt(10),
        	       OffsetDateTime.now().minusMinutes(random.nextInt(500)+100),
        	       "images/user"+i+".png"
        	        ));
        }
        
        Grid<Person> grid = new Grid<>();
        grid.setWidthFull();
        grid.setItems(new ListDataProvider<>(data));
        
        grid.addColumn(Person::getName);
        
        grid.addColumn(new BooleanIconRenderer<>(VaadinIcon.CHECK, VaadinIcon.CLOSE, null, null, Person::getActive));
        
        grid.addColumn(new DurationRenderer<>(Person::getWaited, "H'h 'm'm'"));
        
        grid.addColumn(new NumberRenderer<>(Person::getItemCount, new DecimalFormat("#"), "", true));
        
        grid.addColumn(new TemporalRenderer<>(Person::getTimestamp, DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        
        grid.addColumn(new ImageRenderer<>(Person::getPictureUrl, null, "75px", null));
        
        /* grid.addColumn(Person::getAge);
        
        grid.addComponentColumn(person -> {
        	HorizontalLayout buttons = new HorizontalLayout();
        	Button edit = new Button(VaadinIcon.PENCIL.create());
        	edit.addClassName(HoverColumn.HOVER_COLUMN_EXCLUDE_CLASS);
        	Button delete = new Button(VaadinIcon.TRASH.create());
        	buttons.add(edit,delete);
        	return buttons;
        }).setClassNameGenerator(person -> {
        	return HoverColumn.HOVER_COLUMN_CLASS;
        });*/
        
        add(grid);
    	
    }
    
    public static class Person {
        
    	String name;
    	Duration waited;
    	Boolean active;
    	Integer itemCount;
    	OffsetDateTime timestamp;
    	String pictureUrl;
    	
    	
		
		public Person(String name, Duration waited, Boolean active, Integer itemCount, OffsetDateTime timestamp, String pictureUrl) {
            super();
            this.name = name;
            this.waited = waited;
            this.active = active;
            this.itemCount = itemCount;
            this.timestamp = timestamp;
            this.pictureUrl = pictureUrl;
        }
        public String getName() {
			return name;
		}
		public void setName(String firstName) {
			this.name = firstName;
		}
        public Duration getWaited() {
            return waited;
        }
        public void setWaited(Duration waited) {
            this.waited = waited;
        }
        public Boolean getActive() {
            return active;
        }
        public void setActive(Boolean active) {
            this.active = active;
        }
        public Integer getItemCount() {
            return itemCount;
        }
        public void setItemCount(Integer itemCount) {
            this.itemCount = itemCount;
        }
        public OffsetDateTime getTimestamp() {
            return timestamp;
        }
        public void setTimestamp(OffsetDateTime timestamp) {
            this.timestamp = timestamp;
        }
        public String getPictureUrl() {
            return pictureUrl;
        }
        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }
		
		
		
    	
		
    }
}
