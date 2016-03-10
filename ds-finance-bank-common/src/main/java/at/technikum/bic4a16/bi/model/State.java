package at.technikum.bic4a16.bi.model;

import javax.ejb.Remote;

/**
 * Created by Thomas on 24.02.16.
 */

@Remote
public enum State {
    PENDING,
    APPROVED,
    DECLINED,
    FAILED,
    COMPLETE
}
