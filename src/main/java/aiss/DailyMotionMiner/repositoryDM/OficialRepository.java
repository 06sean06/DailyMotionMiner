package aiss.DailyMotionMiner.repositoryDM;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import aiss.DailyMotionMiner.model.modelDM.caption.CaptionList;
import aiss.DailyMotionMiner.model.modelDM.channel.ChannelList;
import aiss.DailyMotionMiner.model.modelDM.user.UserList;
import aiss.DailyMotionMiner.model.modelDM.video.VideoDM;
import aiss.DailyMotionMiner.model.modelDM.video.VideoList;
import aiss.DailyMotionMiner.model.modelVM.CaptionVM;
import aiss.DailyMotionMiner.model.modelVM.ChannelVM;
import aiss.DailyMotionMiner.model.modelVM.VideoVM;
import aiss.DailyMotionMiner.services.CaptionDMService;
import aiss.DailyMotionMiner.services.ChannelDMService;
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
    private Transformer transformer;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${videominer.url}")
    private String urlvm;

    public ChannelVM getAChannel(String channelId) {
        ChannelList canalDM = channelDMService.getChannelById(channelId);
        if (canalDM == null) {
            return null;
        }
        ChannelVM canalVM = transformer.transformChannel(canalDM);
        VideoDM videosDM = channelDMService.getVideosOfChannel(channelId);
        List<VideoList> listaVideosDM = videosDM.getList();

        List<VideoVM> videosVM = new ArrayList<>();
        for (VideoList videoDM: listaVideosDM) {
            VideoVM videoVM = transformer.transformVideo(videoDM);

            UserList userDM = userDMService.getUserById(videoDM.getOwner());
            videoVM.setUser(transformer.transformUser(userDM));

            List<CaptionList> captionsDM = captionDMService.getCaptions(videoDM.getId());
            List<CaptionVM> captionsVM = captionsDM. stream().map(transformer::transformCaption).toList();
            videoVM.setCaptions(captionsVM);

            videoVM.setComments(new ArrayList<>());
            videosVM.add(videoVM);
        }
        canalVM.setVideos(videosVM);
        return canalVM;
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

    public ChannelVM getAChannelByName(String name) {
        ChannelList canalDM = channelDMService.getChannelByName(name);
        if (canalDM == null) {
            return null;
        }
        ChannelVM canalVM = transformer.transformChannel(canalDM);
        VideoDM videosDM = channelDMService.getVideosOfChannel(canalDM.getId());
        List<VideoList> listaVideosDM = videosDM.getList();

        List<VideoVM> videosVM = new ArrayList<>();
        for (VideoList videoDM: listaVideosDM) {
            VideoVM videoVM = transformer.transformVideo(videoDM);

            UserList userDM = userDMService.getUserById(videoDM.getOwner());
            videoVM.setUser(transformer.transformUser(userDM));

            List<CaptionList> captionsDM = captionDMService.getCaptions(videoDM.getId());
            List<CaptionVM> captionsVM = captionsDM. stream().map(transformer::transformCaption).toList();
            videoVM.setCaptions(captionsVM);

            videoVM.setComments(new ArrayList<>());
            videosVM.add(videoVM);
        }
        canalVM.setVideos(videosVM);
        return canalVM;
    }

}
