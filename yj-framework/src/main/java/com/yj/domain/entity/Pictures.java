package com.yj.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @Package com.yj.domain.entity
 * @Author yJade
 * @Date 2023-02-18 15:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pictures {
    private List<String> pictures;
}
