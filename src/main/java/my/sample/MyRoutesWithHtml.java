package my.sample;

import org.jooby.Result;
import org.jooby.Results;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;


/**
 * @author m-washio
 */
@Path("/routes2")
public class MyRoutesWithHtml {
    @GET
    public Result home() {
        return Results.html("home");
    }

}
