package org.hgq;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-06-23 21:18
 **/
public class TestInner {
    private int n = 100;
    private static String s = "out";
    public static void main(String[] args) {
        TestInner testInner = new TestInner();
        Inner inner = testInner.new Inner();
        if (inner != null) {
            inner.innerMethod();
        }

        SInner sInner = new SInner();
        sInner.innerMethod();
    }
    private static class SInner {

        private int n = 200;
        private static String s = "inner";
        public static void sinnerMethod() {
            //System.out.println(TestInner.this.n);
        }
        public void innerMethod() {
            int n = 300;
            System.out.println(n);
            System.out.println(this.n);
            // System.out.println(TestInner.this.n);
            System.out.println(TestInner.s);
            System.out.println(s);
        }
    }

    private class Inner {

        private int n = 200;
       // private static String s = "inner";
        public void innerMethod() {
            int n = 300;
            System.out.println(n);
            System.out.println(this.n);
            System.out.println(TestInner.this.n);
            System.out.println(TestInner.s);
        }
    }


}
