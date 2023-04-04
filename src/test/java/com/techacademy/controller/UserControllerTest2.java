

package com.techacademy.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.techacademy.TutorialDevelopApplication;
import com.techacademy.entity.User;

@SpringBootTest(classes = TutorialDevelopApplication.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class UserControllerTest2 {
	private MockMvc mockMvc;

	private final WebApplicationContext webApplicationContext;

	UserControllerTest2(WebApplicationContext context) {
		this.webApplicationContext = context;
	}

	@BeforeEach
	void beforeEach() {
		// Spring Securityを有効にする
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.apply(springSecurity()).build();
	}

	@Test
	@DisplayName("getuser")
	@WithMockUser
	void testGetUser() throws Exception {
		// HTTPリクエストに対するレスポンスの検証
		MvcResult result = mockMvc.perform(get("/user/list/")) // URLにアクセス
			.andExpect(status().isOk()) // ステータスを確認
			.andExpect(model().attributeExists("userlist")) // Modelの内容を確認
			.andExpect(model().hasNoErrors()) // Modelのエラー有無の確認
			.andExpect(view().name("user/list")) // viewの確認
			.andReturn(); // 内容の取得


		//userリストから取得したUserのインスタンスを変数に格納し、そこからgetNameやgetIdを行う
		// userlistの検証
		// Modelからuserlistを取り出す
		// ユーザー情報の1件目
			List<User> ul = (List<User>) result.getModelAndView().getModel().get("userlist");
			User u = ul.get(0);
			assertEquals(u.getId(),1 );
			assertEquals(u.getName(), "キラメキ太郎");


		// ユーザー情報の2件目
			u = ul.get(1);
			assertEquals(u.getId(),2 );
			assertEquals(u.getName(), "キラメキ次郎");


		// ユーザー情報の3件目
			u = ul.get(2);
			assertEquals(u.getId(),3 );
			assertEquals(u.getName(), "キラメキ花子");
	}
	}
