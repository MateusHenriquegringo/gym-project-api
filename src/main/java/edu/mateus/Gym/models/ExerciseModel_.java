package edu.mateus.Gym.models;

import edu.mateus.Gym.enums.ExerciseDifficulty;
import edu.mateus.Gym.enums.ExerciseIntensity;
import edu.mateus.Gym.enums.MuscleGroupsEnum;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;


@StaticMetamodel(ExerciseModel.class)
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public class ExerciseModel_ {
	public static volatile SingularAttribute<ExerciseModel, Long> id;
	public static volatile SingularAttribute<ExerciseModel, String> name;
	public static volatile SingularAttribute<ExerciseModel, ExerciseIntensity> intensity;
	public static volatile SetAttribute<ExerciseModel, MuscleGroupsEnum> muscles;
	public static volatile SingularAttribute<ExerciseModel, ExerciseDifficulty> difficulty;
}