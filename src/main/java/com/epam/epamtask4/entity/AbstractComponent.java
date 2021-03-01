package com.epam.epamtask4.entity;

import java.util.List;

public interface AbstractComponent {
    TypeComponent getType();

    List<AbstractComponent> getComponents();

    int countSymbol();

    public String toString();
}