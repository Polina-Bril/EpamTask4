package com.epam.epamtask4.action;

import com.epam.epamtask4.entity.AbstractComponent;
import com.epam.epamtask4.entity.CompositeText;
import com.epam.epamtask4.entity.TypeComponent;
import com.epam.epamtask4.exception.CompositeException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TextAction {
    public static Logger logger = LogManager.getLogger();

    public AbstractComponent findSentenceWithLongestWord(AbstractComponent text) throws CompositeException {
        if (text == null || text.getType() != TypeComponent.TEXT) {
            throw new CompositeException("component is null or not a text");
        }
        AbstractComponent sentenceWithLongestWord = text;
        AbstractComponent longestWord = text;
        int maxWordLenght = 0;
        for (AbstractComponent paragraph : text.getComponents()) {
            for (AbstractComponent sentence : paragraph.getComponents()) {
                for (AbstractComponent lexema : sentence.getComponents()) {
                    for (AbstractComponent partLexema : lexema.getComponents()) {
                        if (partLexema.getType() == TypeComponent.WORD
                                && partLexema.countSymbol() > maxWordLenght) {
                            maxWordLenght = partLexema.countSymbol();
                            longestWord = partLexema;
                            sentenceWithLongestWord = sentence;
                        }
                    }
                }
            }
        }
        logger.log(Level.INFO,
                "the longest word " + longestWord.toString() + " in sentence :" + sentenceWithLongestWord.toString());
        return sentenceWithLongestWord;
    }

    public AbstractComponent removeSentencesWithLessWords(AbstractComponent text, int numberWords) throws CompositeException {
        if (text == null || text.getType() != TypeComponent.TEXT) {
            throw new CompositeException("component is null or not a text");
        }
        List<AbstractComponent> sentencesWithLessWords = new ArrayList<>();
        int counterWords = 0;
        for (AbstractComponent paragraph : text.getComponents()) {
            for (AbstractComponent sentence : paragraph.getComponents()) {
                counterWords = ((CompositeText) sentence).countWord();
                if (counterWords < numberWords) {
                    sentencesWithLessWords.add(sentence);
                }
            }
            for (AbstractComponent sentence : sentencesWithLessWords) {
                ((CompositeText) paragraph).removeComponent(sentence);
            }
            sentencesWithLessWords.removeAll(sentencesWithLessWords);
        }
        logger.log(Level.INFO,
                "text after removing sentences with less than " + numberWords + "  words :" + text.toString());
        return text;
    }

    public Map<String, Integer> findEqualWords(AbstractComponent text) throws CompositeException {
        if (text == null || text.getType() != TypeComponent.TEXT) {
            throw new CompositeException("component is null or not a text");
        }
        Map<String, Integer> equalWords = new HashMap<>();
        for (AbstractComponent paragraph : text.getComponents()) {
            for (AbstractComponent sentence : paragraph.getComponents()) {
                for (AbstractComponent lexema : sentence.getComponents()) {
                    for (AbstractComponent partLexema : lexema.getComponents()) {
                        if (partLexema.getType() == TypeComponent.WORD) {
                            String word = partLexema.toString().toLowerCase();
                            Integer previousValue = equalWords.put(word, 1);
                            if (previousValue != null) {
                                equalWords.put(word, ++previousValue);
                            }
                        }
                    }
                }
            }
        }
        Iterator<Map.Entry<String, Integer>> iterator = equalWords.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> wordAndNumber = iterator.next();
            Integer number = wordAndNumber.getValue();
            if (number == 1) {
                iterator.remove();
            }
        }
        logger.log(Level.INFO, "equal words in the text and their quantity: " + equalWords.toString());
        return equalWords;
    }
}
