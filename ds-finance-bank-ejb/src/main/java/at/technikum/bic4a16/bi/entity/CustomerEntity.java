package at.technikum.bic4a16.bi.entity;

/**
 *
 * @author Romeo
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.List;
import javax.persistence.OneToOne;

@Entity
@Table(name="CUSTOMER")
public class CustomerEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    
    @OneToMany(mappedBy="customer")
    private List<FinancialTransactionEntity> transactions;    
    @OneToOne(mappedBy="customer")
    private UserEntity User;
    
    
    public int getId(){
        return id;
    }
    public void setID(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
