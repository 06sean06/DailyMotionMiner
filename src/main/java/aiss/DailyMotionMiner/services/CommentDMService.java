package aiss.DailyMotionMiner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aiss.DailyMotionMiner.model.modelDM.comment.CommentDM;
import aiss.DailyMotionMiner.model.modelDM.comment.CommentList;
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
        CommentDM response = restTemplate.getForObject(uri, CommentDM.class);
        if (response == null || response.getList() == null){
            repository.saveAll(response.getList());
        }return response.getList();
    }
}
