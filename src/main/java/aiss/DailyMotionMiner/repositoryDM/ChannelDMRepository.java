package aiss.DailyMotionMiner.repositoryDM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import aiss.DailyMotionMiner.exception.ChannelNotFoundException;
import aiss.DailyMotionMiner.model.modelDM.channel.ChannelList;
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
}
