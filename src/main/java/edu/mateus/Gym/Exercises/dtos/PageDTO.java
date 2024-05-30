package edu.mateus.Gym.Exercises.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {
    private long pageNum;
    private long pageItemsNum;
    private long totalItems;
    private long totalPages;
    private List<T> data;
}