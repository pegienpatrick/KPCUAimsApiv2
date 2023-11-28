package org.aims.kpcu.models.repayments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRepaymentSaveRequest {

    @NotNull(message = "Member no cannot be null")
    private Long memberno;

    @NotNull(message = "Amount cannot be blank")
    private Double amount;


}
