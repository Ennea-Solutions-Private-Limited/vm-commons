package com.ennea.enneaservices.model.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BankDetailsDTO {

    @NotBlank
    private String accountNumber;
    @NotBlank
    private String accountHolderName;
    @NotBlank
    private String bankName;
    @NotBlank
    private String branchName;
    @NotBlank
    private String ifscCode;
    @NotBlank
    private String contactNumber;

    public String getEmailHtml() {
        return "<div>"
               + "Account Holder: "
               + "<strong>" + accountHolderName + "</strong>"
               + "</div>"
               + "<div>"
               + "Account Number: "
               + "<strong>" + accountNumber + "</strong>"
               + "</div>"
               + "<div>"
               + "Bank Name: "
               + "<strong>" + bankName + "</strong>"
               + "</div>"
               + "<div>"
               + "Branch Name: "
               + "<strong>" + branchName + "</strong>"
               + "</div>"
               + "<div>"
               + "IFSC Code: "
               + "<strong>" + ifscCode + "</strong>"
               + "</div>"
               + "<div>"
               + "Contact Number: "
               + "<strong>" + contactNumber + "</strong>"
               + "</div>";
    }
}
