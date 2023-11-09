package com.homeboss.model.authentication;

import static java.util.Objects.requireNonNull;

/**
 * Represents the authentication data stored in the authentication.json file.
 */
public class AuthenticationData {
    private String username;
    private String password;
    private String secretQuestion;
    private String answer;

    /**
     * Creates an AuthenticationData object with the given username and password.
     *
     * @param username       Username in String format
     * @param password       Password in String format
     * @param secretQuestion Secret question in String format
     * @param answer         Answer in String format
     */
    public AuthenticationData(String username, String password, String secretQuestion, String answer) {
        requireNonNull(username);
        requireNonNull(password);
        requireNonNull(secretQuestion);
        requireNonNull(answer);

        this.username = username;
        this.password = password;
        this.secretQuestion = secretQuestion;
        this.answer = answer;
    }

    AuthenticationData() {
    }

    /**
     * Returns the username of the AuthenticationData object.
     *
     * @return Username in String format
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the AuthenticationData object.
     *
     * @return Password in String format
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the secret question of the AuthenticationData object.
     *
     * @return Secret question in String format
     */
    public String getSecretQuestion() {
        return secretQuestion;
    }

    /**
     * Returns the answer to the secret question of the AuthenticationData object.
     *
     * @return Answer in String format
     */
    public String getAnswer() {
        return answer;
    }
}
