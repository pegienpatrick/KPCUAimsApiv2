package org.aims.kpcu.models.manager;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerLoginResponse {

    private String authorization;

    private String message;

}
