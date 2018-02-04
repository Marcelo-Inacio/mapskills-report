package br.gov.sp.fatec.mapskills.report.studentresult;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudentResult is a Querydsl query type for StudentResult
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudentResult extends EntityPathBase<StudentResult> {

    private static final long serialVersionUID = 1436544802L;

    public static final QStudentResult studentResult = new QStudentResult("studentResult");

    public final StringPath courseCode = createString("courseCode");

    public final StringPath courseName = createString("courseName");

    public final StringPath documentId = createString("documentId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath institutionCode = createString("institutionCode");

    public final StringPath institutionCompany = createString("institutionCompany");

    public final StringPath institutionLevel = createString("institutionLevel");

    public final StringPath name = createString("name");

    public final StringPath ra = createString("ra");

    public final NumberPath<Integer> startSemester = createNumber("startSemester", Integer.class);

    public final NumberPath<Integer> startYear = createNumber("startYear", Integer.class);

    public final ListPath<StudentResultIndicator, QStudentResultIndicator> studentIndicators = this.<StudentResultIndicator, QStudentResultIndicator>createList("studentIndicators", StudentResultIndicator.class, QStudentResultIndicator.class, PathInits.DIRECT2);

    public QStudentResult(String variable) {
        super(StudentResult.class, forVariable(variable));
    }

    public QStudentResult(Path<? extends StudentResult> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudentResult(PathMetadata metadata) {
        super(StudentResult.class, metadata);
    }

}

