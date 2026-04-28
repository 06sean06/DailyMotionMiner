package aiss.DailyMotionMiner.controllerDM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aiss.DailyMotionMiner.model.modelDM.user.UserList;
import aiss.DailyMotionMiner.repositoryDM.UserDMRepository;

@RestController
@RequestMapping("DailyMotionMiner/users")
public class UserDMController {

    private UserDMRepository userDMRepository;

    @Autowired
    public UserDMController(UserDMRepository userDMRepository) {
        this.userDMRepository = userDMRepository;
    }

    //GET http://localhost:8081/DailyMotionMiner/users
    @GetMapping
    public List<UserList> getAllUsers() {
        return userDMRepository.findAll();
    }

    //GET http://localhost:8081/DailyMotionMiner/users/{id}
    @GetMapping("/{id}")
    public UserList findById(String id) {
        return userDMRepository.findOneById(id);
    }

}
