package org.vaadin.addons.thshsh.easyrender;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

@Route("")
public class AddonView extends Div {

    private static final long serialVersionUID = -5828371605864015426L;
    
    String[] names = {"Rob","Ashley","Victoria","Nate","Rosie","Axl","Caleb","Lucas","Trey","Dana","Beth"};
	Random random = new Random(System.currentTimeMillis());

    public AddonView() {

        List<Person> data = new ArrayList<>();
        for(int i=0;i<names.length;i++) {
        	data.add(new Person(i,names[i],
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
        

        grid.addColumn(new RouterLinkRenderer<>(PersonView.class, Person::getName, Person::getId));
        
        grid.addColumn(new BooleanIconRenderer<>(VaadinIcon.CHECK, VaadinIcon.CLOSE, null, null, Person::getActive))
        .setWidth("100px").setFlexGrow(0);
        
        grid.addColumn(new DurationRenderer<>(Person::getWaited, "H'h 'm'm'"))
        .setWidth("150px").setFlexGrow(0);
        
        grid.addColumn(new NumberRenderer<>(Person::getItemCount, new DecimalFormat("#"), "", true))
        .setWidth("100px").setFlexGrow(0)
        ;
        
        grid.addColumn(new TemporalRenderer<>(Person::getTimestamp, DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        
        //FIXME these images arent showing when running jetty
        grid.addColumn(new ImageRenderer<>(Person::getPictureUrl, null, "75px", null));
        
        add(grid);
    	
    }
    
    public static class Person {
        
        Integer id;
    	String name;
    	Duration waited;
    	Boolean active;
    	Integer itemCount;
    	OffsetDateTime timestamp;
    	String pictureUrl;
    	
    	
		
		public Person(Integer id,String name, Duration waited, Boolean active, Integer itemCount, OffsetDateTime timestamp, String pictureUrl) {
            super();
            this.id = id;
            this.name = name;
            this.waited = waited;
            this.active = active;
            this.itemCount = itemCount;
            this.timestamp = timestamp;
            this.pictureUrl = pictureUrl;
        }
		
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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
