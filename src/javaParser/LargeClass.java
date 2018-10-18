package javaParser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;

public class LargeClass {

    public void run(File file) throws Exception {

        ClassDeclarationVisitor cl = new ClassDeclarationVisitor();
        CompilationUnit cu = JavaParser.parse(file);

        cu.accept(cl,null);
    }

    private static class ClassDeclarationVisitor extends VoidVisitorAdapter{


        public void visit(ClassOrInterfaceDeclaration cl, Object arg ){
            System.out.println(cl.getFields());
            System.out.println(cl.getTypeParameters());
        }

    }

    private static class MethodDeclarationVisitor extends VoidVisitorAdapter{
        public void visit(MethodDeclaration m, Object arg){

        }
    }
}
