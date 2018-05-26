package cn.sweetyhut.user.dao.provider;

import cn.sweetyhut.user.domain.User;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * mybatis provider for UserDao
 * 动态条件拼装SQL语句, 方法不会运行，只取返回值
 *
 * @author Macer
 * @version V1.0
 * @date 2018/05/18 15:58
 */
public class UserDynaSqlProvider {
    public String insertUser(final User user) {
        return new SQL() {
            {
                INSERT_INTO("user_inf");
                if (user.getAccount() != null && !user.getAccount().equals("")) {
                    VALUES("account", "#{account}");
                }
                if (user.getPassword() != null && !user.getPassword().equals("")) {
                    VALUES("password", "#{password}");
                }
                if (user.getUsername() != null && !user.getUsername().equals("")) {
                    VALUES("username", "#{username}");
                }
            }
        }.toString();
    }

    public String count(final Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM("user_inf");
                if (params.get("user") != null) {
                    User user = (User) params.get("user");
                    if (user.getU_status() == User.ACTIVE) {
                        WHERE("status = 'a' ");
                    } else {
                        WHERE("status = 'i' ");
                    }
                }
            }
        }.toString();
    }
}
