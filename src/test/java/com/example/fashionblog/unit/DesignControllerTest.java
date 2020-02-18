package com.example.fashionblog.unit;

import com.example.fashionblog.controllers.DesignController;
import com.example.fashionblog.dto.DesignRequestDTO;
import com.example.fashionblog.models.Design;
import com.example.fashionblog.repositories.DesignRepository;
import com.example.fashionblog.responses.Response;
import com.example.fashionblog.services.designs.DesignService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class DesignControllerTest {

    @Mock
    private DesignService designService;

    @Mock
    private DesignController designController;

    @MockBean
    private static DesignRepository designRepository;

    @Mock
    private ModelMapper modelMapper;

    //private Logger logger = LoggerFactory.getLogger(Design.class);

    @Test
    public void it_should_return_response_created() {
        DesignRequestDTO newTestDesign = new DesignRequestDTO("this is a title", "this is a description", "imageUrl.com");
        Design testDesign = new Design();
        testDesign.setTitle("this is a title");
        testDesign.setDescription("this is a description");
        testDesign.setImageUrl("imageUrl.com");
        testDesign = designRepository.save(testDesign);
        when(designService.postDesign(modelMapper.map(newTestDesign, Design.class))).thenReturn(testDesign);
        Response<Design> result = new Response<>(HttpStatus.CREATED);
        result.setData(testDesign);
        assertThat(result.getStatus(), is(HttpStatus.CREATED));
        assertThat(result.getData(), is(testDesign));
    }

    @Test
    public void it_should_get_all_designs() {
        Design design = new Design();
        design.setTitle("title1");
        design.setDescription("description1");
        design.setImageUrl("imageUrl1");
        Response<List<Design>> result = new Response<>(HttpStatus.OK);
        result.setData(Collections.singletonList(design));
        result.setMessage("All designs successfully retrieved");
        when(designController.getAllDesigns(0, 10)).thenReturn(new ResponseEntity<>(result, HttpStatus.OK));
        assertThat(result.getStatus(), is(HttpStatus.OK));
    }

    @Test
    public void it_should_get_a_design() {
        Design testDesign = new Design();
        testDesign.setTitle("this is a title");
        testDesign.setDescription("this is a description");
        testDesign.setImageUrl("imageUrl.com");
        designRepository.save(testDesign);
        Design design = designService.getADesign(1);
        Response<Design> result = new Response<>(HttpStatus.OK);
        result.setData(design);
        result.setMessage("Design successfully retrieved");
        when(designController.getADesign(1)).thenReturn(new ResponseEntity<>(result, HttpStatus.OK));
        assertThat(result.getStatus(), is(HttpStatus.OK));
    }

    @Test
    public void it_should_edit_a_design() {
        Design testDesign = new Design();
        testDesign.setTitle("this is a title");
        testDesign.setDescription("this is a description");
        testDesign.setImageUrl("imageUrl.com");
        designRepository.save(testDesign);
        DesignRequestDTO editDesign = new DesignRequestDTO("title1", "description1", "image.com");
        when(designController.editADesign(1, editDesign)).thenReturn(new ResponseEntity<>(HttpStatus.ACCEPTED));
    }

    @Test
    public void it_should_delete_a_design() {
        Design testDesign = new Design();
        testDesign.setTitle("this is a title");
        testDesign.setDescription("this is a description");
        testDesign.setImageUrl("imageUrl.com");
        designRepository.save(testDesign);
        when(designController.deleteADesign(1)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
    }

    @Test
    public void it_should_like_a_design() {
        Design testDesign = new Design();
        testDesign.setTitle("this is a title");
        testDesign.setDescription("this is a description");
        testDesign.setImageUrl("imageUrl.com");
        designRepository.save(testDesign);
        when(designController.likeADesign(1)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
    }
}
