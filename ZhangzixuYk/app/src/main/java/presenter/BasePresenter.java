package presenter;

import core.IView;

public abstract class BasePresenter<V extends IView> {
    protected V view;

    public BasePresenter(V view) {
        this.view = view;
    }

    protected abstract void initModel();

    public void onDestroy() {
        view = null;
    }
}
