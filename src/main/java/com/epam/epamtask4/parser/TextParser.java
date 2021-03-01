package com.epam.epamtask4.parser;

import com.epam.epamtask4.entity.AbstractComponent;
import com.epam.epamtask4.entity.CompositeText;
import com.epam.epamtask4.entity.TypeComponent;
import com.epam.epamtask4.exception.CompositeException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextParser implements AbstractParser{
    public static Logger logger = LogManager.getLogger();
    public final static String PARAGRAPH_SPLIT = "\s{4}";
    private AbstractParser parser = new ParagraphParser();

    @Override
    public AbstractComponent parse(String text) throws CompositeException {
        if (text == null || text.isEmpty()) {
            throw new CompositeException("text is null or empty");
        }
        CompositeText textComponent = new CompositeText(TypeComponent.TEXT);
        String[] paragraphData = text.split(PARAGRAPH_SPLIT);
        for (String paragraph : paragraphData) {
            if (!paragraph.isEmpty()) {
                AbstractComponent paragraphComponent = parser.parse(paragraph);
                textComponent.addComponent(paragraphComponent);
            }
        }
        logger.log(Level.INFO, "after parsing the component text is created: " + textComponent.toString());
        return textComponent;
    }
}
