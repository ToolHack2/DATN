package com.example.user.smartfoody.Model;

import java.io.Serializable;

public class Oder implements Serializable {

    private String ProduceId;
    private String ProduceImage;
    private String ProduceName;
    private String ProducePrice;
    private String ProduceQuantity;

    public Oder(){}

    public Oder(String produceId,String produceImage, String produceName, String producePrice, String produceQuantity) {

        ProduceId = produceId;
        ProduceImage = produceImage;
        ProduceName = produceName;
        ProducePrice = producePrice;
        ProduceQuantity = produceQuantity;
    }

    public String getProduceId() {
        return ProduceId;
    }

    public String getProduceImage() {
        return ProduceImage;
    }

    public String getProduceName() {
        return ProduceName;
    }

    public String getProducePrice() {
        return ProducePrice;
    }

    public String getProduceQuantity() {
        return ProduceQuantity;
    }

    public void setProduceId(String produceId) {
        ProduceId = produceId;
    }

    public void setProduceImage(String produceImage) {
        ProduceImage = produceImage;
    }

    public void setProduceName(String produceName) {
        ProduceName = produceName;
    }

    public void setProducePrice(String producePrice) {
        ProducePrice = producePrice;
    }

    public void setProduceQuantity(String produceQuantity) {
        ProduceQuantity = produceQuantity;
    }
}
