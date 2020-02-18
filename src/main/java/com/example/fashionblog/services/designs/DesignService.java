package com.example.fashionblog.services.designs;

import com.example.fashionblog.models.Design;

import java.util.List;

public interface DesignService {
    Design postDesign(Design design);
    Design getADesign(Integer id);
    List<Design> getAllDesigns(Integer page, Integer size);
    Design editADesign(Integer id, Design design);
    void deleteADesign(Integer id);
    Design likeADesign(Integer id);
    List<Design> searchByTitle(String title, Integer page, Integer size);
}
