package com.demo.config;

public class JavafinalizeExample1 {
    public static void main(String[] args)
    {
        JavafinalizeExample1 obj = new JavafinalizeExample1();
        System.out.println(obj.hashCode());
        obj = null;
        // calling garbage collector
        System.out.println("end of garbage collection");
        System.gc();

    }
    @Override
    protected void finalize()
    {
        System.out.println("finalize method called");
    }
}