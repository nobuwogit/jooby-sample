package my.sample.data;

import com.google.inject.Binder;
import com.typesafe.config.Config;
import org.jooby.Env;
import org.jooby.Jooby;

/**
 *
 * @author stephen
 *
 */
public class DataSource implements Jooby.Module {

    @Override
    public void configure(Env env, Config conf, Binder binder) throws Throwable {
        DataStore.init(conf);
    }
}
