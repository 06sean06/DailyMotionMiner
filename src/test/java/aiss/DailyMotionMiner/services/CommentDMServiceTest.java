package aiss.DailyMotionMiner.services;

import aiss.DailyMotionMiner.modelDM.comment.CommentList;
import aiss.DailyMotionMiner.services.CommentDMService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

@SpringBootTest
public class CommentDMServiceTest {
    
  @Autowired
  CommentDMService commentDMService;

  @Test
  @DisplayName("Get comments from a dailymotion video")
  void testGetComment(){
    String videoId = "x90ye84";
    List<CommentList> comments = commentDMService.getComments(videoId);

    assertNotNull(comments, "lista no puede ser nula");
    assertFalse(comments.isEmpty(), "la lista de comentarios no deberia estar vacia");
  }
}
