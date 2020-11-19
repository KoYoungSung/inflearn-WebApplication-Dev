package com.studyolle.settings;

import com.studyolle.domain.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Notifications {
    private boolean studyCreateByEmail;
    private boolean studyCreateByWeb;
    private boolean studyEnrollmentResultByEmail;
    private boolean studyEnrollmentResultByWeb;
    private boolean studyUpdatedByEmail;
    private boolean studyUpdatedByWeb;


    public Notifications(Account account) {
        this.studyCreateByEmail = account.isStudyCreateByEmail();
        this.studyCreateByWeb = account.isStudyCreateByWeb();
        this.studyEnrollmentResultByEmail = account.isStudyEnrollmentResultByEmail();
        this.studyEnrollmentResultByWeb = account.isStudyEnrollmentResultByWeb();
        this.studyUpdatedByEmail = account.isStudyCreateByEmail();
        this.studyUpdatedByWeb = account.isStudyCreateByWeb();
    }
}
