package aiss.DailyMotionMiner.repositoryDM;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import aiss.DailyMotionMiner.exception.ChannelNotFoundException;
import aiss.DailyMotionMiner.model.modelDM.caption.CaptionList;
import aiss.DailyMotionMiner.model.modelDM.channel.ChannelList;
import aiss.DailyMotionMiner.model.modelDM.user.UserList;
import aiss.DailyMotionMiner.model.modelDM.video.VideoDM;
import aiss.DailyMotionMiner.model.modelDM.video.VideoList;
import aiss.DailyMotionMiner.model.modelVM.CaptionVM;
import aiss.DailyMotionMiner.model.modelVM.ChannelVM;
import aiss.DailyMotionMiner.model.modelVM.CommentVM;
import aiss.DailyMotionMiner.model.modelVM.VideoVM;
import aiss.DailyMotionMiner.services.CaptionDMService;
import aiss.DailyMotionMiner.services.ChannelDMService;
import aiss.DailyMotionMiner.services.CommentDMService;
import aiss.DailyMotionMiner.services.UserDMService;
import aiss.DailyMotionMiner.transformer.Transformer;

@Repository
public class OficialRepository {

    @Autowired
    private ChannelDMService channelDMService;

    @Autowired
    private CaptionDMService captionDMService;

    @Autowired
    private UserDMService userDMService;

    @Autowired
    private CommentDMService commentDMService;

    @Autowired
    private Transformer transformer;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${dailymotion.default.maxVideos}")
    private Integer defaultMaxVideos;

    @Value("${dailymotion.default.maxPages}")
    private Integer defaultMaxPages;


    @Value("${videominer.url}")
    private String urlvm;

    public ChannelVM getAChannel(String channelId, Integer maxVideos, Integer maxPages) {
        try {
            ChannelList canalDM = channelDMService.getChannelById(channelId);
        if (canalDM == null) {
            return null;
        }
        ChannelVM canalVM = transformer.transformChannel(canalDM);
        int finalMaxVideos = (maxVideos != null) ? maxVideos : defaultMaxVideos;
        int finalMaxPages  = (maxPages  != null) ? maxPages  : defaultMaxPages;
        List<VideoList> acumulados = new ArrayList<>();
        
        for (int page = 1; page <= finalMaxPages; page++) {
            VideoDM pagina = channelDMService.getVideosOfChannel(channelId, page, finalMaxVideos);

            if (pagina == null || pagina.getList() == null) break;

            acumulados.addAll(pagina.getList());
            if (acumulados.size() >= finalMaxVideos) break;
        }
        // Limitamos los videos y los formamoos con sus comments y captions. 
        List<VideoList> listaVideosDM = acumulados.stream().limit(finalMaxVideos).toList();
        List<VideoVM> videosVM = new ArrayList<>();
        for (VideoList videoDM : listaVideosDM) {
            VideoVM videoVM = transformer.transformVideo(videoDM);

            UserList userDM = userDMService.getUserById(videoDM.getOwner());
            videoVM.setUser(transformer.transformUser(userDM));

            List<CaptionList> captionsDM = captionDMService.getCaptions(videoDM.getId());
            List<CaptionVM> captionsVM = captionsDM.stream().map(transformer::transformCaption).toList();
            videoVM.setCaptions(captionsVM);

            List<String> tagsOfComments = commentDMService.getTagsOfVideo(videoDM.getId());
            List<CommentVM> comments = transformer.transformTags(tagsOfComments);

            videoVM.setComments(comments);
            videosVM.add(videoVM);
        }
        canalVM.setVideos(videosVM);

        return canalVM;
        } catch (ChannelNotFoundException | RuntimeException e) {
            return null;
        }
    }

    // Si no recibe ningún parámetro de consulta, entonces, se tomará como null y se buscarán max 2 páginas y 10 vídeos:
    public ChannelVM getAChannel(String channelId) {
    return getAChannel(channelId, null, null);
    }

    public ChannelVM createAChannel(String channelId) {
        ChannelVM channelVM = getAChannel(channelId);
        if (channelVM == null) {
            return null;
        }
        String uri = urlvm + "/channels";
        ResponseEntity<ChannelVM> response = restTemplate.postForEntity(uri, channelVM, ChannelVM.class);
        return response.getBody();
    }

}
