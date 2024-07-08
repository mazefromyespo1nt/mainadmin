/*package com.service.accesspointv2.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.accesspointv2.controller.UserController;
import com.service.accesspointv2.entity.User;
import com.service.accesspointv2.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllUsers() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setNombre("Juan Perez");
        user.setPassword("12345");
        user.setDireccion("123 Calle Principal");
        user.setFechaRegistro(LocalDateTime.now());
        user.setFechaModificacion(LocalDateTime.now());
        user.setStatus("activo");
        user.setEncargado("Encargado A");
        user.setNombreEncargado("Encargado B");
        user.setEmail("juan.perez@example.com");
        user.setTelefono("1234567890");

        when(userService.findAll()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/api/usuario"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nombre").value("Juan Perez"));
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setNombre("Juan Perez");
        user.setPassword("12345");
        user.setDireccion("123 Calle Principal");
        user.setFechaRegistro(LocalDateTime.now());
        user.setFechaModificacion(LocalDateTime.now());
        user.setStatus("activo");
        user.setEncargado("Encargado A");
        user.setNombreEncargado("Encargado B");
        user.setEmail("juan.perez@example.com");
        user.setTelefono("1234567890");

        when(userService.findById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/usuario/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Juan Perez"));
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setNombre("Juan Perez");
        user.setPassword("12345");
        user.setDireccion("123 Calle Principal");
        user.setFechaRegistro(LocalDateTime.now());
        user.setFechaModificacion(LocalDateTime.now());
        user.setStatus("activo");
        user.setEncargado("Encargado A");
        user.setNombreEncargado("Encargado B");
        user.setEmail("juan.perez@example.com");
        user.setTelefono("1234567890");

        when(userService.save(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Juan Perez"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setNombre("Juan Perez Actualizado");
        user.setPassword("54321");
        user.setDireccion("123 Calle Actualizada");
        user.setFechaRegistro(LocalDateTime.now());
        user.setFechaModificacion(LocalDateTime.now());
        user.setStatus("activo");
        user.setEncargado("Encargado A");
        user.setNombreEncargado("Encargado B");
        user.setEmail("juan.perez.actualizado@example.com");
        user.setTelefono("0987654321");

        when(userService.findById(1L)).thenReturn(Optional.of(user));
        when(userService.save(any(User.class))).thenReturn(user);

        mockMvc.perform(put("/api/usuario/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan Perez Actualizado"));
    }

    @Test
    public void testDeleteUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setNombre("Juan Perez");

        when(userService.findById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(delete("/api/usuario/1"))
                .andExpect(status().isNoContent());
    }
}*/