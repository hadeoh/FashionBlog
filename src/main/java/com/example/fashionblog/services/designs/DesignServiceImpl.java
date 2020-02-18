package com.example.fashionblog.services.designs;

import com.example.fashionblog.exceptions.CustomException;
import com.example.fashionblog.models.Design;
import com.example.fashionblog.repositories.DesignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesignServiceImpl implements DesignService {

    private DesignRepository designRepository;

    @Autowired
    public DesignServiceImpl(DesignRepository designRepository) {
        this.designRepository = designRepository;
    }

    public Design postDesign(Design design) {
        return designRepository.save(design);
    }

    public Design getADesign(Integer id) {
        Optional<Design> foundDesign = designRepository.findById(id);
        if (foundDesign.isEmpty()) {
            throw new CustomException("Such design was not found", HttpStatus.NOT_FOUND);
        } else {
            return foundDesign.get();
        }
    }

    public List<Design> getAllDesigns(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        Slice<Design> designs = designRepository.findAll(paging);
        if (designs.hasContent()) {
            return designs.getContent();
        }
        throw new CustomException("There are no designs available", HttpStatus.NOT_FOUND);

    }

    public Design editADesign(Integer id, Design design) {
        Design existingDesign = this.getADesign(id);
        existingDesign.setTitle(design.getTitle());
        existingDesign.setDescription(design.getDescription());
        existingDesign.setImageUrl(design.getImageUrl());
        return designRepository.save(existingDesign);
    }

    public void deleteADesign(Integer id) {
        this.getADesign(id);
        designRepository.deleteById(id);
    }

    public Design likeADesign(Integer id) {
        Design existingDesign = this.getADesign(id);
        existingDesign.setLikes(existingDesign.getLikes() + 1);
        return designRepository.save(existingDesign);
    }

    public List<Design> searchByTitle(String title, Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        Slice<Design> designs = designRepository.findByTitleContaining(title, paging);
        if (designs.hasContent()) {
            return designs.getContent();
        }
        throw new CustomException("There are no designs with such title", HttpStatus.NOT_FOUND);
    }
}
