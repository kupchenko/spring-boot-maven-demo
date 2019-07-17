package me.kupchenko.springbootmavendemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeanInfo {
    private String id;
    private String clazz;
    private boolean singleton;
}
