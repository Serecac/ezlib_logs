package es.ezlib.logs_test;

import java.util.ArrayList;

public class TestExample {

    private String test_string;
    private int test_int;
    private ArrayList<TestExampleItem> test_array;

    public TestExample() {
        test_int = 1;
        test_string = "2";
        test_array = new ArrayList<>();
        test_array.add(new TestExampleItem());
        test_array.add(new TestExampleItem());
        test_array.add(new TestExampleItem());
    }
}
