package com.epam.epamtask4;

import com.epam.epamtask4.action.TextAction;
import com.epam.epamtask4.comparator.ParagraphComparator;
import com.epam.epamtask4.entity.AbstractComponent;
import com.epam.epamtask4.entity.CompositeText;
import com.epam.epamtask4.exception.CompositeException;
import com.epam.epamtask4.parser.AbstractParser;
import com.epam.epamtask4.parser.TextParser;
import com.epam.epamtask4.reader.TextReader;

public class Main {
    public static void main(String[] args) throws CompositeException {
        TextReader reader = new TextReader();
        String text = reader.read("resources/data/text.txt");

        AbstractParser parser = new TextParser();
        AbstractComponent textComponent = parser.parse(" It has survived - not only (five) centuries, but also the leap into electronic\n" +
                "typesetting, remaining essentially unchanged. It was popularised in the \"Динамо\" (Рига)\n" +
                "with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and\n" +
                "more recently with desktop publishing software like Aldus PageMaker Faclon9 including\n" +
                "versions of Lorem Ipsum!\n" +
                "    It is a long a!=b established fact that a reader will be distracted by the readable\n" +
                "content of a page when looking at its layout. The point of using Ipsum is that it has a\n" +
                "more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here),\n" +
                "content here's, making it look like readable English?\n" +
                "    It is a established fact that a reader will be of a page when looking at its layout...\n" +
                "    Bye бандерлоги.");

        ((CompositeText) textComponent).sortComponents(new ParagraphComparator());
        TextAction action = new TextAction();
        action.findSentenceWithLongestWord(textComponent);
        action.findEqualWords(textComponent);
        action.removeSentencesWithLessWords(textComponent, 19);
    }
}
