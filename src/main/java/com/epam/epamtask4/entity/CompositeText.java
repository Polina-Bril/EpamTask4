package com.epam.epamtask4.entity;

import com.epam.epamtask4.exception.CompositeException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class CompositeText implements AbstractComponent {
    public static Logger logger = LogManager.getLogger();
    public static final String SPACE = " ";
    public static final String TAB = "\t";
    private TypeComponent type;
    private List<AbstractComponent> components = new ArrayList<>();

    public CompositeText() {
    }
    public CompositeText(TypeComponent type) {
        this.type = type;
    }
    @Override
    public TypeComponent getType() {
        return type;
    }
    @Override
    public List<AbstractComponent> getComponents() {
        return Collections.unmodifiableList(components);
    }
    @Override
    public int countSymbol() {
        int counter = 0;
        for (AbstractComponent c : this.components) {
            counter += c.countSymbol();
        }
        return counter;
    }
    public void setType(TypeComponent type) {
        this.type = type;
    }
    public boolean addComponent(AbstractComponent component) {
        return components.add(component);
    }
    public boolean removeComponent(AbstractComponent component) {
        return components.remove(component);
    }
    public List<AbstractComponent> sortComponents(Comparator<AbstractComponent> comparator) throws CompositeException {
        if (comparator == null) {
            throw new CompositeException("comparator can't be null");
        }
        components.sort(comparator);
        logger.log(Level.INFO, "components sorted: " + components.toString());
        return Collections.unmodifiableList(components);
    }
    public int countWord() {
        int counter = 0;
        for (AbstractComponent c : this.components) {
            if (c.getType() == TypeComponent.WORD) {
                counter++;
            } else if (c.getType() != TypeComponent.PUNCTUATION) {
                counter += ((CompositeText) c).countWord();
            }
        }
        return counter;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeText that = (CompositeText) o;
        return type == that.type && Objects.equals(components, that.components);
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((components == null) ? 0 : components.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (AbstractComponent c : this.components) {
            sb.append(c.toString());
            if (c.getType() == TypeComponent.LEXEMA) {
                sb.append(SPACE);
            }
            if (c.getType() == TypeComponent.PARAGRAPH) {
                sb.append(TAB);
            }
        }
        return sb.toString();
    }
}
