package ru.geekbrains.javaback;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseAlbum {
    @JsonProperty("data")
    private AlbumData albumData;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("status")
    private Integer status;

}
