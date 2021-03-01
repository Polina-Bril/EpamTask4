package com.epam.epamtask4.parser;

import com.epam.epamtask4.entity.AbstractComponent;
import com.epam.epamtask4.entity.CompositeText;
import com.epam.epamtask4.entity.TypeComponent;
import com.epam.epamtask4.exception.CompositeException;

public class ParagraphParser implements  AbstractParser {
    public final static String SENTENCE_SPLIT = "(?<=([.!?.{3}]\s))";
    private AbstractParser parser = new SentenceParser();

    @Override
    public AbstractComponent parse(String paragraph) throws CompositeException {
        if (paragraph == null || paragraph.isEmpty()) {
            throw new CompositeException("paragraph is null or empty");
        }
        CompositeText paragraphComponent = new CompositeText(TypeComponent.PARAGRAPH);
        String[] sentenceData = paragraph.split(SENTENCE_SPLIT);
        for (String sentence : sentenceData) {
            AbstractComponent sentenceComponent = parser.parse(sentence);
            paragraphComponent.addComponent(sentenceComponent);
        }
        return paragraphComponent;
    }
}
