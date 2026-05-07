package aiss.DailyMotionMiner.services;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import aiss.DailyMotionMiner.model.modelDM.channel.ChannelList;
import aiss.DailyMotionMiner.model.modelDM.video.VideoDM;

@SpringBootTest
public class ChannelDMServiceTest {
    @Autowired
    ChannelDMService channelDMService;

    @Test
    @DisplayName("Test getChannels")
    public void testGetChannels() {
        List<ChannelList> channels = channelDMService.getChannels();
        assertNotNull(channels, "The list of channels should not be null");
        assertFalse(channels.isEmpty(), "The list of channels should not be empty"); 
        assertNotNull(channels.get(0).getId(), "The channel ID should not be null");
        assertNotNull(channels.get(0).getScreenname(), "The channel screenname should not be null");
        assertNotNull(channels.get(0).getCreatedTime(), "The createdTime atribute shold not be null");
    }

    @Test
    @DisplayName("Test getChannelById")
    public void testGetChannelById() {
        String channelId = "x4y70z4"; 
        ChannelList channel = channelDMService.getChannelById(channelId);
        assertNotNull(channel, "The channel should not be null");
        assertNotNull(channel.getId(), "The channel ID should not be null");
        assertNotNull(channel.getScreenname(), "The channel screenname should not be null");
        assertNotNull(channel.getCreatedTime(), "The createdTime atribute shold not be null");
    }

    @Test
    @DisplayName("Test getVideosOfChannel")
    public void testGetVideosOfChannel() {
        String channelId = "marca";
        VideoDM channel = channelDMService.getVideosOfChannel(channelId);
        assertNotNull(channel, "The channel should not be null");
        assertNotNull(channel.getList(), "The list of videos should not be null");
        assertFalse(channel.getList().isEmpty(), "The list of videos should not be empty");
        assertNotNull(channel.getList().get(0).getId(), "The video ID should not be null");
        assertNotNull(channel.getList().get(0).getTitle(), "The video title should not be null");
    }
}