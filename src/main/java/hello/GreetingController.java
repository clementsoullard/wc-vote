package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.FileOutputStream;
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="value", defaultValue="90") 	Integer value) {
		File file=new File("/tmp/scheduler/CD");
        FileOutputStream fos=new FileOutputStream(file);
		fos.print(value);
		return new Greeting(counter.incrementAndGet(),
                           "Hehe");
    }
}