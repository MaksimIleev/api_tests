package restful.SoundCloud;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Visuals {

    @SerializedName("urn")
    @Expose
    private String urn;
    @SerializedName("enabled")
    @Expose
    private Boolean enabled;
    @SerializedName("visuals")
    @Expose
    private List<Visual> visuals = new ArrayList<Visual>();

    public String getUrn() {
        return urn;
    }

    public void setUrn(String urn) {
        this.urn = urn;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Visual> getVisuals() {
        return visuals;
    }

    public void setVisuals(List<Visual> visuals) {
        this.visuals = visuals;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(urn).append(enabled).append(visuals).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Visuals) == false) {
            return false;
        }
        Visuals rhs = ((Visuals) other);
        return new EqualsBuilder().append(urn, rhs.urn).append(enabled, rhs.enabled).append(visuals, rhs.visuals).isEquals();
    }

}
