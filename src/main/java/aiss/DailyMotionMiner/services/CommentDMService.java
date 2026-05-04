package aiss.DailyMotionMiner.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import aiss.DailyMotionMiner.model.modelDM.comment.CommentDM;
import aiss.DailyMotionMiner.model.modelDM.comment.CommentList;
import aiss.DailyMotionMiner.model.modelDM.comment.TagResponseDM;

@Service
public class CommentDMService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${dailymotion.url}")
    private String url;

    //Get tags:
    //https://api.dailymotion.com/video/{idVideo}?fields=tags
    public List<String> getTagsOfVideo(String videoId) {
        String url = "https://api.dailymotion.com/video/" + videoId + "?fields=tags";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<TagResponseDM> response =
                restTemplate.exchange(url, HttpMethod.GET, request, TagResponseDM.class);
        TagResponseDM body = response.getBody();
        if (body == null || body.getTags() == null) {
            return List.of();
        }
        return body.getTags();
    }

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
}
