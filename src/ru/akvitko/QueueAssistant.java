package ru.akvitko;

import ru.akvitko.context.CashDesksPool;

/**
 * Created by a.kvitko
 * Класс, реализующий автомат по распределению покупателй в очереди к кассам
 */
public class QueueAssistant {

    // Алфавит автомата
    private static final String ALPHABET = "A1234";

    // Символ покупателя
    private static final String BUYER = "A";
    private static final String EMPTY_QUEUE = "Empty Queue";

    /**
     * Распределяет покупателей в очереди к кассам
     * @param value - String ( список символов автомата, например - "AAAA4321AAAA")
     * @return String - номера касс через запятую , в очередь к которой поставлен покупатель (например: A -> 4, A -> 3, A -> 1, A -> 4 )
     */
    public static String arrangeBuyers( String value ) {
        if ( value == null || value.trim().length() == 0 ) {
            return EMPTY_QUEUE;
        }
        StringBuilder buyersQueue = new StringBuilder();
        CashDesksPool cashDesksPool = new CashDesksPool();
        cashDesksPool.initialize();
        for ( int i = 0; i < value.length(); i++ ) {
            String letter = String.valueOf( value.charAt( i ) ).toUpperCase();
            if ( ALPHABET.indexOf( value.charAt( i ) ) > -1 ) {
                buyersQueue.append( BUYER.equals( letter ) ? cashDesksPool.addBuyer( BUYER )
                                                                : cashDesksPool.removeBuyerFromCashDesk( letter ) );
                buyersQueue.append( ", " ).append( "\n" );
            }
        }
        if ( buyersQueue.length() > 0 ){
            buyersQueue.deleteCharAt( buyersQueue.lastIndexOf( "," ) );
        } else {
            buyersQueue.append( EMPTY_QUEUE );
        }
        return buyersQueue.toString();
    }

}
