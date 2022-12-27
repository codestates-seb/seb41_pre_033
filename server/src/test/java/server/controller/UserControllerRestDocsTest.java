package server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import server.auth.userdetails.UsersDetailsService;
import server.user.controller.UserController;
import server.user.dto.UserDto;
import server.user.entity.User;
import server.user.mapper.UserMapper;
import server.user.service.UserService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class UserControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void signUpTest() throws Exception {
        // given
        UserDto.Post post = new UserDto.Post("chaning", "ggammancj@gmail.com", "1111");
        String content = gson.toJson(post);

        UserDto.Response responseDto = new UserDto.Response(
                1L,
                "chaning",
                "ggammancj@gmail.com",
                "",
                0,
                "",
                "",
                "");

        given(userMapper.userPostToUser(Mockito.any(UserDto.Post.class))).willReturn(new User());

        given(userService.createUser(Mockito.any(User.class))).willReturn(new User());

        given(userMapper.userToUserResponse(Mockito.any(User.class))).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.post("/users/sign-up")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.nickname").value(post.getNickname()))
                .andDo(document("sign-up",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("닉네임"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data.country").type(JsonFieldType.STRING).description("국가"),
                                        fieldWithPath("data.reputation").type(JsonFieldType.NUMBER).description("평점"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("자기소개 제목"),
                                        fieldWithPath("data.introduction").type(JsonFieldType.STRING).description("자기소개"),
                                        fieldWithPath("data.link").type(JsonFieldType.STRING).description("링크")
                                )
                        )));

//        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void logInTest() throws Exception {
        // given
        HashMap<String, String> loginForm = new HashMap<>();
        loginForm.put("username", "ggammancj@gmail.com");
        loginForm.put("password", "1111");

        // when
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.post("/users/login")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(loginForm))
                                .with(csrf())
                );

        ResultActions result = actions
                .andExpect(status().isOk())
                .andDo(document("login",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("username").type(JsonFieldType.STRING).description("로그인 ID(이메일)"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("로그인 비밀번호")
                                ),
                                responseFields(
                                        fieldWithPath("access_token").type(JsonFieldType.STRING).description("Access Token"),
                                        fieldWithPath("refresh_token").type(JsonFieldType.STRING).description("Refresh Token")
                                )
                        )
                );
    }

    @Test
    public void editUserTest() throws Exception{
        long userId = 1L;
        UserDto.Patch patch = new UserDto.Patch(
                1L,
                "chaning",
                "France",
//                0,
                "I'm find thank you and you?",
                "King of Korea",
                "https://chaning49.tistory.com/");
        String content = gson.toJson(patch);

        UserDto.Response responseDto = new UserDto.Response(
                1L,
                "chaning",
                "ggammancj@gmail.com",
                "France",
                0,
                "I'm find thank you and you?",
                "King of Korea",
                "https://chaning49.tistory.com/");

        given(userMapper.userPatchToUser(Mockito.any(UserDto.Patch.class))).willReturn(new User());

        given(userService.updateUser(Mockito.any(User.class))).willReturn(new User());

        given(userMapper.userToUserResponse(Mockito.any(User.class))).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.patch("/users/edit/{user-id}", userId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        ResultActions result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.nickname").value(patch.getNickname()))
                .andExpect(jsonPath("$.data.country").value(patch.getCountry()))
                .andExpect(jsonPath("$.data.introduction").value(patch.getIntroduction()))
                .andExpect(jsonPath("$.data.title").value(patch.getTitle()))
                .andExpect(jsonPath("$.data.link").value(patch.getLink()))
                .andDo(document("edit",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                Arrays.asList(parameterWithName("user-id").description("회원 id"))
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("userId").type(JsonFieldType.NUMBER).description("회원 id").ignored(),
                                        fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임").optional(),
                                        fieldWithPath("country").type(JsonFieldType.STRING).description("국가").optional(),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("자기소개 제목").optional(),
                                        fieldWithPath("introduction").type(JsonFieldType.STRING).description("자기소개").optional(),
                                        fieldWithPath("link").type(JsonFieldType.STRING).description("링크").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터").optional(),
                                        fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("닉네임"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data.country").type(JsonFieldType.STRING).description("국가"),
                                        fieldWithPath("data.reputation").type(JsonFieldType.NUMBER).description("평점"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("자기소개 제목"),
                                        fieldWithPath("data.introduction").type(JsonFieldType.STRING).description("자기소개"),
                                        fieldWithPath("data.link").type(JsonFieldType.STRING).description("링크")
                                )
                        )));

//        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void getUserTest() throws Exception {
        // given
        long userId = 1L;

        UserDto.Response responseDto = new UserDto.Response(
                1L,
                "chaning",
                "ggammancj@gmail.com",
                "France",
                0,
                "I'm find thank you and you?",
                "King of Korea",
                "https://chaning49.tistory.com/");

        given(userService.findUser(Mockito.anyLong())).willReturn(new User());

        given(userMapper.userToUserResponse(Mockito.any(User.class))).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/users/{user-id}", userId)
                                .accept(MediaType.APPLICATION_JSON)
                );

        // then
        ResultActions result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userId").value(userId))
                .andDo(document("get-user",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                Arrays.asList(parameterWithName("user-id").description("회원 id"))
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터").optional(),
                                        fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("닉네임"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data.country").type(JsonFieldType.STRING).description("국가"),
                                        fieldWithPath("data.reputation").type(JsonFieldType.NUMBER).description("평점"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("자기소개 제목"),
                                        fieldWithPath("data.introduction").type(JsonFieldType.STRING).description("자기소개"),
                                        fieldWithPath("data.link").type(JsonFieldType.STRING).description("링크")
                                )
                        )));
    }

    @Test
    public void getUsersTest() throws Exception {
        // given
        User user1 = new User();
        user1.setUserId(1L);
        user1.setNickname("chaning");
        user1.setEmail("ggammancj@gmail.com");
        user1.setCountry("France");
        user1.setReputation(0);
        user1.setTitle("I'm find thank you and you?");
        user1.setIntroduction("King of Korea");
        user1.setLink("https://chaning49.tistory.com/");

        User user2 = new User();
        user2.setUserId(2L);
        user2.setNickname("messi");
        user2.setEmail("leomessi10@gmail.com");
        user2.setCountry("Argentina");
        user2.setReputation(0);
        user2.setTitle("Winter is coming soon!");
        user2.setIntroduction("Wordlcup Champion");
        user2.setLink("https://messi.com/");

        String content = gson.toJson(List.of(user1, user2));

        Page<User> pageUsers =
                new PageImpl<>(List.of(user1, user2),
                        PageRequest.of(0, 36, Sort.by("nickname").ascending()), 2);

        UserDto.Response responseDto1 = new UserDto.Response(
                1L,
                "chaning",
                "ggammancj@gmail.com",
                "France",
                0,
                "I'm find thank you and you?",
                "King of Korea",
                "https://chaning49.tistory.com/");

        UserDto.Response responseDto2 = new UserDto.Response(
                2L,
                "messi",
                "leomessi10@gmail.com",
                "Argentina",
                0,
                "Winter is coming soon!",
                "Wordlcup Champion",
                "https://messi.com/");

        List<UserDto.Response> responseDtos = new ArrayList<>(List.of(responseDto1, responseDto2));

        given(userService.findUsers(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString())).willReturn(pageUsers);
        given(userMapper.usersToUserResponses(Mockito.anyList())).willReturn(responseDtos);

        String page = "1";
        String size = "36";
        String tab = "abc";
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);
        queryParams.add("tab", tab);

        // when
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/users")
                                .params(queryParams)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        ResultActions result = actions
                .andExpect(status().isOk())
                .andDo(document("get-users",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                List.of(
                                        parameterWithName("page").description("페이지 번호"),
                                        parameterWithName("size").description("페이지 사이즈").ignored(),
                                        parameterWithName("tab").description("정렬 기준")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터").optional(),
                                        fieldWithPath("data[].userId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].nickname").type(JsonFieldType.STRING).description("닉네임"),
                                        fieldWithPath("data[].email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data[].country").type(JsonFieldType.STRING).description("국가"),
                                        fieldWithPath("data[].reputation").type(JsonFieldType.NUMBER).description("평점"),
                                        fieldWithPath("data[].title").type(JsonFieldType.STRING).description("자기소개 제목"),
                                        fieldWithPath("data[].introduction").type(JsonFieldType.STRING).description("자기소개"),
                                        fieldWithPath("data[].link").type(JsonFieldType.STRING).description("링크"),

                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보").optional(),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지 정보").optional(),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("사이즈 정보").optional(),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 조회 건 수").optional(),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수").optional()
                                )
                        )));

    }

    @Test
    public void deleteUserTest() throws Exception {
        // given
        long userId = 1L;
        doNothing().when(userService).deleteUser(Mockito.anyLong());

        // when
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders
                                .delete("/users/delete/{user-id}", userId));

        // then
        actions.andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                    Arrays.asList(parameterWithName("user-id").description("회원 id"))
                                )
                        )
                );
    }
}
