package com.thirdparty.common.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Unpar on 17/4/11.
 */

public class GsonFactory {

    private static Gson gson = null;

    public static Gson getGson() {
        if (gson == null) {
            synchronized (GsonFactory.class) {
                if(gson == null) {
                    gson = newGson();
                }
            }
        }
        return gson;
    }

    public static Gson newGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                .registerTypeAdapter(long.class, new LongDefault0Adapter())
                .registerTypeAdapterFactory(new SafeTypeAdapterFactory())
                .create();
    }



//    public static <T> Result<List<T>> fromJsonArray(Reader reader, Class<T> clazz) {
//        Type type = TypeBuilder
//                .newInstance(Result.class)
//                .beginSubType(List.class)
//                .addTypeParam(clazz)
//                .endSubType()
//                .build();
//        return getGson().fromJson(reader, type);
//    }
//
//    public static <T> Result<T> fromJsonObject(Reader reader, Class<T> clazz) {
//        Type type = TypeBuilder
//                .newInstance(Result.class)
//                .addTypeParam(clazz)
//                .build();
//        return getGson().fromJson(reader, type);
//    }
}
