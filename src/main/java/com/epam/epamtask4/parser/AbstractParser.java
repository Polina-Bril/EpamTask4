package com.epam.epamtask4.parser;

import com.epam.epamtask4.entity.AbstractComponent;
import com.epam.epamtask4.exception.CompositeException;

public interface AbstractParser {
    AbstractComponent parse(String text) throws CompositeException;
}
