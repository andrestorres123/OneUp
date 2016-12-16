package com.andrestorres.oneup.ui.base;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by andrestorres on 3/14/16.
 */
public class BasePresenter<V extends BaseViewContract> {

    public V view;

    protected final String TAG = BasePresenter.class.getSimpleName();

    protected BasePresenter(V view) {
        this.view = view;
    }

    protected <T> Observable.Transformer<T, T> safely() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    protected <T> Observable.Transformer<T, T> applyLoading() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        view.showLoader();
                    }
                }).doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.hideLoader();
                    }
                }).doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        view.hideLoader();
                    }
                });
            }
        };
    }

    protected <T> Observable.Transformer<T, T> safelyLoading() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return (Observable<T>) tObservable.compose(safely()).compose(applyLoading());
            }
        };
    }

    protected <T> Observable.Transformer<T, T> safelyReport() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return (Observable<T>) tObservable.compose(safely())
                        .doOnError(new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                view.showMessage(throwable.getMessage());
                            }
                        }).onErrorResumeNext(new Func1<Throwable, Observable<?>>() {
                            @Override
                            public Observable<?> call(Throwable throwable) {
                                return Observable.empty();
                            }
                        });
            }
        };
    }

    protected <T> Observable.Transformer<T, T> safelyReportLoading() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return (Observable<T>) tObservable.compose(safelyReport()).compose(applyLoading());
            }
        };
    }
}
