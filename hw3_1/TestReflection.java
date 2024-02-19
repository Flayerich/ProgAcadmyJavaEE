package hw3_1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Test {
    int a();
    int b();
}

class MyClass {
    @Test(a = 4, b = 5120)
    public void test(int a, int b) {
        System.out.println("a: " + a + ", b: " + b);
    }
}

class Main {
    public static void main(String[] args) throws Exception {
        MyClass obj = new MyClass();
        Class<?> cls = obj.getClass();

        for (var method : cls.getMethods()) {
            if (method.isAnnotationPresent(Test.class)) {

                Test testAnnotation = method.getAnnotation(Test.class);

                int a = testAnnotation.a();
                int b = testAnnotation.b();

                method.invoke(obj, a, b);
            }
        }
    }
}

