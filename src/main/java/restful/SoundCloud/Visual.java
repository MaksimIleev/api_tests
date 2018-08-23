package restful.SoundCloud;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Visual {

    @SerializedName("urn")
    @Expose
    private String urn;
    @SerializedName("entry_time")
    @Expose
    private Integer entryTime;
    @SerializedName("visual_url")
    @Expose
    private String visualUrl;

    public String getUrn() {
        return urn;
    }

    public void setUrn(String urn) {
        this.urn = urn;
    }

    public Integer getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Integer entryTime) {
        this.entryTime = entryTime;
    }

    public String getVisualUrl() {
        return visualUrl;
    }

    public void setVisualUrl(String visualUrl) {
        this.visualUrl = visualUrl;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(urn).append(entryTime).append(visualUrl).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Visual) == false) {
            return false;
        }
        Visual rhs = ((Visual) other);
        return new EqualsBuilder().append(urn, rhs.urn).append(entryTime, rhs.entryTime).append(visualUrl, rhs.visualUrl).isEquals();
    }

}
