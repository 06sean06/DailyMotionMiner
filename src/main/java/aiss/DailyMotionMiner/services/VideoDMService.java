package aiss.DailyMotionMiner.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import aiss.DailyMotionMiner.model.modelDM.video.VideoDM;
import aiss.DailyMotionMiner.model.modelDM.video.VideoList;
import aiss.DailyMotionMiner.model.modelVM.VideoVM;
import aiss.DailyMotionMiner.transformer.Transformer;

@Service
public class VideoDMService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${dailymotion.url}")
    private String url;

    @Value("${videominer.url}")
    private String urlvm; //http://localhost:8080/VideoMiner

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
        try {
            return restTemplate.getForObject(uri, VideoDM.class);
        } catch (HttpClientErrorException.NotFound e) {
            return null; 
        } catch (Exception e) {
            return null;
    }
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

    //POST VIDEO http://localhost:8080/VideoMiner/videos
    public VideoVM createVideo(VideoList videoDM) {
        String uri = urlvm + "/videos";
        VideoVM transformed = Transformer.transformVideo(videoDM);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<VideoVM> request = new HttpEntity<>(transformed, headers);
        @SuppressWarnings("null")
        ResponseEntity<VideoVM> response = restTemplate.exchange(uri, HttpMethod.POST, request, VideoVM.class);
        VideoVM video  = response.getBody();
        if (video == null ) {
            return null;
        }
        return video;
    }
}

