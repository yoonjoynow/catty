package org.catty.domains.account.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhoneNumber {

    @Column(name = "code", nullable = false, length = 3)
    private CountryCode code;

    @Column(name = "first_digit", nullable = false, length = 3)
    private String firstDigit;

    @Column(name = "middle_digit", nullable = false, length = 3)
    private String middleDigit;

    @Column(name = "last_digit", nullable = false, length = 3)
    private String lastDigit;

    public PhoneNumber(CountryCode code, String firstDigit, String middleDigit, String lastDigit) {
        this.code = code;
        this.firstDigit = firstDigit;
        this.middleDigit = middleDigit;
        this.lastDigit = lastDigit;
    }

    public String getPhoneNumber() {
        return String.format("%s-%s-%s", this.firstDigit, this.middleDigit, this.lastDigit);
    }
}
