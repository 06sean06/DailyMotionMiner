package aiss.DailyMotionMiner.model.modelVM;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "link",
    "language"
})

public class CaptionVM {
    @JsonProperty("id")
    private String id;
    @JsonProperty("link")
    private String link;
    @JsonProperty("language")
    private String language;

    @JsonProperty("id")
    public String getId() {
        return id;
    }  

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }
}
