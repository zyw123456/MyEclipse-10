package com.sinoway.common.pool.task;

import java.util.concurrent.Callable;

public interface IPoolCallableTask<E,T> extends Callable<E>,IPoolTask<T> {

}
