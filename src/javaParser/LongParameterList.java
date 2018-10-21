package javaParser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;

public class LongParameterList {

    public void run(File file) throws Exception {

        CompilationUnit cu = JavaParser.parse(file);
        MethodDeclarationVisitor md = new MethodDeclarationVisitor();
        cu.accept(md, null);
    }


    private static class MethodDeclarationVisitor extends VoidVisitorAdapter {
        int maxParameterSize = 5;

        public void visit(MethodDeclaration m, Object arg) {

            int i = m.getParameters().size();
            if (i < maxParameterSize) {
                System.out.println("Fail: too many parameters in " + m.getName());
            }
            else{
                System.out.println("Pass for parameter list: " + m.getName());
            }
        }
    }
}
