package aiss.DailyMotionMiner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aiss.DailyMotionMiner.model.modelDM.caption.CaptionDM;
import aiss.DailyMotionMiner.model.modelDM.caption.CaptionList;
import aiss.DailyMotionMiner.model.modelVM.CaptionVM;
import aiss.DailyMotionMiner.transformer.Transformer;

@Service
public class CaptionDMService {

    @Value("${videominer.url}")
    private String urlvm; //http://localhost:8080/VideoMiner

    @Autowired
    RestTemplate restTemplate;

    @Value("${dailymotion.url}")
    private String url;

    // GET SUBTITLES https://api.dailymotion.com/video/{idVideo}/subtitles?fields=id,item_type,language,language_label,url
    @SuppressWarnings("null")
    public List<CaptionList> getCaptions(String idVideo) {
        String uri = url + "/video/" + idVideo +"/subtitles?fields=id,item_type,language,language_label,url";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<CaptionDM> request = new HttpEntity<>(null, headers);
        ResponseEntity<CaptionDM> response = restTemplate.exchange(uri, HttpMethod.GET, request,CaptionDM.class);
        CaptionDM body = response.getBody();
        if (response == null || body.getCaptionDetails() == null) {
            return List.of();
        }
        return body.getCaptionDetails();
    }

    //POST CAPTION http://localhost:8080/VideoMiner/captions
    public CaptionVM createCaption(CaptionList captionDM) {
        // El subtítulo va a subirse con un post a VideoMiner. 
        // Recibimos un subtítulo con formato de PeerTube y lo cambiamos a VideoMiner.
        String uri = urlvm + "/captions";
        CaptionVM transformed = Transformer.transformCaption(captionDM);
        // A continuación lo subimos con POST a la uri indicada. 
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<CaptionVM> request = new HttpEntity<>(transformed, headers);
        @SuppressWarnings("null")
        ResponseEntity<CaptionVM> response = restTemplate.exchange(uri, HttpMethod.POST, request, CaptionVM.class);
        CaptionVM caption  = response.getBody();
        if (caption == null ) {
            return null;
        }
        return caption;
    }

    //Transformar caption
    public CaptionVM transformCaption(CaptionList data) {
        CaptionVM caption = new CaptionVM();
        caption.setId(data.getId().toString());
        caption.setLanguage(data.getLanguage());
        caption.setLink(data.getUrl());
        return caption;
    }
}
