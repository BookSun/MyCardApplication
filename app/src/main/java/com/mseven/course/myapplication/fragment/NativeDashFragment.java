package com.mseven.course.myapplication.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mseven.course.myapplication.R;
import com.mseven.course.myapplication.widget.CollectionView;
import com.mseven.course.myapplication.widget.CollectionViewCallbacks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mseven on 3/7/16.
 */
public class NativeDashFragment extends Fragment {
    private CollectionView mCollectionView;

    public static NativeDashFragment newInstance() {
        return new NativeDashFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nativedash, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mCollectionView = (CollectionView) view.findViewById(R.id.menu_collection_vew);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final NativeDashAdapter adapter = buildAdapter();
        mCollectionView.setCollectionAdapter(adapter);
        mCollectionView.updateInventory(adapter.getInventory());
        mCollectionView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }

    protected NativeDashAdapter buildAdapter() {
        return new NativeDashAdapter(getActivity(), buildMenuList());
    }


    protected static class NativeDashAdapter extends ArrayAdapter<MenuEntry> implements CollectionViewCallbacks {
        protected Context mContext;
        private Toast mCurrentToast;
        int TOKEN = 0x1;
        int TOKEN2 = 0x2;
        int TOKEN3 = 0x3;

        public NativeDashAdapter(Context context, List<MenuEntry> objects) {
            super(context, 0, objects);
            mContext = context;
        }

        public CollectionView.Inventory getInventory() {
            CollectionView.Inventory inventory = new CollectionView.Inventory();

            inventory.addGroup(new CollectionView.InventoryGroup(TOKEN)
                    .setDisplayCols(3)
                    .setItemCount(5)
                    .setHeaderLabel(mContext.getString(R.string.menulist_teacher_header))
                    .setShowHeader(true));

            inventory.addGroup(new CollectionView.InventoryGroup(TOKEN2)
                    .setDisplayCols(3)
                    .setItemCount(5)
                    .setDataIndexStart(5)
                    .setHeaderLabel(mContext.getString(R.string.menulist_course_header))
                    .setShowHeader(true));

            inventory.addGroup(new CollectionView.InventoryGroup(TOKEN3)
                    .setDisplayCols(3)
                    .setItemCount(4)
                    .setDataIndexStart(10)
                    .setHeaderLabel(mContext.getString(R.string.menulist_school_header))
                    .setShowHeader(true));

            return inventory;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }

        @Override
        public View newCollectionHeaderView(Context context, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.menulist_item_header, parent, false);
        }

        @Override
        public void bindCollectionItemView(Context context, View view, int groupId, int indexInGroup, int dataIndex, Object tag) {
            bindView(view,context,dataIndex);
        }

        @Override
        public void bindCollectionHeaderView(Context context, View view, int groupId, String headerLabel) {
            ((TextView) view.findViewById(R.id.name)).setText(headerLabel);
        }

        public View bindView(View view, final Context context, int position) {
            ViewHolder holder = (ViewHolder) view.getTag();
            final MenuEntry menuEntry = getItem(position);

            final String hashtag = mContext.getString(menuEntry.titleId);
            holder.name.setText(hashtag);

            view.setBackgroundColor(mContext.getResources().getColor(menuEntry.colorId));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //MenyEntryUtils.openFragment(mContext, menuEntry);
                    //mCallbacks.onTopicSelected(menuEntry, view);
                }
            });

            final String desc = mContext.getString(menuEntry.descriptionId);

            if (!TextUtils.isEmpty(desc)) {
                holder.description.setVisibility(View.VISIBLE);
                holder.description.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //displayDescription(view, desc);
                    }
                });
            } else {
                holder.description.setVisibility(View.GONE);
            }

            return view;
        }
        public View newView(Context context, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.list_item_dashmenu, parent, false);
            ViewHolder holder = new ViewHolder();
            assert view != null;
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.description = (ImageButton) view.findViewById(R.id.description);
            view.setTag(holder);
            return view;
        }

        @Override
        public View newCollectionItemView(Context context, int groupId, ViewGroup parent) {
            return newView(context,parent);
        }
    }


    private static final class ViewHolder {
        TextView name;
        ImageButton description;
    }
    public static class MenuEntry {
        public int titleId;
        public int colorId;
        public int descriptionId;
        public Class mClass;

        public MenuEntry(int titleId, int colorId, int descriptionId, Class aClass) {
            this.titleId = titleId;
            this.colorId = colorId;
            this.descriptionId = descriptionId;
            mClass = aClass;
        }
    }

    private static List<MenuEntry> buildMenuList() {
        ArrayList<MenuEntry> list = new ArrayList<MenuEntry>();
        list.add(new MenuEntry(R.string.menulist_card_teacher0, R.color.md_indigo_200, R.string.menulist_teacher_header, null));
        list.add(new MenuEntry(R.string.menulist_card_teacher1, R.color.md_green_300, R.string.menulist_teacher_header, null));
        list.add(new MenuEntry(R.string.menulist_card_teacher2, R.color.md_orange_300, R.string.menulist_teacher_header, null));
        list.add(new MenuEntry(R.string.menulist_card_teacher3, R.color.md_yellow_700, R.string.menulist_teacher_header, null));
        list.add(new MenuEntry(R.string.menulist_card_teacher4, R.color.md_cyan_400, R.string.menulist_teacher_header, null));

        list.add(new MenuEntry(R.string.menulist_card_course0, R.color.md_indigo_200, R.string.menulist_course_header, null));
        list.add(new MenuEntry(R.string.menulist_card_course1, R.color.md_green_300, R.string.menulist_course_header, null));
        list.add(new MenuEntry(R.string.menulist_card_course2, R.color.md_orange_300, R.string.menulist_course_header, null));
        list.add(new MenuEntry(R.string.menulist_card_course3, R.color.md_yellow_700, R.string.menulist_course_header, null));
        list.add(new MenuEntry(R.string.menulist_card_course4, R.color.md_cyan_400, R.string.menulist_course_header, null));

        list.add(new MenuEntry(R.string.menulist_card_school0, R.color.md_indigo_200, R.string.menulist_school_header, null));
        list.add(new MenuEntry(R.string.menulist_card_school1, R.color.md_green_300, R.string.menulist_school_header, null));
        list.add(new MenuEntry(R.string.menulist_card_school2, R.color.md_orange_300, R.string.menulist_school_header, null));
        list.add(new MenuEntry(R.string.menulist_card_school3, R.color.md_yellow_700, R.string.menulist_school_header, null));

        return list;
    }
}
