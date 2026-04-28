package aiss.DailyMotionMiner.model.modelVM;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "user_link",
    "picture_link"
})


public class UserVM {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("user_link")
    private String user_link;
    @JsonProperty("picture_link")
    private String picture_link;
    

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

    @JsonProperty("user_link")
    public String getUser_link() {
        return user_link;
    }

    @JsonProperty("user_link")
    public void setUser_link(String user_link) {
        this.user_link = user_link;
    }

    @JsonProperty("picture_link")
    public String getPicture_link() {
        return picture_link;
    }

    @JsonProperty("picture_link")
    public void setPicture_link(String picture_link) {
        this.picture_link = picture_link;
    }
}
