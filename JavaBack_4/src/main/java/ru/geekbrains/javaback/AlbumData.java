package ru.geekbrains.javaback;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class AlbumData {
    @JsonProperty("id")
    private String id;
    @JsonProperty("deletehash")
    private String deletehash;
}
