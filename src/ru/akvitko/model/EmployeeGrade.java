package ru.akvitko.model;

/**
 * Created by a.kvitko
 *  Перечисление , определяющее уровень  работника-кассира.
 *  В качестве значения указывается количество покупателей,
 *  которое обслуживает работник в час
 */
public enum EmployeeGrade {

    TRAINEE( 10 ),
    SPECIALIST( 13 ),
    MAESTRO( 15 ),
    MAGISTR( 17 );

    private int buyerPerHour;

    EmployeeGrade( int bph ) {
        this.buyerPerHour = bph;
    }

    public int getBuyerPerHour() {
        return buyerPerHour;
    }

}
