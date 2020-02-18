package com.example.fashionblog.integration;

import com.example.fashionblog.controllers.DesignController;
import com.example.fashionblog.models.Design;
import com.example.fashionblog.repositories.DesignRepository;
import com.example.fashionblog.services.designs.DesignService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DesignTest {

    @MockBean
    private static DesignRepository designRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private static DesignService designService;

    @Autowired
    private DesignController designController;

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

//    @BeforeAll
//    static void init() {
//        Design design1 = new Design();
//        design1.setTitle("test1");
//        design1.setDescription("test1Description");
//        design1.setImageUrl("image.com");
//        Design design2 = new Design();
//        design2.setTitle("test2");
//        design2.setDescription("test2Description");
//        design2.setImageUrl("image.com");
//    }
//
//    @BeforeAll
//    static void createDesigns() {
//        Design design1 = new Design();
//        design1.setTitle("test1");
//        design1.setDescription("test1Description");
//        design1.setImageUrl("image.com");
//        Design design2 = new Design();
//
//        design2.setTitle("test2");
//        design2.setDescription("test2Description");
//        design2.setImageUrl("image.com");
//
//        designService.postDesign(design1);
//        designService.postDesign(design2);
//    }

    @Test
    public void postDesignWithCorrectValues() throws Exception {
        Design design = new Design();
        design.setTitle("test1");
        design.setDescription("test1 description");
        design.setImageUrl("https://www.gmail.com");
        mockMvc.perform(post("/designs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(design))).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Design successfully posted")));
    }

    @Test
    public void postDesignWithIncorrectValues_notOk() throws Exception {
        Design design = new Design();
        design.setTitle("test1");
        design.setDescription("test1 description");
        mockMvc.perform(post("/designs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(design))).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Validation Error")));
    }

    @Test
    public void getAllDesigns_Ok() throws Exception {
        Design design1 = new Design();
        design1.setTitle("test1");
        design1.setDescription("test1Description");
        design1.setImageUrl("image.com");
        Design design2 = new Design();

        design2.setTitle("test2");
        design2.setDescription("test2Description");
        design2.setImageUrl("image.com");
        designRepository.saveAll(Arrays.asList(design1, design2));
        mockMvc.perform(get("/designs"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("All designs successfully retrieved")));
    }

//    @Test
//    public void getADesign_Ok() throws Exception {
//        Design design1 = new Design();
//        design1.setTitle("test1");
//        design1.setDescription("test1Description");
//        design1.setImageUrl("image.com");
//        Design design2 = new Design();
//
//        design2.setTitle("test2");
//        design2.setDescription("test2Description");
//        design2.setImageUrl("image.com");
//        Design designs = designRepository.save(design2);
//        System.out.println(designs);
//        mockMvc.perform(get("/designs/2"))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Design successfully retrieved")));
//    }
}
