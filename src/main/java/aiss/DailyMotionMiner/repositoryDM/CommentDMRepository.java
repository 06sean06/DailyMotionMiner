package aiss.DailyMotionMiner.repositoryDM;

import org.springframework.stereotype.Repository;

import aiss.DailyMotionMiner.model.modelDM.comment.CommentList;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentDMRepository {
    
    private List<CommentList> comments = new ArrayList<>();

    public List<CommentList> findAll(){
        return comments;
    }

    public void save(CommentList comment){
        comments.add(comment);
    }

    public void saveAll(List<CommentList> newComments){
        comments.addAll(newComments);
    }
}
