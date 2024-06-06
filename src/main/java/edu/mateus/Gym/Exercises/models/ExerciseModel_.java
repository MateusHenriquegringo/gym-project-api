package edu.mateus.Gym.Exercises.models;

import edu.mateus.Gym.Exercises.enums.ExerciseDifficulty;
import edu.mateus.Gym.Exercises.enums.ExerciseType;
import edu.mateus.Gym.Exercises.enums.ExerciseIntensity;
import edu.mateus.Gym.Exercises.enums.MuscleGroupsEnum;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;

@StaticMetamodel(ExerciseModel.class)
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public class ExerciseModel_ {
    public static volatile SingularAttribute<ExerciseModel, Long> id;
    public static volatile SingularAttribute<ExerciseModel, String> name;
    public static volatile SingularAttribute<ExerciseModel, ExerciseIntensity> intensity;
    public static volatile SingularAttribute<ExerciseModel, ExerciseType> type;
    public static volatile ListAttribute<ExerciseModel, MuscleGroupsEnum> muscles;
    public static volatile SingularAttribute<ExerciseModel, ExerciseDifficulty> difficulty;
}