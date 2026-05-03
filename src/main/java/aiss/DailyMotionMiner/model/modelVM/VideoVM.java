package aiss.DailyMotionMiner.model.modelVM;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "description",
    "releaseTime",
    "captions",
    "comments",
    "user"
})


public class VideoVM {
    
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("releaseTime")
    private String releaseTime;

    @JsonProperty("captions")
    private List<CaptionVM> captions;

    @JsonProperty("comments")
    private List<CommentVM> comments;

    @JsonProperty("user")
    private UserVM user;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("releaseTime")
    public String getReleaseTime() {
        return releaseTime;
    }

    @JsonProperty("releaseTime")
    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    @JsonProperty("captions")
    public List<CaptionVM> getCaptions() { return captions; }

    @JsonProperty("captions")
    public void setCaptions(List<CaptionVM> captions) { this.captions = captions; }

    @JsonProperty("comments")
    public List<CommentVM> getComments() { return comments; }

    @JsonProperty("comments")
    public void setComments(List<CommentVM> comments) { this.comments = comments; }

    @JsonProperty("user")
    public UserVM getUser() { return user; }

    @JsonProperty("user")
    public void setUser(UserVM user) { this.user = user; }
}
