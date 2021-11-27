import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class AuthorizationSystemTest {

    private static Stream<Arguments> loginsAndPasswords() {
        return Stream.of(
                Arguments.of("test", "test"),
                Arguments.of("test", "password"));
    }

    private static Stream<Arguments> users() {
        return Stream.of(
                Arguments.of(new User("test", "test")),
                Arguments.of(new User("someLogin", "P@ssw0rd")));
    }

    private static Stream<Arguments> passwords() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("password"),
                Arguments.of("Password"),
                Arguments.of("P@ssword"),
                Arguments.of("P@ssw0rd"));
    }

    @ParameterizedTest
    @MethodSource("users")
    void tryAuthorize(User user){
        boolean actual;
        AuthorizationSystem system;

        system = new AuthorizationSystem();
        actual = system.tryAuthorize(user);

        Assertions.assertTrue(actual);
    }

    @ParameterizedTest
    @MethodSource("loginsAndPasswords")
    void checkLogin(String login, String password) {
        boolean actual;
        AuthorizationSystem system;

        system = new AuthorizationSystem();
        actual =  system.checkLogin(login, password);

        Assertions.assertTrue(actual);
    }

    @ParameterizedTest
    @MethodSource("passwords")
    void checkPassword(String password) {
        int expected, actual;
        AuthorizationSystem system;

        expected = 5;
        system = new AuthorizationSystem();
        actual = system.checkPassword(password);

        Assertions.assertEquals(expected, actual);
    }
}