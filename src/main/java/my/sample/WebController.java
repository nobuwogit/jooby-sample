package my.sample;

import com.sun.xml.internal.bind.v2.model.core.ID;
import my.sample.data.DataStore;
import my.sample.view.Contact;
import org.jooby.Jooby;
import org.jooby.Result;
import org.jooby.Results;
import org.jooby.mvc.GET;
import org.jooby.mvc.POST;
import org.jooby.mvc.Path;
import org.jooby.thymeleaf.Thl;
import org.jooq.DSLContext;
import org.jooq.example.gradle.db.public_.tables.Book;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.SQLException;

import static org.jooq.example.gradle.db.public_.Tables.BOOK;

/**
 * @author m-washio
 */
@Path("/thl_index")
public class WebController extends Jooby {
    @GET
    public Result index() {
        return Results.html("index")
                .put("model", "model_str");
    }

    @GET
    @Path("/load_contact")
    public Result loadContact() {
        org.jooq.example.gradle.db.public_.tables.pojos.Book oneBook = null;
        try (Connection c = DataStore.getConnection()) {
            oneBook = DSL.using(c)
                    .selectFrom(BOOK)
                    .where(BOOK.ID.eq(1))
                    .fetchOne()
                    .into(org.jooq.example.gradle.db.public_.tables.pojos.Book.class);
   
        } catch (SQLException e) {
            e.printStackTrace();
        }
                 return Results.html("book")
                    .put("book", oneBook);
    }

    @POST
    @Path("/save")
    public Result submit(Contact formContact) {
        return Results.html("save_contact")
                .put("contact", formContact);
    }
}
