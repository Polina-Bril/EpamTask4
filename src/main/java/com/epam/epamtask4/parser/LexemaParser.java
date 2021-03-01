package com.epam.epamtask4.parser;

import com.epam.epamtask4.entity.AbstractComponent;
import com.epam.epamtask4.entity.CompositeText;
import com.epam.epamtask4.entity.Symbol;
import com.epam.epamtask4.entity.TypeComponent;
import com.epam.epamtask4.exception.CompositeException;

public class LexemaParser implements AbstractParser {
    public final static String WORD_CODE = "\\w+\\.\\w+\\(.*\\)";
    public final static String PUNCTUATION = "(\\p{Punct})";
    public final static String MARK_WORD_MARK = "(\\p{Punct}.+\\p{Punct})";
    public final static String MARK_WORD = "(\\p{Punct}.+)";
    public final static String CODE_MARK = "\\w+\\.\\w+\\(.*\\)\\p{Punct}";
    public final static String WORD_MARK = ".+\\p{Punct}";
    public final static String WORD_MARK_SPLIT = "(?=[,.!?)])";
    private AbstractParser parser = new WordParser();

    @Override
    public AbstractComponent parse(String lexema) throws CompositeException {
        if (lexema == null || lexema.isEmpty()) {
            throw new CompositeException("lexema is null or empty");
        }
        CompositeText lexemaComponent = new CompositeText(TypeComponent.LEXEMA);
        String word;
        Symbol symbol;
        if (lexema.matches(WORD_CODE)) {
            AbstractComponent wordComponent = parser.parse(lexema);
            lexemaComponent.addComponent(wordComponent);
        } else if (lexema.matches(PUNCTUATION)) {
            symbol = new Symbol(TypeComponent.PUNCTUATION, lexema.charAt(0));
            lexemaComponent.addComponent(symbol);
        } else if (lexema.matches(MARK_WORD_MARK)) {
            Character firstSymbol = lexema.charAt(0);
            symbol = new Symbol(TypeComponent.PUNCTUATION, firstSymbol);
            lexemaComponent.addComponent(symbol);
            word = lexema.substring(1, lexema.length() - 1);
            AbstractComponent wordComponent = parser.parse(word);
            lexemaComponent.addComponent(wordComponent);
            Character lastSymbol = lexema.charAt(lexema.length() - 1);
            symbol = new Symbol(TypeComponent.PUNCTUATION, lastSymbol);
            lexemaComponent.addComponent(symbol);
        } else if (lexema.matches(MARK_WORD)) {
            Character firstSymbol = lexema.charAt(0);
            symbol = new Symbol(TypeComponent.PUNCTUATION, firstSymbol);
            lexemaComponent.addComponent(symbol);
            word = lexema.substring(1, lexema.length());
            AbstractComponent wordComponent = parser.parse(word);
            lexemaComponent.addComponent(wordComponent);
        } else if (lexema.matches(CODE_MARK)) {
            word = lexema.substring(0, lexema.length() - 1);
            AbstractComponent wordComponent = parser.parse(word);
            lexemaComponent.addComponent(wordComponent);
            Character lastSymbol = lexema.charAt(lexema.length() - 1);
            symbol = new Symbol(TypeComponent.PUNCTUATION, lastSymbol);
            lexemaComponent.addComponent(symbol);
        } else if (lexema.matches(WORD_MARK)) {
            String[] lexemaElements = lexema.split(WORD_MARK_SPLIT);
            for (String el : lexemaElements) {
                if (el.matches(PUNCTUATION)) {
                    Character markSymbol = el.charAt(0);
                    symbol = new Symbol(TypeComponent.PUNCTUATION, markSymbol);
                    lexemaComponent.addComponent(symbol);
                } else {
                    AbstractComponent wordComponent = parser.parse(el);
                    lexemaComponent.addComponent(wordComponent);
                }
            }
        } else {
            AbstractComponent wordComponent = parser.parse(lexema);
            lexemaComponent.addComponent(wordComponent);
        }
        return lexemaComponent;
    }
}
