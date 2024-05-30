package edu.mateus.Gym.Exercises.dtos.request;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageFilter {

    private Integer page;
    private Integer maxItems;

}