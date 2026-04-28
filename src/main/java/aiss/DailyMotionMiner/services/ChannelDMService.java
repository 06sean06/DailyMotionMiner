package aiss.DailyMotionMiner.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aiss.DailyMotionMiner.model.modelDM.channel.ChannelDM;
import aiss.DailyMotionMiner.model.modelDM.channel.ChannelList;

@Service
public class ChannelDMService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${dailymotion.url}")
    private String url;

    //Get channels https://api.dailymotion.com/users?fields=id,screenname,description,created_time
    public List<ChannelList> getChannels() {
        String uri = url + "/users?fields=id,screenname,description,created_time";
        ChannelDM response = restTemplate.getForObject(uri, ChannelDM.class);
        if (response == null || response.getList() == null) {
            return List.of(); 
        }
        return response.getList();
    }

    //Get channel by ID https://api.dailymotion.com/user/{id}?fields=id,screenname,description,created_time
    public ChannelList getChannelById(String id) {
        String uri = url + "/user/" + id + "?fields=id,screenname,description,created_time";
        return restTemplate.getForObject(uri, ChannelList.class);
    }



}
