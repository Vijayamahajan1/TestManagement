package com.bnt.TestManagement.Model;

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
    String question;
	String optionOne;
	String optionTwo;
	String optionThree;
	String optionFour;
	String correctOption;
	int positiveMark;
	int negativeMark;
}
