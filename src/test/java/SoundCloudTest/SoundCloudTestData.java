package SoundCloudTest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SoundCloudTestData {

    private Float testCaseId;

    private String testCaseName;

    private String testCaseDescription;

    private String searchQuery;

    private String limit;

    private String offset;

    private String appLocale;

    public Float getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Float testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public String getTestCaseDescription() {
        return testCaseDescription;
    }

    public void setTestCaseDescription(String testCaseDescription) {
        this.testCaseDescription = testCaseDescription;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getAppLocale() {
        return appLocale;
    }

    public void setAppLocale(String appLocale) {
        this.appLocale = appLocale;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(testCaseId).append(testCaseName).append(testCaseDescription).append(searchQuery).append(limit).append(offset).append(appLocale).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SoundCloudTestData) == false) {
            return false;
        }
        SoundCloudTestData rhs = ((SoundCloudTestData) other);
        return new EqualsBuilder().append(testCaseId, rhs.testCaseId).append(testCaseName, rhs.testCaseName).append(testCaseDescription, rhs.testCaseDescription).append(searchQuery, rhs.searchQuery).append(limit, rhs.limit).append(offset, rhs.offset).append(appLocale, rhs.appLocale).isEquals();
    }

}
