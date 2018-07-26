package myyl.com.myyl.utils.views.pickTime.widget.listener;


import myyl.com.myyl.utils.views.pickTime.widget.bean.DateBean;
import myyl.com.myyl.utils.views.pickTime.widget.bean.DateType;

/**
 * Created by codbking on 2016/9/22.
 */

public interface WheelPickerListener {
     void onSelect(DateType type, DateBean bean);
}
