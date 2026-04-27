package aiss.DailyMotionMiner.services;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import aiss.DailyMotionMiner.modelDM.channel.ChannelList;

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
 }
    @Test
    @DisplayName("Test getChannelById")
    public void testGetChannelById() {
        String channelId = "x4y70z4"; // Reemplaza con un ID de canal válido
        ChannelList channel = channelDMService.getChannelById(channelId);
        assertNotNull(channel, "The channel should not be null");
    }

}
