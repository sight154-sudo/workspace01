package com.jike;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Base64;

/**
 * @author king
 * @date 2023/4/25-22:36
 * @Desc
 */
public class MyClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            new MyClassLoader().findClass("com.jike.Hello").newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String base64 = "yv66vgAAADQAHwoABgARCQASABMIABQKABUAFgcAFwcAGAEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBABJMb2NhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQAQTGNvbS9qaWtlL0hlbGxvOwEACDxjbGluaXQ+AQAKU291cmNlRmlsZQEACkhlbGxvLmphdmEMAAcACAcAGQwAGgAbAQALaGVsbG8gd29ybGQHABwMAB0AHgEADmNvbS9qaWtlL0hlbGxvAQAQamF2YS9sYW5nL09iamVjdAEAEGphdmEvbGFuZy9TeXN0ZW0BAANvdXQBABVMamF2YS9pby9QcmludFN0cmVhbTsBABNqYXZhL2lvL1ByaW50U3RyZWFtAQAHcHJpbnRsbgEAFShMamF2YS9sYW5nL1N0cmluZzspVgAhAAUABgAAAAAAAgABAAcACAABAAkAAAAvAAEAAQAAAAUqtwABsQAAAAIACgAAAAYAAQAAAAgACwAAAAwAAQAAAAUADAANAAAACAAOAAgAAQAJAAAAJQACAAAAAAAJsgACEgO2AASxAAAAAQAKAAAACgACAAAACgAIAAsAAQAPAAAAAgAQ";
        byte[] decode = Base64.getDecoder().decode(base64);
        return defineClass(name, decode, 0 , decode.length);
    }
}
