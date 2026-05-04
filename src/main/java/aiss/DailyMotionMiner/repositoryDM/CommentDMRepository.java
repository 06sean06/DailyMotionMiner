package aiss.DailyMotionMiner.repositoryDM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import aiss.DailyMotionMiner.model.modelDM.comment.CommentDM;
import aiss.DailyMotionMiner.model.modelDM.comment.CommentList;
import aiss.DailyMotionMiner.model.modelDM.comment.TagResponseDM;
import aiss.DailyMotionMiner.services.CommentDMService;

import java.util.ArrayList;
import java.util.List;

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
