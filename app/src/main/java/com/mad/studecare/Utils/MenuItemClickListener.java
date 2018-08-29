package com.mad.studecare.Utils;

import android.view.MenuItem;
import android.widget.PopupMenu;

import com.mad.studecare.R;

public class MenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

    public MenuItemClickListener() {
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_add_favourite:
                return true;
            case R.id.action_play_next:
                return true;
            default:
        }
        return false;
    }
}
