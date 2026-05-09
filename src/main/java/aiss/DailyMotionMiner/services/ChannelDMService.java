package aiss.DailyMotionMiner.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aiss.DailyMotionMiner.exception.ChannelNotFoundException;
import aiss.DailyMotionMiner.model.modelDM.channel.ChannelDM;
import aiss.DailyMotionMiner.model.modelDM.channel.ChannelList;
import aiss.DailyMotionMiner.model.modelDM.video.VideoDM;

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

    // GET CHANNEL BY ID 
    //Get channel by ID https://api.dailymotion.com/user/{id}?fields=id,screenname,description,created_time
    public ChannelList getChannelById(String id) throws ChannelNotFoundException {
        String uri = url + "/user/" + id + "?fields=id,screenname,description,created_time";
        ChannelList channel = restTemplate.getForObject(uri, ChannelList.class);
        if (channel == null) {
            throw new ChannelNotFoundException();
        }
        return channel;
    }

    // GET VIDEOS OF A CHANNEL: page = maxPages y limit = maxVideos. 
    public VideoDM getVideosOfChannel(String channelId, Integer page, Integer limit) {
    String uri = url + "/user/" + channelId + "/videos" +"?fields=id,title,description,created_time,owner" + "&page=" + page + "&limit=" + limit;
    return restTemplate.getForObject(uri, VideoDM.class);
}



    // GET CHANNEL BY NAME
    public ChannelList getChannelByName(String name) {
        String uri = url + "/users?search=" + name + "&fields=id,screenname,description,created_time";
        ChannelDM response = restTemplate.getForObject(uri, ChannelDM.class);
        if (response == null || response.getList() == null || response.getList().isEmpty()) {
            return null; 
        }
        return response.getList().get(0); 
    }


}