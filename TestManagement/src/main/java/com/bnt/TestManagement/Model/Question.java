package com.bnt.TestManagement.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "mcq_question")
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long question_id;
    @ManyToOne
	@JoinColumn(name = "subcategory_id")
	SubCategory subCategory;
	@Column(name = "question")
    String question;
	@Column(name = "option_one")
	String optionOne;
	@Column(name = "option_two")
	String optionTwo;
	@Column(name = "option_three")
	String optionThree;
	@Column(name = "option_Four")
	String optionFour;
	@Column(name = "correct_option")
	String correctOption;
	@Column(name = "positive_mark")
	int positiveMark;
	@Column(name = "negative_mark")
	int negativeMark;
}
