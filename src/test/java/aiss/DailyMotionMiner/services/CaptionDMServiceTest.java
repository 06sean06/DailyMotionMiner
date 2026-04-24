package aiss.DailyMotionMiner.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import aiss.DailyMotionMiner.modelDM.caption.CaptionList;

@SpringBootTest
public class CaptionDMServiceTest {

    @Autowired
    CaptionDMService captionDMService;

    @Test
    @DisplayName("Get all subtitles from an specific video")
    void testGetCaptions() {
        List<CaptionList> captions = captionDMService.getCaptions("x979phu");
        assertNotNull(captions, "The captions list should not be null");
        assertFalse(captions.isEmpty(), "The captions list should not be empty");
        assertEquals(1, captions.size(), "There should be exactly one caption");
        CaptionList c = captions.get(0);
        assertEquals("x979phu.en", c.getId());
        assertEquals("subtitle", c.getItemType());
        assertEquals("en", c.getLanguage());
        assertEquals("English", c.getLanguageLabel());
        assertNotNull(c.getUrl());
        assertTrue(c.getUrl().contains("subtitle_en"));
        assertTrue(c.getUrl().endsWith(".srt") || c.getUrl().endsWith(".vtt"));
}


}
