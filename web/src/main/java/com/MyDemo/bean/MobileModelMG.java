package com.MyDemo.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ren on 2017/6/2.
 */
@Document(collection="mobileModel")
public class MobileModelMG implements Serializable {
    @Id
    private String _id;
    private String type;          //手机
    private String model;          //手机
    private String models;          //手机


    public MobileModelMG() {
    }

    public MobileModelMG(String _id, String type, String model, String models) {
        this._id = _id;
        this.type = type;
        this.model = model;
        this.models = models;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModels() {
        return models;
    }

    @Override
    public String toString() {
        return "MobileModelMG{" +
                "_id='" + _id + '\'' +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", models='" + models + '\'' +
                '}';
    }

    public void setModels(String models) {
        this.models = models;
    }
}
