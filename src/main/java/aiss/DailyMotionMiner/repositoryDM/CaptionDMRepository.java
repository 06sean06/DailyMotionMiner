package aiss.DailyMotionMiner.repositoryDM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import aiss.DailyMotionMiner.model.modelDM.caption.CaptionList;
import aiss.DailyMotionMiner.services.CaptionDMService;

@Repository
public class CaptionDMRepository {
    @Autowired
    CaptionDMService captionDMService;

    public List<CaptionList> findAll(String idVideo) {
        List<CaptionList> captions = captionDMService.getCaptions(idVideo);
        return captions;
    }
}
