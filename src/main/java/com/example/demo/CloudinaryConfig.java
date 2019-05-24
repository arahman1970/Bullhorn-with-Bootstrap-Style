package com.example.demo;

import com.cloudinary.Transformation;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import java.util.Map;



import java.io.IOException;

@Component
public class CloudinaryConfig {
    private Cloudinary cloudinary;
    @Autowired
    public CloudinaryConfig(@Value("${cloud.key}") String key,
                            @Value("${cloud.secret}") String secret,
                            @Value("${cloud.name}") String cloud){
        cloudinary = Singleton.getCloudinary();
        cloudinary.config.cloudName=cloud;
        cloudinary.config.apiSecret=secret;
        cloudinary.config.apiKey=key;

    }
    public Map upload(byte[] file, Map options){
        try {
            return cloudinary.uploader().upload(file, options);
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
    }

    public String createURl(String name, int width, int height, String action){
        return cloudinary.url()
                .transformation(new com.cloudinary.Transformation()
                        .width(width).height(height)
                        .border("2px_solid_black").crop(action))
                .imageTag(name);
    }
}