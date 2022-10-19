package com.example.TP2.presentation.view;

import com.example.TP2.presentation.model.DollarModel;

import java.util.Collection;

public interface DollarListView extends LoadDataView {
    void renderDollarList(Collection<DollarModel> dollarModelCollection);
}
