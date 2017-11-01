package com.jdkgroup.pocketaccount.adapter;

import android.app.Activity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdkgroup.pocketaccount.R;
import com.jdkgroup.pocketaccount.model.ModelTransaction;
import com.jdkgroup.utils.AppUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DebitTransactionListAdapter extends RecyclerView.Adapter<DebitTransactionListAdapter.ViewHolder> {

    private List<ModelTransaction> alTransaction;
    private Activity activity;

    private ItemListener listener;

    public DebitTransactionListAdapter(Activity activity, List<ModelTransaction> alTransaction) {
        this.activity = activity;
        this.alTransaction = alTransaction;
    }

    public void setOnListener(ItemListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_debit_transaction_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelTransaction modelTransaction = alTransaction.get(position);
        holder.appTvTitle.setText(modelTransaction.getTitle());
        holder.appTvCreditAmount.setText(modelTransaction.getCreditdebitamount() + "");
        holder.appTvDescription.setText(AppUtils.getCapitalizeFirstChar(modelTransaction.getDescripton() + ""));
        holder.appTvDateTime.setText(AppUtils.getDateFormatDDMMYYYY(modelTransaction.getDate() + ""));
    }

    @Override
    public int getItemCount() {
        return alTransaction.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.appTvTitle)
        AppCompatTextView appTvTitle;
        @BindView(R.id.appTvCreditAmount)
        AppCompatTextView appTvCreditAmount;
        @BindView(R.id.appTvDescription)
        AppCompatTextView appTvDescription;
        @BindView(R.id.appTvDateTime)
        AppCompatTextView appTvDateTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(view -> listener.onClickCreditTransaction(alTransaction.get(getAdapterPosition())));
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
            }
        }
    }

    public interface ItemListener {
        void onClickCreditTransaction(final ModelTransaction modelTransaction);
    }
}