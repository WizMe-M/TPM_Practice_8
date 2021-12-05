import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class RegistrationSystem {

    private final ArrayList<User> registeredUsers;

    public RegistrationSystem() {
        registeredUsers = new ArrayList<>();
        registeredUsers.add(new User("test", "P@ssw0rd"));
        registeredUsers.add(new User("example", "P@ssw0rd"));
        registeredUsers.add(new User("something", "P@ssw0rd"));
        registeredUsers.add(new User("sample", "P@ssw0rd"));
    }

    public boolean tryRegister(User user) {
        boolean canRegister = checkLogin(user.Login) && checkPassword(user.Password) == 5;
        if (canRegister) registeredUsers.add(user);
        return canRegister;
    }

    public boolean checkLogin(String login) {
        User[] userArray = registeredUsers.toArray(new User[0]);
        return Arrays.stream(userArray).noneMatch(user -> Objects.equals(user.Login, login));
    }

    public int checkPassword(String password) {
        int score = 0;
        if (password.length() > 7) score++;
        if (containsUpperCaseLetter(password)) score++;
        if (containsLowerCaseLetter(password)) score++;
        if (containsDigit(password)) score++;
        if (containsSpecialSymbol(password)) score++;
        return score;
    }

    private boolean containsSpecialSymbol(String toTest) {
        for (char c : toTest.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c) && !Character.isSpaceChar(c))
                return true;
        }
        return false;
    }

    private boolean containsDigit(String toTest) {
        for (char c : toTest.toCharArray()) {
            if (Character.isDigit(c)) return true;
        }

        return false;
    }

    private boolean containsLowerCaseLetter(String toTest) {
        for (char c : toTest.toCharArray()) {
            if (Character.isLowerCase(c) && Character.isLetter(c)) return true;
        }

        return false;
    }

    private boolean containsUpperCaseLetter(String toTest) {
        for (char c : toTest.toCharArray()) {
            if (Character.isUpperCase(c) && Character.isLetter(c)) return true;
        }

        return false;
    }
}
