package com.epam.epamtask4.comparator;

import com.epam.epamtask4.entity.AbstractComponent;

import java.util.Comparator;

public class ParagraphComparator implements Comparator<AbstractComponent> {

    @Override
    public int compare(AbstractComponent paragraph1, AbstractComponent paragraph2) {
        int countSentences1 = paragraph1.getComponents().size();
        int countSentences2 = paragraph2.getComponents().size();
        return countSentences1 - countSentences2;
    }
}