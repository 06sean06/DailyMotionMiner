package aiss.DailyMotionMiner.repositoryDM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import aiss.DailyMotionMiner.model.modelDM.user.UserList;
import aiss.DailyMotionMiner.services.UserDMService;

@Repository
public class UserDMRepository {

    @Autowired
    UserDMService userDMService;
    

    public List<UserList> findAll() {
        List<UserList> users = userDMService.getUsers();
        return users;
    }

    public UserList findOneById(String id) {
        UserList user = userDMService.getUserById(id);
        return user;
    }
    
}
