package org.example.rbc.util;

import java.io.InputStream;

public class ResourceManager {
    public static InputStream readInputStream(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }
}
