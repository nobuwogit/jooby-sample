package my.sample;

import my.sample.view.Contact;
import org.jooby.Jooby;
import org.jooby.Result;
import org.jooby.Results;
import org.jooby.mvc.GET;
import org.jooby.mvc.POST;
import org.jooby.mvc.Path;
import org.jooby.thymeleaf.Thl;

/**
 * @author m-washio
 */
@Path("/thl_index")
public class WebController {
    @GET
    public Result  index() {
        return Results.html("index")
                .put("model", "model_str");
    }

    @POST
    @Path("/save")
    public Result submit(Contact formContact){
        return Results.html("save_contact")
                .put("contact", formContact);
    }
}
