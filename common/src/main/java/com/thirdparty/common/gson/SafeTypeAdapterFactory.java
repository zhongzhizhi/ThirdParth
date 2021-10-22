package com.thirdparty.common.gson;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by Unpar on 17/4/12.
 */

class SafeTypeAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {
        final TypeAdapter delegate = gson.getDelegateAdapter(this, type);
        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                try {
                    delegate.write(out, value);
                }catch (Exception e) {
                    delegate.write(out, null);
                }
            }

            @Override
            public T read(JsonReader in) throws IOException {
                try {
                    return (T) delegate.read(in);
                }catch (IOException e) {
                    in.skipValue();
                }catch (IllegalStateException e) {
                    in.skipValue();
                }catch (JsonSyntaxException e) {
                    in.skipValue();
                    if(type.getType() instanceof Class) {
                        try {
                            return (T) ((Class) type.getType()).newInstance();
                        }catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }

                return null;
            }
        };
    }
}

