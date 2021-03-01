package com.epam.epamtask4.entity;

import java.util.List;
import java.util.Objects;

public class Symbol implements AbstractComponent {
    public static final int SYMBOL_LENGHT = 1;
    private TypeComponent type;
    private char value;

    public Symbol() {
    }

    public Symbol(TypeComponent type, char value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public TypeComponent getType() {
        return type;
    }

    @Override
    public List<AbstractComponent> getComponents() {
        throw new UnsupportedOperationException("Symbols don't have components");
    }

    @Override
    public int countSymbol() {
        return SYMBOL_LENGHT;
    }

    public void setType(TypeComponent type) {
        this.type = type;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return value == symbol.value && type == symbol.type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + value;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Symbol{");
        sb.append("value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
