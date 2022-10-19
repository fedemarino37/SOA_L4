package com.example.TP2.presentation.mapper;

import com.example.TP2.domain.Dollar;
import com.example.TP2.presentation.internal.di.PerActivity;
import com.example.TP2.presentation.model.DollarModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

@PerActivity
public class DollarModelDataMapper {

    @Inject
    public DollarModelDataMapper() {}

    public DollarModel transform(Dollar dollar) {
        if (dollar == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        final DollarModel dollarModel = new DollarModel();
        dollarModel.setDateTime(dollar.getDateTime());
        dollarModel.setBuyValue(dollar.getBuyValue());
        dollarModel.setSellValue(dollar.getSellValue());
        dollarModel.setType(dollar.getType());

        return dollarModel;
    }


    public Collection<DollarModel> transform(Collection<Dollar> dollarCollection) {
        Collection<DollarModel> userModelsCollection;

        if (dollarCollection != null && !dollarCollection.isEmpty()) {
            userModelsCollection = new ArrayList<>();
            for (Dollar dollar : dollarCollection) {
                userModelsCollection.add(transform(dollar));
            }
        } else {
            userModelsCollection = Collections.emptyList();
        }

        return userModelsCollection;
    }
}
