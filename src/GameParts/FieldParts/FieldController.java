package GameParts.FieldParts;

import Components.CollideComponent.CollideComponent;
import Components.Component;
import Components.MovingComponent.MovingComponent;
import ModelDrawer.ObjectDrawer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FieldController {
    public final Field field;
    protected List<Component> components;

    public FieldController(Field field) {
        this.field = field;
        this.components = new ArrayList<>(5);

        this.components.add(new MovingComponent());
        this.components.add(new CollideComponent());
    }

    public void update() {
        components.forEach(Component::update);
    }

    public void add(ObjectDrawer objectDrawer) {
        field.add(objectDrawer);
        components.forEach(component -> component.add(objectDrawer.getCollideModel()));
    }

    public void draw(Graphics2D g) {
        field.objectDrawers.forEach(x -> x.draw(g));
    }
}
