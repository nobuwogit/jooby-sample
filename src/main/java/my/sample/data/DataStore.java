package my.sample.data;

import com.typesafe.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author stephen
 *
 */
public class DataStore {
    private static DataStoreProperties prop;
    public static void init(Config conf) {
        System.out.println("ああああああああああああああああああああああ");
        prop = new DataStoreProperties(conf.getString("db.url"), conf.getString("db.user"), conf.getString("db.password"));
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(prop.getUrl(), prop.getUserName(), prop.getPassword());
    }

    static class DataStoreProperties {
        private final String url;
        private final String userName;
        private final String password;
        public DataStoreProperties(String url, String userName, String password) {
            super();
            this.url = url;
            this.userName = userName;
            this.password = password;
        }
        public String getUrl() {
            return url;
        }
        public String getUserName() {
            return userName;
        }
        public String getPassword() {
            return password;
        }
    }
}
