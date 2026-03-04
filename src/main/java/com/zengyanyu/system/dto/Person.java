package com.zengyanyu.system.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zengyanyu
 */
@Getter
@Setter
@NoArgsConstructor
public class Person implements Serializable {
    
    private String name;

    private Integer age;

    private String grande;

    public Person(String name, Integer age, String grande) {
        this.name = name;
        this.age = age;
        this.grande = grande;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", grande='" + grande + '\'' +
                '}';
    }
}
