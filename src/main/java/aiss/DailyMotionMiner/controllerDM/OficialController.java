package aiss.DailyMotionMiner.controllerDM;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aiss.DailyMotionMiner.exception.ChannelNotFoundException;
import aiss.DailyMotionMiner.model.modelVM.ChannelVM;
import aiss.DailyMotionMiner.repositoryDM.OficialRepository;

@RestController
@RequestMapping("dailymotion")
public class OficialController {

    private OficialRepository oficialRepository;

    public OficialController(OficialRepository oficialRepository) {
        this.oficialRepository = oficialRepository;
    }

    //GET http://localhost:8081/dailymotion/{channelId}
    @GetMapping("/{channelId}")
    public ChannelVM getChannelById(@PathVariable String channelId) throws ChannelNotFoundException{
        ChannelVM channel = oficialRepository.getAChannel(channelId);
        if (channel == null) {
            throw new ChannelNotFoundException();
        }
        return channel;
    }

    @PostMapping("/{channelId}")
    public ChannelVM createChannel(@PathVariable String channelId) throws ChannelNotFoundException {
        ChannelVM created = oficialRepository.createAChannel(channelId);
        if (created == null) {
        throw new ChannelNotFoundException();
    }
    return created;
}

}
