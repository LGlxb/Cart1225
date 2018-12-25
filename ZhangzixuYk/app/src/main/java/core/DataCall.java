package core;

import java.util.List;

import bean.GoodsBean;
import bean.Shop;

public interface DataCall extends IView {
    //商品详情接口
    void ShowSuccess(GoodsBean goodsBean);

    //购物车
    void ShowGoodsSuccess(List<Shop.DataBean> dataBean);

    //未加载接口
    void ShowError(String error);
}
