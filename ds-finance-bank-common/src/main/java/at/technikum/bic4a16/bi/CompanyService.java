package at.technikum.bic4a16.bi;

/**
 * Created by Thomas on 25.02.16.
 */
public interface CompanyService {
    Company getCompany(String symbol);
    Company[] getAllCompanies();
}
