//package com.software.rateit;
//
//import com.software.rateit.Entity.User;
//import com.software.rateit.controllers.UserController;
//import com.software.rateit.repositories.UserRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.core.Is.is;
//import static org.hamcrest.core.IsNull.nullValue;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//public class UserControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserController userController;
//
//    @Before
//    public void init(){
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders
//                .standaloneSetup(userController)
//                .build();
//    }
//
//    @Test
//    public void test_get_all_success() throws Exception {
//        List<User> users = Arrays.asList(
//                new User(1L, "novy", "spnr57@wp.pl",  "*******", 1050, "novice", true, "http://ale.pl/123", null, null),
//                new User(2L, "adam22", "andre123@gmail.com",  "***********", 1000000, "advanced", false, "http://hehuehue.pl/333", null, null));
//        when(userRepository.findAll()).thenReturn(users);
//        mockMvc.perform(get("/api/users"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].nick", is("novy")))
//                .andExpect(jsonPath("$[0].email", is("spnr57@wp.pl")))
//                .andExpect(jsonPath("$[0].password", is("*******")))
//                .andExpect(jsonPath("$[0].score", is(1050)))
//                .andExpect(jsonPath("$[0].badges", is("novice")))
//                .andExpect(jsonPath("$[0].active", is(true)))
//                .andExpect(jsonPath("$[0].photoURL", is("http://ale.pl/123")))
//                .andExpect(jsonPath("$[0].userscd", is(nullValue())))
//                .andExpect(jsonPath("$[0].roles", is(nullValue())))
//                .andExpect(jsonPath("$[1].id", is(2)))
//                .andExpect(jsonPath("$[1].nick", is("adam22")))
//                .andExpect(jsonPath("$[1].email", is("andre123@gmail.com")))
//                .andExpect(jsonPath("$[1].password", is("***********")))
//                .andExpect(jsonPath("$[1].score", is(1000000)))
//                .andExpect(jsonPath("$[1].badges", is("advanced")))
//                .andExpect(jsonPath("$[1].active", is(false)))
//                .andExpect(jsonPath("$[1].photoURL", is("http://hehuehue.pl/333")))
//                .andExpect(jsonPath("$[1].userscd", is(nullValue())))
//                .andExpect(jsonPath("$[1].roles", is(nullValue())));
//        verify(userRepository, times(1)).findAll();
//        verifyNoMoreInteractions(userRepository);
//    }
//    @Test
//    public void test_get_by_id_success() throws Exception {
//        User user1 = new User(1L, "novy", "spnr57@wp.pl",  "*******", 1050, "novice", true, "http://ale.pl/123", null, null);
//        Optional<User> user1o = Optional.of(user1);
//        when(userRepository.findById(1L)).thenReturn(user1o);
//
//        mockMvc.perform(get("/api/users/{id}", 1))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.nick", is("novy")))
//                .andExpect(jsonPath("$.email", is("spnr57@wp.pl")))
//                .andExpect(jsonPath("$.password", is("*******")))
//                .andExpect(jsonPath("$.score", is(1050)))
//                .andExpect(jsonPath("$.badges", is("novice")))
//                .andExpect(jsonPath("$.active", is(true)))
//                .andExpect(jsonPath("$.photoURL", is("http://ale.pl/123")))
//                .andExpect(jsonPath("$.userscd", is(nullValue())))
//                .andExpect(jsonPath("$.roles", is(nullValue())));
//
//        verify(userRepository, times(1)).findById(1L);
//        verifyNoMoreInteractions(userRepository);
//    }
//    @Test
//    public void test_get_by_id_fail_404_not_found() throws Exception {
//
//        when(userRepository.findById(1L)).thenReturn(Optional.empty());
//
//        mockMvc.perform(get("api/users/{id}", 1))
//                .andExpect(status().isNotFound());
//
//        verify(userRepository, times(0)).findById(1L);
//        verifyNoMoreInteractions(userRepository);
//    }
//}