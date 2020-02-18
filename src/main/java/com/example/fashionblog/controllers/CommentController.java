package com.example.fashionblog.controllers;

import com.example.fashionblog.dto.CommentRequestDTO;
import com.example.fashionblog.models.Comment;
import com.example.fashionblog.responses.Response;
import com.example.fashionblog.services.comments.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("comments/{designId}")
public class CommentController {

    private CommentService commentService;

    private ModelMapper modelMapper;

    @Autowired
    public CommentController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<Response<Comment>> postComment(@PathVariable Integer designId, @Valid @RequestBody CommentRequestDTO comment) {
        Comment newComment = commentService.postComment(designId, modelMapper.map(comment, Comment.class));
        Response<Comment> response = new Response<>(HttpStatus.CREATED);
        response.setMessage("Comment successfully added to design with id:" + designId);
        response.setData(newComment);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
