package net.osmand.plus.quickaction;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import net.osmand.plus.activities.MapActivity;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class QuickAction {

    public interface QuickActionSelectionListener {

        void onActionSelected(QuickAction action);
    }

    protected int type;
    protected long id;
    protected @StringRes int nameRes;
    protected @DrawableRes int iconRes;

    protected boolean toggle;
    protected boolean single;

    private String name;
    private HashMap<String, String> params;

    protected QuickAction() {
        this.id = System.currentTimeMillis();
    }

    protected QuickAction(int type, int nameRes) {
        this.id = System.currentTimeMillis();
        this.nameRes = nameRes;
    }

    protected QuickAction(int type) {
        this.id = System.currentTimeMillis();
        this.type = type;
    }

    public QuickAction(QuickAction quickAction) {
        this.type = quickAction.type;
        this.id = quickAction.id;
        this.nameRes = quickAction.nameRes;
        this.iconRes = quickAction.iconRes;
        this.name = quickAction.name;
        this.params = quickAction.params;
    }

    public int getNameRes() {
        return nameRes;
    }

    public int getIconRes() {
        return iconRes;
    }

    public long getId() {
        return id;
    }

    public String getName(Context context) {
        return name == null || name.isEmpty() ? nameRes > 0 ? context.getString(nameRes) : "" : name;
    }

    public HashMap<String, String> getParams() {

        if (params == null) params = new HashMap<>();

        return params;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public void execute(MapActivity activity){};
    public void drawUI(ViewGroup parent, MapActivity activity){};
    public boolean fillParams(View root, MapActivity activity){ return true; };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuickAction action = (QuickAction) o;

        if (type != action.type) return false;
        if (id != action.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type;
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + nameRes;
        result = 31 * result + iconRes;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

