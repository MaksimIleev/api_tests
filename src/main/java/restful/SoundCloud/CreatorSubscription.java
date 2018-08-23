package restful.SoundCloud;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CreatorSubscription {

    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("recurring")
    @Expose
    private Boolean recurring;
    @SerializedName("hug")
    @Expose
    private Boolean hug;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

    public Boolean getHug() {
        return hug;
    }

    public void setHug(Boolean hug) {
        this.hug = hug;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(product).append(recurring).append(hug).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CreatorSubscription) == false) {
            return false;
        }
        CreatorSubscription rhs = ((CreatorSubscription) other);
        return new EqualsBuilder().append(product, rhs.product).append(recurring, rhs.recurring).append(hug, rhs.hug).isEquals();
    }

}
