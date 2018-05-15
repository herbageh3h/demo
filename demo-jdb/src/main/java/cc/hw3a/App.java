package cc.hw3a;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class App {
	public static void main(String... args) {
        App app = new App();
        app.foo();
		System.out.println("Hello World!");
	}

    public void foo() {
        Date d = new Date();
        foo2(d);
    }

    public void foo2(Date date) {
        List<String> list = Arrays.asList(new String[]{"apple", "orange"});
        System.out.println(list);
    }
}
