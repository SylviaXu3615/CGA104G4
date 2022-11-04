package com.member.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="MEMBER")
public class MemberVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MEM_ID")
	private Integer memId;
	@Column(name="MEM_EMAIL")
	private String memEmail;
	@Column(name = "MEM_PWD")
	private String memPwd;
	@Column(name = "ACC_STAT")
	private Integer accStat;
	@Column(name = "MEM_NAME")
	private String memName;
	@Column(name="MEM_MOBILE")
	private String memMobile;
	@Column(name="MEM_CITY")
	private String memCity;
	@Column(name="MEM_DIST")
	private String memDist;
	@Column(name="MEM_ADR")
	private String memAdr;
	@Column(name="MEM_REG_TIME",insertable = false)
	private Date memRegTime;
	@Column(name="MEM_PIC")
	private byte[] memPic;
	@Column(name="MEM_TOKEN",insertable = false)
	private Integer memToken;


}
