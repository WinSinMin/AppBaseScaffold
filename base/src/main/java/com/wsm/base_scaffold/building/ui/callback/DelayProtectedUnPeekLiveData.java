package com.wsm.base_scaffold.building.ui.callback;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: WinSinMin
 * @Email: winsinmin@foxmail.com
 * @CreateDate: 2020/12/21 16:40
 * @Description:
 */
public class DelayProtectedUnPeekLiveData<T> extends LiveData<T> {

    private boolean isCleaning;
    private boolean hasHandled = true;
    private boolean isDelaying;
    protected int DELAY_TO_CLEAR_EVENT = 1000;
    private Timer mTimer = new Timer();
    private TimerTask mTask;
    protected boolean isAllowNullValue;
    protected boolean isAllowToClear = true;

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {

        super.observe(owner, t -> {

            if (isCleaning) {
                hasHandled = true;
                isDelaying = false;
                isCleaning = false;
                return;
            }

            if (!hasHandled) {
                hasHandled = true;
                isDelaying = true;
                observer.onChanged(t);
            } else if (isDelaying) {
                observer.onChanged(t);
            }
        });
    }

    /**
     * UnPeekLiveData 主要用于表现层的 页面转场 和 页面间通信 场景下的非粘性消息分发，
     * 出于生命周期安全等因素的考虑，不建议使用 observeForever 方法，
     * <p>
     * 对于数据层的工作，如有需要，可结合实际场景使用 MutableLiveData 或 kotlin flow。
     *
     * @param observer
     */
    @Override
    public void observeForever(@NonNull Observer<? super T> observer) {
        throw new IllegalArgumentException("Do not use observeForever for communication between pages to avoid lifecycle security issues");
    }

    /**
     * 重写的 setValue 方法，默认不接收 null
     * 可通过 Builder 配置允许接收
     * 可通过 Builder 配置消息延时清理的时间
     * <p>
     * override setValue, do not receive null by default
     * You can configure to allow receiving through Builder
     * And also, You can configure the delay time of message clearing through Builder
     *
     * @param value
     */
    @Override
    protected void setValue(T value) {

        if (!isCleaning && (!isAllowNullValue && value == null)) {
            return;
        }

        hasHandled = false;
        isDelaying = false;
        super.setValue(value);

        if (mTask != null) {
            mTask.cancel();
            mTimer.purge();
        }

        if (value != null) {
            mTask = new TimerTask() {
                @Override
                public void run() {
                    clear();
                }
            };
            mTimer.schedule(mTask, DELAY_TO_CLEAR_EVENT);
        }
    }

    private void clear() {
        if (isAllowToClear) {
            isCleaning = true;
            super.postValue(null);
        } else {
            hasHandled = true;
            isDelaying = false;
        }
    }
}
