package ru.akvitko.helper;

import ru.akvitko.model.CashDesk;

/**
 * Created by a.kvitko
 * Класс содержащий вспомогательные методы для модели данных
 */
public class CashDeskHelper {

    private static final int NUMBER_MINUTE_IN_HOUR = 60;
    private static final double BUSY_TIME = 99999.00;

    private CashDesk cashDesk;

    public CashDeskHelper( CashDesk cashDesk ) {
        this.cashDesk = cashDesk;
    }

    /**
     * Проверяет не превышен ли лимит очереди покупателей в кассу
     *
     * @return boolean
     */
    public boolean isQueueAvailable(){
        return cashDesk.getBuyers().size() <  CashDesk.MAX_BUYER;
    }

    /**
     *  Возвращает количество времени, которое проведет покупатель в очереди кассы
     *
     *  @return double
     */
    public double getTimeInQueue(){
        return isQueueAvailable()
                ?  cashDesk.getBuyers().size()*( (double) NUMBER_MINUTE_IN_HOUR / (double) cashDesk.getEmployeeGrade().getBuyerPerHour() )
                : BUSY_TIME;
    }

    /**
     *  Возвращает порядковый номер кассы
     *
     *  @return int
     */
    public int getCashDeskNumber(){
        return cashDesk.getCashDeskNumber();
    }

    /**
     *  Возвращает количество покупателей, обслуживаемых кассой за час
     *
     *  @return int
     */
    public int getBuyerPerHour(){
        return cashDesk.getEmployeeGrade().getBuyerPerHour();
    }

    /**
     *  Добавляет покупателя в очередь
     */
    public void addBuyerToQueue( String buyer){
        if( cashDesk.getBuyers().size() < CashDesk.MAX_BUYER ){
            cashDesk.getBuyers().add( buyer );
        }
    }

    /**
     *  Покупатель покидает очередь
     */
    public void removeBuyerFromQueue(){
        if( cashDesk.getBuyers().size() > 0 ){
            cashDesk.getBuyers().remove( 0 );
        }
    }
}
