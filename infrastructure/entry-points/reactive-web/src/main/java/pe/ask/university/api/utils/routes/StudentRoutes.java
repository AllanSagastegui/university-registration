package pe.ask.university.api.utils.routes;

public class StudentRoutes {
    public static final String GET_ALL_STUDENTS = "/api/v1/student";
    public static final String GET_STUDENT_BY_DNI = "/api/v1/student/dni/{dni}";
    public static final String GET_STUDENT_BY_EMAIL = "/api/v1/student/email/{email}";
    public static final String GET_STUDENT_BY_ID = "/api/v1/student/{id}";
    public static final String GET_STUDENT_BY_NAME = "/api/v1/student/name/{name}";
    public static final String SAVE_STUDENT = "/api/v1/student";
    public static final String UPDATE_STUDENT = "/api/v1/student/{id}";

}
