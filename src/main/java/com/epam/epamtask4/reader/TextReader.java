package com.epam.epamtask4.reader;

import com.epam.epamtask4.exception.CompositeException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TextReader {
    public static Logger logger = LogManager.getLogger();

    public String read(String filePath) throws CompositeException {
        if (filePath == null) {
            throw new CompositeException("filePath is null");
        }
        Path path = Paths.get(filePath);
        String text = null;
        try (Stream<String> lines = Files.lines(path)) {
            text = lines.collect(Collectors.joining());
        } catch (IOException e) {
            logger.log(Level.ERROR, "file " + filePath + " not found", e);
        }
        logger.log(Level.INFO, "read data from file : " + text);
        return text;
    }
}