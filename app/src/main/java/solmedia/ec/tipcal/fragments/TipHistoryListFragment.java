package solmedia.ec.tipcal.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import solmedia.ec.tipcal.DetailActivity;
import solmedia.ec.tipcal.R;
import solmedia.ec.tipcal.adapter.OnClickItemTipListener;
import solmedia.ec.tipcal.adapter.TipAdapter;
import solmedia.ec.tipcal.models.TipModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment
        implements TipHistoriListFragmentListener, OnClickItemTipListener {

    RecyclerView recyclerView;
    private TipAdapter adapter;
    private OnClickItemTipListener listener;


    public TipHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tip_history_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rvTipHistory);

        setupAdapter();
        setupRecyclerView();

        return view;
    }

    private void setupAdapter() {
        if (adapter == null) {
            adapter = new TipAdapter(getContext(), this);
        }
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void addTip(TipModel tipModel) {
        adapter.add(tipModel);
    }

    @Override
    public void clearList() {
        adapter.clear();
    }

    @Override
    public void onClick(TipModel tipModel) {
        Intent intent = new Intent(
                getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.KEY_BILL, tipModel.getBill());
        intent.putExtra(DetailActivity.KEY_TIP, tipModel.getTip());
        startActivity(intent);
    }
}
