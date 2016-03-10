package at.technikum.bic4a16.bi.model;

import at.technikum.bic4a16.bi.model.Customer;

/**
 * Created by Patrik on 29.02.2016.
 */
public class CustomerModel implements Customer {

    private int id;
    private String name;

    public CustomerModel() {

    }
    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
