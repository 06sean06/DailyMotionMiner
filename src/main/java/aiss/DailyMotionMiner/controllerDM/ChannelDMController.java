package aiss.DailyMotionMiner.controllerDM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aiss.DailyMotionMiner.modelDM.channel.ChannelDM;
import aiss.DailyMotionMiner.modelDM.channel.ChannelList;
import aiss.DailyMotionMiner.repositoryDM.ChannelDMRepository;

@RestController
@RequestMapping("DailyMotionMiner/channels")
public class ChannelDMController {

    private ChannelDMRepository channelDMRepository;

    @Autowired
    public ChannelDMController (ChannelDMRepository channelDMRepository) {
        this.channelDMRepository = channelDMRepository;
    }

    // GET http://localhost:8081/DailyMotionMiner/channels
    @GetMapping
    public List<ChannelList> getAllChannels() {
        return channelDMRepository.findAll();
    }

    // GET http://localhost:8081/DailyMotionMiner/channels/{id}
    @GetMapping("/{id}")
    public ChannelDM findById(@PathVariable String id) {
        return channelDMRepository.findOneById(id);
    }
}
