package kr.ac.jejunu.userdao;

public class DaoFactory {
    public UserDao getUserDao() {
        return new UserDao(getJejuUserDao());
    }

    public ConnectionMaker getJejuUserDao() {
        return new JejuUserDao();
    }
}
