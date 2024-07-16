package br.com.test.goat.validator;

public class ValidatorConstants {

    public static final String TASK_ID = "id";
    public static final String TASK_NOME = "nome";
    public static final String TASK_DESCRIPTION = "description";


    public static final int TASK_NOME_MAX_LENGTH = 60;
    public static final int TASK_DESCRIPTION_MAX_LENGTH = 150;


    public static final String MISSING = ".missing";
    public static final String EXCEEDS_MAX_LENGTH = ".exceedsMaxLength";

    private ValidatorConstants() {}
}