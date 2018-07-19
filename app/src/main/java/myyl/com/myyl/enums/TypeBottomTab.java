package myyl.com.myyl.enums;

////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//              佛祖保佑       永无BUG     永不修改                  //
//                                                                //
//          佛曰:                                                  //
//                  写字楼里写字间，写字间里程序员；                   //
//                  程序人员写程序，又拿程序换酒钱。                   //
//                  酒醒只在网上坐，酒醉还来网下眠；                   //
//                  酒醉酒醒日复日，网上网下年复年。                   //
//                  但愿老死电脑间，不愿鞠躬老板前；                   //
//                  奔驰宝马贵者趣，公交自行程序员。                   //
//                  别人笑我忒疯癫，我笑自己命太贱；                   //
//                  不见满街漂亮妹，哪个归得程序员？                   //
////////////////////////////////////////////////////////////////////


import myyl.com.myyl.R;
import myyl.com.myyl.activity.DiscoverActivity;
import myyl.com.myyl.activity.MainActivity;
import myyl.com.myyl.activity.MineActivity;
import myyl.com.myyl.activity.WorkActivity;

/**********************************************************
 * *
 * Created by wucongpeng on 2016/12/4.        *
 **********************************************************/


public enum TypeBottomTab {

    Tab1("首页", "Spec_Main", R.drawable.ic_tab_a, MainActivity.class),
    Tab2("工作台", "Spec_Main2", R.drawable.ic_tab_b, WorkActivity.class),
    Tab3("发现", "Spec_Main3", R.drawable.ic_tab_c, DiscoverActivity.class),
    Tab4("我的", "Spec_Main4", R.drawable.ic_tab_d, MineActivity.class),
    ;

    private String tabName;
    private String specInfo;
    private int tabIcon;
    @SuppressWarnings("rawtypes")
    private Class targetClass;

    TypeBottomTab(String tabName, String specInfo, int tabIcon, Class targetClass){
        this.tabName = tabName;
        this.specInfo = specInfo;
        this.tabIcon = tabIcon;
        this.targetClass = targetClass;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getSpecInfo() {
        return specInfo;
    }

    public void setSpecInfo(String specInfo) {
        this.specInfo = specInfo;
    }

    public int getTabIcon() {
        return tabIcon;
    }

    public void setTabIcon(int tabIcon) {
        this.tabIcon = tabIcon;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }
}
