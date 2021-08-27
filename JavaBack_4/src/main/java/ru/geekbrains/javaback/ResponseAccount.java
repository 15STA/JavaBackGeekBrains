package ru.geekbrains.javaback;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ResponseAccount {
    @JsonProperty("data")
    private AccountData accountData;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("status")
    private Integer status;

}
