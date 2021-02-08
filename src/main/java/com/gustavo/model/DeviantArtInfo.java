package com.gustavo.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class DeviantArtInfo {
    Boolean hasMore;
    Integer nextOffset;
    List<String> urlImages;
}
