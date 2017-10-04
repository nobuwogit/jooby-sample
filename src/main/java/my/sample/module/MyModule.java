package my.sample.module;

import com.typesafe.config.Config;
import my.sample.App;
import org.jooby.Env;
import org.jooby.Jooby;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.Binder;

/**
 * @author m-washio
 */
public class MyModule implements Jooby.Module {


    Logger log = LoggerFactory.getLogger(App.class);
    @Override
    public void configure(Env env, Config conf, com.google.inject.Binder binder) throws Throwable {
        env.onStart(() -> {
            log.info("starting module");
        });

        env.onStop(() -> {
            log.info("stopping module");
        });

        env.onStarted(() -> {
            log.info("app started");
        });
    }
}
