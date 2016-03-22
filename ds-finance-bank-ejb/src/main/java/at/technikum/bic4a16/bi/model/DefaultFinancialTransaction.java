package at.technikum.bic4a16.bi.model;

import java.math.BigDecimal;
import java.util.UUID;

public class DefaultFinancialTransaction implements FinancialTransaction{

    private FinancialTransactionRequest request;
    private State state;
    private UUID id;
    private Double price;

    @Override
    public FinancialTransactionRequest getRequest() {
        return request;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    public void setRequest(FinancialTransactionRequest request) {
        this.request = request;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
