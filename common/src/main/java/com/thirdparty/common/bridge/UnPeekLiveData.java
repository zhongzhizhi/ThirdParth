/*
 * Copyright 2018-2019 KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thirdparty.common.bridge;

import com.dosmono.logger.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class UnPeekLiveData<T> extends MutableLiveData<T> {

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, observer);
        hook(observer);
    }

    private void hook(Observer<? super T> observer) {
        Class<LiveData> liveDataClass = LiveData.class;
        try {
            //获取field private SafeIterableMap<Observer<T>, ObserverWrapper> mObservers
            Field mObservers = liveDataClass.getDeclaredField("mObservers");
            mObservers.setAccessible(true);
            //获取SafeIterableMap集合mObservers
            Object observers = mObservers.get(this);
            Class<?> observersClass = observers.getClass();
            //获取SafeIterableMap的get(Object obj)方法
            Method methodGet = observersClass.getDeclaredMethod("get", Object.class);
            methodGet.setAccessible(true);
            //获取到observer在集合中对应的ObserverWrapper对象
            Object objectWrapperEntry = methodGet.invoke(observers, observer);
            Object objectWrapper = null;
            if (objectWrapperEntry instanceof Map.Entry) {
                objectWrapper = ((Map.Entry) objectWrapperEntry).getValue();
            }
            if (objectWrapper == null) {
                throw new NullPointerException("ObserverWrapper can not be null");
            }
            //获取ObserverWrapper的Class对象  LifecycleBoundObserver extends ObserverWrapper
            Class<?> wrapperClass = objectWrapper.getClass().getSuperclass();
            //获取ObserverWrapper的field mLastVersion
            Field mLastVersion = wrapperClass.getDeclaredField("mLastVersion");
            mLastVersion.setAccessible(true);
            //获取liveData的field mVersion
            Field mVersion = liveDataClass.getDeclaredField("mVersion");
            mVersion.setAccessible(true);
            Object mV = mVersion.get(this);
            //把当前ListData的mVersion赋值给 ObserverWrapper的field mLastVersion
            mLastVersion.set(objectWrapper, mV);

            mObservers.setAccessible(false);
            methodGet.setAccessible(false);
            mLastVersion.setAccessible(false);
            mVersion.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hookObserveForever() {
        Class<LiveData> liveDataClass = LiveData.class;
        try {
            Field mVersionField = liveDataClass.getDeclaredField("mVersion");
            mVersionField.setAccessible(true);
            mVersionField.set(this,-1);

            Field mObservers = liveDataClass.getDeclaredField("mObservers");
            mObservers.setAccessible(true);
            //获取SafeIterableMap集合mObservers
            Object observers = mObservers.get(this);
            Class<?> observersClass = observers.getClass();
            Method methodIterator = observersClass.getMethod("iterator");
            Object iteratorObject = methodIterator.invoke(observers);

            Iterator<Map.Entry> iterator = (Iterator<Map.Entry>) iteratorObject;
            while (iterator.hasNext()){
                Map.Entry next = iterator.next();
                Object objectWrapper = next.getValue();
                if (objectWrapper == null){
                    throw new NullPointerException("ObserverWrapper can not be null");
                }
                Class<?> wrapperClass = objectWrapper.getClass().getSuperclass();
                Field mLastVersion = wrapperClass.getDeclaredField("mLastVersion");
                mLastVersion.setAccessible(true);
                mLastVersion.set(objectWrapper,-1);

                mLastVersion.setAccessible(false);
            }

            mVersionField.setAccessible(false);

        } catch (Exception e) {
            Logger.e("hookObserveForever====================>" + e);
            e.printStackTrace();
        }
    }
}
