package restful.SoundCloud;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class _Collections {

    @SerializedName("collection")
    @Expose
    private List<Collection> collection = new ArrayList<Collection>();
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("next_href")
    @Expose
    private String nextHref;
    @SerializedName("query_urn")
    @Expose
    private String queryUrn;

    public List<Collection> getCollection() {
        return collection;
    }

    public void setCollection(List<Collection> collection) {
        this.collection = collection;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public String getNextHref() {
        return nextHref;
    }

    public void setNextHref(String nextHref) {
        this.nextHref = nextHref;
    }

    public String getQueryUrn() {
        return queryUrn;
    }

    public void setQueryUrn(String queryUrn) {
        this.queryUrn = queryUrn;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(collection).append(totalResults).append(nextHref).append(queryUrn).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof _Collections) == false) {
            return false;
        }
        _Collections rhs = ((_Collections) other);
        return new EqualsBuilder().append(collection, rhs.collection).append(totalResults, rhs.totalResults).append(nextHref, rhs.nextHref).append(queryUrn, rhs.queryUrn).isEquals();
    }

}
