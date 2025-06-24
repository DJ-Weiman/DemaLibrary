package com.djw.DemaLibrary.domain.auth;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {
    private String username;
    private String email;
}
