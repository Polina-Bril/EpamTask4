package com.epam.epamtask4.parser;

import com.epam.epamtask4.entity.AbstractComponent;
import com.epam.epamtask4.entity.CompositeText;
import com.epam.epamtask4.entity.TypeComponent;
import com.epam.epamtask4.exception.CompositeException;

public class SentenceParser implements AbstractParser {
        public final static String LEXEMA_SPLIT = "\s";
        private AbstractParser parser = new LexemaParser();

        @Override
        public AbstractComponent parse(String sentence) throws CompositeException {
            if (sentence == null || sentence.isEmpty()) {
                throw new CompositeException("sentence is null or empty");
            }
            CompositeText sentenceComponent = new CompositeText(TypeComponent.SENTENCE);
            String[] lexemaData = sentence.split(LEXEMA_SPLIT);
            for (String lexema : lexemaData) {
                AbstractComponent lexemaComponent = parser.parse(lexema);
                sentenceComponent.addComponent(lexemaComponent);
            }
            return sentenceComponent;
        }
}
