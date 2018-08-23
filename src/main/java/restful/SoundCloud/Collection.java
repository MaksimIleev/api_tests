package restful.SoundCloud;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Collection {

    @SerializedName("artwork_url")
    @Expose
    private String artworkUrl;
    @SerializedName("commentable")
    @Expose
    private Boolean commentable;
    @SerializedName("comment_count")
    @Expose
    private Integer commentCount;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("downloadable")
    @Expose
    private Boolean downloadable;
    @SerializedName("download_count")
    @Expose
    private Integer downloadCount;
    @SerializedName("download_url")
    @Expose
    private String downloadUrl;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("full_duration")
    @Expose
    private Integer fullDuration;
    @SerializedName("embeddable_by")
    @Expose
    private String embeddableBy;
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("has_downloads_left")
    @Expose
    private Boolean hasDownloadsLeft;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("label_name")
    @Expose
    private String labelName;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("likes_count")
    @Expose
    private Integer likesCount;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("permalink_url")
    @Expose
    private String permalinkUrl;
    @SerializedName("playback_count")
    @Expose
    private Integer playbackCount;
    @SerializedName("public")
    @Expose
    private Boolean _public;
    @SerializedName("publisher_metadata")
    @Expose
    private Object publisherMetadata;
    @SerializedName("purchase_title")
    @Expose
    private Object purchaseTitle;
    @SerializedName("purchase_url")
    @Expose
    private Object purchaseUrl;
    @SerializedName("release_date")
    @Expose
    private Object releaseDate;
    @SerializedName("reposts_count")
    @Expose
    private Integer repostsCount;
    @SerializedName("secret_token")
    @Expose
    private Object secretToken;
    @SerializedName("sharing")
    @Expose
    private String sharing;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("streamable")
    @Expose
    private Boolean streamable;
    @SerializedName("tag_list")
    @Expose
    private String tagList;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("urn")
    @Expose
    private String urn;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("visuals")
    @Expose
    private Object visuals;
    @SerializedName("waveform_url")
    @Expose
    private String waveformUrl;
    @SerializedName("display_date")
    @Expose
    private String displayDate;
    @SerializedName("monetization_model")
    @Expose
    private String monetizationModel;
    @SerializedName("policy")
    @Expose
    private String policy;
    @SerializedName("user")
    @Expose
    private User user;

    public String getArtworkUrl() {
        return artworkUrl;
    }

    public void setArtworkUrl(String artworkUrl) {
        this.artworkUrl = artworkUrl;
    }

    public Boolean getCommentable() {
        return commentable;
    }

    public void setCommentable(Boolean commentable) {
        this.commentable = commentable;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDownloadable() {
        return downloadable;
    }

    public void setDownloadable(Boolean downloadable) {
        this.downloadable = downloadable;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getFullDuration() {
        return fullDuration;
    }

    public void setFullDuration(Integer fullDuration) {
        this.fullDuration = fullDuration;
    }

    public String getEmbeddableBy() {
        return embeddableBy;
    }

    public void setEmbeddableBy(String embeddableBy) {
        this.embeddableBy = embeddableBy;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Boolean getHasDownloadsLeft() {
        return hasDownloadsLeft;
    }

    public void setHasDownloadsLeft(Boolean hasDownloadsLeft) {
        this.hasDownloadsLeft = hasDownloadsLeft;
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

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
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

    public Integer getPlaybackCount() {
        return playbackCount;
    }

    public void setPlaybackCount(Integer playbackCount) {
        this.playbackCount = playbackCount;
    }

    public Boolean getPublic() {
        return _public;
    }

    public void setPublic(Boolean _public) {
        this._public = _public;
    }

    public Object getPublisherMetadata() {
        return publisherMetadata;
    }

    public void setPublisherMetadata(Object publisherMetadata) {
        this.publisherMetadata = publisherMetadata;
    }

    public Object getPurchaseTitle() {
        return purchaseTitle;
    }

    public void setPurchaseTitle(Object purchaseTitle) {
        this.purchaseTitle = purchaseTitle;
    }

    public Object getPurchaseUrl() {
        return purchaseUrl;
    }

    public void setPurchaseUrl(Object purchaseUrl) {
        this.purchaseUrl = purchaseUrl;
    }

    public Object getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Object releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRepostsCount() {
        return repostsCount;
    }

    public void setRepostsCount(Integer repostsCount) {
        this.repostsCount = repostsCount;
    }

    public Object getSecretToken() {
        return secretToken;
    }

    public void setSecretToken(Object secretToken) {
        this.secretToken = secretToken;
    }

    public String getSharing() {
        return sharing;
    }

    public void setSharing(String sharing) {
        this.sharing = sharing;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getStreamable() {
        return streamable;
    }

    public void setStreamable(Boolean streamable) {
        this.streamable = streamable;
    }

    public String getTagList() {
        return tagList;
    }

    public void setTagList(String tagList) {
        this.tagList = tagList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Object getVisuals() {
        return visuals;
    }

    public void setVisuals(Object visuals) {
        this.visuals = visuals;
    }

    public String getWaveformUrl() {
        return waveformUrl;
    }

    public void setWaveformUrl(String waveformUrl) {
        this.waveformUrl = waveformUrl;
    }

    public String getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(String displayDate) {
        this.displayDate = displayDate;
    }

    public String getMonetizationModel() {
        return monetizationModel;
    }

    public void setMonetizationModel(String monetizationModel) {
        this.monetizationModel = monetizationModel;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(artworkUrl).append(commentable).append(commentCount).append(createdAt).append(description).append(downloadable).append(downloadCount).append(downloadUrl).append(duration).append(fullDuration).append(embeddableBy).append(genre).append(hasDownloadsLeft).append(id).append(kind).append(labelName).append(lastModified).append(license).append(likesCount).append(permalink).append(permalinkUrl).append(playbackCount).append(_public).append(publisherMetadata).append(purchaseTitle).append(purchaseUrl).append(releaseDate).append(repostsCount).append(secretToken).append(sharing).append(state).append(streamable).append(tagList).append(title).append(uri).append(urn).append(userId).append(visuals).append(waveformUrl).append(displayDate).append(monetizationModel).append(policy).append(user).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Collection) == false) {
            return false;
        }
        Collection rhs = ((Collection) other);
        return new EqualsBuilder().append(artworkUrl, rhs.artworkUrl).append(commentable, rhs.commentable).append(commentCount, rhs.commentCount).append(createdAt, rhs.createdAt).append(description, rhs.description).append(downloadable, rhs.downloadable).append(downloadCount, rhs.downloadCount).append(downloadUrl, rhs.downloadUrl).append(duration, rhs.duration).append(fullDuration, rhs.fullDuration).append(embeddableBy, rhs.embeddableBy).append(genre, rhs.genre).append(hasDownloadsLeft, rhs.hasDownloadsLeft).append(id, rhs.id).append(kind, rhs.kind).append(labelName, rhs.labelName).append(lastModified, rhs.lastModified).append(license, rhs.license).append(likesCount, rhs.likesCount).append(permalink, rhs.permalink).append(permalinkUrl, rhs.permalinkUrl).append(playbackCount, rhs.playbackCount).append(_public, rhs._public).append(publisherMetadata, rhs.publisherMetadata).append(purchaseTitle, rhs.purchaseTitle).append(purchaseUrl, rhs.purchaseUrl).append(releaseDate, rhs.releaseDate).append(repostsCount, rhs.repostsCount).append(secretToken, rhs.secretToken).append(sharing, rhs.sharing).append(state, rhs.state).append(streamable, rhs.streamable).append(tagList, rhs.tagList).append(title, rhs.title).append(uri, rhs.uri).append(urn, rhs.urn).append(userId, rhs.userId).append(visuals, rhs.visuals).append(waveformUrl, rhs.waveformUrl).append(displayDate, rhs.displayDate).append(monetizationModel, rhs.monetizationModel).append(policy, rhs.policy).append(user, rhs.user).isEquals();
    }

}
