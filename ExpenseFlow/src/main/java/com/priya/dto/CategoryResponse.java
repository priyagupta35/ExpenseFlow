package com.priya.dto;


public class CategoryResponse {

    private int id;
    private String name;
    private String type;

    public CategoryResponse() {
    }

    public CategoryResponse(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
