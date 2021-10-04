package com.xantrix.webapp.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import com.xantrix.webapp.Application;
import com.xantrix.webapp.entity.User;
import com.xantrix.webapp.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
 
@ContextConfiguration(classes = Application.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserControllerTest {
	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;

    private MockMvc mockMvc;

	@BeforeEach
	public void setup() throws JSONException, IOException{
		DefaultMockMvcBuilder webAppContextSetup = MockMvcBuilders.webAppContextSetup(wac);
		this.mockMvc = webAppContextSetup.build();	
	}
	
	String jsonData =  
			"{\n" + 
			"    \"userId\": \"Nicola\",\n" + 
			"    \"password\": \"123Stella\",\n" + 
			"    \"attivo\": \"Si\",\n" + 
			"    \"ruoli\": [\n" + 
			"            \"USER\"\n" + 
			"        ]\n" + 
			"}";
	
	@Test
	@Order(1)
	public void testInsUtente1() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonData)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(print());
	}

	@Test
	@Order(2)
	public void testListUserByUserId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/Nicola")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				  
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.username").exists())
				.andExpect(jsonPath("$.username").value("Nicola"))
				.andExpect(jsonPath("$.password").exists())
				.andExpect(jsonPath("$.attivo").exists())
				.andExpect(jsonPath("$.attivo").value("Si"))
				  
				.andExpect(jsonPath("$.ruoli[0]").exists())
				.andExpect(jsonPath("$.ruoli[0]").value("USER")) 
				.andDo(print());
		
				User userFetched = userRepository.findByUsername("Nicola");
				assertThat( passwordEncoder.matches("123Stella", userFetched.getPassword()))
					.isEqualTo(Boolean.TRUE);
	}
	
	String jsonData2 = 
			"{\n" + 
			"    \"userId\": \"Admin\",\n" + 
			"    \"password\": \"VerySecretPwd\",\n" + 
			"    \"attivo\": \"Si\",\n" + 
			"    \"ruoli\": [\n" + 
			"            \"USER\",\n" + 
			"            \"ADMIN\"\n" + 
			"        ]\n" + 
			"}";
	
	@Test
	@Order(3)
	public void testInsUtente2() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonData2)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(print());
	}
	
	String jsonDataUsers = 
			"[\n" + 
			"	{\n" + 
			"	    \"userId\": \"Nicola\",\n" + 
			"	    \"password\": \"123Stella\",\n" + 
			"	    \"attivo\": \"Si\",\n" + 
			"	    \"ruoli\": [\n" + 
			"		    \"USER\"\n" + 
			"		]\n" + 
			"	},\n" + 
			"	{\n" + 
			"	    \"userId\": \"Admin\",\n" + 
			"	    \"password\": \"VerySecretPwd\",\n" + 
			"	    \"attivo\": \"Si\",\n" + 
			"	    \"ruoli\": [\n" + 
			"		    \"USER\",\n" + 
			"		    \"ADMIN\"\n" + 
			"		]\n" + 
			"	}\n" + 
			"]";
	
	@Test
	@Order(4)
	public void testGetAllUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				 //UTENTE 1
				.andExpect(jsonPath("$[0].id").exists())
				.andExpect(jsonPath("$[0].username").exists())
				.andExpect(jsonPath("$[0].username").value("Nicola"))
				.andExpect(jsonPath("$[0].password").exists())
				.andExpect(jsonPath("$[0].attivo").exists())
				.andExpect(jsonPath("$[0].attivo").value("Si"))
				.andExpect(jsonPath("$[0].ruoli[0]").exists())
				.andExpect(jsonPath("$[0].ruoli[0]").value("USER")) 
				 //UTENTE 2
				.andExpect(jsonPath("$[1].id").exists())
				.andExpect(jsonPath("$[1].username").exists())
				.andExpect(jsonPath("$[1].username").value("Admin"))
				.andExpect(jsonPath("$[1].password").exists())
				.andExpect(jsonPath("$[1].attivo").exists())
				.andExpect(jsonPath("$[1].attivo").value("Si"))
				.andExpect(jsonPath("$[1].ruoli[0]").exists())
				.andExpect(jsonPath("$[1].ruoli[0]").value("USER")) 
				.andExpect(jsonPath("$[1].ruoli[1]").exists())
				.andExpect(jsonPath("$[1].ruoli[1]").value("ADMIN")) 
				.andReturn();
		
				User userFetched = userRepository.findByUsername("Admin");
				assertThat(passwordEncoder.matches("VerySecretPwd", userFetched.getPassword()))
					.isEqualTo(Boolean.TRUE);
	}
	
	@Test
	@Order(5)
	public void testDelUtente1() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/Nicola")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value("200 OK"))
				.andExpect(jsonPath("$.message").value("Eliminazione Utente Nicola Eseguita Con Successo"))
				.andDo(print());
	}
	
	@Test
	@Order(6)
	public void testDelUtente2() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/Admin")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value("200 OK"))
				.andExpect(jsonPath("$.message").value("Eliminazione Utente Admin Eseguita Con Successo"))
				.andDo(print());
	}
}