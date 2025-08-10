package com.pragma.mealssquare.application.dto;

import com.pragma.mealssquare.domain.model.Rol;
import com.pragma.mealssquare.domain.model.TypeDocumentEnum;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTOResponse {
    private Long idUser;
    private String nameUser;
    private String lastNameUser;
    private TypeDocumentEnum typeDocumentUser;
    private String documentUser;
    private String phoneNumberUser;
    private LocalDate dateBirthUser;
    private String email;
    private RolDTOResponse rolDTOResponse;
}
