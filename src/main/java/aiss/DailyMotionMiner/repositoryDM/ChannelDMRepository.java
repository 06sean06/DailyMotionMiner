package aiss.DailyMotionMiner.repositoryDM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import aiss.DailyMotionMiner.exception.ChannelNotFoundException;
import aiss.DailyMotionMiner.model.modelDM.channel.ChannelList;
import aiss.DailyMotionMiner.model.modelDM.video.VideoDM;
import aiss.DailyMotionMiner.services.ChannelDMService;

@Repository
public class ChannelDMRepository {
    
    @Value("${dailymotion.default.maxVideos}")
    private Integer defaultMaxVideos;

    @Value("${dailymotion.default.maxPages}")
    private Integer defaultMaxPages;
    
    @Autowired
    private RestTemplate restTemplate;

    @Value("${dailymotion.url}") 
    private String url;
    @Autowired

    ChannelDMService channelDMService;

    public List<ChannelList> findAll() {
        List<ChannelList> channels = channelDMService.getChannels();
        return channels;
    }

    public ChannelList findOneById(String id) throws ChannelNotFoundException {
        ChannelList channel = channelDMService.getChannelById(id);
        if (channel == null) {
            throw new ChannelNotFoundException();
        }
        return channel;
    }

    public ChannelList getChannelByName(String name) {
        ChannelList channel = channelDMService.getChannelByName(name);
        return channel;
    }

    public VideoDM getVideosOfChannel(String channelId) {
        return getVideosOfChannel(channelId, defaultMaxPages, defaultMaxVideos);
    }


    public VideoDM getVideosOfChannel(String channelId, Integer page, Integer limit) {
        int finalPage = (page != null) ? page : 1;
        int finalLimit = (limit != null) ? limit : defaultMaxVideos;

        String uri = url + "/user/" + channelId + "/videos" +
                 "?fields=id,title,description,created_time,owner" +
                 "&page=" + finalPage + 
                 "&limit=" + finalLimit;   
    return restTemplate.getForObject(uri, VideoDM.class);
}
}
