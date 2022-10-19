package com.example.TP2.data.net;

import com.example.TP2.data.entity.DollarEntity;

import io.reactivex.Observable;

public interface RestApiInterface {
    String API_BASE_URL =
            "https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture/";

    /** Api url for getting a user profile: Remember to concatenate id + 'json' */
    String API_URL_GET_USER_DETAILS = API_BASE_URL + "user_";


    /**
     * Retrieves an {@link Observable} which will emit a {@link DollarEntity}.
     *
     * @param userId The user id used to get user data.
     */
    Observable<DollarEntity> userEntityById(final int userId);
}
