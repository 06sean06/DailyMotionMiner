package aiss.DailyMotionMiner.transformer;

import java.time.Instant;

import org.springframework.stereotype.Component;

import aiss.DailyMotionMiner.model.modelDM.caption.CaptionList;
import aiss.DailyMotionMiner.model.modelDM.channel.ChannelList;
import aiss.DailyMotionMiner.model.modelDM.comment.CommentList;
import aiss.DailyMotionMiner.model.modelDM.user.UserList;
import aiss.DailyMotionMiner.model.modelDM.video.VideoList;
import aiss.DailyMotionMiner.model.modelVM.CaptionVM;
import aiss.DailyMotionMiner.model.modelVM.ChannelVM;
import aiss.DailyMotionMiner.model.modelVM.CommentVM;
import aiss.DailyMotionMiner.model.modelVM.UserVM;
import aiss.DailyMotionMiner.model.modelVM.VideoVM;

@Component
public class Transformer {
     public CaptionVM transformCaption(CaptionList data) {
        CaptionVM caption = new CaptionVM();
        caption.setId(null); // La pondrá automáticamente la bd. 
        caption.setLanguage(data.getLanguage());
        caption.setLink(data.getUrl());
        return caption;


    }

     public ChannelVM transformChannel(ChannelList data) {
        ChannelVM channel = new ChannelVM();
        channel.setId(transformId(data.getId()).toString());
        channel.setName(data.getScreenname());
        channel.setDescription(data.getDescription());
        // Inserta la fecha actual, porque channel de daily no tiene fecha ;(.
        channel.setCreatedTime(Instant.ofEpochSecond(data.getCreatedTime()).toString());
        return channel;
}


     public CommentVM transformComment(CommentList data) {
        CommentVM comment = new CommentVM();
        comment.setId(transformId(data.getId()).toString());
        comment.setText(data.getMessage());
        comment.setCreatedOn(data.getCreatedTime().toString());
        return comment;
    }

     public UserVM transformUser(UserList data) {
        UserVM user = new UserVM();
        user.setId(transformId(data.getId()).toString());
        user.setName(data.getScreenname());
        user.setPicture_link(data.getAvatar120Url());
        user.setUser_link(data.getUrl());
        return user;
    }

     public VideoVM transformVideo(VideoList data) {
        VideoVM video = new VideoVM();
        video.setId(transformId(data.getId()).toString());
        video.setName(data.getTitle());
        video.setDescription(data.getChannel()); 
        video.setReleaseTime("00:00");
        return video;
    }

    private Long transformId(String s) {
        return Math.abs(s.hashCode()) * 1L;
    }


}