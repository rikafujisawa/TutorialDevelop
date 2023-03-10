package com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "user")
public class User {

	/**性別用の列挙型*/
	public static enum Gender {
		男性, 女性
	}

	/**主キー。自動生成*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**名前。２０桁。null不可*/
	@Column(length = 20, nullable = false)
	private String name;

	/**性別。２桁。列挙型（文字列）*/
	@Column(length = 2)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	/**　年齢*/
	private Integer age;

	/**　メールアドレス。５０桁。null許可　*/
	@Column(length = 50)
	private String email;
}