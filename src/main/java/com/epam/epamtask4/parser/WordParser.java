package com.epam.epamtask4.parser;

import com.epam.epamtask4.entity.AbstractComponent;
import com.epam.epamtask4.entity.CompositeText;
import com.epam.epamtask4.entity.Symbol;
import com.epam.epamtask4.entity.TypeComponent;
import com.epam.epamtask4.exception.CompositeException;

public class WordParser implements AbstractParser {
    @Override
    public AbstractComponent parse(String word) throws CompositeException {
        CompositeText wordComponent = new CompositeText(TypeComponent.WORD);
        char[] symbols = word.toCharArray();
        Symbol symbolComponent;
        for (char symbol : symbols) {
            symbolComponent = new Symbol(TypeComponent.LETTER, symbol);
            wordComponent.addComponent(symbolComponent);
        }
        return wordComponent;
    }
}
