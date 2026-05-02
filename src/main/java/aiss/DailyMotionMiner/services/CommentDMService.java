package aiss.DailyMotionMiner.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import aiss.DailyMotionMiner.model.modelDM.comment.CommentDM;
import aiss.DailyMotionMiner.model.modelDM.comment.CommentList;
import aiss.DailyMotionMiner.model.modelVM.CommentVM;
import aiss.DailyMotionMiner.repositoryDM.CommentDMRepository;

@Service
public class CommentDMService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CommentDMRepository repository;

    @Value("${dailymotion.url}")
    private String url;

    // Get comments https://api.dailymotion.com/video/{id}/comments?fields=id,message,created_time

    public List<CommentList> getComments(String videoId){
        String uri = url + "/video/" + videoId + "/comments?fields=id, message, created_time";
        try{
            CommentDM response = restTemplate.getForObject(uri, CommentDM.class);
            if (response != null && response.getList() != null){
                return response.getList();
        }return Collections.emptyList();
    }catch (HttpClientErrorException.NotFound e) {
        return null;
    }catch (Exception e) {
        return Collections.emptyList();
    }
}


    //Transformar comentario
    public CommentVM transformComment(CommentList data) {
        CommentVM comment = new CommentVM();
        comment.setId(data.getId().toString());
        comment.setText(data.getMessage());
        comment.setCreatedOn(data.getCreatedTime().toString());
        return comment;
    }
}
