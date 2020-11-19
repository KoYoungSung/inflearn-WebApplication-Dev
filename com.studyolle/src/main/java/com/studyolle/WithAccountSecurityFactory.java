package com.studyolle;

import com.studyolle.account.AccountService;
import com.studyolle.account.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.List;

@RequiredArgsConstructor
public class WithAccountSecurityFactory implements WithSecurityContextFactory<WithAccount> {

    private final AccountService accountService;

    @Override
    public SecurityContext createSecurityContext(WithAccount withAccount) {
        String nickname = withAccount.value();

        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setNickname(nickname);
        signUpForm.setPassword("1234578");
        signUpForm.setEmail(nickname+"@email.com");
        accountService.processNewAccount(signUpForm);

        UserDetails principal = accountService.loadUserByUsername(nickname);
        Authentication aUthentication = new UsernamePasswordAuthenticationToken(principal,
                principal.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(aUthentication);
        return context;
    }
}
