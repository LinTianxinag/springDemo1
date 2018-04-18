package com.MyDemo.model;


/**
 * desciper::介绍方法名
 * Created by LinTianxiang.
 * Date: 2018/3/28.
 * time:${time}
 */
public class Dish {
    private final String name;
    private final boolean vegitarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegitarian, int calories, Type type) {
        this.name = name;
        this.vegitarian = vegitarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegitarian() {
        return vegitarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    public enum Type {MEAT,FISH,OTHER};
}
