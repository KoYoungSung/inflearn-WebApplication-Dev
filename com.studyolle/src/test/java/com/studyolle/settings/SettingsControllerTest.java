package com.studyolle.settings;

import com.studyolle.WithAccount;
import com.studyolle.account.AccountRepository;
import com.studyolle.account.AccountService;
import com.studyolle.domain.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.studyolle.settings.SettingsController.SETTINGS_PROFILE_URL;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SettingsControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired AccountRepository accountRepository;

    @AfterEach
    void afterEach() {
        accountRepository.deleteAll();
    }

    @Test
    @DisplayName("프로필 수정 폼")
    @WithAccount("ko")
    void updateProfileForm() throws Exception {

        String bio = "짧은 소개를 수정";
        mockMvc.perform(get(SETTINGS_PROFILE_URL))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("profile"));

    }

    @Test
    @DisplayName("프로필 수정 - 입력 값 정상")
    @WithAccount("ko")
    void updateProfile() throws Exception {

        String bio = "짧은 소개를 수정";
        mockMvc.perform(post(SETTINGS_PROFILE_URL)
                .param("bio", bio)
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(SETTINGS_PROFILE_URL))
                .andExpect(flash().attributeExists("message"));

        Account ko = accountRepository.findByNickname("ko");
        assertEquals(bio, ko.getBio());

    }

    @Test
    @DisplayName("프로필 수정 - 입력 값 오류")
    @WithAccount("ko")
    void updateProfile_error() throws Exception {

        String bio = "짧은 소개 글 35자 넘어감짧은 소개 글 35자 넘어감짧은 소개 글 35자 넘어감짧은 소개 글 35자 넘어감짧은 소개 글 35자 넘어감짧은 소개 글 35자 넘어감";
        mockMvc.perform(post(SETTINGS_PROFILE_URL)
                .param("bio", bio)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name(SettingsController.SETTINGS_PROFILE_VIEW_NAME))
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("profile"))
                .andExpect(model().hasErrors());

        Account ko = accountRepository.findByNickname("ko");
        assertNull(ko.getBio());

    }
}