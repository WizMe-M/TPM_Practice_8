import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class RegistrationSystemTest {
    RegistrationSystem system;

    @BeforeEach
    public void writeBefore() {
        System.out.println("Начинаю процесс авторизации...");
        system = new RegistrationSystem();
    }

    //параметры указываются в статичных методах, возвращающих Stream<Arguments>
    private static Stream<Arguments> users() {
        return Stream.of(
                Arguments.of(new User("test", "test")),
                Arguments.of(new User("sample", "P@ssw0rd")),
                Arguments.of(new User("example", "test_password")),
                Arguments.of(new User("someLogin", "P@ssw0rd")));
    }

    private static Stream<Arguments> logins() {
        return Stream.of(
                Arguments.of("test"),
                Arguments.of("unique"));
    }

    private static Stream<Arguments> passwords() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("pass"),
                Arguments.of("password"),
                Arguments.of("Password"),
                Arguments.of("P@ssword"),
                Arguments.of("P@ssw0rd"));
    }

    //источник, откуда тесты будут брать параметры указывается с помощью аннотации @MethodSource("источник")
    @ParameterizedTest
    @MethodSource("users")
    void tryAuthorize(User user) {
        boolean actual;

        actual = system.tryRegister(user);

        Assertions.assertTrue(actual);
        System.out.println("Регистрация успешна. Пользователь создан");
    }

    @ParameterizedTest
    @MethodSource("logins")
    void checkLogin(String login) {
        boolean actual;

        actual = system.checkLogin(login);

        Assertions.assertTrue(actual, "Логин и пароль совпали!");
        System.out.println("Логин прошел тестирование");
    }

    @ParameterizedTest
    @MethodSource("passwords")
    void checkPassword(String password) {
        int expected, actual;

        expected = 5;
        actual = system.checkPassword(password);

        Assertions.assertEquals(expected, actual, "Оценка пароля не соответствует ожидаемой");
        System.out.println("Пароль прошел тестирование");
    }
}