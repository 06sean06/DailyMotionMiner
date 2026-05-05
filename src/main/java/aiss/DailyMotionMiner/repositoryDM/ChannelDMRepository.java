package aiss.DailyMotionMiner.repositoryDM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import aiss.DailyMotionMiner.exception.ChannelNotFoundException;
import aiss.DailyMotionMiner.model.modelDM.channel.ChannelList;
import aiss.DailyMotionMiner.model.modelDM.video.VideoDM;
import aiss.DailyMotionMiner.services.ChannelDMService;

@Repository
public class ChannelDMRepository {
    
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

    public VideoDM getVideosOfChannel(String channelId) {
        VideoDM videos = channelDMService.getVideosOfChannel(channelId);
        return videos;
    }
}
