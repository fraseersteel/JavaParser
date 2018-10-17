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
        MethodVisitor mv = new MethodVisitor();
        cu.accept(mv, null);
        if (mv.maxParam == true) {
            System.out.println("Error: " + "too many parameters in method " + mv.toString());
        }
    }

    private static class MethodVisitor extends VoidVisitorAdapter {

        int count = 0;
        int maxParameters = 5;
        boolean maxParam = false;

        @Override
        public void visit(MethodDeclaration n, Object arg) {
            System.out.println("Method name: " + n.getName());
            // Call accept on the instance of the method body and pass it the visitor
            // defined in the class below (which doesn't otherwise get called)
            ParameterVisitor pm = new ParameterVisitor();
            if(count>maxParameters){
                n.getBody().get().accept(pm, null);
                count++;
            }else{
                maxParam = true;
            }
        }

        public int getCount() {
            return count;
        }
    }

    private static class ParameterVisitor extends VoidVisitorAdapter<Void> {

        int count = 0;
        int maxParameters = 5;
        boolean maxParam = false;

        @Override
        public void visit(Parameter p, Void arg) {
            System.out.println("parameter name: " + p.getName());
            super.visit(p, arg);

        }
    }
}
