package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.FinancialTransaction;
import at.technikum.bic4a16.bi.model.FinancialTransactionRequest;
import at.technikum.bic4a16.bi.model.State;

import java.math.BigDecimal;
import java.util.UUID;

class DefaultFinancialTransaction implements FinancialTransaction{

    private FinancialTransactionRequest request;
    private State state;
    private UUID id;
    private BigDecimal amount;

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
    public BigDecimal getAmount() {
        return null;
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

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
