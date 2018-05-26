package cn.sweetyhut.user.service.impl;

import cn.sweetyhut.user.dao.UserDao;
import cn.sweetyhut.user.domain.User;
import cn.sweetyhut.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 业务逻辑组件
 *
 * @author Macer
 * @version V1.0
 * @date 2018/05/18 16:31
 */
@Service("registerService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Boolean addUser(User user) {
        if (userDao.selectByAcc(user.getAccount()) != null) {
            return false;
        }
        LOGGER.info("一位用户注册了");
        userDao.save(user);
        return true;
    }

    @Override
    public Boolean verifyUser(User user) {
        if (userDao.selectByAcc(user.getAccount()) != null) {
            if (userDao.selectByAccNPw(user.getPassword(), user.getAccount()) != null) {
                LOGGER.info("一位用户登录了");
                return true;
            }
            LOGGER.info("账号[" + user.getAccount() + "]被尝试登录！");
        }
        return false;
    }
}
