package at.technikum.bic4a16.bi.entity;

import at.technikum.bic4a16.bi.entity.CompanyEntity;
import at.technikum.bic4a16.bi.model.Company;
import at.technikum.bic4a16.bi.model.Stock;
import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class StockEntity implements Stock, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDENTITY")
    private long id;


    @ManyToOne
    @JoinColumn(name="COMPANY_FK")
    private CompanyEntity companyEntity;

    @Column(name = "SHARES")
    private int numberOfShares;

    @Override
    public Company getCompany() {
        return companyEntity;
    }

    @Override
    public int getNumberOfShares() {
        return this.numberOfShares;
    }

    @Override
    public BigDecimal getCurrentValue() {
        return this.companyEntity.getLastTradingPrice().multiply(new BigDecimal(this.numberOfShares));
    }
}
