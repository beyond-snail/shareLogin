package myyl.com.myyl.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SharedUtil {
	
	private static final int MODE_CODE = Context.MODE_PRIVATE;
	
	public static final String SHARE_OPEN_FIRSTTIME = "SHARE_OPEN_FIRSTTIME";
	
	public static final String SHARED = "eboss";
	
	private Context context;
	private SharedPreferences sharedPreferences;
	private Editor mEditor;
	
	public SharedUtil(Context ctx){
		this.context = ctx;
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		mEditor = sharedPreferences.edit();
	}
	
	public String getShareStringPara(String name, String defaultStringValue){
		return getSharedPreferences(SHARED).getString(name, "");
	}
	
	public String getShareStringPara(String filename, String name, String defaultStringValue){
		return getSharedPreferences(filename).getString(name, "");
	}
	
	public void setShareStringPara(String name, String value){
		Editor tEditor = getEditor(SHARED);
		tEditor.remove(name);
		tEditor.putString(name, value);
		tEditor.commit();
	}
	
	public void setShareStringPara(String filename, String name, String value){
		Editor tEditor = getEditor(filename);
		tEditor.remove(name);
		tEditor.putString(name, value);
		tEditor.commit();
	}
	
	public int getShareIntPara(String name, int defaultIntValue){
		return getSharedPreferences(SHARED).getInt(name, defaultIntValue);
	}
	
	public int getShareIntPara(String filename, String name, int defaultIntValue){
		return getSharedPreferences(filename).getInt(name, defaultIntValue);
	}
	
	public void setShareIntPara(String name, int value){
		Editor tEditor = getEditor(SHARED);
		tEditor.putInt(name, value);
		tEditor.commit();
	}
	
	public void setShareIntPara(String filename, String name, int value){
		Editor tEditor = getEditor(filename);
		tEditor.putInt(name, value);
		tEditor.commit();
	}
	
	public boolean getShareBooleanPara(String name, boolean defaultBooleanValue){
		return getSharedPreferences(SHARED).getBoolean(name, defaultBooleanValue);
	}
	
	public boolean getShareBooleanPara(String filename, String name, boolean defaultBooleanValue){
		return getSharedPreferences(filename).getBoolean(name, defaultBooleanValue);
	}
	
	public void setShareBooleanPara(String name, boolean value){
		Editor tEditor = getEditor(SHARED);
		tEditor.putBoolean(name, value);
		tEditor.commit();
	}
	
	public void setShareBooleanPara(String filename, String name, boolean value){
		Editor tEditor = getEditor(filename);
		tEditor.putBoolean(name, value);
		tEditor.commit();
	}
	
	public void removeSharePara(String name){
		Editor tEditor = getEditor(SHARED);
		tEditor.remove(name);
		tEditor.commit();
	}
	
	public void removeSharePara(String filename, String name){
		Editor tEditor = getEditor(filename);
		tEditor.remove(name);
		tEditor.commit();
	}
	
	
	private SharedPreferences getSharedPreferences(String filename){
		if(StringUtils.isBlank(filename)){return sharedPreferences;}
		SharedPreferences tempSharePreferences = context.getSharedPreferences(filename, MODE_CODE);
		return tempSharePreferences;
	}
	
	@SuppressLint("CommitPrefEdits")
	private Editor getEditor(String filename){
		if(StringUtils.isBlank(filename)){return mEditor;}
		SharedPreferences tempSharePreferences = context.getSharedPreferences(filename, MODE_CODE);
		Editor tEditor = tempSharePreferences.edit();
		return tEditor;
	}
	
}
