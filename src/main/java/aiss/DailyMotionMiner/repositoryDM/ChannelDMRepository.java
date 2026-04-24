package aiss.DailyMotionMiner.repositoryDM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import aiss.DailyMotionMiner.modelDM.channel.ChannelDM;
import aiss.DailyMotionMiner.modelDM.channel.ChannelList;
import aiss.DailyMotionMiner.services.ChannelDMService;

@Repository
public class ChannelDMRepository {
    
    @Autowired
    ChannelDMService channelDMService;

    public List<ChannelList> findAll() {
        List<ChannelList> channels = channelDMService.getChannels();
        return channels;
    }

    public ChannelDM findOneById(String id) {
        ChannelDM channel = channelDMService.getChannelById(id);
        return channel;
    }
}
