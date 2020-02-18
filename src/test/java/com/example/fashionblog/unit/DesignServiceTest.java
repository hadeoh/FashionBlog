package com.example.fashionblog.unit;

import com.example.fashionblog.models.Design;
import com.example.fashionblog.repositories.DesignRepository;
import com.example.fashionblog.services.designs.DesignServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class DesignServiceTest {

    @MockBean
    private DesignRepository designRepository;

    @Mock
    private DesignServiceImpl designService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private List<Design> designs() {
        Design design1 = new Design();
        design1.setId(1);
        design1.setTitle("designServiceTitle1");
        design1.setDescription("designServiceDescription1");
        design1.setImageUrl("designServiceImageUrl1");
        Design design2 = new Design();
        design1.setId(2);
        design2.setTitle("designServiceTitle2");
        design2.setDescription("designServiceDescription2");
        design2.setImageUrl("designServiceImageUrl2");
        return Arrays.asList(design1, design2);
    }

    @Test
    public void it_should_return_when_a_design_is_posted() {
        Design design = designs().get(0);
        when(designRepository.save(any())).thenReturn(design);
        assertThat(designService.postDesign(design), is(design));
        assertThat(design.getDescription(), is("designServiceDescription1"));
        verify(designRepository, times(1)).save(design);
    }

    @Test
    public void it_should_get_a_design() {
        Design design = designs().get(0);
        designRepository.save(design);
        when(designService.getADesign(any())).thenReturn(design);
        assertThat(design.getId(), is(2));
    }

    @Test
    public void it_should_get_all_designs() {
        List<Design> design = designs();
        when(designService.getAllDesigns(0, 10)).thenReturn(design);
    }

    @Test
    public void it_should_edit_a_design() {
        Design design = designs().get(0);
        when(designService.editADesign(design.getId(), design)).thenReturn(design);
    }

    @Test
    public void it_should_delete_a_design() {
        Design design = designs().get(0);
        verify(designService, times(0)).deleteADesign(design.getId());
    }

    @Test
    public void it_should_like_a_design() {
        Design design = designs().get(0);
        when(designService.likeADesign(design.getId())).thenReturn(design);
    }
}
