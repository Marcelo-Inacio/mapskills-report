package br.gov.sp.fatec.mapskills.report.studentresult;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudentResultIndicator is a Querydsl query type for StudentResultIndicator
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QStudentResultIndicator extends BeanPath<StudentResultIndicator> {

    private static final long serialVersionUID = -593505011L;

    public static final QStudentResultIndicator studentResultIndicator = new QStudentResultIndicator("studentResultIndicator");

    public final StringPath skillDescription = createString("skillDescription");

    public final StringPath skillName = createString("skillName");

    public final NumberPath<Integer> total = createNumber("total", Integer.class);

    public QStudentResultIndicator(String variable) {
        super(StudentResultIndicator.class, forVariable(variable));
    }

    public QStudentResultIndicator(Path<? extends StudentResultIndicator> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudentResultIndicator(PathMetadata metadata) {
        super(StudentResultIndicator.class, metadata);
    }

}

