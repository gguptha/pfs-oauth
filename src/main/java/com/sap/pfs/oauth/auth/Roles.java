package com.sap.pfs.oauth.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  Roles
{
    PROSPECT("TR0110", "Prospect"),
    APPRAISAL_OFFICER("ZLM013", "Appraisal Officer"),
    CO_APPRAISAL_OFFICER("ZLM010", "Co-Appraisal Officer"),
    PFS_IT_TEAM("ZLM023", "PFS IT Team"),
    PROMOTER("ZLM001", "Promoter"),
    MAIN_LOAN_PARTNER("TR0100", "Main Loan Partner"),
    CO_BORROWER("TR010", "Co-Borrower");

    String abbreviation;
    String description;

    Roles(String abbreviation,String description)
    {
        this.abbreviation = abbreviation;
        this.description = description;
    }
}
