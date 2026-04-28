package aiss.DailyMotionMiner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aiss.DailyMotionMiner.model.modelDM.user.UserDM;
import aiss.DailyMotionMiner.model.modelDM.user.UserList;

@Service
public class UserDMService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${dailymotion.url}")
    private String url;

    //Get list of users
    //GET https://api.dailymotion.com/users?fields=id,screenname,url,avatar_120_url
    public List<UserList> getUsers() {
        String uri = url + "/users?fields=id,screenname,url,avatar_120_url";
        UserDM response = restTemplate.getForObject(uri, UserDM.class);
        if (response == null || response.getList() == null) {
            return List.of(); 
        }
        return response.getList();
    }

    //Get user by id
    //GET https://api.dailymotion.com/user/{id}?fields=id,screenname,url,avatar_120_url
    public UserList getUserById(String id) {
        String uri = url + "/user/" + id + "?fields=id,screenname,url,avatar_120_url";
        return restTemplate.getForObject(uri, UserList.class);
    }
    
}
