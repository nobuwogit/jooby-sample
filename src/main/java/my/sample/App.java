package my.sample;

import com.typesafe.config.Config;
import my.sample.module.MyModule;
import org.jooby.Jooby;
import org.jooby.Result;
import org.jooby.Results;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;
import org.jooby.thymeleaf.Thl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

/**
 * @author m-washio
 */
public class App extends Jooby {

    Logger log = LoggerFactory.getLogger(App.class);

//    {
//        get("/", req -> {
//            Config conf = require(Config.class);
//            String myprop = conf.getString("myprop");
//            Controller controller = new Controller();
//            return myprop;
//        });
//    }

    {
        get("/test", () ->
                "test"
        );
    }

    {
        post("/test_post", () ->
                "test"
        );
    }

    //    {
//        use("*","/test_all", () -> "test_all");
//    }
    {
        get("/test_getParam", req -> "hey " + req.param("name").value());
    }

    {
        get("/test_getParam2", (req, rsp) -> rsp.send("hey " + req.param("name").value())).name("mike_test2");
    }

    {
        //静的ファイルの扱い　別ルートのやり方はよくわからない
        assets("/test_html", "hello_world.html");
        assets("/assets/**");
        assets("/assets/**", "/META-INF/resources/webjars/{0}");
        assets("/static/**", Paths.get("/www"));
    }

    {
        //ルーティング
        get("/abc", (req, rsp, chain) -> {
            System.out.println("first");
            chain.next(req, rsp);
        });

        get("/abc", (req, rsp) -> {
            rsp.send("second");
        });
        get("/abcd", (req, rsp, chain) -> rsp.send("first"));

        get("/abcd", (req, rsp, chain) -> rsp.send("second"));

        get("/abcd", (req, rsp) -> rsp.send("third"));


        //chainを使ったrouting
        get("/bcd", (req, rsp, chain) -> {
            System.out.println("first");
            chain.next(req, rsp);
        });

        get("/bcd", (req, rsp, chain) -> {
            System.out.println("second");
            chain.next(req, rsp);
        });


        get("/bcd", (req, rsp) -> {
            rsp.send("third");
        });


        //chainを使わずrouting
        get("/cde", (req, rsp) -> {
            System.out.println("first");
        });

        get("/cde", (req, rsp) -> {
            System.out.println("second");
        });


        get("/cde", (req, rsp) -> {
            rsp.send("third");
        });
    }

    {
        //before after complete http filter
        before("/test_filter", (req, rsp) -> {
            System.out.println("before filter run");
        });
        after((req, rsp, result) -> {
            // your code goes here
            System.out.println("after filter run");
            System.out.println(result);
            return result;
        });
        complete("*", (req, rsp, cause) -> {
            // your code goes here
            System.out.println("complete filter run");
        });
        get("/test_filter", (req, rsp) -> {

            rsp.send("test_filter");
        });


    }

    {
        use(MyRoutes.class);
        use(MyRoutesWithHtml.class);

        //thymeleaf_controller
        use(new Thl());
        use(WebController.class);
        assets("/home", "home.html");
        get("/home_page", (req, rsp) ->
        {
            rsp.send(Results.html("home").put("model", "model_str"));
        });
    }

    {
        {

//            assets("/foo", "foo.html");
//            assets("/bar", "bar.html");
//            use("*", (req, rsp, chain) -> {
//                if (req.hostname().equals("foo.com")) {
//                    chain.next("/foo-branding", req, rsp);
//                } else {
//                    chain.next("/bar-branding", req, rsp);
//                }
//            });
//
//            get("/foobar", () -> Results.html("foo")).name("foo-branding");
//
//            get("/foobar", () -> Results.html("bar")).name("bar-branding");
//                    get("/search", req -> {
//                        return "aaal";
//        });

        }
    }


    {
        onStart(() -> {
            log.info("starting app ddddddddddddddd");
        });

        onStop(() -> {
            log.info("stopping app dddddddddddddd");
        });

        onStarted(() -> {
            log.info("app started ddddddddddddddd");
        });
    }


    public static void main(final String[] args) throws Throwable {
        run(App::new, args);
    }

}
