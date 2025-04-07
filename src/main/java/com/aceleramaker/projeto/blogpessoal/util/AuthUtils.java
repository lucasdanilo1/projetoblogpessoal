package com.aceleramaker.projeto.blogpessoal.util;

import jakarta.servlet.http.Cookie;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthUtils {
    public static Cookie generateTokenCookie(String nome, String conteudo, long tempo, String caminho,
                                             boolean httponly) {
        Cookie cookie = new Cookie(nome, conteudo);
        cookie.setHttpOnly(httponly);
        cookie.setSecure(false);
        cookie.setPath(caminho);
        cookie.setMaxAge(Math.toIntExact(tempo));
        return cookie;
    }
}
