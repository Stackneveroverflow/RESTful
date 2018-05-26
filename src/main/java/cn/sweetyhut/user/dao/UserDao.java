package cn.sweetyhut.user.dao;
/*
 *　　　　　　　 ┏┓      ┏┓+ +
 *           ┏━┛┻━━━━━━┛┻━┓ + +
 *           ┃　　　　　　　┃ + + +
 *           ┃     ━      ┃ + + + +
 *           ┃ ████━████  ┃
 *           ┃            ┃ + +
 *           ┃     ┻      ┃
 *           ┃            ┃ +
 *           ┗━━┓      ┏━━┛
 *              ┃      ┃
 *              ┃      ┃ + + + +
 *              ┃      ┃　　　　 Code is far away from bug with the animal protecting
 *              ┃      ┃ + 　　　　 神兽保佑,代码无bug
 *              ┃      ┃
 *              ┃      ┃　　+
 *              ┃      ┗━━━━━━━┓ + +
 *              ┃              ┣━┓
 *              ┃              ┏━┛
 *              ┗━┓ ┓ ┏━━┳━┓ ┏━┛ + + + +
 *                ┃ ┫ ┫  ┃ ┫ ┫
 *                ┗━┻━┛  ┗━┻━┛+ + + +
 */

import cn.sweetyhut.user.dao.provider.UserDynaSqlProvider;
import cn.sweetyhut.user.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Map;

/**
 * DAO接口
 *
 * @author Macer
 */
public interface UserDao {
    /**
     * 存储新注册用户
     *
     * @param user
     */
    @SelectProvider(type = UserDynaSqlProvider.class, method = "insertUser")
    void save(User user);

    @Select("select * from user_inf where account = #{account} and password = #{password}")
    User selectByAccNPw(@Param("password") String password, @Param("account") String account);

    @SelectProvider(type = UserDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @Select("select * from user_inf where account = #{account}")
    User selectByAcc(@Param("account") String account);
}
