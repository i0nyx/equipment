package by.onyx.common.pojo;

import java.util.HashMap;

public enum TypeWork {
    SUBSTITUTION, FUELING, SUBSTITUTION_FUELING;

    public final static HashMap<TypeWork, String> typeWorkHashMap = new HashMap<TypeWork, String>() {{
        put(SUBSTITUTION, "замена");
        put(FUELING, "заправка");
        put(SUBSTITUTION_FUELING, "замена и заправка");
    }};
}
