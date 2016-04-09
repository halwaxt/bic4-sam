package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.entity.UserEntity;
import at.technikum.bic4a16.bi.entity.CustomerEntity;
import at.technikum.bic4a16.bi.dao.UserEntityDAO;
import at.technikum.bic4a16.bi.model.User;
import at.technikum.bic4a16.bi.model.Customer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
@PermitAll
public class DefaultAuthenticationService implements AuthenticationService{

    @EJB
    private UserEntityDAO userEntityDAO;
    // used to authenticate
    @Override
    public User authenticate(String username, String password)throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        String pw = sb.toString();
        System.out.println(pw);
        UserEntity user = userEntityDAO.findByUserName(username);
        String userpw=user.getPwhash();
        System.out.println(userpw);
        if(userpw.equals(pw)){
            return user;
        }
        else {
            return null;
        }




    }

    // used to create a new user
    @Override
    public User createUser(String username, String password, Boolean isEmployee, Customer customer)throws NoSuchAlgorithmException {

        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        String pw = sb.toString();


        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPwhash(pw);
        userEntity.setIsEmployee(isEmployee);
        userEntity.setCustomer((CustomerEntity) customer);



        userEntityDAO.persist(userEntity);


        return userEntity;
    }

    // used to update a user - e.g. for setting the corresponding customer
    @Override
    public void updateUser(User user) {

    }
}
