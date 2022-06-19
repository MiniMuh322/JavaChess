package GUI;


import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    public Logger logger;
    FileHandler fh;

    public Log() throws IOException,RuntimeException{
        File f = new File("Warnings");

        if(!f.exists()){
            f.createNewFile();
        }

        fh = new FileHandler("Warnings", true);
        logger = Logger.getLogger("test");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
    }

}