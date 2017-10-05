package my.sample;

import org.jooby.Jooby;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;

/**
 * @author m-washio
 */
public class Controller extends Jooby {

  {
    get("/", req -> {
  int iparam = req.param("intparam").intValue();

  String str = req.param("str").value();
  String defstr = req.param("str").value("def");

  List<String> strList = req.param("strList").toList(String.class);



  // optional parameter
  Optional<String> optStr = req.param("optional").toOptional();
  return "test";
});
  }
  @GET
  @Inject
  public String salute(@Named("myprop") String myprop) {
    return myprop;
  }


}
