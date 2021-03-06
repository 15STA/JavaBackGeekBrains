package ru.geekbrains.javaback;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountData {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("bio")
    private Object bio;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("avatar_name")
    private String avatarName;
    @JsonProperty("cover")
    private String cover;
    @JsonProperty("cover_name")
    private String coverName;
    @JsonProperty("reputation")
    private Integer reputation;
    @JsonProperty("reputation_name")
    private String reputationName;
    @JsonProperty("created")
    private Integer created;
    @JsonProperty("pro_expiration")
    private Boolean proExpiration;
    @JsonProperty("user_follow")
    private UserFollow userFollow;
    @JsonProperty("is_blocked")
    private Boolean isBlocked;
}
