package com.example.fashionblog.controllers;

import com.example.fashionblog.dto.DesignRequestDTO;
import com.example.fashionblog.models.Design;
import com.example.fashionblog.responses.Response;
import com.example.fashionblog.services.designs.DesignService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("designs")
public class DesignController {

    private DesignService designService;

    private ModelMapper modelMapper;

    @Autowired
    public DesignController(DesignService designService, ModelMapper modelMapper) {
        this.designService = designService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<Response<Design>> postDesign(@Valid @RequestBody DesignRequestDTO design) {
        Design newDesign = designService.postDesign(modelMapper.map(design, Design.class));
        Response<Design> response = new Response<>(HttpStatus.CREATED);
        response.setMessage("Design successfully posted");
        response.setData(newDesign);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Response<List<Design>>> getAllDesigns(@RequestParam(defaultValue = "0") Integer page,
                                                                @RequestParam(defaultValue = "10") Integer size) {
        List<Design> designs = designService.getAllDesigns(page, size);
        Response<List<Design>> response = new Response<>(HttpStatus.OK);
        response.setMessage("All designs successfully retrieved");
        response.setData(designs);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response<Design>> getADesign(@PathVariable Integer id) {
        Design foundDesign = designService.getADesign(id);
        Response<Design> response = new Response<>(HttpStatus.OK);
        response.setMessage("Design successfully retrieved");
        response.setData(foundDesign);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Response<Design>> editADesign(@PathVariable Integer id, @Valid @RequestBody DesignRequestDTO design) {
        Design editedDesign = designService.editADesign(id, modelMapper.map(design, Design.class));
        Response<Design> response = new Response<>(HttpStatus.ACCEPTED);
        response.setMessage("Successfully updated the design with id: " + id);
        response.setData(editedDesign);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response<Design>> deleteADesign(@PathVariable Integer id) {
        designService.deleteADesign(id);
        Response<Design> response = new Response<>(HttpStatus.OK);
        response.setMessage("Design with id:" + id + " was successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}/like")
    public ResponseEntity<Response<Design>> likeADesign(@PathVariable Integer id) {
        Design likedDesign = designService.likeADesign(id);
        Response<Design> response = new Response<>(HttpStatus.ACCEPTED);
        response.setMessage("Design with id: " + id + " was successfully liked");
        response.setData(likedDesign);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/searchBy/title")
    public ResponseEntity<Response<List<Design>>> searchByTitle(@RequestParam(defaultValue = "0") Integer page,
                                                                @RequestParam(defaultValue = "10") Integer size,
                                                                @Valid @RequestBody String title) {
        List<Design> designs = designService.searchByTitle(title, page, size);
        Response<List<Design>> response = new Response<>(HttpStatus.OK);
        response.setMessage("All designs successfully retrieved");
        response.setData(designs);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
