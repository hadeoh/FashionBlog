package com.example.fashionblog.services.comments;

import com.example.fashionblog.exceptions.CustomException;
import com.example.fashionblog.models.Comment;
import com.example.fashionblog.models.Design;
import com.example.fashionblog.repositories.CommentRepository;
import com.example.fashionblog.repositories.DesignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private DesignRepository designRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, DesignRepository designRepository) {
        this.commentRepository = commentRepository;
        this.designRepository = designRepository;
    }

    public Comment postComment(Integer designId, Comment comment) {
        Optional<Design> existingDesign = designRepository.findById(designId);
        if (existingDesign.isPresent()) {
            existingDesign.get().addComments(comment);
//            designRepository.save(existingDesign.get());
            return commentRepository.save(comment);
        }
        throw new CustomException("Such design was not found", HttpStatus.NOT_FOUND);
    }
}
