package com.pragma.mealssquare.application.dto;

import com.pragma.users.domain.model.Rol;
import com.pragma.users.domain.model.TypeDocumentEnum;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTOResponse {
    private UUID id_user;  // Se devuelve el ID generado por la BD
    private String name_user;
    private String lastName_user;
    private TypeDocumentEnum typeDocument_user;
    private String document_user;
    private String phoneNumber_user;
    private LocalDate dateBirth_user;
    private String email;
    private Rol rol;
}
