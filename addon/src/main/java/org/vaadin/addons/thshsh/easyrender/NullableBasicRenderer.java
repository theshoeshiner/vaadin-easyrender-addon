package org.vaadin.addons.thshsh.easyrender;

import com.vaadin.flow.data.renderer.BasicRenderer;
import com.vaadin.flow.function.ValueProvider;

public class NullableBasicRenderer<Source,Target> extends BasicRenderer<Source,Target> {

    private static final long serialVersionUID = -2138067004640938548L;

    protected String nullRepresentation = "";
    
    public NullableBasicRenderer(ValueProvider<Source, Target> valueProvider) {
        super(valueProvider);
    }
    
    public NullableBasicRenderer(ValueProvider<Source, Target> valueProvider,String nullRep) {
        super(valueProvider);
        this.nullRepresentation = nullRep;
    }

    public String getNullRepresentation() {
        return nullRepresentation;
    }

    public void setNullRepresentation(String nullRepresentation) {
        this.nullRepresentation = nullRepresentation;
    }

    
    
}
