package restful.SoundCloud;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class User {

    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("comments_count")
    @Expose
    private Integer commentsCount;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("creator_subscriptions")
    @Expose
    private List<CreatorSubscription> creatorSubscriptions = new ArrayList<CreatorSubscription>();
    @SerializedName("creator_subscription")
    @Expose
    private CreatorSubscription_ creatorSubscription;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("followers_count")
    @Expose
    private Integer followersCount;
    @SerializedName("followings_count")
    @Expose
    private Integer followingsCount;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("groups_count")
    @Expose
    private Integer groupsCount;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("likes_count")
    @Expose
    private Integer likesCount;
    @SerializedName("playlist_likes_count")
    @Expose
    private Integer playlistLikesCount;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("permalink_url")
    @Expose
    private String permalinkUrl;
    @SerializedName("playlist_count")
    @Expose
    private Integer playlistCount;
    @SerializedName("reposts_count")
    @Expose
    private Object repostsCount;
    @SerializedName("track_count")
    @Expose
    private Integer trackCount;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("urn")
    @Expose
    private String urn;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("visuals")
    @Expose
    private Visuals visuals;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<CreatorSubscription> getCreatorSubscriptions() {
        return creatorSubscriptions;
    }

    public void setCreatorSubscriptions(List<CreatorSubscription> creatorSubscriptions) {
        this.creatorSubscriptions = creatorSubscriptions;
    }

    public CreatorSubscription_ getCreatorSubscription() {
        return creatorSubscription;
    }

    public void setCreatorSubscription(CreatorSubscription_ creatorSubscription) {
        this.creatorSubscription = creatorSubscription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public Integer getFollowingsCount() {
        return followingsCount;
    }

    public void setFollowingsCount(Integer followingsCount) {
        this.followingsCount = followingsCount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getGroupsCount() {
        return groupsCount;
    }

    public void setGroupsCount(Integer groupsCount) {
        this.groupsCount = groupsCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getPlaylistLikesCount() {
        return playlistLikesCount;
    }

    public void setPlaylistLikesCount(Integer playlistLikesCount) {
        this.playlistLikesCount = playlistLikesCount;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getPermalinkUrl() {
        return permalinkUrl;
    }

    public void setPermalinkUrl(String permalinkUrl) {
        this.permalinkUrl = permalinkUrl;
    }

    public Integer getPlaylistCount() {
        return playlistCount;
    }

    public void setPlaylistCount(Integer playlistCount) {
        this.playlistCount = playlistCount;
    }

    public Object getRepostsCount() {
        return repostsCount;
    }

    public void setRepostsCount(Object repostsCount) {
        this.repostsCount = repostsCount;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrn() {
        return urn;
    }

    public void setUrn(String urn) {
        this.urn = urn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Visuals getVisuals() {
        return visuals;
    }

    public void setVisuals(Visuals visuals) {
        this.visuals = visuals;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(avatarUrl).append(city).append(commentsCount).append(countryCode).append(createdAt).append(creatorSubscriptions).append(creatorSubscription).append(description).append(followersCount).append(followingsCount).append(firstName).append(fullName).append(groupsCount).append(id).append(kind).append(lastModified).append(lastName).append(likesCount).append(playlistLikesCount).append(permalink).append(permalinkUrl).append(playlistCount).append(repostsCount).append(trackCount).append(uri).append(urn).append(username).append(verified).append(visuals).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof User) == false) {
            return false;
        }
        User rhs = ((User) other);
        return new EqualsBuilder().append(avatarUrl, rhs.avatarUrl).append(city, rhs.city).append(commentsCount, rhs.commentsCount).append(countryCode, rhs.countryCode).append(createdAt, rhs.createdAt).append(creatorSubscriptions, rhs.creatorSubscriptions).append(creatorSubscription, rhs.creatorSubscription).append(description, rhs.description).append(followersCount, rhs.followersCount).append(followingsCount, rhs.followingsCount).append(firstName, rhs.firstName).append(fullName, rhs.fullName).append(groupsCount, rhs.groupsCount).append(id, rhs.id).append(kind, rhs.kind).append(lastModified, rhs.lastModified).append(lastName, rhs.lastName).append(likesCount, rhs.likesCount).append(playlistLikesCount, rhs.playlistLikesCount).append(permalink, rhs.permalink).append(permalinkUrl, rhs.permalinkUrl).append(playlistCount, rhs.playlistCount).append(repostsCount, rhs.repostsCount).append(trackCount, rhs.trackCount).append(uri, rhs.uri).append(urn, rhs.urn).append(username, rhs.username).append(verified, rhs.verified).append(visuals, rhs.visuals).isEquals();
    }

}
