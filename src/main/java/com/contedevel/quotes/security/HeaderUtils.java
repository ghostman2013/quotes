package com.contedevel.quotes.security;

import com.contedevel.quotes.exceptions.InvalidAuthorizationHeaderException;
import com.contedevel.quotes.exceptions.MissingAuthorizationHeaderException;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.util.Base64;

public final class HeaderUtils {

    private HeaderUtils() {

    }

    public static UserCredentials parseAuthorizationHeader(String header) {

        if (StringUtils.isEmpty(header)) {
            throw new MissingAuthorizationHeaderException();
        }

        String encoded = header.replace("Basic ", "");

        try {
            String auth = new String(Base64.getDecoder().decode(encoded), Charset.forName("UTF-8"));
            String[] emailAndPassword = auth.split(":");

            return new UserCredentials(emailAndPassword[0], emailAndPassword[1]);
        } catch (IllegalArgumentException e) {
            throw new InvalidAuthorizationHeaderException();
        }
    }
}
