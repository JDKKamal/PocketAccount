package com.jdkgroup.pocketaccount.adapter;

import android.app.Activity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdkgroup.customviews.characterview.CharacterView;
import com.jdkgroup.pocketaccount.R;
import com.jdkgroup.pocketaccount.model.ModelUser;
import com.jdkgroup.utils.AppUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<ModelUser> alHome;
    private Activity activity;

    private ItemListener listener;

    public UserAdapter(Activity activity, List<ModelUser> alHome) {
        this.activity = activity;
        this.alHome = alHome;
    }

    public void setOnListener(ItemListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_user_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelUser modelUser = alHome.get(position);
        holder.appTvName.setText(AppUtils.getCapitalizeFirstChar(modelUser.getName() + ""));
        holder.characterView.setText(AppUtils.getCapitalizeFirstChar(modelUser.getName().substring(0, 1)));
        holder.appTvCreditAmount.setText(modelUser.getCreditamount() + "");
        holder.appTvDateTime.setText(AppUtils.getDateFormatDDMMYYYY(modelUser.getDate() + ""));
    }

    @Override
    public int getItemCount() {
        return alHome.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.characterView)
        CharacterView characterView;
        @BindView(R.id.appTvName)
        AppCompatTextView appTvName;
        @BindView(R.id.appTvCreditAmount)
        AppCompatTextView appTvCreditAmount;
        @BindView(R.id.appTvDateTime)
        AppCompatTextView appTvDateTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(view -> listener.onClickHome(alHome.get(getAdapterPosition())));
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
            }
        }
    }

    public interface ItemListener {
        void onClickHome(final ModelUser modelUser);
    }
}