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
        ParameterVisitor pm = new ParameterVisitor();
        cu.accept(pm,null);
        if(pm.maxParam==true){
            System.out.println("Error: "  + "too many parameters");
        }
    }


    private static class MethodVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(MethodDeclaration n, Object arg) {
            System.out.println("Method name: " + n.getName());
            // Call accept on the instance of the method body and pass it the visitor
            // defined in the class below (which doesn't otherwise get called)
           n.getBody().get().accept(new ParameterVisitor(),null);
        }
    }

    private static class ParameterVisitor extends VoidVisitorAdapter<Void> {

        int count = 0;
        int maxParameters = 5;
        boolean maxParam = false;

        @Override
        public void visit(Parameter p, Void arg) {
            if(count<maxParameters) {
                System.out.println("parameter name: " + p.getName());
                super.visit(p, arg);
                count++;
            }else{
                maxParam = true;
            }
        }

        public int getCount() {
            return count;
        }
    }
}
