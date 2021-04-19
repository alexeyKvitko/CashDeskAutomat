package ru.akvitko.context;

import ru.akvitko.helper.CashDeskHelper;
import ru.akvitko.model.CashDesk;
import ru.akvitko.model.EmployeeGrade;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by a.kvitko
 * Класс, в котором реализована логика выбора кассы для постановки покупателя в очередь.
 */
public class CashDesksPool {

    private static final String JOIN_STR = " -> ";
    private static final String BUSY = "BUSY";
    private static final EmployeeGrade[] CASHIERS = {EmployeeGrade.TRAINEE, EmployeeGrade.SPECIALIST, EmployeeGrade.MAESTRO, EmployeeGrade.MAGISTR};

    private List< CashDeskHelper > cashDesks;

    /**
     * Создание и инициализация касс
     */
    public void initialize() {
        cashDesks = new LinkedList<>();
        int number = 1;
        for ( EmployeeGrade employeeGrade : CASHIERS ) {
            cashDesks.add( new CashDeskHelper( new CashDesk.Builder()
                    .withNumber( number++ )
                    .withEmployee( employeeGrade )
                    .withEmptyQueue()
                    .build() ) );
        }
    }

    /**
     * Событие для добавления покупателя в очередь кассы так,
     * чтобы проведенное им время в ней было минимальным
     *
     * @param buyer
     * @return String
     */
    public String addBuyer( String buyer ) {
        CashDeskHelper minTimeCashDesk = getMinWaithingTimeCashDesk();
        String suffix = BUSY;
        if ( minTimeCashDesk.isQueueAvailable() ) {
            minTimeCashDesk.addBuyerToQueue( buyer );
            suffix = minTimeCashDesk.getCashDeskNumber() + "";
        }
        return buyer + JOIN_STR + suffix;
    }

    /**
     * Событие, для удаления покупателя из очереди
     *
     * @param cashDeskNumber
     * @return String
     */
    public String removeBuyerFromCashDesk( String cashDeskNumber ) {
        CashDeskHelper releasedCashDeskDesk = cashDesks.stream().filter( cd -> cd.getCashDeskNumber() == Integer.valueOf( cashDeskNumber ) ).findFirst().orElseThrow( IllegalArgumentException::new );
        releasedCashDeskDesk.removeBuyerFromQueue();
        return cashDeskNumber + JOIN_STR + getMinWaithingTimeCashDesk().getCashDeskNumber();
    }

    /**
     * Возващает кассу с минимальный пребыванием покупателя в очереди
     *
     * @return CashDeskHelper
     */
    private CashDeskHelper getMinWaithingTimeCashDesk() {
        Map< Double, List< CashDeskHelper > > minWaitingTimes = cashDesks.
                stream().collect( Collectors.groupingBy( CashDeskHelper::getTimeInQueue ) );
        List< CashDeskHelper > cashDeskesWithMinTime = minWaitingTimes.get( Collections.min( minWaitingTimes.keySet() ) );
        if ( cashDeskesWithMinTime.size() == 1 ) {
            return cashDeskesWithMinTime.get( 0 );
        }
        Collections.sort( cashDeskesWithMinTime, Comparator.comparing( CashDeskHelper::getBuyerPerHour ).reversed() );
        return cashDeskesWithMinTime.get( 0 );
    }

}
