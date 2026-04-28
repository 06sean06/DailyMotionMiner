package aiss.DailyMotionMiner.controllerDM;

import aiss.DailyMotionMiner.exception.VideoNotFoundException;
import aiss.DailyMotionMiner.modelDM.comment.CommentList;
import aiss.DailyMotionMiner.services.CommentDMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dailymotion/comments")
public class CommentDMController {

    @Autowired
    CommentDMService commentDMService;

    //GET http://localhost:8081/api/dailymotion/comments/{videoId}
    @GetMapping("/{videoId}")
    public List<CommentList> getVideoComments(@PathVariable String videoId) throws VideoNotFoundException {
        return commentDMService.getComments(videoId);
    }
    
}
