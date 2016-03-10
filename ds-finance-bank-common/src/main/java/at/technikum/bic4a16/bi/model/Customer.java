package at.technikum.bic4a16.bi.model;

import javax.ejb.Remote;
import java.io.Serializable;

/**
 * Created by Thomas on 24.02.16.
 */

@Remote
public interface Customer extends Serializable {
    // id, which is an unqiue identifier - e.g. 1
    int getId();

    // name - e.g. Jordan Belfort
    String getName();
}
