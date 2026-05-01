package aiss.DailyMotionMiner.transformer;

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

public class Transformer {
     public static CaptionVM transformCaption(CaptionList data) {
        CaptionVM caption = new CaptionVM();
        caption.setId(data.getId().toString());
        caption.setLanguage(data.getLanguage());
        caption.setLink(data.getUrl());
        return caption;


    }

     public ChannelVM transformChannel(ChannelList data) {
        ChannelVM channel = new ChannelVM();
        channel.setId(data.getId());
        channel.setName(data.getScreenname());
        channel.setDescription(data.getDescription());
        channel.setCreated_time(data.getCreatedTime().toString());
        return channel;
    }

     public CommentVM transformComment(CommentList data) {
        CommentVM comment = new CommentVM();
        comment.setId(data.getId().toString());
        comment.setText(data.getMessage());
        comment.setCreatedOn(data.getCreatedTime().toString());
        return comment;
    }

     public UserVM transformUser(UserList data) {
        UserVM user = new UserVM();
        user.setId(data.getId());
        user.setName(data.getScreenname());
        user.setPicture_link(data.getAvatar120Url());
        user.setUser_link(data.getUrl());
        return user;
    }

     public static VideoVM transformVideo(VideoList data) {
        VideoVM video = new VideoVM();
        video.setId(data.getId());
        video.setName(data.getScreenname());
        video.setDescription(data.getDescription());
        video.setReleaseTime(data.getCreatedTime().toString());
        return video;
    }





}