package ru.netology.delivery.data;


import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public  class UserInfo {

     String name;
     String phone;
     String city;
}

