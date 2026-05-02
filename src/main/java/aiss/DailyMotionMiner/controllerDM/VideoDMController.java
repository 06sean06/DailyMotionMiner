package aiss.DailyMotionMiner.controllerDM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aiss.DailyMotionMiner.exception.VideoNotFoundException;
import aiss.DailyMotionMiner.model.modelDM.video.VideoDM;
import aiss.DailyMotionMiner.model.modelDM.video.VideoList;
import aiss.DailyMotionMiner.services.VideoDMService;


@RestController
@RequestMapping("/api/dailymotion/videos")
public class VideoDMController {

    @Autowired
    VideoDMService videoDMService;

    //GET http://localhost:8081/DailyMotionMiner/videos
    @GetMapping
    public List<VideoList> findAll(){
        return videoDMService.getVideos();
    }

    //GET http://localhost:8081/DailyMotionMiner/videos/{id}
    @GetMapping("/{id}")
    public VideoDM findById(@PathVariable String id) throws VideoNotFoundException{
        VideoDM video = videoDMService.getVideoById(id);
        if (video == null){
            throw new VideoNotFoundException();
        }return video;

    }

}
