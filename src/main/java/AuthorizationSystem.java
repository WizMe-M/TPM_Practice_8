public class AuthorizationSystem {
    public boolean tryAuthorize(User user) {
        return checkLogin(user.Login, user.Password)
                && checkPassword(user.Password) == 5;
    }

    public boolean checkLogin(String login, String password) {
        return !login.equals(password);
    }

    public int checkPassword(String password) {
        int score = 0;

        if (password.length() > 7) score++;

        if (ContainsUpperCaseLetter(password)) score++;

        if (ContainsLowerCaseLetter(password)) score++;

        if (ContainsDigit(password)) score++;

        if (ContainsSpecialSymbol(password)) score++;

        return score;
    }

    private boolean ContainsSpecialSymbol(String toTest) {
        for (char c : toTest.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c) && !Character.isSpaceChar(c))
                return true;
        }
        return false;
    }

    private boolean ContainsDigit(String toTest) {
        for (char c : toTest.toCharArray()) {
            if (Character.isDigit(c)) return true;
        }

        return false;
    }

    private boolean ContainsLowerCaseLetter(String toTest) {
        for (char c : toTest.toCharArray()) {
            if (Character.isLowerCase(c) && Character.isLetter(c)) return true;
        }

        return false;
    }

    private boolean ContainsUpperCaseLetter(String toTest) {
        for (char c : toTest.toCharArray()) {
            if (Character.isUpperCase(c) && Character.isLetter(c)) return true;
        }

        return false;
    }
}
