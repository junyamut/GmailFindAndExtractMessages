package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Oauth {
    @SerializedName("accessType")
    @Expose
    private String accessType;
    @SerializedName("approvalPrompt")
    @Expose
    private String approvalPrompt;
    @SerializedName("credentialsFilePath")
    @Expose
    private String credentialsFilePath;
    @SerializedName("port")
    @Expose
    private Integer port;
    @SerializedName("scopes")
    @Expose
    private Scopes scopes;
    @SerializedName("tokensDirectoryPath")
    @Expose
    private String tokensDirectoryPath;
    @SerializedName("userId")
    @Expose
    private String userId;

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getApprovalPrompt() {
        return approvalPrompt;
    }

    public void setApprovalPrompt(String approvalPrompt) {
        this.approvalPrompt = approvalPrompt;
    }

    public String getCredentialsFilePath() {
        return credentialsFilePath;
    }

    public void setCredentialsFilePath(String credentialsFilePath) {
        this.credentialsFilePath = credentialsFilePath;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Scopes getScopes() {
        return scopes;
    }

    public void setScopes(Scopes scopes) {
        this.scopes = scopes;
    }

    public String getTokensDirectoryPath() {
        return tokensDirectoryPath;
    }

    public void setTokensDirectoryPath(String tokensDirectoryPath) {
        this.tokensDirectoryPath = tokensDirectoryPath;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}