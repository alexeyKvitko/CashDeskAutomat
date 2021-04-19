package ru.akvitko.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by a.kvitko
 *  Модель данных - сущность описывающая кассу в магазине
 */
public class CashDesk {

    // Максимальное количество покупателей в кассе
    public static final int MAX_BUYER = 20;

    private int cashDeskNumber;
    private EmployeeGrade employeeGrade;
    private List<String> buyers;

    public int getCashDeskNumber() {
        return cashDeskNumber;
    }

    public EmployeeGrade getEmployeeGrade() {
        return employeeGrade;
    }

    public List< String > getBuyers() {
        return buyers;
    }

    public static class Builder{

        private CashDesk cashDesk;

        public Builder() {
            this.cashDesk = new CashDesk();
        }

        public Builder withNumber( int number ){
            this.cashDesk.cashDeskNumber = number;
            return this;
        }

        public Builder withEmployee( EmployeeGrade employeeGrade ){
            this.cashDesk.employeeGrade = employeeGrade;
            return this;
        }

        public Builder withEmptyQueue(){
            this.cashDesk.buyers = new LinkedList<>();
            return this;
        }

        public CashDesk build(){
            return this.cashDesk;
        }
    }

}
