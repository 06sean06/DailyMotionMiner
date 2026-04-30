package aiss.DailyMotionMiner.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aiss.DailyMotionMiner.model.modelDM.video.VideoDM;
import aiss.DailyMotionMiner.model.modelDM.video.VideoList;
import aiss.DailyMotionMiner.model.modelVM.VideoVM;

@Service
public class VideoDMService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${dailymotion.url}")
    private String url;

    // GET VIDEOS https://api.dailymotion.com/videos?fields=id,title,description,created_time
    public List<VideoList> getVideos() {
        String uri = url + "/videos?fields=id,title,description,created_time";
        VideoDM response = restTemplate.getForObject(uri, VideoDM.class);
        if (response == null || response.getList() == null) {
            return List.of(); 
    }
        return response.getList();
    }

    // GET VIDEO BY ID https://api.dailymotion.com/video/{id}?fields=id,title,description,created_time
    public VideoDM getVideoById(String id) {
        String uri = url + "/video/" + id + "?fields=id,title,description,created_time";
        return restTemplate.getForObject(uri, VideoDM.class);
    }
    

    //Transformar video
    public VideoVM transformVideo(VideoList data) {
        VideoVM video = new VideoVM();
        video.setId(data.getId());
        video.setName(data.getScreenname());
        video.setDescription(data.getDescription());
        video.setReleaseTime(data.getCreatedTime().toString());
        return video;
    }
}

