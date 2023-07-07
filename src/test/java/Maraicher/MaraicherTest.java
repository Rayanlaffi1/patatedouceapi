package Maraicher;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.rlaffi.spring.patatedouce.controllers.UtilisateurController;
import dev.rlaffi.spring.patatedouce.entities.Maraicher;
import dev.rlaffi.spring.patatedouce.entities.Utilisateur;
import dev.rlaffi.spring.patatedouce.services.UtilisateurService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = UtilisateurController.class)
@AutoConfigureMockMvc(addFilters = false)
@EnableWebMvc
public class MaraicherTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UtilisateurController utilisateurController;

    @Test
    public void createMaraicher() throws Exception {
        String jsonPayload = "{\"id\":0,\"prenom\":\"firstName4\",\"nom\":\"lastName4\",\"email\":\"email4@mail.com\"}";
        mvc.perform(
                put("http://localhost:8080/utilisateur/creation/maraicher/{mdp}", "21212")
                    .content(jsonPayload)
                        .locale(Locale.FRANCE)
                        .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8")
            )
            .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""))
                .andDo(MockMvcResultHandlers.print());
    }

}
