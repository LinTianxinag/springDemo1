package com.MyDemo.test;

import com.MyDemo.model.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * desciper::这里进行一些简单的Java新特性的使用，
 * Created by LinTianxiang.
 * Date: 2018/3/28.
 * time:${time}
 */
public class JavaFeatureTest {



    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork",false,300,Dish.Type.FISH),
                new Dish("pork",false,300,Dish.Type.FISH),
                new Dish("pork",false,300,Dish.Type.FISH),
                new Dish("pork",false,300,Dish.Type.FISH),
                new Dish("pork",false,300,Dish.Type.FISH),
                new Dish("pork",false,300,Dish.Type.FISH),
                new Dish("pork",false,300,Dish.Type.FISH),
                new Dish("pork",false,300,Dish.Type.FISH)
        );
        List<String> strs=Arrays.asList("java","8","newFeature");
        Stream<String> s=strs.stream();
        s.forEach(System.out::println);
//        s.forEach(System.out::println);
    }

}
