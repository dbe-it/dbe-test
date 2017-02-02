package spittr;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import spittr.controller.SpitterController;
import spittr.data.Spitter;
import spittr.data.repository.SpitterRepositoryJPA;

public class SpitterControllerTest {

    @Mock
    SpitterRepositoryJPA mockRepository = mock(SpitterRepositoryJPA.class);

    @Test
    public void shouldShowRegistration() throws Exception {
        SpitterController controller = new SpitterController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/spitter/register")).andExpect(view().name("registerForm"));
    }

    @Test
    public void shouldProcessRegistration() throws Exception {

        Spitter unsaved = new Spitter(Spitter.DEFAULT_FIRSTNAME, Spitter.DEFAULT_LASTNAME, Spitter.DEFAULT_USERNAME,
            Spitter.DEFAULT_PASSWORD);
        Spitter saved = new Spitter(Spitter.DEFAULT_FIRSTNAME, Spitter.DEFAULT_LASTNAME, Spitter.DEFAULT_USERNAME,
            Spitter.DEFAULT_PASSWORD);
        saved.setId(999L);
        when(mockRepository.save(unsaved)).thenReturn(saved);
        SpitterController controller = new SpitterController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(post("/spitter/register")
            .param("firstname", Spitter.DEFAULT_FIRSTNAME)
            .param("lastname", Spitter.DEFAULT_LASTNAME)
            .param("username", Spitter.DEFAULT_USERNAME)
            .param("password", Spitter.DEFAULT_PASSWORD)).andExpect(redirectedUrl("/spitter/jbauer"));
        verify(mockRepository, atLeastOnce()).save(unsaved);
    }

}
