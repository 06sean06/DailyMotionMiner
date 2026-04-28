package aiss.DailyMotionMiner.services;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import aiss.DailyMotionMiner.model.modelDM.video.VideoDM;
import aiss.DailyMotionMiner.model.modelDM.video.VideoList;

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
 }
    @Test
    @DisplayName("Test getVideoById")
    public void testGetVideoById() {
        String videoId = "xa6kc0m"; // Reemplaza con un ID de video válido
        VideoDM video = videoDMService.getVideoById(videoId);
        assertNotNull(video, "The video should not be null");
    }


}
