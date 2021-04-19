package ru.akvitko;

/**
 * Created by a.kvitko
 */
public class CoreClassTestApp {

    public static void main(String[] args) {
        String inputString = "AAAA4321AAAA";
        String result = QueueAssistant.arrangeBuyers( inputString );
        System.out.println( result );
    }
}
