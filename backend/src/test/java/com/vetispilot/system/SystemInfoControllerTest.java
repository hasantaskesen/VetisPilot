package com.vetispilot.system;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Tests only the web layer (no database needed):
 * does GET /api/v1/system/info answer with the expected JSON?
 */
@WebMvcTest(SystemInfoController.class)
@TestPropertySource(properties = "vetispilot.version=test")
class SystemInfoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void infoReturnsNameLanguagesAndChannels() throws Exception {
        mvc.perform(get("/api/v1/system/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("VetisPilot"))
                .andExpect(jsonPath("$.version").value("test"))
                .andExpect(jsonPath("$.languages.length()").value(3))
                .andExpect(jsonPath("$.channels[0]").value("HTGO"))
                .andExpect(jsonPath("$.channels[1]").value("GK"));
    }
}
