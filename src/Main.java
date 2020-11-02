
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.T;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        OptimizedLog<Integer>log=new OptimizedLog<>();
        log.add(1);
        log.add(1);
        log.add(1);
        log.add(2);
        log.add(2);
        log.add(3);
        log.add(1);
        OptimizedLog<Integer>log2=new OptimizedLog<>();
        log2.add(5);
        log2.add(1);
        log2.add(2);
            log.clear();
       System.out.print(log);
    }

}
