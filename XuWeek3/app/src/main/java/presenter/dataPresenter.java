package presenter;

import java.util.List;

import beans.Goods;

public interface dataPresenter {
    void success(List<Goods.DataBean> list);
    void error();
}
