package aiss.DailyMotionMiner.modelDM.comment;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"message",
"created_time",
"screenname"
})
@Generated("jsonschema2pojo")
public class CommentList {

@JsonProperty("id")
private String id;
@JsonProperty("message")
private String message;
@JsonProperty("created_time")
private Integer createdTime;
@JsonProperty("screenname")
private String screenname;

@JsonProperty("id")
public String getId() {
return id;
}

@JsonProperty("id")
public void setId(String id) {
this.id = id;
}

@JsonProperty("message")
public String getMessage() {
return message;
}

@JsonProperty("message")
public void setMessage(String text) {
this.message = text;
}

@JsonProperty("created_time")
public Integer getCreatedTime() {
return createdTime;
}

@JsonProperty("created_time")
public void setCreatedTime(Integer createdTime) {
this.createdTime = createdTime;
}

@JsonProperty("screenname")
public String getScreenname() {
return screenname;
}

@JsonProperty("screenname")
public void setScreenname(String screenname) {
this.screenname = screenname;
}

@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(CommentList.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
sb.append("id");
sb.append('=');
sb.append(((this.id == null)?"<null>":this.id));
sb.append(',');
sb.append("text");
sb.append('=');
sb.append(((this.message == null)?"<null>":this.message));
sb.append(',');
sb.append("createdTime");
sb.append('=');
sb.append(((this.createdTime == null)?"<null>":this.createdTime));
sb.append(',');
sb.append("screenname");
sb.append('=');
sb.append(((this.screenname == null)?"<null>":this.screenname));
sb.append(',');
if (sb.charAt((sb.length()- 1)) == ',') {
sb.setCharAt((sb.length()- 1), ']');
} else {
sb.append(']');
}
return sb.toString();
}

}