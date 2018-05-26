package cn.sweetyhut.user.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Entity
 *
 * @author Macer
 * @version V1.0
 * @date 2018/05/18 15:13
 */
public class User implements Serializable {

    public static final char ACTIVE = 'a';
    public static final char INACTIVE = 'i';
    public static final char FROZEN = 'f';

    private static final long serialVersionUID = -5072508649749016600L;
    private Integer uid;
    @NotNull
    @Size(min = 6, max = 16)
    private String account;
    @NotNull
    @Size(min = 6, max = 16)
    private String password;
    private String username;
    private char u_status;
    private Timestamp create_date;

    public Integer getUid() {
        return uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char getU_status() {
        return u_status;
    }

    public void setU_status(char u_status) {
        this.u_status = u_status;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }
}
