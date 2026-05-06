package aiss.DailyMotionMiner.controllerDM;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aiss.DailyMotionMiner.exception.ChannelAlreadyExistsException;
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

    //GET http://localhost:8081/dailymotion/{channelName}
    @GetMapping("/{channelName}")
    public ChannelVM getChannelByName(@PathVariable String channelName) throws ChannelNotFoundException{
        ChannelVM channel = oficialRepository.getAChannelByName(channelName);
        if (channel == null) {
            throw new ChannelNotFoundException();
        }
        return channel;
    }

    @PostMapping("/{channelName}")
    public ChannelVM createChannel(@PathVariable String channelName, @RequestParam(required = false) Integer maxVideos, @RequestParam(required = false) Integer maxPages) throws ChannelNotFoundException, ChannelAlreadyExistsException {
        ChannelVM created = oficialRepository.createAChannel(channelName, maxVideos, maxPages);
        if (created == null) {
        throw new ChannelNotFoundException();
    }
    return created;
}

}
