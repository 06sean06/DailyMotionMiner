
package aiss.DailyMotionMiner.modelDM.user;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "screenname",
    "url",
    "avatar_120_url"
})
@Generated("jsonschema2pojo")
public class UserList {

    @JsonProperty("id")
    private String id;
    @JsonProperty("screenname")
    private String screenname;
    @JsonProperty("url")
    private String url;
    @JsonProperty("avatar_120_url")
    private String avatar120Url;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("screenname")
    public String getScreenname() {
        return screenname;
    }

    @JsonProperty("screenname")
    public void setScreenname(String screenname) {
        this.screenname = screenname;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("avatar_120_url")
    public String getAvatar120Url() {
        return avatar120Url;
    }

    @JsonProperty("avatar_120_url")
    public void setAvatar120Url(String avatar120Url) {
        this.avatar120Url = avatar120Url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserList.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("screenname");
        sb.append('=');
        sb.append(((this.screenname == null)?"<null>":this.screenname));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
        sb.append(',');
        sb.append("avatar120Url");
        sb.append('=');
        sb.append(((this.avatar120Url == null)?"<null>":this.avatar120Url));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
