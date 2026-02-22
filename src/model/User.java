package model;

public class User {

    private String login;

    
    private String password;            
    private String securityAnswer;      

    
    private String passwordHash;
    private String security_Answer_Hash;
    private String salt;

    private String securityQuestion;
    private String email;

    public User() {}

    public User(String login, String password ,String securityQuestion, String securityAnswer, String email) {
        this.login = login;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.email = email;
    }

    public User(String login, String passwordHash, String salt, String securityQuestion, String security_Answer_Hash, String email) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.securityQuestion = securityQuestion;
        this.security_Answer_Hash = security_Answer_Hash;
        this.email = email;
    }


    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getSecurityAnswer() { return securityAnswer; }
    public void setSecurityAnswer(String securityAnswer) { this.securityAnswer = securityAnswer; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getSecurityAnswerHash() { return security_Answer_Hash; }
    public void setSecurityAnswerHash(String security_Answer_Hash) { this.security_Answer_Hash = security_Answer_Hash; }

    public String getSalt() { return salt; }
    public void setSalt(String salt) { this.salt = salt; }

    public String getSecurityQuestion() { return securityQuestion; }
    public void setSecurityQuestion(String securityQuestion) { this.securityQuestion = securityQuestion; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}