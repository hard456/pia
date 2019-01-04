package cz.jpalcut.pia.service;

import cz.jpalcut.pia.dao.UserRequestDAO;
import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserRequestService {

    @Autowired
    UserRequestDAO userRequestDAO;

    public Page<UserRequest> getAllUserRequestPageable(Pageable pageable){
        return userRequestDAO.findAll(pageable);
    }

    public UserRequest getUserRequestById(Integer id){
        return userRequestDAO.findUserRequestById(id);
    }

    public UserRequest saveUserRequest(UserRequest userRequest){
        return userRequestDAO.save(userRequest);
    }

    public void deleteUserRequest(UserRequest userRequest){
        userRequestDAO.delete(userRequest);
    }

    public List<UserRequest> getUserRequestsByAcount(Account account){
        return userRequestDAO.findAllByAccount(account);
    }

    public UserRequest getUserRequestByTypeAndAccount(String type, Account account){
        return userRequestDAO.findUserRequestByTypeAndAccount(type, account);
    }

}
