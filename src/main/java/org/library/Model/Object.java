package org.library.Model;

import org.bson.codecs.pojo.annotations.BsonProperty;

/*
 * This is a test class
 */
public class Object {
    @BsonProperty(value = "name")
    String name;

    public Object(){}

    public Object(String name){
        this.name=name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
