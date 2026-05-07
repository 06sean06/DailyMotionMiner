package aiss.DailyMotionMiner.services;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import aiss.DailyMotionMiner.model.modelDM.video.VideoList;
import aiss.DailyMotionMiner.model.modelVM.VideoVM;

@SpringBootTest
public class VideoDmServiceTest {

    @Autowired
    VideoDMService videoDMService;
 
    @Test
    @DisplayName("Test getVideos")
    public void testGetVideos() {
     List<VideoList> videos = videoDMService.getVideos();
        assertNotNull(videos, "The list of videos should not be null");
        assertFalse(videos.isEmpty(), "The list of videos should not be empty");
        assertNotNull(videos.get(0).getId(), "The first video should have an ID");
        assertNotNull(videos.get(0).getTitle(), "The first video should have a title");
    }
    
    @Test
    @DisplayName("Test getVideoById")
    public void testGetVideoById() {
        String videoId = "xa6kc0m"; // Reemplaza con un ID de video válido
        VideoList video = videoDMService.getVideoById(videoId);
        assertNotNull(video, "The video should not be null");
        assertNotNull(video.getId(), "The video should have an ID");
        assertNotNull(video.getTitle(), "The video should have a title");
    }

    @Test
    @DisplayName("Test createVideo")
    public void testCreateVideo() {
        VideoList videoDM = new VideoList();
        videoDM.setId("test123");
        videoDM.setTitle("Test Video");
        videoDM.setChannel("Mío");
        videoDM.setOwner("Yo");

        VideoVM createdVideo = videoDMService.createVideo(videoDM);
        assertNotNull(createdVideo, "The created video should not be null");
        assertNotNull(createdVideo.getId(), "The created video should have an ID");
        assertNotNull(createdVideo.getName(), "The created video should have a name");
        assertNotNull(createdVideo.getReleaseTime(), "The created video should have a release time");
        assertNotNull(createdVideo.getDescription(), "The created video should have a description");
        
    }


}
