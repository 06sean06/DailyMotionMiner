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
import aiss.DailyMotionMiner.transformer.Transformer;

@Repository
public class OficialRepository {

        @Autowired
        private ChannelDMRepository channelDMRepository;

        @Autowired
        private CaptionDMRepository captionDMRepository;

        @Autowired
        private UserDMRepository userDMRepository;


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
        ChannelList canalDM = channelDMRepository.findOneById(channelId);
        ChannelVM canalVM = transformer.transformChannel(canalDM);
        int videoLimit = (maxVideos != null) ? maxVideos : defaultMaxVideos;
        int pageLimit = (maxPages != null) ? maxPages : defaultMaxPages;

        List<VideoList> listaVideos = new ArrayList<>();

        for (int i = 1; i<= pageLimit; i++){
            VideoDM paginaVideos = channelDMRepository.getVideosOfChannel(channelId, i, videoLimit);
            if (paginaVideos != null && paginaVideos.getList() !=null){
                listaVideos.addAll(paginaVideos.getList());
                if (listaVideos.size() >= videoLimit){
                    break;
                }
            }else{
                break;
            }
        }
        List<VideoList> listaVideosDM = listaVideos.stream() //se corta la lista
                .limit(videoLimit)
                .toList();

        List<VideoVM> videosVM = new ArrayList<>();

        for (VideoList videoDM: listaVideosDM) {
            VideoVM videoVM = transformer.transformVideo(videoDM);
            UserList userDM = userDMRepository.getUserById(videoDM.getOwner());
            videoVM.setUser(transformer.transformUser(userDM));

            List<CaptionList> captionsDM = captionDMRepository.findAll(videoDM.getId());
            List<CaptionVM> captionsVM = captionsDM. stream().map(transformer::transformCaption).toList();
            videoVM.setCaptions(captionsVM);

            videoVM.setComments(new ArrayList<>());
            videosVM.add(videoVM);
        }
        canalVM.setVideos(videosVM);
        return canalVM;
        } catch (Exception e) {
            return null;
        }
    }

    public ChannelVM createAChannel(String channelId, Integer maxVideos, Integer maxPages) {
        ChannelVM channelVM = getAChannel(channelId, maxVideos, maxPages);
        if (channelVM == null) {
            return null;
        }
        String uri = urlvm + "/channels";
        ResponseEntity<ChannelVM> response = restTemplate.postForEntity(uri, channelVM, ChannelVM.class);
        return response.getBody();
    }

    public ChannelVM getAChannelByName(String name) {
        ChannelList canalDM = channelDMRepository.getChannelByName(name);
        if (canalDM == null) {
            return null;
        }
        ChannelVM canalVM = transformer.transformChannel(canalDM);
        VideoDM videosDM = channelDMRepository.getVideosOfChannel(canalDM.getId());
        List<VideoList> listaVideosDM = videosDM.getList();

        List<VideoVM> videosVM = new ArrayList<>();
        for (VideoList videoDM: listaVideosDM) {
            VideoVM videoVM = transformer.transformVideo(videoDM);

            UserList userDM = userDMRepository.getUserById(videoDM.getOwner());
            videoVM.setUser(transformer.transformUser(userDM));

            List<CaptionList> captionsDM = captionDMRepository.findAll(videoDM.getId());
            List<CaptionVM> captionsVM = captionsDM. stream().map(transformer::transformCaption).toList();
            videoVM.setCaptions(captionsVM);

            videoVM.setComments(new ArrayList<>());
            videosVM.add(videoVM);
        }
        canalVM.setVideos(videosVM);
        return canalVM;
    }

}
