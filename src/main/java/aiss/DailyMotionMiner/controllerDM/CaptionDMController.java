package aiss.DailyMotionMiner.controllerDM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aiss.DailyMotionMiner.exception.VideoNotFoundException;
import aiss.DailyMotionMiner.model.modelDM.caption.CaptionList;
import aiss.DailyMotionMiner.repositoryDM.CaptionDMRepository;

@RestController
@RequestMapping("DailyMotionMiner/captions")
public class CaptionDMController {

    private CaptionDMRepository captionDMRepository;

    @Autowired
    public CaptionDMController (CaptionDMRepository captionDMRepository) {
        this.captionDMRepository = captionDMRepository;
    }

    // GET http://localhost:8081/DailyMotionMiner/captions/{id}
    @GetMapping("/{idVideo}")
    public List<CaptionList> getCaptions(@PathVariable String idVideo) throws VideoNotFoundException{
        return captionDMRepository.findAll(idVideo);
    }

}
