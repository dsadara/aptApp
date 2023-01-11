package com.dsadara.aptApp.apartment.entity;

import com.dsadara.aptApp.common.entity.BaseEntity;
import com.dsadara.aptApp.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FavoriteApt extends BaseEntity {
    @ManyToOne
    @JoinColumn(name="ID_APT")
    private Apartment apartment;    // 아파트 ID
    @ManyToOne
    @JoinColumn(name="ID_MEMBER")
    private Member member;          // 회원번호
}
