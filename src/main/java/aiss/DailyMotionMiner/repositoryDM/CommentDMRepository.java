package aiss.DailyMotionMiner.repositoryDM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import aiss.DailyMotionMiner.model.modelDM.comment.CommentList;
import aiss.DailyMotionMiner.services.CommentDMService;

@Repository
public class CommentDMRepository {

    @Autowired
    private CommentDMService commentDMService;


    @Autowired
    RestTemplate restTemplate;

    public List<String> getTagsOfVideo(String videoId) {
        List<String> tags = commentDMService.getTagsOfVideo(videoId);
        return tags;
    }

    public List<CommentList> findAll(String videoId){
        List<CommentList> comments = commentDMService.getComments(videoId);
        return comments;
    }
}
