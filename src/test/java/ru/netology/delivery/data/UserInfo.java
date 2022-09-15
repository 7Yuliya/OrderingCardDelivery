package ru.netology.delivery.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;
@RequiredArgsConstructor
@Data
public  class UserInfo {

    private final String name;
    private final String phone;
    private final String city;
}
