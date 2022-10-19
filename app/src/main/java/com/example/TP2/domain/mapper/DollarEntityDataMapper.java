package com.example.TP2.domain.mapper;

import com.example.TP2.data.entity.DollarEntity;
import com.example.TP2.domain.Dollar;

import javax.inject.Inject;

public class DollarEntityDataMapper {

    @Inject
    DollarEntityDataMapper() {}

    public Dollar transform(DollarEntity dollarEntity) {
        Dollar dollar = null;

        if (dollarEntity != null) {
            dollar = new Dollar();
            dollar.setDateTime(dollarEntity.getDateTime());
            dollar.setBuyValue(dollarEntity.getBuyValue());
            dollar.setSellValue(dollarEntity.getSellValue());
            dollar.setType(dollarEntity.getType());
        }

        return dollar;
    }
}
