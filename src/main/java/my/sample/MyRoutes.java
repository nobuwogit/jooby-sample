package my.sample;

import org.jooby.Result;
import org.jooby.Results;
import org.jooby.mvc.GET;
import org.jooby.mvc.Header;
import org.jooby.mvc.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;

/**
 * @author m-washio
 */
@Path("/routes")
public class MyRoutes {
    Logger log = LoggerFactory.getLogger(MyRoutes.class);

    @GET
    public void first() {
        log.info("first");
    }

    @GET
    public void second() {
        log.info("second");
    }
    @GET
    public String reqParam(@Named("req-param") String reqParam) {
        return "Named "+reqParam;
    }
//    @GET
//    public String reqParam(Optional<String> q) {
//        return q.orElse("none param");
//    }
    @GET
    /*
    http://localhost:8080/routes?q=abc&q=edf
     */
    public String reqParam(List<String> q) {
        return q.stream().reduce((str1,str2) -> str1 + str2).get();
    }
    @GET
    public String third() {
        return "third";
    }

}
