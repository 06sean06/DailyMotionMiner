package aiss.DailyMotionMiner.transformer;

import aiss.DailyMotionMiner.model.modelDM.caption.CaptionDM;
import aiss.DailyMotionMiner.model.modelVM.CaptionVM;
import aiss.DailyMotionMiner.model.modelVM.ChannelVM;
import aiss.DailyMotionMiner.model.modelVM.CommentVM;
import aiss.DailyMotionMiner.model.modelVM.UserVM;
import aiss.DailyMotionMiner.model.modelVM.VideoVM;

public class Transformer {
     //Transformar caption
    public CaptionVM transformCaption(CaptionDM data) {
        CaptionVM caption = new CaptionVM ();
        caption.setLanguage(data.getLanguage().getLabel());   //mirar si label es el nombre del lenguaje
        caption.setLink(data.getCaptionPath());    //mirar si captionPath es el enlace de la caption
        return caption;
    }

    //Transformar canal
    public ChannelVM transformChannel(ChannelDM data) {
        ChannelVM channel = new ChannelVM();
        channel.setId(data.getId().toString());
        channel.setName(data.getName());
        channel.setDescription(data.getDescription().toString());
        channel.setCreated_time(data.getCreatedAt());
        return channel;
    }

    //Transformar user
    public UserVM transformUser(OwnerAccountPT data) {
        UserVM user = new UserVM();
        user.setId(data.getId().toString());
        user.setName(data.getName());
        if (data.getAvatars() != null && !data.getAvatars().isEmpty()) {
            user.setPicture_link(data.getAvatars().get(0).toString());
        } else {
            user.setPicture_link(null);
        }
        return user;
    }

    //Transformar video
    public VideoVM transformVideo(VideoPT data) {
        VideoVM video = new VideoVM();
        video.setId(data.getId().toString());
        video.setName(data.getName());
        video.setDescription(data.getDescription().toString());
        video.setReleaseTime(data.getPublishedAt());
        return video;
    }
     //Transformar comentario
    public CommentVM transformComment(CommentBasePT data) {
        CommentVM comment = new CommentVM();
        comment.setId(data.getId().toString());
        comment.setText(data.getText());
        comment.setCreatedOn(data.getCreatedAt());
        return comment;
    }
}



