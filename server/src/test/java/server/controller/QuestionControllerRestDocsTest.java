//package server.controller;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import server.question.controller.QuestionController;
//import server.question.dto.QuestionDto;
//import server.question.dto.QuestionTagDto;
//import server.question.dto.QuestionTagResponseDto;
//import server.question.entity.Question;
//import server.question.entity.QuestionTag;
//import server.question.mapper.QuestionMapper;
//import server.question.service.QuestionService;
//import com.google.gson.Gson;
//import server.tag.entity.Tag;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(QuestionController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//public class QuestionControllerRestDocsTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private QuestionService questionService;
//
//    @Autowired
//    private QuestionMapper questionMapper;
//
//    @Autowired
//    private Gson gson;
//
//    @Test
//    public void postQuestion() throws Exception {
//        List<QuestionTagDto> questionTagDtos = new ArrayList<>();
//        QuestionTagDto questionTagDto = new QuestionTagDto("자바");
//        QuestionTagDto questionTagDto2 = new QuestionTagDto("파이썬");
//        questionTagDtos.add(questionTagDto);
//        questionTagDtos.add(questionTagDto2);
//        QuestionDto.PostQuestion postQuestion = new QuestionDto.PostQuestion(1L, "타이틀", "바디", questionTagDtos);
//        String content = gson.toJson(postQuestion);
//
//        Question question = new Question();
//        Tag tag = new Tag("자바", "자바의 설명", null, new ArrayList<QuestionTag>());
//        Tag tag2 = new Tag("파이썬", "파이썬의 설명", null, new ArrayList<QuestionTag>());
//
//        QuestionTagResponseDto questionTagResponseDto = QuestionTagResponseDto.builder().tagName("자바").explanation("자바의 설명").build();
//        QuestionTagResponseDto questionTagResponseDto2 = QuestionTagResponseDto.builder().tagName("파이썬").explanation("파이썬의 설명").build();
//        List<QuestionTagResponseDto> questionTagResponseDtos = new ArrayList<>();
//        questionTagResponseDtos.add(questionTagResponseDto);
//        questionTagResponseDtos.add(questionTagResponseDto2);
//        QuestionDto.Response response =
//                new QuestionDto.Response(1L, 1L, "닉네임", "타이틀", "바디", 0, LocalDateTime.now(), 0, 0, questionTagResponseDtos, new ArrayList<>());
//
//        ResultActions actions =
//                mockMvc.perform(
//                        post("/questions")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        actions
//                .andExpect(status().isCreated())
//                .andDo(document("post-question",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저아이디"),
//                                        fieldWithPath("title").type(JsonFieldType.STRING).description("타이틀"),
//                                        fieldWithPath("body").type(JsonFieldType.STRING).description("바디"),
//                                        fieldWithPath("questionTags").type(JsonFieldType.OBJECT).description("태그들")
//                                )
//                        ),
//                        responseFields(
//                                List.of(
////                                        Response(1L,1L,"닉네임", "타이틀", "바디", 0, LocalDateTime.now(), 0,0, questionTagResponseDtos,new ArrayList<>());
//                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문아이디"),
//                                        fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저아이디"),
//                                        fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
//                                        fieldWithPath("title").type(JsonFieldType.STRING).description("타이틀"),
//                                        fieldWithPath("body").type(JsonFieldType.STRING).description("바디"),
//                                        fieldWithPath("bounty").type(JsonFieldType.NUMBER).description("바운티"),
//                                        fieldWithPath("created").type(JsonFieldType.OBJECT).description("작성일"),
//                                        fieldWithPath("viewed").type(JsonFieldType.NUMBER).description("조회수"),
//                                        fieldWithPath("vote").type(JsonFieldType.NUMBER).description("투표수"),
//                                        fieldWithPath("questionTags").type(JsonFieldType.OBJECT).description("태그들"),
//                                        fieldWithPath("answers").type(JsonFieldType.OBJECT).description("답변들")
//                                )
//                        )
//                ));
//    }
//}
