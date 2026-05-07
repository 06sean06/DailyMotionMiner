package aiss.DailyMotionMiner.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import aiss.DailyMotionMiner.model.modelVM.CommentVM;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

@SpringBootTest
public class CommentDMServiceTest {
    
  @Autowired
  CommentDMService commentDMService; 



  @Test
  @DisplayName("Get tags from a dailymotion video")
  void testGetTagsOfVideo() {
    String videoId = "x90ye84";
    List<String> tags = commentDMService.getTagsOfVideo(videoId);

    assertNotNull(tags, "The list of tags should not be null");
    assertFalse(tags.isEmpty(), "The list of tags should not be empty");   
  }

  

  @Test
  @DisplayName("Get comments from a dailymotion video")
  void testGetComment() {
    String videoId = "xa8ewo4";
    List<CommentVM> comments = commentDMService.getComments1(videoId);

    assertNotNull(comments, "The list of comments should not be null");
    assertFalse(comments.isEmpty(), "The list of comments should not be empty");
    assertNotNull(comments.get(0), "The first comment should not be null");

    assertNotNull(comments.get(0).getText(), "The text of the first comment should not be null");
  }
}
