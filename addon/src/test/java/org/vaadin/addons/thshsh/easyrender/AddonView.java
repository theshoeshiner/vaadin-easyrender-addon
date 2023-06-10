package org.vaadin.addons.thshsh.easyrender;

import java.util.Random;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("")
public class AddonView extends Div {
	
	String[] names = {"Rob","Ashley","Victoria","Nate","Rosie","Axl","Caleb","Lucas","Trey","Dana","Beth"};
	Random random = new Random(System.currentTimeMillis());

    public AddonView() {

        /*List<Person> data = new ArrayList<>();
        for(int i=0;i<names.length;i++) {
        	data.add(new Person(names[i],random.nextInt(70)));
        }
        
        Grid<Person> grid = new Grid<>();
        grid.setWidthFull();
        grid.setItems(new ListDataProvider<>(data));
        
        grid.addColumn(Person::getName);
        grid.addColumn(Person::getAge);
        
        grid.addComponentColumn(person -> {
        	HorizontalLayout buttons = new HorizontalLayout();
        	Button edit = new Button(VaadinIcon.PENCIL.create());
        	edit.addClassName(HoverColumn.HOVER_COLUMN_EXCLUDE_CLASS);
        	Button delete = new Button(VaadinIcon.TRASH.create());
        	buttons.add(edit,delete);
        	return buttons;
        }).setClassNameGenerator(person -> {
        	return HoverColumn.HOVER_COLUMN_CLASS;
        });
        
        add(grid);*/
    	
    }
    
    public static class Person {
    	String name;
    	Integer age;
		public Person(String name, Integer age) {
			super();
			this.name = name;
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String firstName) {
			this.name = firstName;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		
    	
		
    }
}
