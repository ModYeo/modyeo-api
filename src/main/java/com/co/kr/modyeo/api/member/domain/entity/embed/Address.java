package com.co.kr.modyeo.api.member.domain.entity.embed;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @ApiModelProperty(value = "도시(임시)", dataType = "string", required = false)
    private String city;

    @ApiModelProperty(value = "도로명(임시)", dataType = "string", required = false)
    private String street;

    @ApiModelProperty(value = "우편번호(임시)", dataType = "string", required = true)
    private String zipcode;

    @Builder
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
