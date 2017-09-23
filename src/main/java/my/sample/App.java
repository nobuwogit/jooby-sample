package my.sample;

import org.jooby.Jooby;

/**
 * @author m-washio
 */
public class App extends Jooby {

  {
    get("/", () -> "Hello World!");
  }

  public static void main(final String[] args) throws Throwable {
    run(App::new, args);
  }
}
