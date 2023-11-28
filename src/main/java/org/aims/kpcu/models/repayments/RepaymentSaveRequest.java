package org.aims.kpcu.models.repayments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepaymentSaveRequest {

    @NotEmpty(message = "no access Token")
    private String accessToken;

    @NotEmpty(message = "season cannot be empty")
    private String season;

    @NotEmpty(message = "payment cannot be empty")
    private String payment;

    List<MemberRepaymentSaveRequest> repayments;

}
