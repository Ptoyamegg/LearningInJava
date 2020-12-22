package xyz.dyk.auth;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.PrivilegedAction;

/**
 * This program authenticates a user via a custom login
 * and then executes the SysPropAction with the user's privileges.
 */
public class AuthTest {
    public static void main(String[] args) {
        System.setProperty("java.security.policy", "CoreJavaVolume/AdvancedFeatures/src/main/java/xyz/dyk/auth/AuthTest.policy");
        System.setProperty("java.security.auth.login.config", "CoreJavaVolume/AdvancedFeatures/src/main/java/xyz/dyk/auth/jaas.config");
        System.setSecurityManager(new SecurityManager());
        try {
            LoginContext context = new LoginContext("Login1");
            context.login();
            System.out.println("Authentication successful.");
            Subject subject = context.getSubject();
            PrivilegedAction<String> action = new SysPropAction("user.home");
            String result = Subject.doAsPrivileged(subject, action, null);
            System.out.println(result);
            context.logout();
        } catch (LoginException e) {

        }
    }
}
